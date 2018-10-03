package com.mygdx.eater.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.eater.Eater;
import com.mygdx.eater.actors.Character;
import com.mygdx.eater.actors.Food;
import com.mygdx.eater.actors.TutorialHand;
import com.mygdx.eater.actors.menu.Background;
import com.mygdx.eater.actors.menu.CharactersButton;
import com.mygdx.eater.actors.menu.HungerLevel;
import com.mygdx.eater.actors.menu.Label;
import com.mygdx.eater.actors.menu.PauseButton;
import com.mygdx.eater.actors.menu.ResumeButton;
import com.mygdx.eater.actors.menu.ScoreLabel;
import com.mygdx.eater.actors.menu.Window;
import com.mygdx.eater.screens.CharacterScreen;
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
    private ResumeButton btn_new_game;
    private Label lbl_game_end;
    private Label lbl_game_end_highscore;
    private Label lbl_game_end_score;
    private int rotation_coef;
    private float rotation;
    private TutorialHand hand;
    private int size;
    private Window win_pause;
    private Window win_end;

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
        lbl_score.setVisible(true);
        hunger_level.setVisible(true);

        win_pause.setVisible(false);
        btn_resume.setVisible(false);
        lbl_game_paused.setVisible(false);

        win_end.setVisible(false);
        btn_character.setVisible(false);
        btn_new_game.setVisible(false);
        lbl_game_end.setVisible(false);
        lbl_game_end_highscore.setVisible(false);
        lbl_game_end_score.setVisible(false);
    }

    private void showPauseMenu() {
        btn_pause.setVisible(false);
        lbl_score.setVisible(true);
        hunger_level.setVisible(true);

        win_pause.setVisible(true);
        btn_resume.setVisible(true);
        lbl_game_paused.setVisible(true);

        win_end.setVisible(false);
        btn_character.setVisible(false);
        btn_new_game.setVisible(false);
        lbl_game_end.setVisible(false);
        lbl_game_end_highscore.setVisible(false);
        lbl_game_end_score.setVisible(false);
    }

    private void createGameOverMenu() {
        btn_pause.setVisible(false);
        lbl_score.setVisible(false);
        hunger_level.setVisible(false);

        win_pause.setVisible(false);
        btn_resume.setVisible(false);
        lbl_game_paused.setVisible(false);

        win_end.setVisible(true);
        btn_character.setVisible(true);
        btn_new_game.setVisible(true);
        lbl_game_end.setVisible(true);
        lbl_game_end_highscore.setVisible(true);
        lbl_game_end_score.setVisible(true);

        int high_score = PreferencesManager.getHighScore();
        lbl_game_end_score.setText(String.format("SCORE: %d", score));
        lbl_game_end_highscore.setText(String.format("BEST: %d", high_score));;
        lbl_game_end_score.setX(getWidth()/2 - lbl_game_end_score.getWidth()/2);
        lbl_game_end_highscore.setX(getWidth()/2 - lbl_game_end_highscore.getWidth()/2);
    }

    private void createGameElements() {
        Background background = new Background(getWidth(), getHeight());
        addActor(background);

        character = new Character(Gdx.graphics.getWidth()/5);
        addActor(character);

        size = (int) (getWidth() / 10);

        lbl_score = new ScoreLabel((int) (getWidth()/2 - size/2), (int) (getHeight()-size*2.5), size*2);
        addActor(lbl_score);
        hunger_level = new HungerLevel(getWidth()/4, getHeight()-size*5/4, getWidth()/2, size, hunger);
        addActor(hunger_level);

        // Menus
        btn_pause = new PauseButton((int) (getWidth() - size*1.25), (int) (getHeight() - size*1.25), size, size, new GameStage.PauseButtonListener());

        lbl_game_paused = new Label((int) (getWidth()/2-size), (int) (getHeight() - size*5), "PAUSED", size);
        lbl_game_paused.setColor(Color.WHITE);
        lbl_game_paused.setX(getWidth()/2 - lbl_game_paused.getWidth()/2);
        btn_resume = new ResumeButton((int) (getWidth()/2), (int) (getHeight()- size*7), size*2, size*2, new GameStage.ResumeButtonListener());
        win_pause = new Window((int) (lbl_game_paused.getX()-size), (int) (getHeight()- size*8.5), (int) (lbl_game_paused.getWidth()+ size*2), size*4);

        lbl_game_end = new Label((int) (getWidth()/2-size), (int) (getHeight() - size*3), "GAME OVER", size*3/4);
        lbl_game_end.setColor(Color.WHITE);
        lbl_game_end.setX(getWidth()/2 - lbl_game_end.getWidth()/2);

        btn_new_game = new ResumeButton((int) (getWidth()/2), (int) (getHeight() - size*7), size*2, size*2, new GameStage.NewGameButtonListener());
        btn_character = new CharactersButton((int) (getWidth()-size*1.75), (int) (size*0.25),(int) (size*1.5), (int) (size*1.5), new GameStage.CharactersMenuButtonListener());
        lbl_game_end_score = new Label((int) (getWidth()/2-size), (int) (getHeight() - size*4), "", size*3/4);
        lbl_game_end_score.setColor(Color.WHITE);
        lbl_game_end_highscore = new Label((int) (getWidth()/2-size), (int) (getHeight()-size*5), "BEST:", size*3/4);
        lbl_game_end_highscore.setColor(Color.WHITE);
        win_end = new Window((int) (lbl_game_paused.getX()-size), (int) (getHeight()- size*8.5), (int) (lbl_game_paused.getWidth()+ size*2), size*6);

        addActor(win_pause);
        addActor(win_end);
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
            PreferencesManager.incTotalScore(score);
            createGameOverMenu();
        }
        lbl_score.setScore(score);
        hunger_level.setLevel(hunger);
        rotateFood(delta);

    }

    private void createFood() {
        Food food = new Food(size, (Gdx.graphics.getWidth()*2/5-size), character, new GameStage.IncrementScoreWrapper(), this);
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
            score += 1;
        }
    }


    private class NewGameButtonListener implements ResumeButton.ResumeListener {
        public void onResume() {
            initGameParams();
            showGameMenu();
        }
    }

    private class CharactersMenuButtonListener implements CharactersButton.CharactersMenuListener {
        @Override
        public void onCharactersMenu() {
            dispose();
            game.setScreen(new CharacterScreen(game));
        }
    }

}
