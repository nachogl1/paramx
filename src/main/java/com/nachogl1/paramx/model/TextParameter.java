package com.nachogl1.paramx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty
    @Column(nullable = false)
    private LocalDate date;
    @NotEmpty
    @Column(nullable = false)
    private String valueParameter;
    @NotEmpty
    @Column(nullable = false)
    private String name;
    @NotEmpty
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "paramUserId", nullable = false)
    private ParamUser paramUser;
}
