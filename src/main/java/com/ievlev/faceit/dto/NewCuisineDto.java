package com.ievlev.faceit.dto;

import com.ievlev.faceit.validation.constraint.NameSymbolsAreCorrectConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Jacksonized
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewCuisineDto {
    @NotNull
    @NotBlank
    @NameSymbolsAreCorrectConstraint
    private String name;
}
