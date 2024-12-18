package com.reser.tennissec.Services;

import com.reser.tennissec.Repositories.BookingRepository;
import com.reser.tennissec.Repositories.CourtRepository;
import com.reser.tennissec.Repositories.UserRepository;
import com.reser.tennissec.entites.Booking;
import com.reser.tennissec.entites.User;
import com.reser.tennissec.entites.court;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourtRepository courtRepository;

    public boolean isCourtAvailable(Long courtId, LocalDateTime startTime, int duration) {
        LocalDateTime endTime = startTime.plusHours(duration);

        List<Booking> bookings = bookingRepository.findByCourtId(courtId);
        for (Booking booking : bookings) {
            LocalDateTime bookingEndTime = booking.getStartTime().plusHours(booking.getDuration());
            if ((startTime.isBefore(bookingEndTime) && endTime.isAfter(booking.getStartTime())) ||
                    (booking.getStartTime().isBefore(endTime) && bookingEndTime.isAfter(startTime))) {
                return false;
            }
        }

        return true;
    }


    public Booking reserveCourt(Booking booking, UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        court court = courtRepository.findById(booking.getCourt().getId())
                .orElseThrow(() -> new RuntimeException("Court not found"));

        if (!isCourtAvailable(booking.getCourt().getId(), booking.getStartTime(), booking.getDuration())) {
            throw new RuntimeException("Court is not available at the requested time.");
        }
        if (!court.isAvailable()) {
            throw new RuntimeException("Court is not available in the database.");
        }
        if (booking.getDuration() < 1 || booking.getDuration() > 4) {
            throw new RuntimeException("The reservation duration must be between 1 and 4 hours.");
        }

        booking.setUser(user);
        return bookingRepository.save(booking);
    }

    public List<Booking> getReservationsByCourtId(Long courtId) {
        return bookingRepository.findByCourtId(courtId);
    }
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
