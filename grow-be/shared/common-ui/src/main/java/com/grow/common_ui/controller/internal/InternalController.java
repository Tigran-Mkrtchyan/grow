package com.grow.common_ui.controller.internal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/internal")
public class InternalController {

    @GetMapping(path = "/health")
    public ResponseEntity<Void> getHealthOK() {
        return ResponseEntity.ok().build();
    }

}
