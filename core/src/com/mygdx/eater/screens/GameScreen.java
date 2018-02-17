package com.mygdx.eater.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.eater.Eater;
import com.mygdx.eater.actors.Character;
import com.mygdx.eater.actors.menu.PauseButton;
import com.mygdx.eater.actors.menu.ResumeButton;
import com.mygdx.eater.utils.GameState;

/**
 * Created by marat on 28.01.18.
 */

public class GameScreen implements Screen {
    private final Stage stage;
    private Eater game;

//    buttons:
    private PauseButton btn_pause;
    private ResumeButton btn_resume;

    public GameScreen(Eater game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        createGameMenu();
        createGame();
    }
    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        game.batch.begin();
        stage.draw();
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private void createGameMenu() {
        btn_pause = new PauseButton((int) (stage.getWidth() - 200), (int) stage.getHeight()-200, 200, 200, new PauseButtonListener());
        stage.addActor(btn_pause);
    }

    private void createPauseMenu() {
        btn_resume = new ResumeButton((int) (stage.getWidth() - 200), (int) stage.getHeight() - 200, 200, 200, new ResumeButtonListener());
        stage.addActor(btn_resume);
    }

    private void createGame() {
        Character character = new Character();
        stage.addActor(character);
    }

    private void clearGameMenu() {
        btn_pause.remove();
    }

    private void clearPauseMenu() {
        btn_resume.remove();
    }

    private class PauseButtonListener implements PauseButton.PauseListener {
        public void onPause() {
            GameState.getInstance().pause();
            clearGameMenu();
            createPauseMenu();
            dispose();
        }
    }

    private class ResumeButtonListener implements ResumeButton.ResumeListener {
        public void onResume() {
            GameState.getInstance().resume();
            clearPauseMenu();
            createGameMenu();
            dispose();
        }
    }

}
