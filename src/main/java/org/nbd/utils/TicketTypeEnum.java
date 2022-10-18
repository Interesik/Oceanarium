package org.nbd.utils;

public class TicketTypeEnum {

    public enum TicketType {
        GROUP, SINGLE
    }

    public enum TicketSubtype {
        NORMAL,
        REDUCED
    }

    public enum TicketDiscount {
        NORMAL, CITY_CARD, CHARITY;


        @Override
        public String toString() {
            switch (this) {
                case CITY_CARD -> {
                    return "city card discount";
                }
                case CHARITY -> {
                    return "charity discount";
                }
                default -> {
                    return "no discount";
                }
            }
        }
    }
}