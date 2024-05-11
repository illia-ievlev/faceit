package com.ievlev.faceit.util;

import com.ievlev.faceit.dto.OrderToShowDto;
import com.ievlev.faceit.dto.OrderedMealToShowDto;
import com.ievlev.faceit.model.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderToShowDtoConverterUtil {


    public static OrderToShowDto orderToShowDtoToOrder(Order order) {

        List<OrderedMealToShowDto> orderedMealToShowDtoList = order.getOrderedMealList().stream()
                .map(OrderedMealDtoConverter::orderedMealDtoToShowFromOrderedMeal)
                .collect(Collectors.toList());
        return new OrderToShowDto(order.getId(),
                order.getUser().getId(),
                order.getUser().getUsername(),
                orderedMealToShowDtoList,
                order.getTotalPrice().toString());
    }
}
