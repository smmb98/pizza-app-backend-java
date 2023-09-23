package dev.mohibullah.pizzaappbackendjava.enums;

public enum Stage {
    ORDER_PLACED("Order Placed"),
    IN_PROGRESS("In Progress"),
    PREPARED("Prepared"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled");

    private final String stage;

    private Stage(String label) {
        this.stage = label;
    }

    public String getLabel() {
        return stage;
    }
}