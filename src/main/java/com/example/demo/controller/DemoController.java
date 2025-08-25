package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/remove")
    public ResponseEntity<String> removeFirstAndLast(@RequestParam("str") String str) {
        if (str == null || str.length() < 2) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Input string must have at least 2 characters");
        }

        if (str.length() == 2) {
            return ResponseEntity.ok("");
        }

        String result = str.substring(1, str.length() - 1);
        return ResponseEntity.ok(result);
    }
}