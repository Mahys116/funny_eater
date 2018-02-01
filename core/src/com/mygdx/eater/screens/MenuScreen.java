package com.mygdx.eater.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.eater.Eater;

/**
 * Created by marat on 25.01.18.
 */

public class MenuScreen implements Screen {
    private Eater game;

    Stage stage;
    private Button.ButtonStyle button_style_play;
    private Button btn_new_game;
    private Button.ButtonStyle button_style_characters;
    private Button button_characters;

    public MenuScreen(final Eater game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        createButtons();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
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
        stage.dispose();
    }


    private void createButtons() {
        button_style_play = new Button.ButtonStyle();
        button_style_play.up = game.skin.getDrawable("play");
        button_style_play.down = game.skin.getDrawable("play");

        btn_new_game = new Button(button_style_play);
        btn_new_game.setSize(200, 200);
        btn_new_game.setPosition(stage.getWidth()/2-100, 0);
        btn_new_game.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game));
                dispose();
                return true;
            }
        });

        stage.addActor(btn_new_game);

        button_style_characters = new Button.ButtonStyle();
        button_style_characters.up = game.skin.getDrawable("menu");
        button_style_characters.down = game.skin.getDrawable("menu");

        button_characters = new Button(button_style_characters);
        button_characters.setSize(200, 200);
        button_characters.setPosition(10, 0);
        button_characters.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new CharacterScreens(game));
                dispose();
                return true;

            }
        });

        stage.addActor(button_characters);
    }

}
