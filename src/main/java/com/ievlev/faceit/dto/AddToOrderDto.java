package com.ievlev.faceit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import javax.validation.Valid;
import java.util.List;

@Data
@Jacksonized
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddToOrderDto {
    @Valid
    private List<ItemInOrderDto> itemInOrderDtoList;
}
