package com.space.game.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.space.game.config.ConfigUtils;
import com.space.game.entities.Spaceship;
import com.badlogic.gdx.Gdx;
import java.util.List;
import com.space.game.Game;

public class UIManager {
    private BitmapFont font30, font100, font150;
    private Game game;
    private SpriteBatch batch;
    private int hordas;
    private final int const_larg = 21;
    private Color cian_color;

    public UIManager(Game game, SpriteBatch batch) {
        this.game = game;
        this.batch = batch;
        this.cian_color = new Color(0.0f, 1.0f, 1.0f, 1.0f);

        initializeFonts();

        
    }

    private void initializeFonts() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("assets\\fonts\\nasalization-rg.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        float scaleFactor = ConfigUtils.calcularFatorDeEscala();

        parameter.size = (int) (30 * scaleFactor);
        font30 = generator.generateFont(parameter);

        parameter.size = (int) (100 * scaleFactor);
        parameter.borderWidth = 4 * scaleFactor;
        parameter.borderColor = cian_color;
        parameter.color = Color.BLACK;
        font100 = generator.generateFont(parameter);

        parameter.size = (int) (150 * scaleFactor);
        parameter.borderWidth = 4 * scaleFactor;
        font150 = generator.generateFont(parameter);

        generator.dispose();
    }

    public void displayMenu() {
        // Desenha o título "SPACE GAME"
        String title = "SPACE GAME";
        GlyphLayout titleLayout = new GlyphLayout(font150, title);
        float title_x = game.getWorldWidth() / const_larg;
        float title_y = game.getWorldHeight()/1.5f + titleLayout.height;
        font150.draw(batch, title, title_x,  title_y);

        // Desenha o botão "New Game"
        String buttonText = "1. Start Arcade Mode";
        GlyphLayout buttonLayout = new GlyphLayout(font30, buttonText);
        float buttonX = game.getWorldWidth() / const_larg;
        float buttonY = title_y - titleLayout.height*3;
        font30.setColor(cian_color);
        font30.draw(batch, buttonText, buttonX, buttonY);

        // Desenha o botão "Scores"
        buttonText = "2. Scores";
        buttonLayout = new GlyphLayout(font30, buttonText);
        buttonX = game.getWorldWidth() / const_larg;
        buttonY = buttonY - buttonLayout.height*3;
        font30.draw(batch, buttonText, buttonX, buttonY);

        // Desenha o botão "Exit"
        buttonText = "0. Exit";
        buttonLayout = new GlyphLayout(font30, buttonText);
        buttonX = game.getWorldWidth() / const_larg;
        buttonY = buttonY - buttonLayout.height*12;
        font30.draw(batch, buttonText, buttonX, buttonY);
    }

    public void displayGameControls() {
        float scaleFactor = ConfigUtils.calcularFatorDeEscala();
    
        String title = "GAME CONTROLS";
        GlyphLayout titleLayout = new GlyphLayout(font100, title);
        float title_x = game.getWorldWidth() / const_larg;
        float title_y = game.getWorldHeight() / 1.2f + titleLayout.height * scaleFactor;
        font100.draw(batch, title, title_x, title_y);
    
        font30.setColor(cian_color);
        float startY = game.getWorldHeight() / 2 + 3 * 30 * scaleFactor; // 3 é o número de controles
    
        // Desenhar cabeçalhos da tabela
        String actionHeader = "Action";
        String controlHeader = "Control";
    
        GlyphLayout controlLayout = new GlyphLayout(font30, controlHeader);
        GlyphLayout actionLayout = new GlyphLayout(font30, actionHeader);
    
        float actionX = game.getWorldWidth() / const_larg; // Espaçamento entre colunas
        float controlX = actionX + actionLayout.width + 100 * scaleFactor;
    
        float headerY = startY + 60 * scaleFactor; // Cabeçalhos um pouco acima da lista de controles
        font30.draw(batch, controlHeader, controlX + controlLayout.width/2, headerY);
        font30.draw(batch, actionHeader, actionX, headerY);
    
        // Controles do jogo
        String[] actions = { "Turn Left", "Turn Right", "Shoot", "Pause Game", "Prev. Song", "Pause Song", "Next Song"};
        String[] controls = { "A", "D", "Spacebar", "P", "Q", "W", "E"};
    
        // Desenhar controles
        float y = startY;
    
        for (int i = 0; i < controls.length; i++) {
            String control = controls[i];
            String action = actions[i];
            
            GlyphLayout controlTextLayout = new GlyphLayout(font30, control);
            GlyphLayout actionTextLayout = new GlyphLayout(font30, action);
            
            
            font30.draw(batch, control, controlX+ controlLayout.width/2, y);
            font30.draw(batch, action, actionX, y);
    
            y -= 50 * scaleFactor;
        }
    
        // Desenha as instruções de iniciar e voltar na parte inferior da tela
        String startText = "Enter. Start";
        GlyphLayout startLayout = new GlyphLayout(font30, startText);
        // float start_x = game.getWorldWidth() / 2 + game.getWorldWidth() / 4 - startLayout.width / 2;
        float start_x = (const_larg-1)*(game.getWorldWidth() / const_larg) - startLayout.width ;
        float start_y = game.getWorldHeight() * 0.1f; // Posição inferior
        font30.draw(batch, startText, start_x, start_y);

        String backText = "Backspace. Back";
        GlyphLayout backLayout = new GlyphLayout(font30, backText);
        // float back_x = game.getWorldWidth() / 2 - game.getWorldWidth() / 4 - backLayout.width / 2;
        float back_x = game.getWorldWidth() / const_larg;
        float back_y = start_y;
        font30.draw(batch, backText, back_x, back_y);
    }
    

