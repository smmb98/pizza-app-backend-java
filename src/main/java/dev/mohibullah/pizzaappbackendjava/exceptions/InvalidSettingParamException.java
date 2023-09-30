package dev.mohibullah.pizzaappbackendjava.exceptions;

public class InvalidSettingParamException extends RuntimeException {
    public InvalidSettingParamException() {
        super("Invalid 'setting' parameter value. It should be either 'restaurant_info' or 'app_setting'.");
    }
}