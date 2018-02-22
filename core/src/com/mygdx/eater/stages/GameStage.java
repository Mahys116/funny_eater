package com.mygdx.eater.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.eater.actors.Character;
import com.mygdx.eater.actors.Food;
import com.mygdx.eater.actors.menu.Label;
import com.mygdx.eater.actors.menu.PauseButton;
import com.mygdx.eater.actors.menu.ResumeButton;
import com.mygdx.eater.actors.menu.ScoreLabel;
import com.mygdx.eater.utils.GameState;

public class GameStage extends Stage {
    private static final int HUNGER_MAX = 10;
    private Character character;
    private PauseButton btn_pause;
    private ResumeButton btn_resume;
    private ScoreLabel lbl_score;
    private Label lbl_game_paused;
    private float food_delta;
    private int score;
    private float hunger;

    public GameStage(ScreenViewport screen) {
        super(screen);
        initGameParams();
        createGameElements();
        showGameMenu();
    }

    private void showGameMenu() {
        btn_pause.setVisible(true);

        btn_resume.setVisible(false);
        lbl_game_paused.setVisible(false);
    }

    private void showPauseMenu() {
        btn_pause.setVisible(false);

        btn_resume.setVisible(true);
        lbl_game_paused.setVisible(true);
    }

    private void  createGameOverMenu() {
        Label gameEnd = new Label((int) (getWidth()/2), (int) (getHeight()*3/4), "Game Over");
        addActor(gameEnd);

        btn_pause.setVisible(false);
        btn_resume.setVisible(false);
        lbl_game_paused.setVisible(false);

    }

    private void createGameElements() {
        character = new Character(Gdx.graphics.getWidth()/5);
        addActor(character);

        lbl_score = new ScoreLabel((int) (getWidth()/2), (int) getHeight()/2);
        addActor(lbl_score);

        // Menus
        lbl_game_paused = new Label((int) (getWidth()/2), (int) (getHeight()*3/4), "Paused");
        btn_pause = new PauseButton((int) (getWidth() - 200), (int) getHeight()-200, 200, 200, new GameStage.PauseButtonListener());

        btn_resume = new ResumeButton((int) (getWidth() - 200), (int) getHeight() - 200, 200, 200, new GameStage.ResumeButtonListener());

        addActor(lbl_game_paused);
        addActor(btn_pause);
        addActor(btn_resume);
    }

    private void initGameParams() {
        food_delta = 0;
        score = 0;
        hunger = 7;
        GameState.getInstance().setSpeed(200);

    }

    @Override
    public void draw() {
        super.draw();
        getBatch().begin();
        character.draw_top(getBatch());
        getBatch().end();
    }

    @Override
    public void act(float delta) {
        if (GameState.getInstance().isPaused()) return;
        super.act(delta);
        food_delta += delta;
        hunger -= delta*1;
        if (food_delta > ((getWidth()/8)/GameState.getInstance().getSpeed())) {
            Food food = new Food((int) getWidth()/10,(int) (Gdx.graphics.getWidth()*2/5-getWidth()/10), character, new GameStage.IncrementScoreWrapper());
            addActor(food);
            food_delta = 0;
            GameState.getInstance().incSpeed(1);
        }
        if (hunger <= 0) {
            GameState.getInstance().end();
            Preferences prefs = Gdx.app.getPreferences("preferences");
            int high_score = prefs.getInteger("high_score", 0);
            if (high_score < score) prefs.putInteger("high_score", score);
            prefs.flush();
            createGameOverMenu();
        }
        lbl_score.setScore(score);
    }

    private class PauseButtonListener implements PauseButton.PauseListener {
        public void onPause() {
            GameState.getInstance().pause();
            showPauseMenu();
        }
    }

    private class ResumeButtonListener implements ResumeButton.ResumeListener {
        public void onResume() {
            GameState.getInstance().resume();
            showGameMenu();
        }
    }

    private  class IncrementScoreWrapper implements Food.IncrementWrapper {
        public void incrementScore(int hungerRate) {
            hunger += hungerRate;
            if (hunger > HUNGER_MAX) { hunger = 10; }
            if (hungerRate > 0) score += hungerRate;
        }
    }

}
