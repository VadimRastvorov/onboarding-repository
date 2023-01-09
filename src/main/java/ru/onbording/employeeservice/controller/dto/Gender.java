package ru.onbording.employeeservice.controller.dto;

public enum Gender {
    M,
    F;

    public static boolean checkValue(String gender) {
        for (Gender env : values()) {
            if (env.name().equals(gender)) {
                return true;
            }
        }
        return false;
    }
}
