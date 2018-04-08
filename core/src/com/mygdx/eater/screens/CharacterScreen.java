package com.mygdx.eater.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.eater.Eater;
import com.mygdx.eater.actors.menu.CharacterSelectButton;
import com.mygdx.eater.actors.menu.CharacterView;
import com.mygdx.eater.actors.menu.MainMenuButton;
import com.mygdx.eater.actors.menu.NewGameButton;
import com.mygdx.eater.actors.menu.NextButton;
import com.mygdx.eater.actors.menu.PreviousButton;
import com.mygdx.eater.utils.Constants;
import com.mygdx.eater.utils.PreferencesManager;

import java.lang.reflect.Array;

public class CharacterScreen implements Screen {
    private final Eater game;
    private final Stage stage;

    private String current_name;
    private String[] characters;
    private int index;
    private CharacterView face;


    public CharacterScreen(final Eater game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        int size = (int) (stage.getWidth()/10);

        MainMenuButton btn_main_menu = new MainMenuButton((int) (stage.getWidth()- size), (int) (stage.getHeight() - size), size, size, new CharacterScreen.MainMenuButtonListener());
        NextButton btn_next = new NextButton((int) (stage.getWidth() - size), 0, size, size, new NextButtonListener());
        PreviousButton btn_prev = new PreviousButton(0, 0, size, size, new PreviousButtonListener());
        CharacterSelectButton btn_select = new CharacterSelectButton(size, 0, (int) (stage.getWidth() - size*2), size, new CharacterScreen.ChoseCharacterListener());

        stage.addActor(btn_main_menu);
        stage.addActor(btn_next);
        stage.addActor(btn_prev);
        stage.addActor(btn_select);

        current_name = PreferencesManager.getCharacterName();
        characters = Constants.getCharacters();
        index = java.util.Arrays.asList(characters).indexOf(current_name);

        face = new CharacterView(size*2, current_name, stage.getWidth()/4);
        stage.addActor(face);
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

    public class MainMenuButtonListener implements MainMenuButton.MainMenuListener {
        public void onMain() {
            game.setScreen(new MenuScreen(game));
            dispose();
        }
    }

    public class NextButtonListener implements NextButton.NextButtonListener {
        public void onNext() {
            index = (index + 1) % characters.length;
            current_name = characters[index];
            face.setCharacter(current_name);
//            Gdx.app.log("index", String.format("%d", index));
//            Gdx.app.log("name", current_name);
        }
    }

    public class PreviousButtonListener implements PreviousButton.PreviousButtonListener {
        public void onPrevious() {
            index = (index - 1);
            if (index < 0) index = characters.length - 1;
            current_name = characters[index];
            face.setCharacter(current_name);
//            Gdx.app.log("index", String.format("%d", index));
//            Gdx.app.log("name", current_name);
        }
    }

    public class ChoseCharacterListener implements CharacterSelectButton.ChoseCharacterListener {
        public void onChose() {
            PreferencesManager.setCharacterName(current_name);
        }
    }

}
