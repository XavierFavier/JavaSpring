package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GameController {
    @PostMapping("/games")
    public String createGame(@RequestBody GameCreationParams params) {
        // TODO - actually create a new game

        return UUID.randomUUID().toString();
    }
}
