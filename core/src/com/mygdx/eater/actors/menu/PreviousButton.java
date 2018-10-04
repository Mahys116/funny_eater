package com.mygdx.eater.actors.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.eater.utils.PreferencesManager;

/**
 * Created by marat on 07.04.18.
 */

public class PreviousButton extends BasicButton {
    public interface PreviousButtonListener {
        public void onPrevious();
    }

    private PreviousButton.PreviousButtonListener listener;
    public PreviousButton(int x, int y, int width, int height, PreviousButton.PreviousButtonListener listener) {
        super();
        Skin skin = new Skin();
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
                        public void clicked(InputEvent event, float x, float y) {
                            if (PreferencesManager.getSound()) {click.play();}
                            touched();
                        }

                    }
        );

    }

    private void touched() {
        listener.onPrevious();
    }
}
