package com.mygdx.eater.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.eater.Eater;
import com.mygdx.eater.actors.LoadImage;
import com.mygdx.eater.actors.menu.Label;

public class LoadScreen implements Screen {
    private final Eater game;
    private final Stage stage;
    private final int size;
    private final Label lbl_name;
    private boolean init;

    public LoadScreen(final Eater game) {
        init = true;
        this.game = game;
        stage = new Stage(new ScreenViewport());
        size = (int) (stage.getWidth()/4);

        lbl_name = new Label(0, (int) (stage.getHeight()/2-size), "Giggle Box", (int) (stage.getWidth()/15));
        lbl_name.setColor(Color.WHITE);
        lbl_name.setX(stage.getWidth()/2-lbl_name.getWidth()/2);

        LoadImage label = new LoadImage(stage.getWidth()/2-size/2, stage.getHeight()/2-size/2, size);
        stage.addActor(label);
        stage.addActor(lbl_name);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        stage.draw();
        game.batch.end();
        if (init) {
            game.onLoadInit();
            init = false;
        }

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
