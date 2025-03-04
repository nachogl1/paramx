package com.nachogl1.paramx.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "First name is mandatory")
    @Column(nullable = false)
    private String firstName;
    @NotEmpty
    @Column(nullable = false)
    @NotEmpty(message = "Second name is mandatory")
    private String secondName;
    @NotEmpty(message = "Email is mandatory")
    @Column(nullable = false, unique = true)
    @Email(message = "Email should have valid format")
    private String email;
    @JsonManagedReference
    @OneToMany(mappedBy = "paramUser",cascade = CascadeType.REMOVE)
    private List<TextParameter> textParametersList;


}
