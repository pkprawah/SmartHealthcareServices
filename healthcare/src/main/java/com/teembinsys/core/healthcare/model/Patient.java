package com.teembinsys.core.healthcare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Entity
@Table(name = "patient")
@Getter
@Setter
public class Patient extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id", length = 20, unique = true, nullable = false)
    private Long  patientId;

    @Column(name = "custom_patient_id", unique = true)
    private String customPatientId;
    @Column(name = "patient_name", unique = true)
    private String patientName;
    @Column(name = "patient_mobile", unique = true)
    private String patientMobileNum;
    @Column(name = "patient_alternate_mobile", unique = false)
    private String alternateMobileNum;
    @Column(name = "patient_relation", unique = false)
    private String patientRelation;

    @OneToMany(mappedBy = "patient")
    private Set<Appointment> appointments;

    public Patient() {}
    public Patient(String patientName, String patientMobileNum) {
        this.patientName=patientName;
        this.patientMobileNum=patientMobileNum;
    }

    @PostPersist
    public void generateCustomPatientId() {
        if (this.patientName != null && this.patientId != null) {
            String prefix = patientName.length() >= 3
                    ? patientName.substring(0, 3).toUpperCase()
                    : patientName.toUpperCase();

            // Always append patientId
            this.customPatientId = "PAT-" + prefix + this.patientId;
        } else {
            this.customPatientId = "PAT-UNKNOWN-" + (this.patientId != null ? this.patientId : "000");
        }
    }
}
