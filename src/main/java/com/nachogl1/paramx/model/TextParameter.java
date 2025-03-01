package com.nachogl1.paramx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @NotNull(message = "Date is mandatory")
    @Column(nullable = false)
    private LocalDate date;
    @NotEmpty(message = "Parameter value is mandatory")
    @Column(nullable = false)
    private String valueParameter;
    @NotEmpty(message = "Parameter name is mandatory")
    @Column(nullable = false)
    private String name;
    @NotNull(message = "Associated user is mandatory for this parameter")
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "paramUserId", nullable = false)
    private ParamUser paramUser;
}
