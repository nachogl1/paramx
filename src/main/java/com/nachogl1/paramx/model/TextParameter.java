package com.nachogl1.paramx.model;

import jakarta.persistence.*;
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
@Entity
public class TextParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String valueParameter;
    private LocalDate date;
    private String name;

}
