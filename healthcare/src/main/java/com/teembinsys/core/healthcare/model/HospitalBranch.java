package com.teembinsys.core.healthcare.model;


import com.teembinsys.core.healthcare.constant.BranchType;
import com.teembinsys.core.healthcare.util.CommonUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hospital_branch")
@Getter
@Setter
public class HospitalBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;

    @Column(unique = true, nullable = false, length = 50)
    private String branchCode;

    @Column(nullable = false, length = 100)
    private String branchName;

    private String address;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private BranchType branchType = BranchType.MAIN;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_branch_id")
    private HospitalBranch parentBranch;

    @OneToMany(mappedBy = "parentBranch", cascade = CascadeType.ALL)
    private Set<HospitalBranch> subBranches = new HashSet<>();

    private LocalDateTime createdAt = CommonUtils.truncateToSeconds(LocalDateTime.now());
    private LocalDateTime updatedAt = CommonUtils.truncateToSeconds(LocalDateTime.now());
}
