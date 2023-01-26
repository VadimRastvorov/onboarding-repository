package ru.onbording.employeeservice.config;

import org.springframework.context.annotation.Configuration;

import java.util.ResourceBundle;

@Configuration
public class MessageBundleConfig {
    public static ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle");

    public static String getMessage(String key, Object... arg) {
        return String.format(bundle.getString(key), arg);
    }
}
