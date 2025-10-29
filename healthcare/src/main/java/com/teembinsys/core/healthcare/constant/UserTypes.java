package com.teembinsys.core.healthcare.constant;

public enum UserTypes {
        ADMIN("Admin"),
        NURSE("Nurse"),
        PATIENT("Patient"),
        DOCTOR("Doctor"),
        RECEPTIONIST("Receptionist");

        private final String userType;
        UserTypes(String userType) {
            this.userType = userType;
        }

        // Getter for the user type
        public String getUserType() {
            return userType;
        }

        @Override
        public String toString() {
            return userType;
        }
    }

