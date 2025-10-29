package com.teembinsys.core.healthcare.model;


import com.teembinsys.core.healthcare.util.CommonUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "doctor_department_mapping",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"doctor_id", "branch_id", "department_id", "specialization_id"}))
@Getter
@Setter
public class DoctorDepartmentMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mappingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private HospitalBranch hospitalBranch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialization_id", nullable = false)
    private Specialization specialization;

    @Column(nullable = false)
    private BigDecimal consultationFee;

    private String availabilityStatus = "AVAILABLE";

    private LocalDateTime createdAt = CommonUtils.truncateToSeconds(LocalDateTime.now());
    private LocalDateTime updatedAt = CommonUtils.truncateToSeconds(LocalDateTime.now());
}
