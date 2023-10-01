package dev.mohibullah.pizzaappbackendjava.exceptions;

public class EmptyItemsListException extends RuntimeException {
    public EmptyItemsListException(String message) {
        super(message);
    }
}