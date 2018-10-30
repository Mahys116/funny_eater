package com.mygdx.eater.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.eater.Eater;
import com.mygdx.eater.actors.menu.Background;
import com.mygdx.eater.actors.menu.CharacterSelectButton;
import com.mygdx.eater.actors.menu.CharacterView;
import com.mygdx.eater.actors.menu.Label;
import com.mygdx.eater.actors.menu.MainMenuButton;
import com.mygdx.eater.actors.menu.NextButton;
import com.mygdx.eater.actors.menu.PreviousButton;
import com.mygdx.eater.actors.menu.Window;
import com.mygdx.eater.utils.Constants;
import com.mygdx.eater.utils.PreferencesManager;
import java.util.ArrayList;

public class CharacterScreen implements Screen {
    private final Eater game;
    private final Background background;
    private final Label lbl_total;
    private final Label lbl_score;
    public Stage stage;
    private String[] character_names;
    private String[] character_descriptions;
    private String[] character_conditions;
    private final CharacterSelectButton btn_select;
    private final Label lbl_character_name;
    private final Label lbl_character_cond;
    private final Label lbl_character_desc;

    private String current_name;
    private String[] characters;
    private ArrayList<String> available_chracters;
    private int index;
    private CharacterView face;


    public CharacterScreen(final Eater game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());

        int size = Constants.getSize(stage.getWidth(), stage.getHeight());

        MainMenuButton btn_main_menu = new MainMenuButton((int) (stage.getWidth()- size*1.5), (int) (stage.getHeight() - size*1.5), size, size, new CharacterScreen.MainMenuButtonListener());
        NextButton btn_next = new NextButton((int) (stage.getWidth()-size*1.75), (int) (size*0.25),(int) (size*1.5), (int) (size*1.5), new NextButtonListener());
        PreviousButton btn_prev = new PreviousButton((int) (size*0.25),(int) (size*0.25),(int) (size*1.5), (int) (size*1.5), new PreviousButtonListener());
        btn_select = new CharacterSelectButton((int) (stage.getWidth()/2 - size * 1.614), 0, (int) (size * 1.614*2), size*2, new CharacterScreen.ChoseCharacterListener());

        lbl_score = new Label(size, (int) (stage.getHeight()-3*size),"BEST: "+ String.format("%d", PreferencesManager.getHighScore()),size*3/4);
        lbl_total = new Label(size, (int) (stage.getHeight()-4*size),"TOTAL: "+ String.format("%d", PreferencesManager.getTotalScore()),size*3/4);

        lbl_score.setX(stage.getWidth()/2-lbl_score.getWidth()/2);
        lbl_total.setX(stage.getWidth()/2-lbl_total.getWidth()/2);
        lbl_character_name = new Label(size,(int) (stage.getHeight()-5*size),"",size);
        lbl_character_cond = new Label(size,(int) (stage.getHeight()-6*size),"",size*2/3);
        lbl_character_desc = new Label(size,(int) (stage.getHeight()-7*size),"",size/2);

        background = new Background(stage.getWidth(), stage.getHeight());
        stage.addActor(background);
        Window window = new Window(size / 2, (int) (2.5*size), (int) (stage.getWidth() - size), (int) (stage.getHeight() - 5*size));
        stage.addActor(window);

        stage.addActor(btn_main_menu);
        stage.addActor(btn_next);
        stage.addActor(btn_prev);
        stage.addActor(btn_select);

        lbl_score.setColor(Color.WHITE);
        lbl_total.setColor(Color.WHITE);
        lbl_character_name.setColor(Color.WHITE);
        lbl_character_cond.setColor(Color.WHITE);
        lbl_character_desc.setColor(Color.WHITE);
        stage.addActor(lbl_score);
        stage.addActor(lbl_total);
        stage.addActor(lbl_character_name);
        stage.addActor(lbl_character_cond);
        stage.addActor(lbl_character_desc);

        initParams();
        face = new CharacterView(size*2, current_name, stage.getWidth()/2-size);
        stage.addActor(face);
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
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

    private void setCharacterDesc() {
        lbl_character_name.setText(character_names[index]);
        lbl_character_desc.setText(character_descriptions[index]);
        lbl_character_cond.setText(character_conditions[index]);

        lbl_character_name.setX(stage.getWidth()/2-lbl_character_name.getWidth()/2);
        lbl_character_cond.setX(stage.getWidth()/2-lbl_character_cond.getWidth()/2);
    }

    public class MainMenuButtonListener implements MainMenuButton.MainMenuListener {
        public void onMain() {
            game.setScreen("menu");
        }
    }

    public class NextButtonListener implements NextButton.NextButtonListener {
        public void onNext() {
            index = (index + 1) % characters.length;
            current_name = characters[index];
            face.setCharacter(current_name);
            btn_select.setDisabled(!available_chracters.contains(current_name));
            setCharacterDesc();
            if (available_chracters.contains(current_name)) {
                lbl_character_cond.setColor(Color.WHITE);
            } else {
                lbl_character_cond.setColor(new Color(0xdf1b22FF));
            }
            updateBackground();
        }
    }

    public class PreviousButtonListener implements PreviousButton.PreviousButtonListener {
        public void onPrevious() {
            index = (index - 1);
            if (index < 0) index = characters.length - 1;
            current_name = characters[index];
            face.setCharacter(current_name);
            btn_select.setDisabled(!available_chracters.contains(current_name));
//            btn_select.setDisabled(false);
            setCharacterDesc();
            if (available_chracters.contains(current_name)) {
                lbl_character_cond.setColor(Color.WHITE);
            } else {
                lbl_character_cond.setColor(new Color(0xdf1b22FF));
            }
            updateBackground();
        }
    }

    public class ChoseCharacterListener implements CharacterSelectButton.ChoseCharacterListener {
        public void onChose() {
            if (available_chracters.contains(current_name)) {
                PreferencesManager.setCharacterName(current_name);
                game.setScreen("menu");
            }
        }
    }

    private void updateBackground() {
        if (current_name.equals("face") || current_name.equals("vegan")) {
            background.setBackgroundSprite(0);
        } else if (current_name.equals("pirate")) {
            background.setBackgroundSprite(1);
        } else if (current_name.equals("skeleton")) {
            background.setBackgroundSprite(2);
        }
    }
    public void initParams() {
        current_name = PreferencesManager.getCharacterName();
        characters = Constants.getCharacters();
        character_names = Constants.getCharactersFullName();
        character_descriptions = Constants.getCharactersDescriptions();
        character_conditions = Constants.getCharactersConditions();
        available_chracters = PreferencesManager.updateAvailableCharacters();
        index = java.util.Arrays.asList(characters).indexOf(current_name);
        setCharacterDesc();

        lbl_score.setText(String.format("BEST: %d", PreferencesManager.getHighScore()));
        lbl_total.setText(String.format("TOTAL: %d", PreferencesManager.getTotalScore()));
        updateBackground();
    }
}
