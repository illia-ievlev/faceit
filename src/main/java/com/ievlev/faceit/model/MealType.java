package com.ievlev.faceit.model;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.EnumSet;
import java.util.Optional;

@RequiredArgsConstructor
public enum MealType {
    MAIN("main"), DESSERT("dessert"), DRINK("drink");

    private final String name;

    public String getName() {
        return name;
    }

    public static Optional<MealType> fromName(String name) {
        return EnumSet.allOf(MealType.class).stream().filter(t -> t.getName().equals(name)).findFirst();
    }

}
