package com.space.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

public class SpaceGame extends ApplicationAdapter {

    static private Game game;

    @Override
    public void create() {
        // Captura o cursor para fazer ele desaparecer
        Gdx.input.setCursorCatched(true);
        game = new Game();
    }

    @Override
    public void render() {
        game.render();
    }

    @Override
    public void resize(int width, int height) {
        game.resize(width, height);
    }

    @Override
    public void dispose() {
        game.dispose();

    }

    static public Game getGame() {
        return game;
    }   
    
}
