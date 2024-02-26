package com.example.demo.services;

import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

public interface GameService {
    List<String> getGames(Locale locale);
    void addGame();
}
