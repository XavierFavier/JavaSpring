package com.example.demo.controller;

import com.example.demo.dto.TokenDTO;
import com.example.demo.dto.gameDto;
import com.example.demo.services.GameCatalogDummyImpl;
import com.example.demo.services.GameCreationParams;
import com.example.demo.services.GameService;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.IntRange;
import fr.le_campus_numerique.square_games.engine.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@RestController
public class GameController {
    @Autowired
    GameService service;

    private gameDto gameToDTO(Game game){
        return new gameDto(game.getId(),game.getFactoryId());
    }
    private TokenDTO tokenToDTO(Token token){
        return new TokenDTO(token.getName(),token.getPosition());
    }

    @PostMapping("/games")
    public gameDto createGame() {
        service.addGame();
        gameDto gameDto = new gameDto(UUID.randomUUID(), "name");
        return gameDto;
    }

    @GetMapping("/games")
    public List<String> getGames(@RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return service.getGames(locale);
    }
}
