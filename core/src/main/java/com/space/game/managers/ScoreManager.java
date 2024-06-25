package com.space.game.managers;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import com.space.game.SpaceGame;


import static com.mongodb.client.model.Filters.eq;

public class ScoreManager {

    // pegar password no drive
    private static final String CONNECTION_STRING = "mongodb+srv://eduardows:<password>@cluster0.bt0tzst.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
    private static final String DATABASE_NAME = "game_database";
    private static final String COLLECTION_NAME = "scores";
    private static final String FILE_PATH = "data/scores.csv";

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public static class ScoreEntry {
        public String playerName;
        public int score;

        public ScoreEntry(String playerName, int score) {
            this.playerName = playerName;
            this.score = score;
        }
    }

    public ScoreManager() {
        try {
            ConnectionString connectionString = new ConnectionString(CONNECTION_STRING);
            ServerApi serverApi = ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build();
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .serverApi(serverApi)
                    .build();
            mongoClient = MongoClients.create(settings);
            database = mongoClient.getDatabase(DATABASE_NAME);
            collection = database.getCollection(COLLECTION_NAME);
            System.out.println("Successfully connected to MongoDB!");
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }

    public void saveGlobalScore(String playerName, int score) {
        List<ScoreEntry> scoresList = loadGlobalScores();

        if (scoresList.size() < 10 || score > scoresList.get(scoresList.size() - 1).score) {
            scoresList.add(new ScoreEntry(playerName, score));
        }

        scoresList.sort(Comparator.comparingInt(o -> -o.score));

        if (scoresList.size() > 10) {
            scoresList = scoresList.subList(0, 10);
        }

        collection.drop(); // Clear the collection before inserting new scores
        for (ScoreEntry entry : scoresList) {
            Document doc = new Document("playerName", entry.playerName)
                    .append("score", entry.score);
            collection.insertOne(doc);
        }
        System.out.println("Global score of " + score + " saved for player " + playerName);
    }

    public List<ScoreEntry> loadGlobalScores() {
        SpaceGame.getLogger().debug("Loading global scores");
        List<ScoreEntry> scoresList = new ArrayList<>();
        Consumer<Document> processDocument = document -> {
            String playerName = document.getString("playerName");
            int score = document.getInteger("score");
            scoresList.add(new ScoreEntry(playerName, score));
        };
        collection.find().forEach(processDocument);
        scoresList.sort(Comparator.comparingInt(o -> -o.score)); // Ordenate in descending order
        SpaceGame.getLogger().debug("Global scores loaded");
        // SpaceGame.getLogger().error("Error loading global scores", new Exception("Test exception"));
        return scoresList;
    }

    public void saveLocalScore(String playerName, int score) {
        FileHandle file = Gdx.files.local(FILE_PATH);
        List<ScoreEntry> scoresList = loadLocalScores();

        if (scoresList.size() < 10 || score > scoresList.get(scoresList.size() - 1).score) {
            scoresList.add(new ScoreEntry(playerName, score));
        }

        scoresList.sort(Comparator.comparingInt(o -> -o.score));

        if (scoresList.size() > 10) {
            scoresList = scoresList.subList(0, 10);
        }

        try (OutputStreamWriter writer = new OutputStreamWriter(file.write(false))) {
            writer.append("PlayerName,Score\n");
            for (ScoreEntry entry : scoresList) {
                writer.append(entry.playerName)
                        .append(",")
                        .append(String.valueOf(entry.score))
                        .append("\n");
            }
            System.out.println("Local score of " + score + " saved for player " + playerName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ScoreEntry> loadLocalScores() {
        List<ScoreEntry> scoresList = new ArrayList<>();
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
        List<ScoreEntry> scoresList = loadGlobalScores();
        return scoresList.size() < 10 || score > scoresList.get(scoresList.size() - 1).score;
    }

    public boolean isLocalHighScore(int score) {
        List<ScoreEntry> scoresList = loadLocalScores();
        return scoresList.size() < 10 || score > scoresList.get(scoresList.size() - 1).score;
    }

    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
