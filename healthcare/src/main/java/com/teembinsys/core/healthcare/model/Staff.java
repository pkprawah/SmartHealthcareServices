package com.teembinsys.core.healthcare.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

public class Staff extends Base{

    @Id
    @Column(name = "staff_id", length = 20, unique = true, nullable = false)
    private String staffId;

    private String name;

    @PrePersist
    private void generateStaffId() {
        this.staffId = "staff-" + this.getUserName().substring(0, 3).toUpperCase() + System.currentTimeMillis(); // or use UUID
    }

}
