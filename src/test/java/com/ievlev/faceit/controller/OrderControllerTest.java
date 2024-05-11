package com.ievlev.faceit.controller;

import com.ievlev.faceit.dto.AddToOrderDto;
import com.ievlev.faceit.dto.OrderToShowDto;
import com.ievlev.faceit.service.OrderService;
import com.ievlev.faceit.util.UserIdFromAuthenticationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testDeleteOrder() {
        orderController.deleteOrder(1L);
        verify(orderService, times(1)).deleteOrder(1L);
    }

    @Test
    public void testGetAllOrdersSortedByUsers() {
        List<OrderToShowDto> orderDtoList = new ArrayList<>();
        when(orderService.getAllOrdersSortedByUsers(0, 10)).thenReturn(orderDtoList);
        List<OrderToShowDto> result = orderController.getAllOrdersSortedByUsers(0, 10);
        assertEquals(orderDtoList, result);
    }

    @Test
    public void testGetOrderOfSpecificUser() {
        OrderToShowDto orderDto = new OrderToShowDto();
        when(orderService.getOrderByUserId(1L)).thenReturn(orderDto);
        OrderToShowDto result = orderController.getOrderOfSpecificUser(1L);
        assertEquals(orderDto, result);
    }
}