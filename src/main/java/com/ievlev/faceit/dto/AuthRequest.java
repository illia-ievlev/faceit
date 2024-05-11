package com.ievlev.faceit.dto;

import com.ievlev.faceit.validation.constraint.NameSymbolsAreCorrectConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotNull
    @Size(min = 1, max = 30)
    @NameSymbolsAreCorrectConstraint
    private String username;

    @NotNull
    @Size(min = 1, max = 30)
    private String password;


}
