package com.ievlev.faceit.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ordered_meal")
@Getter
@Setter
public class OrderedMeal {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    @NotNull
    private Meal meal;

    @Column(name = "add_ice")
    private boolean addIce;

    @Column(name = "add_lemon")
    private boolean addLemon;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    public OrderedMeal(Meal meal, boolean addIce, boolean addLemon, Order order) {
        this.meal = meal;
        this.addIce = addIce;
        this.addLemon = addLemon;
        this.order = order;
    }

    public OrderedMeal() {
    }
}
