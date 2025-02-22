package com.nachogl1.paramx.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ParamUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String firstName;
    private String secondName;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<NumericParameter> numericParameterList;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<TextParameter> textParametersList;

}
