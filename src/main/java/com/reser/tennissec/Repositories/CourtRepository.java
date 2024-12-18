package com.reser.tennissec.Repositories;

import com.reser.tennissec.entites.court;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourtRepository extends JpaRepository<court, Long> {
    List<court> findByAvailableTrue();
}
