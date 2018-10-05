package com.mygdx.eater.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.eater.Eater;
import com.mygdx.eater.stages.GameStage;
import com.mygdx.eater.utils.GameState;

public class GameScreen implements Screen {
    public final GameStage stage;
    private Eater game;

    public GameScreen(Eater game) {
        this.game = game;
        stage = new GameStage(new ScreenViewport(), game);
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
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
        if (GameState.getInstance().isGameRunning()) {
            stage.showPauseMenu();
            GameState.getInstance().pause();
        }
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
}
