package com.ievlev.faceit.service;

import com.ievlev.faceit.dto.AddToOrderDto;
import com.ievlev.faceit.dto.ItemInOrderDto;
import com.ievlev.faceit.dto.OrderToShowDto;
import com.ievlev.faceit.exceptions.AddToOrderException;
import com.ievlev.faceit.exceptions.OrderNotFoundException;
import com.ievlev.faceit.model.*;
import com.ievlev.faceit.repository.OrderRepository;
import com.ievlev.faceit.util.OrderToShowDtoConverterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class OrderService {
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final MealService mealService;
    private static final String ADD_TO_ORDER_DTO_MUST_NOT_BE_NULL = "AddToOrderDto must not be null";
    private static final String CAN_T_ADD_NOTHING_TO_ORDER = "can't add nothing to order";


    @Transactional
    public void addRequestToOrder(@Min(1) long userId, AddToOrderDto addToOrderDto) {
        validateAddToOrderDto(addToOrderDto);
        User user = userService.getUserById(userId);
        Order order = user.getOrder();
        if (order != null) {
            addItemsToOrder(order, addToOrderDto);
        } else {
            createAndAddToOrder(user, addToOrderDto);
        }
    }

    private void createAndAddToOrder(User user, AddToOrderDto addToOrderDto) {
        List<OrderedMeal> orderedMealList = new ArrayList<>();
        Order newOrder = new Order(user, orderedMealList, BigDecimal.ZERO);
        saveNew(newOrder);
        addItemsToOrder(newOrder, addToOrderDto);
    }

    private void addItemsToOrder(Order order, AddToOrderDto addToOrderDto) {
        List<ItemInOrderDto> itemsToAdd = addToOrderDto.getItemInOrderDtoList();
        Set<String> errors = new HashSet<>();
        List<OrderedMeal> orderedMealList = order.getOrderedMealList();

        for (ItemInOrderDto itemInOrder : itemsToAdd) {
            Optional<Meal> availableMealOptional = mealService.findById(itemInOrder.getMealId());
            if (availableMealOptional.isEmpty()) {
                errors.add("can't find meal with id " + itemInOrder.getMealId());
                continue;
            }
            Meal availableMealToAdd = availableMealOptional.get();
            if ((!availableMealToAdd.getMealType().equals(MealType.DRINK)) && (itemInOrder.isAddIce() || itemInOrder.isAddLemon())) {
                errors.add("you cannot add lemon or ice to food that is not a drink");
                continue;
            }
            orderedMealList.add(new OrderedMeal(availableMealToAdd, itemInOrder.isAddIce(), itemInOrder.isAddLemon(), order));
            BigDecimal a = order.getTotalPrice();
            BigDecimal b = a.add(availableMealToAdd.getPrice());
            order.setTotalPrice(b);
        }
        if (!errors.isEmpty()) {
            throw new AddToOrderException("can't add items to order: " + String.join(", ", errors));
        }
    }

    public OrderToShowDto getOrderToShow(@Min(1) long userId) {
        Order order = userService.getUserById(userId).getOrder();
        if (order == null) {
            throw new OrderNotFoundException("the user doesn't have an order");
        }
        return OrderToShowDtoConverterUtil.orderToShowDtoToOrder(order);
    }

    public void deleteOrder(long orderId) {
        orderRepository.deleteById(orderId);
    }

    private void validateAddToOrderDto(AddToOrderDto addToOrderDto) {
        if (addToOrderDto == null) {
            throw new IllegalArgumentException(ADD_TO_ORDER_DTO_MUST_NOT_BE_NULL);
        }
        if (addToOrderDto.getItemInOrderDtoList().isEmpty()) {
            throw new IllegalArgumentException(CAN_T_ADD_NOTHING_TO_ORDER);
        }
    }

    private void saveNew(@Valid @NotNull Order order) {
        order.setId(null); // that is done to make sure that hibernate will save new order even if order somehow will be with id attached
        orderRepository.save(order);
    }

    public List<OrderToShowDto> getAllOrdersSortedByUsers(int pageNumber, int pageSize) {
        Page<Order> orderPage = orderRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return orderPage.get()
                .map(OrderToShowDtoConverterUtil::orderToShowDtoToOrder)
                .sorted(Comparator.comparingLong(OrderToShowDto::getOwnerUserId))
                .collect(Collectors.toList());
    }

    public OrderToShowDto getOrderByUserId(@Min(1) long userId) {
        Order order = orderRepository.findByUserId(userId).orElseThrow(() -> new OrderNotFoundException("this user doesn't have order"));
        return OrderToShowDtoConverterUtil.orderToShowDtoToOrder(order);
    }
}
