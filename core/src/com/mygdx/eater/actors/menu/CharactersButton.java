package com.mygdx.eater.actors.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.eater.utils.AssetManager;

/**
 * Created by marat on 02.02.18.
 */

public class CharactersButton extends Button {
    public interface CharactersMenuListener {
        public void onCharactersMenu();
    }
    private Skin skin;
    private CharactersMenuListener listener;
    public CharactersButton(int x, int y, int width, int height, CharactersMenuListener listener) {
        TextureAtlas atlas = new AssetManager().getAtlas();
        skin = new Skin();
        skin.addRegions(atlas);
        ButtonStyle style = new ButtonStyle();
        style.up = skin.getDrawable("menu");
        style.down = skin.getDrawable("menu");

        setStyle(style);
        setSize(width, height);
        setPosition(x, y);

        this.listener = listener;
        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                touched();
                return true;
            }

           }
        );

    }

    private void touched() {
        listener.onCharactersMenu();
    }
}
