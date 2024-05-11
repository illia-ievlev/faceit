package com.ievlev.faceit.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderedMeal> orderedMealList;

    @Column(name = "total_price")
    @NotNull
    @Min(0)
    private BigDecimal totalPrice;

    public Order(User user, List<OrderedMeal> orderedMealList) {
        this.user = user;
        this.orderedMealList = orderedMealList;
    }

    public Order() {
    }

    public Order(User user, List<OrderedMeal> orderedMealList, BigDecimal totalPrice) {
        this.user = user;
        this.orderedMealList = orderedMealList;
        this.totalPrice = totalPrice;
    }
}
