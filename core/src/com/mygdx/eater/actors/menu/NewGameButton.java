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

public class NewGameButton extends Button {
    public interface NewGameListener {
        public void onStart();
    }
    private Skin skin;
    private NewGameListener listener;
    public NewGameButton(int x, int y, int width, int height, NewGameListener listener) {
        TextureAtlas atlas = new AssetManager().getAtlas();
        skin = new Skin();
        skin.addRegions(atlas);
        ButtonStyle style = new ButtonStyle();
        style.up = skin.getDrawable("play_normal");
        style.down = skin.getDrawable("play_pressed");

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
        listener.onStart();
    }
}
