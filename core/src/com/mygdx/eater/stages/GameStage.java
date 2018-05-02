package com.mygdx.eater.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.eater.Eater;
import com.mygdx.eater.actors.Character;
import com.mygdx.eater.actors.Food;
import com.mygdx.eater.actors.TutorialHand;
import com.mygdx.eater.actors.menu.CharactersButton;
import com.mygdx.eater.actors.menu.HungerLevel;
import com.mygdx.eater.actors.menu.Label;
import com.mygdx.eater.actors.menu.NewGameButton;
import com.mygdx.eater.actors.menu.PauseButton;
import com.mygdx.eater.actors.menu.ResumeButton;
import com.mygdx.eater.actors.menu.ScoreLabel;
import com.mygdx.eater.screens.CharacterScreen;
import com.mygdx.eater.screens.GameScreen;
import com.mygdx.eater.screens.MenuScreen;
import com.mygdx.eater.utils.GameState;
import com.mygdx.eater.utils.PreferencesManager;

public class GameStage extends Stage {
    private static final int HUNGER_MAX = 10;
    private Eater game;
    private Character character;
    private PauseButton btn_pause;
    private ResumeButton btn_resume;
    private ScoreLabel lbl_score;
    private Label lbl_game_paused;
    private float food_delta;
    private int score;
    private float hunger;
    private HungerLevel hunger_level;
    private CharactersButton btn_character;
    private NewGameButton btn_new_game;
    private Label lbl_game_end;
    private Label lbl_game_end_highscore;
    private Label lbl_game_end_score;
    private int rotation_coef;
    public float rotation;
    private TutorialHand hand;

    public GameStage(ScreenViewport screen, Eater game) {
        super(screen);
        this.game = game;
        initGameParams();
        createGameElements();
        showGameMenu();
        rotation = 0;
        rotation_coef = 1;
    }

    private void showGameMenu() {
        btn_pause.setVisible(true);

        btn_resume.setVisible(false);
        lbl_game_paused.setVisible(false);

        btn_character.setVisible(false);
        btn_new_game.setVisible(false);
        lbl_game_end.setVisible(false);
        lbl_game_end_highscore.setVisible(false);
        lbl_game_end_score.setVisible(false);
    }

    private void showPauseMenu() {
        btn_pause.setVisible(false);

        btn_resume.setVisible(true);
        lbl_game_paused.setVisible(true);

        btn_character.setVisible(false);
        btn_new_game.setVisible(false);
        lbl_game_end.setVisible(false);
        lbl_game_end_highscore.setVisible(false);
        lbl_game_end_score.setVisible(false);
    }

    private void  createGameOverMenu() {
        btn_pause.setVisible(false);

        btn_resume.setVisible(false);
        lbl_game_paused.setVisible(false);

        btn_character.setVisible(true);
        btn_new_game.setVisible(true);
        lbl_game_end.setVisible(true);
        lbl_game_end_highscore.setVisible(true);
        lbl_game_end_score.setVisible(true);

        int high_score = PreferencesManager.getHighScore();
        lbl_game_end_score.setText("Score: "+ String.format("%d", score));
        lbl_game_end_highscore.setText("Highscore: "+ String.format("%d", high_score));
    }

    private void createGameElements() {
        character = new Character(Gdx.graphics.getWidth()/5);
        addActor(character);

        int size = (int) (getWidth() / 10);

        lbl_score = new ScoreLabel((int) (getWidth()/2), (int) getHeight()*3/4);
        addActor(lbl_score);
        hunger_level = new HungerLevel(getWidth()/4, getHeight()-size-10, getWidth()/2, size, hunger);
        addActor(hunger_level);

        // Menus
        btn_pause = new PauseButton((int) (getWidth() - size), (int) (getHeight() - size), size, size, new GameStage.PauseButtonListener());

        lbl_game_paused = new Label((int) (getWidth()/2-size), (int) (getHeight()/2+size), "Paused");
        btn_resume = new ResumeButton((int) (getWidth()/2), (int) (getHeight()/2), size, size, new GameStage.ResumeButtonListener());

        lbl_game_end = new Label((int) (getWidth()/2-size), (int) (getHeight()/2+size), "Game Over");
        btn_new_game = new NewGameButton((int) (getWidth()/2 - size), 0, size*2, size*2, new GameStage.NewGameButtonListener());
        btn_character = new CharactersButton(size/2, 0, size, size, new GameStage.CharactersMenuButtonListener());
        lbl_game_end_score = new Label((int) (getWidth()/2-size), (int) (getHeight()/2), "");
        lbl_game_end_highscore = new Label((int) (getWidth()/2-size), (int) (getHeight()/2-size), "Highscore:");

        addActor(lbl_game_paused);
        addActor(btn_pause);
        addActor(btn_resume);
        addActor(btn_new_game);
        addActor(btn_character);
        addActor(lbl_game_end);
        addActor(lbl_game_end_score);
        addActor(lbl_game_end_highscore);

        // Tutorial
        hand = new TutorialHand(size, getWidth()/2,getHeight()/6, character);
        addActor(hand);

    }

    private void initGameParams() {
        food_delta = 0;
        score = 0;
        hunger = 7;
        GameState.getInstance().init();
        GameState.getInstance().setSpeed(200);
    }

    @Override
    public void draw() {
        super.draw();
        getBatch().begin();
        character.draw_top(getBatch());
        hand.draw(getBatch(),0);
        getBatch().end();
    }

    @Override
    public void act(float delta) {
        if (GameState.getInstance().isPaused()) return;
        super.act(delta);
        if (GameState.getInstance().isInit()) return;
        food_delta += delta;
        hunger -= delta*1;
        if (food_delta > ((getWidth()/8)/GameState.getInstance().getSpeed())) {
            createFood();
            food_delta = 0;
            GameState.getInstance().incSpeed(1);
        }
        if (hunger <= 0) {
            GameState.getInstance().end();
            int high_score = PreferencesManager.getHighScore();
            if (high_score < score) PreferencesManager.setHighScore(score);
            createGameOverMenu();
        }
        lbl_score.setScore(score);
        hunger_level.setLevel(hunger);
        rotateFood(delta);

    }

    private void createFood() {
        Food food = new Food((int) getWidth()/10,(int) (Gdx.graphics.getWidth()*2/5-getWidth()/10), character, new GameStage.IncrementScoreWrapper(), this);
        addActor(food);
    }

    private void rotateFood(float delta) {
        rotation = rotation + delta*50*rotation_coef;
        if (rotation > 20) rotation_coef = -1;
        if (rotation < -20) rotation_coef = 1;
    }

    public float getRotation(){
        return rotation;
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
//            if (hungerRate > 0) score += hungerRate;
            score += 1;
        }
    }


    private class NewGameButtonListener implements NewGameButton.NewGameListener {
        public void onStart() {
            initGameParams();
            showGameMenu();
        }
    }

    private class CharactersMenuButtonListener implements CharactersButton.CharactersMenuListener {
        @Override
        public void onCharactersMenu() {
            game.setScreen(new CharacterScreen(game));
            dispose();
        }
    }

}