    public void displayGameInfo(Spaceship spaceship) {
        // Exibir informações do jogo como munição e hordas
        font30.setColor(Color.WHITE);
        String ammoText = "AMMO: " + spaceship.getAmmunitions();
        font30.draw(batch, ammoText, 10, 30);

        String hordasText = "WAVE: " + hordas;
        GlyphLayout hordasLayout = new GlyphLayout(font30, hordasText);
        font30.draw(batch, hordasText, game.getWorldWidth() - hordasLayout.width - 10, 30);

        String killsText = "SCORE: " + (spaceship.getKillCount());
        GlyphLayout killsLayout = new GlyphLayout(font30, killsText);
        font30.draw(batch, killsText, game.getWorldWidth() / 2 - killsLayout.width / 2, 30); 
    }

    public void displayGameOverInfo(Spaceship spaceship) {
        String gameOverText = "GAME OVER";
        GlyphLayout gameOverLayout = new GlyphLayout(font100, gameOverText);
        float gameOver_x = game.getWorldWidth() / 2 - gameOverLayout.width / 2;
        float gameOver_y = game.getWorldHeight() / 2 + gameOverLayout.height;
        font100.draw(batch, gameOverText, gameOver_x, gameOver_y);

        font30.setColor(cian_color);
        String restartText = "Press Enter to Continue";
        GlyphLayout restartLayout = new GlyphLayout(font30, restartText);
        font30.draw(batch, restartText, game.getWorldWidth() / 2 - restartLayout.width / 2, gameOver_y - gameOverLayout.height * 2);

    }

    public void displayPausedInfo(Spaceship spaceship) {
        String pausedText = "PAUSED";
        GlyphLayout pausedLayout = new GlyphLayout(font100, pausedText);
        font100.draw(batch, pausedText, game.getWorldWidth() / 2 - pausedLayout.width / 2, game.getWorldHeight() / 1.3f + pausedLayout.height);

        font30.setColor(cian_color);
        String restartText = "Backspace. Exit   |   Enter. Resume";
        GlyphLayout restartLayout = new GlyphLayout(font30, restartText);
        font30.draw(batch, restartText, game.getWorldWidth() / 2 - restartLayout.width / 2, game.getWorldHeight() / 1.3f - restartLayout.height * 3);

        String ammoText = "AMMO: ";
        // GlyphLayout ammoLayout = new GlyphLayout(font30, ammoText);
        font30.draw(batch, ammoText + spaceship.getAmmunitions(), 10, 30);

        String hordeText = "WAVE: ";
        GlyphLayout hordeLayout = new GlyphLayout(font30, hordeText + hordas);
        font30.draw(batch, hordeText + hordas, game.getWorldWidth() - hordeLayout.width - 10, 30);

        String killsText = "SCORE: ";
        int totalKills = (spaceship.getKillCount());
        GlyphLayout killsLayout = new GlyphLayout(font30, killsText + totalKills);
        font30.draw(batch, killsText + totalKills, game.getWorldWidth() / 2 - killsLayout.width / 2, 30);

    }

    public boolean displayNewLevel(boolean fadeIn, boolean fadeOut, float alpha, float fadeSpeed, float deltaTime) {

        if (fadeIn) {
            alpha += fadeSpeed * deltaTime;
            // alpha = Math.min(alpha, 1f); // Limita o alpha para não ultrapassar 1
    
            batch.setColor(1, 1, 1, alpha);
            String newLevelText = "WAVE " + hordas;
            GlyphLayout newLevelLayout = new GlyphLayout(font100, newLevelText);
            font100.draw(batch, newLevelText, game.getWorldWidth() / 2 - newLevelLayout.width / 2, game.getWorldHeight() / 1.3f + newLevelLayout.height);
            batch.setColor(1, 1, 1, 1);
    
            return alpha >= 1f; // isso é verdadeiro quando o alpha é maior ou igual a 1
        }
    
        if (fadeOut) {
            alpha -= fadeSpeed * deltaTime;
            // alpha = Math.max(alpha, 0f); // Limita o alpha para não ser menor que 0
    
            batch.setColor(1, 1, 1, alpha);
            String newLevelText = "WAVE " + hordas;
            GlyphLayout newLevelLayout = new GlyphLayout(font100, newLevelText);
            font100.draw(batch, newLevelText, game.getWorldWidth() / 2 - newLevelLayout.width / 2, game.getWorldHeight() / 1.3f + newLevelLayout.height);
            batch.setColor(1, 1, 1, 1);
    
            return alpha <= 0f;
        }
    
        return false;
        
    }

