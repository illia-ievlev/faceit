package com.ievlev.faceit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderToShowDto implements Comparable<OrderToShowDto> {
    private long orderId;
    private long ownerUserId;
    private String ownerUsername;
    private List<OrderedMealToShowDto> orderedItemList;
    private String totalPrice;

    @Override
    public int compareTo(OrderToShowDto o) {
        return Long.compare(this.ownerUserId, o.ownerUserId);
    }
}
