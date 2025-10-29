package com.teembinsys.core.healthcare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "specialization")
@Getter
@Setter
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specialization_id")
    private Long id;

    @Column(name = "specialization_name", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "specializations")
    private Set<Doctor> doctors;

    public Specialization() {}

    public Specialization(String name) {
        this.name = name;
    }
}

