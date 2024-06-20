package com.space.game.config;

import java.util.List;

public class LevelConfig {
    public int levelNumber;
    public int enemyCount;
    public float enemySpeed;
    public List<Integer> enemyMovementPatterns;

    public int ammunitions;
    public int kills;

    public LevelConfig(int ammunitions, int kills, int levelNumber, int enemyCount, float enemySpeed, List<Integer> enemyMovementPatterns) {
        if (enemyMovementPatterns.size() != enemyCount) {
            throw new IllegalArgumentException("The size of enemyMovementPatterns must match enemyCount");
        }
        this.levelNumber = levelNumber;
        this.enemyCount = enemyCount;
        this.enemySpeed = enemySpeed;
        this.enemyMovementPatterns = enemyMovementPatterns;

        this.ammunitions = ammunitions;
        this.kills = kills;

    }
}
