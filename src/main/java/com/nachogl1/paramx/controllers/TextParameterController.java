package com.nachogl1.paramx.controllers;

import com.nachogl1.paramx.model.TextParameter;
import com.nachogl1.paramx.services.TextParameterService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Log
public class TextParameterController {

    @Autowired
    private TextParameterService service;

    @GetMapping("/textParameters/{paramUserId}")
    public ResponseEntity<List<TextParameter>> getAllByUser(@PathVariable UUID paramUserId) {
        log.info(String.format("--*Fetching all params for : %s *--", paramUserId.toString()));

        final List<TextParameter> responseBody = service.getAllByParamUser(paramUserId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @PostMapping("/textParameters")
    public ResponseEntity<TextParameter> add(@RequestBody TextParameter parameter) {
        log.info(String.format("--*Creating instance of param: %s *--", parameter.getName()));
        final TextParameter responseBody = service.save(parameter);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseBody);
    }

    @PutMapping("/textParameters")
    public ResponseEntity<TextParameter> update(@RequestBody TextParameter parameter) {
        log.info(String.format("--*Updating instance of param: %s *--", parameter.getId()));

        final TextParameter responseBody = service.save(parameter);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @DeleteMapping("/textParameters/{parameterId}")
    public ResponseEntity<Void> delete(@PathVariable UUID parameterId) {
        log.info(String.format("--*Deleting instance of param: %s *--", parameterId.toString()));

        service.delete(parameterId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }


}
