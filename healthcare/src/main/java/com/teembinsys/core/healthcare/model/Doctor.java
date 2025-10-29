package com.teembinsys.core.healthcare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "doctor")
@Getter
@Setter
public class Doctor extends Base {

    public Doctor() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id", length = 20, unique = true, nullable = false)
    private Long  doctorId;

    @Column(name = "custom_doctor_id")
    private String customDoctorId;
    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "doctor_mobile", unique = true)
    private String doctorMobileNum;
    @Column(name = "doctor_alternate_mobile", unique = true)
    private String doctorAlternateMobileNum;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "Doctor_Specializations",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "specialization_id")
    )
    private Set<Specialization> specializations;


    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private Set<Appointment> appointments;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<DoctorSlotAvailability> doctorSlotAvailability;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DoctorDepartmentMapping> doctorDepartments = new HashSet<>();



    @PostPersist
    public void generateCustomDoctorId() {
        if (this.doctorName != null && this.doctorId != null) {
            String prefix = doctorName.length() >= 3 ? doctorName.substring(0, 3).toUpperCase() : doctorName.toUpperCase()+ this.doctorId;
            this.customDoctorId = "DOC-" + prefix;
        } else {
            this.customDoctorId = "DOC-UNKNOWN-" + (this.customDoctorId != null ? this.customDoctorId : "000");
        }
    }


}