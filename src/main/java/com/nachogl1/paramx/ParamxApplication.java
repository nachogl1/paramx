package com.nachogl1.paramx;

import com.nachogl1.paramx.controllers.ParamUserController;
import com.nachogl1.paramx.controllers.TextParameterController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class ParamxApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParamxApplication.class, args);
    }

}