package com.iremembr.jtraxxsexamples.promotecustomer;

class Customer {

    private final int id;
    private final String email;
    private final boolean canBePromoted;

    Customer(int id, String email, boolean canBePromoted) {
        this.id = id;
        this.email = email;
        this.canBePromoted = canBePromoted;
    }

    int id() {
        return id;
    }

    String email() {
        return email;
    }

    boolean canBePromoted() {
        return canBePromoted;
    }

    void promote() {
        if (!canBePromoted) {
            throw new IllegalStateException("The customer can not be promoted.");
        }
    }
}
