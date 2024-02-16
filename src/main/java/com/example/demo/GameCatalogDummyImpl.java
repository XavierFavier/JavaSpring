package com.example.demo;

import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class GameCatalogDummyImpl implements GameCatalog {
    TicTacToeGameFactory test = new TicTacToeGameFactory();

    @Override
    public Collection<String> getGameIdentifiers() {
        Collection<String> myCollec = new ArrayList<>();
        myCollec.add(test.getGameId());
        return myCollec;
    }
}
