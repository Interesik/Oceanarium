package org.nbd.utils;

import org.nbd.utils.TicketTypeEnum.TicketDiscount;

import javax.persistence.AttributeConverter;

public class TicketDiscountConverter implements AttributeConverter<TicketDiscount, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TicketDiscount ticketDiscount) {
        return ticketDiscount.discount;
    }

    @Override
    public TicketDiscount convertToEntityAttribute(Integer ticketDiscount) {
        return TicketDiscount.byInteger(ticketDiscount);
    }
}
