package dev.mohibullah.pizzaappbackendjava.exceptions;

public class InvalidUserRoleParamException extends RuntimeException {
    public InvalidUserRoleParamException() {
        super("Invalid 'role' parameter value. It should be either 'cutomer' or 'admin'.");
    }
}
