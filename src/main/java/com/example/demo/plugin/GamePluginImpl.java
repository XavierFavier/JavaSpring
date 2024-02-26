package com.example.demo.plugin;

import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;

public class GamePluginImpl implements GamePlugin {
    protected GameFactory factory = new TicTacToeGameFactory();
    public String getName(){
        return factory.getGameFactoryId();
    }
}
