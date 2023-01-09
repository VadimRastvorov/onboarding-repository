package ru.onbording.employeeservice.controller.dto; //todo enum держи в отдельно папке, например type

public enum Gender {
    M,
    F;

    public static boolean checkValue(String gender) { //todo check лучше делать в сервисе валидации, либо все check-еры держать в отдельном классе
        for (Gender env : values()) {
            if (env.name().equals(gender)) {
                return true;
            }
        }
        return false;
    }
}