    public void displaySaveScore(Spaceship spaceship, String playerName, boolean showCursor) {
        String highscore = "HIGH SCORE: " + (spaceship.getKillCount());
        GlyphLayout highscoreLayout = new GlyphLayout(font100, highscore);
        float highscore_x = game.getWorldWidth() / 2 - highscoreLayout.width / 2;
        float highscore_y = game.getWorldHeight() / 1.3f + highscoreLayout.height;
        font100.draw(batch, highscore, highscore_x, highscore_y);

        // String scoreText = "Score: " + (spaceship.getKillCount());
        // GlyphLayout scoreLayout = new GlyphLayout(font100, scoreText);
        // font100.draw(batch, scoreText, game.getWorldWidth() / 2 - scoreLayout.width / 2, highscore_y - highscoreLayout.height * 2);
    
        font30.setColor(cian_color);
        String playerText = "Player: " + playerName + (showCursor ? "_" : "  ");
        GlyphLayout playerLayout = new GlyphLayout(font30, playerText);
        float player_x = game.getWorldWidth() / 2 - playerLayout.width / 2;
        float player_y = game.getWorldHeight() / 2;
        font30.draw(batch, playerText, player_x, player_y);
    
        String continueText = "Press Enter to Continue";
        GlyphLayout continueLayout = new GlyphLayout(font30, continueText);
        font30.draw(batch, continueText, game.getWorldWidth() / 2 - continueLayout.width / 2, player_y - continueLayout.height * 3);
    }


    public void displayScores(List<ScoreManager.ScoreEntry> scoresList) {
        float scaleFactor = ConfigUtils.calcularFatorDeEscala();
        
        String title = "HIGH SCORES";
        GlyphLayout titleLayout = new GlyphLayout(font100, title);
        float title_x = game.getWorldWidth() / const_larg;
        float title_y = game.getWorldHeight() / 1.2f + titleLayout.height * scaleFactor;
        font100.draw(batch, title, title_x, title_y);
        
        font30.setColor(cian_color);
        float startY = game.getWorldHeight() / 2 + (scoresList.size() / 2) * 30 * scaleFactor;
        
        // Desenhar cabeçalhos da tabela
        String rankHeader = "Rank";
        String playerHeader = "Player";
        String scoreHeader = "Score";
        
        GlyphLayout rankLayout = new GlyphLayout(font30, rankHeader);
        GlyphLayout playerLayout = new GlyphLayout(font30, playerHeader);
        GlyphLayout scoreLayout = new GlyphLayout(font30, scoreHeader);
        
        float rankX = game.getWorldWidth() / const_larg;
        float playerX = rankX + rankLayout.width + 20 * scaleFactor;  // Espaçamento entre colunas
        float scoreX = playerX + playerLayout.width + 200 * scaleFactor;
        
        float headerY = startY + 60 * scaleFactor;  // Cabeçalhos um pouco acima da lista de scores
        font30.draw(batch, rankHeader, rankX, headerY);
        font30.draw(batch, playerHeader, playerX, headerY);
        font30.draw(batch, scoreHeader, scoreX, headerY);
        
        // Determinar a largura máxima da coluna Rank
        float maxRankWidth = rankLayout.width * scaleFactor;
        for (int i = 0; i < scoresList.size(); i++) {
            String rank = (i + 1) + ".";
            GlyphLayout rankTextLayout = new GlyphLayout(font30, rank);
            if (rankTextLayout.width * scaleFactor > maxRankWidth) {
                maxRankWidth = rankTextLayout.width * scaleFactor;
            }
        }
        
        // Desenhar scores
        float y = startY;
        
        for (int i = 0; i < scoresList.size(); i++) {
            ScoreManager.ScoreEntry entry = scoresList.get(i);
            String rank = (i + 1) + ".";
            String player = entry.playerName;
            String score = String.valueOf(entry.score);
        
            GlyphLayout rankTextLayout = new GlyphLayout(font30, rank);
            GlyphLayout playerTextLayout = new GlyphLayout(font30, player);
            GlyphLayout scoreTextLayout = new GlyphLayout(font30, score);
        
            float rankXAdjusted = rankX + maxRankWidth - rankTextLayout.width * scaleFactor;
        
            font30.draw(batch, rank, rankXAdjusted, y);
            font30.draw(batch, player, playerX, y);
            font30.draw(batch, score, scoreX, y);
        
            y -= 50 * scaleFactor;
        }
    
        String continueText = "Backspace. Back";
        GlyphLayout continueLayout = new GlyphLayout(font30, continueText);
        font30.draw(batch, continueText, game.getWorldWidth() / const_larg, game.getWorldHeight() * 0.1f);
    }
    

    public void dispose() {
        font30.dispose();
        font100.dispose();
        font150.dispose();
    }

    public void setHordas(int hordas) {
        this.hordas = hordas;
    }

    public int getHordas() {
        return hordas;
    }
}
