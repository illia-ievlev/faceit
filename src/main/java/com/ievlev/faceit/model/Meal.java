package com.ievlev.faceit.model;

import com.ievlev.faceit.converter.MealTypeConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "meals")
@Getter
@Setter
public class Meal {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    private Long id;

    @Column(name = "name")
    @NotBlank
    @NotNull
    private String name;

    @Column(name = "price")
    @NotNull
    @Min(0)
    private BigDecimal price;

    @Column(name = "meal_type")
    @Convert(converter = MealTypeConverter.class)
    @NotNull
    private MealType mealType;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "cuisine_id")
    private Cuisine cuisine;

    public Meal(String name, BigDecimal price, MealType mealType, Cuisine cuisine) {
        this.name = name;
        this.price = price;
        this.mealType = mealType;
        this.cuisine = cuisine;
    }

    public Meal(Long id, String name, BigDecimal price, MealType mealType, Cuisine cuisine) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.mealType = mealType;
        this.cuisine = cuisine;
    }

    public Meal() {
    }
}
