package com.reser.tennissec.Services;
import com.reser.tennissec.Repositories.CourtRepository;
import com.reser.tennissec.entites.court;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourtService {
    @Autowired
    private CourtRepository courtRepository;

    public court addCourt(court newCourt) {
        return courtRepository.save(newCourt);
    }
    public void deleteCourt(Long id) {
        courtRepository.deleteById(id);
    }
    public List<court> getAllCourts() {
        return courtRepository.findAll();
    }
}

