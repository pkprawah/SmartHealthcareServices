package com.teembinsys.core.healthcare.model;


import com.teembinsys.core.healthcare.util.CommonUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "department")
@Getter
@Setter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    private String departmentName;
    private String description;
    private LocalDateTime createdAt = CommonUtils.truncateToSeconds(LocalDateTime.now());
    private LocalDateTime updatedAt =  CommonUtils.truncateToSeconds(LocalDateTime.now());
}
