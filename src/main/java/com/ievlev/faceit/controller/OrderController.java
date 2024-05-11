package com.ievlev.faceit.controller;

import com.ievlev.faceit.dto.AddToOrderDto;
import com.ievlev.faceit.dto.OrderToShowDto;
import com.ievlev.faceit.service.OrderService;
import com.ievlev.faceit.util.UserIdFromAuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping("/api/v1/secured/order")
    public void addToOrder(Authentication authentication, @RequestBody @Valid AddToOrderDto addToOrderDto) {
        long userId = UserIdFromAuthenticationUtil.getUserIdFromAuthentication(authentication);
        orderService.addRequestToOrder(userId, addToOrderDto);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("api/v1/secured/order")
    public OrderToShowDto getOrder(Authentication authentication) {
        long userId = UserIdFromAuthenticationUtil.getUserIdFromAuthentication(authentication);
        return orderService.getOrderToShow(userId);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping("/api/v1/admin/order")
    public void deleteOrder(@RequestParam @Min(1) long orderId) {
        orderService.deleteOrder(orderId);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("api/v1/admin/order")
    public List<OrderToShowDto> getAllOrdersSortedByUsers(@RequestParam @Min(0) int pageNumber, @RequestParam @Min(0) int pageSize) {
        return orderService.getAllOrdersSortedByUsers(pageNumber, pageSize);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("api/v1/admin/order/{id}")
    public OrderToShowDto getOrderOfSpecificUser(@PathVariable @Min(1) long userId) {
        return orderService.getOrderByUserId(userId);
    }
}
