package org.nbd.utils;

public class TicketTypeEnum {

    public enum GroupTicketType {
        NORMAL,
        REDUCED;

        @Override
        public String toString() {
            if (this == REDUCED) return "reduced group ticket";
            else return "normal group ticket";
        }
    }

    public enum TicketDiscount {
        NORMAL(0), CITY_CARD(-3), CHARITY(5);

        public final int discount;

        TicketDiscount(int discount) {
            this.discount = discount;
        }

        public static TicketDiscount byInteger(Integer discount) {
            if (discount == CITY_CARD.discount)
                return CITY_CARD;
            else if (discount == CHARITY.discount)
                return CHARITY;
            else return NORMAL;
        }

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