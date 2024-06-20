package com.space.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.space.game.entities.Spaceship;
import com.space.game.levels.Level;
import com.space.game.levels.LevelFactory;
import com.space.game.Game;

public class MapManager {
    //private Game game;
    private Level currentLevel;
    private LevelFactory levelFactory;

    public MapManager(Game game) {
        this.levelFactory = new LevelFactory();
        // this.game = game;
    }

    public void loadLevel(int levelNumber) {
        if (currentLevel != null) {
            currentLevel.dispose();
        }
        currentLevel = levelFactory.createLevel(levelNumber);

        if (currentLevel == null) {
            throw new IllegalArgumentException("Invalid level number: " + levelNumber);
        }
        
    }

    public void render(SpriteBatch batch) {
        if (currentLevel != null) {
            currentLevel.render(batch);
        }
    }

    public void update() {
        if (currentLevel != null && currentLevel.getEndLevel()) {
            loadLevel(currentLevel.getConfig().levelNumber + 1);
        }
        if (currentLevel != null) {
            currentLevel.update();
        }
    }

    public void dispose() {
        if (currentLevel != null) {
            currentLevel.dispose();
            currentLevel = null;
            levelFactory.dispose();
        }
    }

    public Spaceship getSpaceship() {
        return currentLevel != null ? currentLevel.getSpaceship() : null;
    }

    public void freeSpaceship() {
        if (currentLevel != null) {
            currentLevel.freeSpaceship();
        }
    }

    
}
