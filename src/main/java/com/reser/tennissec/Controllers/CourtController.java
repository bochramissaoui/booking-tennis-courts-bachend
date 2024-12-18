package com.reser.tennissec.Controllers;
import com.reser.tennissec.Services.BookingService;
import com.reser.tennissec.Services.CourtService;
import com.reser.tennissec.entites.Booking;
import com.reser.tennissec.entites.court;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courts")
public class CourtController {
    @Autowired
    private CourtService courtService;
    @Autowired
    private BookingService reservationService;

    @PostMapping("/add")
    public ResponseEntity<?> addCourt(@RequestBody court newCourt) {
        court addedCourt = courtService.addCourt(newCourt);
        return ResponseEntity.ok(addedCourt);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCourt(@PathVariable Long id) {
        courtService.deleteCourt(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{courtId}/reservations")
    public List<Booking> getReservationsByCourtId(@PathVariable Long courtId) {
        return reservationService.getReservationsByCourtId(courtId);
    }
    @GetMapping("/all")
    public ResponseEntity<List<court>> getAllCourts() {
        return ResponseEntity.ok(courtService.getAllCourts());
    }
}

