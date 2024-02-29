package com.example.demo.controller;

import com.example.demo.dao.JbdcDao;
import com.example.demo.dao.MySQLUserDAO;
import com.example.demo.dao.Users;
import com.example.demo.dao.UserRepository;
import com.example.demo.dto.GameDto;
import com.example.demo.dto.TokenDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.services.GameService;
import fr.le_campus_numerique.square_games.engine.Game;
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

    private GameDto gameToDTO(Game game){
        return new GameDto(game.getId(),game.getFactoryId());
    }
    private TokenDTO tokenToDTO(Token token){
        return new TokenDTO(token.getName(),token.getPosition());
    }

    @PostMapping("/games")
    public GameDto createGame() {
        service.addGame();
        GameDto gameDto = new GameDto(UUID.randomUUID(), "name");
        return gameDto;
    }

    @GetMapping("/games")
    public List<String> getGames(@RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return service.getGames(locale);
    }
}
