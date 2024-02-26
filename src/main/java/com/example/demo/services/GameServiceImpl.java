package com.example.demo.services;

import com.example.demo.plugin.GamePlugin;
import com.example.demo.plugin.GamePluginImpl;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameServiceImpl implements GameService {

    private List<GamePlugin> gamePlugins = new ArrayList<>();

    @Autowired
    private MessageSource messageSource;

    @Override
    public List<String> getGames(Locale locale) {
        List<String> returnList = new ArrayList<String>();
        for (GamePlugin plugin : gamePlugins) {
            //returnList.add(messageSource.getMessage(plugin.getName().replace(" ", ""), null, locale));
            returnList.add(plugin.getName().replace(" ", ""));
        }
        return returnList;
    }

    @Override
    public void addGame() {
        GamePluginImpl game = new GamePluginImpl();
        gamePlugins.add(game);
    }
}
