package com.mygdx.eater.actors.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.eater.utils.PreferencesManager;

public class NextButton extends BasicButton {
    public interface NextButtonListener {
        public void onNext();
    }
    private NextButton.NextButtonListener listener;
    public NextButton(int x, int y, int width, int height, NextButton.NextButtonListener listener) {
        super();
        Skin skin = new Skin();
        skin.addRegions(atlas);
        Button.ButtonStyle style = new Button.ButtonStyle();
        style.up = skin.getDrawable("right_normal");
        style.down = skin.getDrawable("right_pressed");

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
        listener.onNext();
    }
}
