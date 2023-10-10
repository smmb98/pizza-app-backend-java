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

    public Stage next() {
        int nextOrdinal = this.ordinal() + 1;
        if (nextOrdinal < Stage.values().length) {
            return Stage.values()[nextOrdinal];
        }
        return null; // Return null if there is no next stage
    }
//    Enumerations in Java are assigned an ordinal value,
//    which represents their position in the order they are defined.
//    this.ordinal() returns the ordinal value of the current enum instance
//    (e.g., ORDER_PLACED has an ordinal value of 0,
//    IN_PROGRESS has an ordinal value of 1, and so on).
}