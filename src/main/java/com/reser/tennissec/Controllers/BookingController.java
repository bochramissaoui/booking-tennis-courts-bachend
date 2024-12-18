package com.reser.tennissec.Controllers;

import com.reser.tennissec.Services.BookingService;
import com.reser.tennissec.entites.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/reserve")
    public ResponseEntity<?> reserveCourt(@RequestBody Booking booking,
                                          @AuthenticationPrincipal UserDetails userDetails) {
        try {
            Booking reservedBooking = bookingService.reserveCourt(booking, userDetails);
            return ResponseEntity.ok(reservedBooking);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
}
