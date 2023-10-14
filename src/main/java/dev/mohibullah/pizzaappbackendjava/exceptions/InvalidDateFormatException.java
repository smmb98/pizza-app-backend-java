package dev.mohibullah.pizzaappbackendjava.exceptions;

public class InvalidDateFormatException extends RuntimeException {
    public InvalidDateFormatException() {
        super("Invalid date format. Please use the format 'yyyy-MM-ddTHH:mm:ss.SSSZ'.");
    }
}