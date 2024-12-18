package com.reser.tennissec.Repositories;

import com.reser.tennissec.entites.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCourtIdAndStartTimeLessThanAndStartTimeGreaterThan(Long courtId, LocalDateTime endTime, LocalDateTime startTime);
    List<Booking> findByCourtId(Long courtId);


}
