package org.nbd.utils;

public enum TicketType {

    NORMAL(0), CITY_CARD(-3), CHARITY(5);

    final int discountValue;

    TicketType(int value) {
        discountValue = value;
    }

    public int getValue() {
        return discountValue;
    }
}
