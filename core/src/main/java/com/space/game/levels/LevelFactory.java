package com.space.game.levels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.List;

import com.space.game.config.LevelConfig;
// import com.space.game.Game;
import com.space.game.SpaceGame;

public class LevelFactory {
    private Map<Integer, LevelConfig> levelConfigs;
    // private Game game;
    private Random random;
    private float factor_speed_initial;
    private float speed;

    public LevelFactory() {
        // this.game = game;
        this.levelConfigs = new HashMap<>();
        this.random = new Random();
        
    }

    private void setFactorSpeedInitial(float speed){
        this.factor_speed_initial -= speed;
    }

    public Level createLevel(int levelNumber) {
        LevelConfig config = levelConfigs.get(levelNumber);
        if (config == null) {
            config = generateNewLevelConfig(levelNumber);
            levelConfigs.put(levelNumber, config);
        }
        return new DynamicLevel(config);
    }

    private LevelConfig generateNewLevelConfig(int levelNumber) {
        if (levelNumber == 1) {
            // Configuração inicial para o nível 1

            this.speed = SpaceGame.getGame().getWorldWidth();
            this.factor_speed_initial = 70f;
            int enemyCount = 7;
            int ammunitions = 49;
            int kills = 0;
            int streak = 1;
            int consecutiveKills = 0;
            return new LevelConfig(ammunitions, kills, streak, consecutiveKills, levelNumber, enemyCount, speed/factor_speed_initial, generateMovementPatterns(enemyCount));

        } else {
            // Configuração para os níveis seguintes
            LevelConfig previousConfig = levelConfigs.get(levelNumber - 1);
            int newEnemyCount = previousConfig.enemyCount + random.nextInt(7) + 3; // Aumenta de 3 a 7 inimigos
            float newEnemySpeed;

            int ammunitions = SpaceGame.getGame().getMapManager().getSpaceship().getAmmunitions() + 7;
            int kills = SpaceGame.getGame().getMapManager().getSpaceship().getKillCount();
            int streak = SpaceGame.getGame().getMapManager().getSpaceship().getStreakCount();
            int consecutiveKills = SpaceGame.getGame().getMapManager().getSpaceship().getConsecutiveKills();

            // Aumenta a velocidade a cada nível par
            if (previousConfig.levelNumber % 2 == 0 && this.factor_speed_initial >= 15f){
                setFactorSpeedInitial(1f);
                newEnemySpeed = speed/factor_speed_initial;
            } else {
                newEnemySpeed = previousConfig.enemySpeed; // Mantém a velocidade
            }
            
            List<Integer> newMovementPatterns = generateMovementPatterns(newEnemyCount);
    
            return new LevelConfig(ammunitions, kills, streak, consecutiveKills, levelNumber, newEnemyCount, newEnemySpeed, newMovementPatterns);
        }
    }

    private List<Integer> generateMovementPatterns(int enemyCount) {
        List<Integer> patterns = new ArrayList<>();
        List<Integer> weightedPatterns = createWeightedPatterns(enemyCount);

        for (int i = 0; i < enemyCount; i++) {
            patterns.add(weightedPatterns.get(random.nextInt(weightedPatterns.size())));
        }
        return patterns;
    }

    private List<Integer> createWeightedPatterns(int enemyCount) {
        List<Integer> weightedPatterns = new ArrayList<>();
        // criar pesos:
        int weightFor0 = (int) (enemyCount * 0.45f);
        int weightFor1 = (int) (enemyCount * 0.35f);
        int weightFor2 = enemyCount - weightFor0 - weightFor1;

        for (int i = 0; i < weightFor0; i++) weightedPatterns.add(0);
        for (int i = 0; i < weightFor1; i++) weightedPatterns.add(1);
        for (int i = 0; i < weightFor2; i++) weightedPatterns.add(2);

        return weightedPatterns;
    }

    public void dispose() {
        levelConfigs.clear();
    }
}
