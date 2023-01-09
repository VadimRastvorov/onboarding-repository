package ru.onbording.employeeservice.config;

import java.util.ResourceBundle;

public class MessageBundleConfig {
    public static ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle");

    public static String getMessageBundleValue(String key) {
        return bundle.getString(key);
    }
}
