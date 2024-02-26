package com.example.demo.controller;

import com.example.demo.dao.MySQLUserDAO;
import com.example.demo.dao.User;
import com.example.demo.dto.GameDto;
import com.example.demo.dto.TokenDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.services.GameCatalogDummyImpl;
import com.example.demo.services.GameCreationParams;
import com.example.demo.services.GameService;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.IntRange;
import fr.le_campus_numerique.square_games.engine.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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


    //DAO
    private MySQLUserDAO myDAO = new MySQLUserDAO();

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return myDAO.getAllUsers();
    }

    @GetMapping("/user")
    public User getUserById(@RequestParam UUID id) {
        return myDAO.getUserById(id);
    }

    @PostMapping("/user")
    public UUID addUser(@RequestParam("name") String name) {
        UserDTO user = new UserDTO(UUID.randomUUID(), name);
        myDAO.addUser(user);
        return user.id();
    }

    @PutMapping("/user")
    public void updateUser(@RequestParam String id,
                           @RequestParam String newID, @RequestParam String newName) {

        UserDTO user = new UserDTO(UUID.fromString(newID), newName);
        myDAO.updateUser(UUID.fromString(id), user);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam UUID id) {
        myDAO.deleteUser(id);
    }
}
