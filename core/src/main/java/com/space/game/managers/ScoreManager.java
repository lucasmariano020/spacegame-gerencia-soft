package com.space.game.managers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class ScoreManager {

    private static final String FILE_PATH = "data/scores.csv";

    public static class ScoreEntry {
        public String playerName;
        public int score;

        public ScoreEntry(String playerName, int score) {
            this.playerName = playerName;
            this.score = score;
        }
    }

    public void saveScore(String playerName, int score) {
        // usar o Gdx.files.internal para acessar arquivos dentro do JAR
        FileHandle file = Gdx.files.local(FILE_PATH);
        List<ScoreEntry> scoresList = loadScores();

        // Adicionar novo score se necessário
        if (scoresList.size() < 10 || score > scoresList.get(scoresList.size() - 1).score) {
            scoresList.add(new ScoreEntry(playerName, score));
        }

        // Ordenar scores em ordem decrescente
        scoresList.sort(Comparator.comparingInt(o -> -o.score));

        // Manter apenas os 10 melhores scores
        if (scoresList.size() > 10) {
            scoresList = scoresList.subList(0, 10);
        }
        // Salvar scores de volta no arquivo
        try (OutputStreamWriter writer = new OutputStreamWriter(file.write(false))) {
            writer.append("PlayerName,Score\n"); // Adiciona o cabeçalho novamente
            for (ScoreEntry entry : scoresList) {
                writer.append(entry.playerName)
                      .append(",")
                      .append(String.valueOf(entry.score))
                      .append("\n");
            }
            System.out.println("Score of " + score + " saved for player " + playerName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ScoreEntry> loadScores() {
        List<ScoreEntry> scoresList = new ArrayList<>();
        // File file = new File(FILE_PATH);
        FileHandle file = Gdx.files.local(FILE_PATH);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.read()))) {
                String line;
                boolean isFirstLine = true;
                while ((line = reader.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String playerName = parts[0];
                        int score = Integer.parseInt(parts[1]);
                        scoresList.add(new ScoreEntry(playerName, score));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return scoresList;
    }

    public boolean isHighScore(int score) {
        List<ScoreEntry> scoresList = loadScores();
        return scoresList.size() < 10 || score > scoresList.get(scoresList.size() - 1).score;
    }
}
