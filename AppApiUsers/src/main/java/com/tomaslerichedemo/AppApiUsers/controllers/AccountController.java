package com.tomaslerichedemo.AppApiUsers.controllers;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    Environment environment;

    public AccountController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/status/check")
    public String status() {
        return "Working on " + environment.getProperty("local.server.port");
    }
}
