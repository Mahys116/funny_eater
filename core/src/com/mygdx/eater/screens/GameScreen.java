package com.mygdx.eater.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.eater.Eater;
import com.mygdx.eater.stages.GameStage;

/**
 * Created by marat on 28.01.18.
 */

public class GameScreen implements Screen {
    private final GameStage stage;
    private Eater game;

    public GameScreen(Eater game) {
        this.game = game;
        stage = new GameStage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
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
}
