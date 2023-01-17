package ru.onbording.employeeservice.type;

import lombok.Getter;

@Getter
public enum Position {
    ASSISTANT(100000.00, 200000.00, 2),
    MANAGER(200000.00, 300000.00, 4),
    ANALYST(300000.00, 400000.00, 5),
    ACCOUNTANT(400000.00, 500000.00, 7),
    DIRECTOR(500000.00, 1000000.00, 9);
    private final double salaryMin;
    private final double salaryMax;
    private final int tasksMax;

    Position(double salaryMin, double salaryMax, int tasksMax) {
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.tasksMax = tasksMax;
    }
}