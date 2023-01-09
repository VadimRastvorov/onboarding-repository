package ru.onbording.employeeservice.controller.dto; //todo enum держи в отдельно папке, например type

public enum Position {
    assistant(100000.00, 200000.00, 2),
    manager(200000.00, 300000.00, 4),
    analyst(300000.00, 400000.00, 5),
    accountant(400000.00, 500000.00, 7),
    director(500000.00, 1000000.00, 9);
    private final double salaryMin; //todo понадобятся геттеры, кода будешь выносить check
    private final double salaryMax;
    private final int tasksMax;

    Position(double salaryMin, double salaryMax, int tasksMax) {
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.tasksMax = tasksMax;
    }

    public boolean salaryByPosition(double salary) { //todo не используется?
        return salaryMin <= salary && salaryMax >= salary;
    }

    public static boolean checkTasks(int tasks, String position) {//todo check лучше делать в сервисе валидации, либо все check-еры держать в отдельном классе
        for (Position env : values()) {
            if (env.name().equals(position)) {
                return tasks < env.tasksMax; //todo меньше равно?
            }
        }
        return false;
    }

    public static boolean checkValue(String position, double salary) {//todo check лучше делать в сервисе валидации, либо все check-еры держать в отдельном классе
        for (Position env : values()) {
            if (env.name().equals(position)) {
                return env.salaryMax >= salary && env.salaryMin <= salary;
            }
        }
        return false;
    }
}