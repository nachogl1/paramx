package com.nachogl1.paramx.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvController {

    @Value("${environment.val}")
    private String val;

    @GetMapping("/env")
    public String get() {
        return this.val;
    }
}
