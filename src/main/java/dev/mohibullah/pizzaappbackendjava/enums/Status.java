package dev.mohibullah.pizzaappbackendjava.enums;

public enum Status {
    ACTIVE("Active"),
    INACTIVE("InActive");

    private final String status;

    private Status(String label) {
        this.status = label;
    }

    public String getLabel() {
        return status;
    }
}
