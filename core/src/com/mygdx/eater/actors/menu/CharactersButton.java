package com.mygdx.eater.actors.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.eater.utils.AssetManager;

/**
 * Created by marat on 02.02.18.
 */

public class CharactersButton extends BasicButton {

    public interface CharactersMenuListener {
        public void onCharactersMenu();
    }
    private CharactersMenuListener listener;
    public CharactersButton(int x, int y, int width, int height, CharactersMenuListener listener) {
        super();
        Skin skin = new Skin();
        skin.addRegions(atlas);
        ButtonStyle style = new ButtonStyle();
        style.up = skin.getDrawable("menu_normal");
        style.down = skin.getDrawable("menu_pressed");

        setStyle(style);
        setSize(width, height);
        setPosition(x, y);
        this.listener = listener;
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                click.play();
                touched();
            }
           }
        );
    }

    private void touched() {
        listener.onCharactersMenu();
    }

}
