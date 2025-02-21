package com.nachogl1.paramx.model;

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
public class User {
    private UUID id;
    private String firstName;
    private String secondName;
    private List<NumericParameter> numericParameterList;
    private List<TextParameter> textParametersList;

}
