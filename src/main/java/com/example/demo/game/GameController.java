package com.example.demo.game;

import fr.le_campus_numerique.square_games.engine.IntRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class GameController {
    GameCatalogDummyImpl curGame;

    @Autowired
    GameService service;

    @PostMapping("/games")
    //public String createGame(@RequestBody GameCreationParams params) {
    public String createGame() {
        curGame = new GameCatalogDummyImpl();
        return UUID.randomUUID().toString();
    }

    @GetMapping("/games/{gameId}")
    public Object getGame(@PathVariable String gameId) {
    //TODO - actually get and return game with id 'gameId'
        return curGame.getGameIdentifiers();
    }

    @GetMapping("/games/{gameId}/boardSizeRange")
    public IntRange getBoardSizeRange(@PathVariable String gameId) {
        return curGame.test.getBoardSizeRange(2);
    }

    @GetMapping("/games/{gameId}/playerCountRange")
    public IntRange getPlayerCountRange(@PathVariable String gameId) {
        return curGame.test.getPlayerCountRange();
    }
}
