package com.example.sbertaste.dto.statistic;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TopRequest {
    private int count;
    private LocalDate begin;
    private LocalDate end;
}
