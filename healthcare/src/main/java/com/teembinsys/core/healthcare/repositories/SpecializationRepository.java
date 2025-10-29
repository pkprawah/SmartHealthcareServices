package com.teembinsys.core.healthcare.repositories;

import com.teembinsys.core.healthcare.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {


    Optional<Specialization> findByName(String name);

}

