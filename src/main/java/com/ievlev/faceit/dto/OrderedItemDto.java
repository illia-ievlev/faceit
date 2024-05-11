package com.ievlev.faceit.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Builder
@Jacksonized
public class OrderedItemDto {

    private long id;
    private BigDecimal price;
    private int quantity;
}
