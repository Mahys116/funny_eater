package com.mygdx.eater.actors.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.eater.utils.AssetManager;

/**
 * Created by marat on 07.04.18.
 */

public class PreviousButton extends Button {
    public interface PreviousButtonListener {
        public void onPrevious();
    }
    private Skin skin;
    private PreviousButton.PreviousButtonListener listener;
    public PreviousButton(int x, int y, int width, int height, PreviousButton.PreviousButtonListener listener) {
        TextureAtlas atlas = new AssetManager().getAtlas();
        skin = new Skin();
        skin.addRegions(atlas);
        Button.ButtonStyle style = new Button.ButtonStyle();
        style.up = skin.getDrawable("left_normal");
        style.down = skin.getDrawable("left_pressed");

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
        listener.onPrevious();
    }
}
