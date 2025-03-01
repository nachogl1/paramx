package com.nachogl1.paramx.controllers;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class EnvController {

    @Value("${environment.val}")
    private String val;

    @GetMapping("/env")
    public String get() {
        log.info("--*Fetching the env profile: "+ this.val +" *--");
        return this.val;
    }
}
