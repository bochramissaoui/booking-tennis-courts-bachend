package com.reser.tennissec.entites;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class court {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String img;
    private String description;
    private String adresse;
    private String prixParHeure;
    private boolean available;

    @OneToMany(mappedBy = "court", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Booking> reservations;
}
