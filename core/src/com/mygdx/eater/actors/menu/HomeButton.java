package com.mygdx.eater.actors.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.eater.utils.PreferencesManager;

/**
 * Created by marat on 04.10.18.
 */

public class HomeButton extends BasicButton {
    public interface HomeButtonListener {
        public void onMain();
    }
    private HomeButton.HomeButtonListener listener;
    public HomeButton(int x, int y, int width, int height, HomeButton.HomeButtonListener listener) {
        super();

        Skin skin = new Skin();
        skin.addRegions(atlas);
        Button.ButtonStyle style = new Button.ButtonStyle();
        style.up = skin.getDrawable("home_normal");
        style.down = skin.getDrawable("home_pressed");

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
        listener.onMain();
    }
}
