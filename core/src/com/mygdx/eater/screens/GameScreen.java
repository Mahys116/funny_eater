package com.mygdx.eater.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.eater.Eater;
import com.mygdx.eater.stages.GameStage;

/**
 * Created by marat on 28.01.18.
 */

public class GameScreen implements Screen {
    private final GameStage stage;
    private final Sprite backgroundSprite;
    private Eater game;

    public GameScreen(Eater game) {
        this.game = game;
        stage = new GameStage(new ScreenViewport(), game);
        Gdx.input.setInputProcessor(stage);
        Texture backgroundTexture = new Texture("background1.png");
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setSize(stage.getWidth(),stage.getHeight());
    }
    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        game.batch.begin();
        stage.getBatch().begin();
        backgroundSprite.draw(stage.getBatch());
        stage.getBatch().end();
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
