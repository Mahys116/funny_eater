package com.mygdx.eater.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.eater.actors.Character;
import com.mygdx.eater.actors.Food;
import com.mygdx.eater.actors.menu.PauseButton;
import com.mygdx.eater.actors.menu.ResumeButton;
import com.mygdx.eater.utils.GameState;

import static com.badlogic.gdx.utils.TimeUtils.millis;


public class GameStage extends Stage {
    private Character character;
    private PauseButton btn_pause;
    private ResumeButton btn_resume;
    private float food_delta;

    public GameStage(ScreenViewport screen) {
        super(screen);
        createGameMenu();
        createGame();
    }

    private void createGameMenu() {
        btn_pause = new PauseButton((int) (getWidth() - 200), (int) getHeight()-200, 200, 200, new GameStage.PauseButtonListener());
        addActor(btn_pause);
    }

    private void createPauseMenu() {
        btn_resume = new ResumeButton((int) (getWidth() - 200), (int) getHeight() - 200, 200, 200, new GameStage.ResumeButtonListener());
        addActor(btn_resume);
    }

    private void createGame() {
        character = new Character(Gdx.graphics.getWidth()/5);
        addActor(character);

        food_delta = 0;
        GameState.getInstance().setSpeed(getWidth()/8);
    }

    private void clearGameMenu() {
        btn_pause.remove();
    }

    private void clearPauseMenu() {
        btn_resume.remove();
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
        super.act(delta);
        if (GameState.getInstance().isPaused()) return;
        food_delta += delta;
        if (food_delta > ((getWidth()/8)/GameState.getInstance().getSpeed())) {
            Food food = new Food((int) getWidth()/10,(int) (Gdx.graphics.getWidth()*2/5-getWidth()/10));
            addActor(food);
            food_delta = 0;
            GameState.getInstance().incSpeed(1);
        }

    }

    private class PauseButtonListener implements PauseButton.PauseListener {
        public void onPause() {
            GameState.getInstance().pause();
            clearGameMenu();
            createPauseMenu();
        }
    }

    private class ResumeButtonListener implements ResumeButton.ResumeListener {
        public void onResume() {
            GameState.getInstance().resume();
            clearPauseMenu();
            createGameMenu();
        }
    }

}
