package com.ievlev.faceit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppErrorStatusDto {
    private int status;
    private String message;
}
