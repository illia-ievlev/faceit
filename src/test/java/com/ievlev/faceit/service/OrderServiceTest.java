package com.ievlev.faceit.service;

import com.ievlev.faceit.dto.AddToOrderDto;
import com.ievlev.faceit.dto.ItemInOrderDto;
import com.ievlev.faceit.exceptions.OrderNotFoundException;
import com.ievlev.faceit.model.*;
import com.ievlev.faceit.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private MealService mealService;

    @InjectMocks
    private OrderService orderService;


    @Test
    public void testAddRequestToOrder_WhenValidDataAndNewOrder_ShouldCreateNewOrder() {
        AddToOrderDto addToOrderDto = new AddToOrderDto(Collections.singletonList(new ItemInOrderDto(1L, false, false)));
        User user = new User();
        when(userService.getUserById(1L)).thenReturn(user);
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(mealService.findById(1L)).thenReturn(Optional.of(new Meal(1L, "Test Meal", BigDecimal.TEN, MealType.MAIN, new Cuisine())));
        assertDoesNotThrow(() -> orderService.addRequestToOrder(1L, addToOrderDto));
    }

    @Test
    public void testAddRequestToOrder_WhenAddToOrderDtoIsNull_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> orderService.addRequestToOrder(1L, null));
    }

    @Test
    public void testAddRequestToOrder_WhenItemInOrderDtoListIsEmpty_ShouldThrowIllegalArgumentException() {
        AddToOrderDto addToOrderDto = new AddToOrderDto(Collections.emptyList());
        assertThrows(IllegalArgumentException.class, () -> orderService.addRequestToOrder(1L, addToOrderDto));
    }


    @Test
    public void testGetOrderByUserId_WhenNoOrderExistsForUser_ShouldThrowOrderNotFoundException() {
        when(orderRepository.findByUserId(1L)).thenReturn(Optional.empty());
        assertThrows(OrderNotFoundException.class, () -> orderService.getOrderByUserId(1L));
    }

}
