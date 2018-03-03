package com.mygdx.eater.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.eater.Eater;
import com.mygdx.eater.actors.menu.CharactersButton;
import com.mygdx.eater.actors.menu.Label;
import com.mygdx.eater.actors.menu.NewGameButton;

/**
 * Created by marat on 25.01.18.
 */

public class MenuScreen implements Screen {
    private Eater game;

    Stage stage;
    private Button btn_new_game;
    private Button.ButtonStyle button_style_characters;
    private Button button_characters;

    public MenuScreen(final Eater game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Preferences prefs = Gdx.app.getPreferences("preferences");
        int high_score = prefs.getInteger("high_score", 0);
        Gdx.app.log("score", String.format("%d", high_score));
        Label lbl_score = new Label((int) (stage.getWidth()/2), (int) (stage.getHeight()/2), String.format("%d", high_score));
        stage.addActor(lbl_score);
        createButtons();
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
        stage.dispose();
    }


    private void createButtons() {
        btn_new_game = new NewGameButton((int) (stage.getWidth()/2 - 100), 0, 200, 200, new NewGameButtonListener());
        stage.addActor(btn_new_game);

        button_characters = new CharactersButton(10, 0, 200, 200, new CharactersMenuButtonListener());
        stage.addActor(button_characters);
    }

    public class NewGameButtonListener implements NewGameButton.NewGameListener {
        public void onStart() {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    public class CharactersMenuButtonListener implements CharactersButton.CharactersMenuListener {
        @Override
        public void onCharactersMenu() {
            game.setScreen(new CharacterScreens(game));
            dispose();
        }
    }


}
