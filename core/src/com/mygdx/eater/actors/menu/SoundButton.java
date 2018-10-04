package com.mygdx.eater.actors.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.eater.Eater;
import com.mygdx.eater.utils.PreferencesManager;

/**
 * Created by marat on 04.10.18.
 */

public class SoundButton extends BasicButton {
    private final ButtonStyle style_on;
    private final ButtonStyle style_off;
    private Game game;

    public interface SoundButtonListener {
        public void onSoundButton();
    }
    private SoundButton.SoundButtonListener listener;

    public SoundButton(int x, int y, int width, int height, final Eater game) {
        super();
        this.game = game;
        Skin skin = new Skin();
        skin.addRegions(atlas);
        style_on = new ButtonStyle();
        style_on.up = skin.getDrawable("sound_normal");
        style_on.down = skin.getDrawable("sound_pressed");

        style_off = new ButtonStyle();
        style_off.up = skin.getDrawable("soundoff_normal");
        style_off.down = skin.getDrawable("soundoff_pressed");

        setSize(width, height);
        setPosition(x, y);
        if (PreferencesManager.getSound()) {
            setStyle(style_off);
        } else {
            setStyle(style_on);
        }


        this.listener = listener;
        addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                        if (PreferencesManager.getSound()) {click.play();}
                        if (PreferencesManager.switchSound())  {
                            setStyle(style_off);
                            game.music.play();
                        } else {
                            setStyle(style_on);
                            game.music.stop();
                        }
                    }

                }
        );
    }

}
