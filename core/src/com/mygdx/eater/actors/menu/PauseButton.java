package com.mygdx.eater.actors.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.eater.utils.AssetManager;

/**
 * Created by marat on 07.02.18.
 */

public class PauseButton extends BasicButton {
    public interface PauseListener {
        void onPause();
    }
    private PauseButton.PauseListener listener;

    public PauseButton(int x, int y, int width, int height, PauseButton.PauseListener listener) {
        super();
        Skin skin = new Skin();
        skin.addRegions(atlas);
        Button.ButtonStyle style = new Button.ButtonStyle();
        style.up = skin.getDrawable("pause_normal");
        style.down = skin.getDrawable("pause_pressed");

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
        listener.onPause();
    }
}
