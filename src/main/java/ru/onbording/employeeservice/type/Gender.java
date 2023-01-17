package ru.onbording.employeeservice.type;

public enum Gender {
    M("М"),
    F("Ж");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }
}
