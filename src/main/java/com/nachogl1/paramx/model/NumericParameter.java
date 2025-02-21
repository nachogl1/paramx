package com.nachogl1.paramx.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NumericParameter {
    private UUID id;
    private BigDecimal value;
    private LocalDate date;
    private String name;
    private UUID userId;



}
