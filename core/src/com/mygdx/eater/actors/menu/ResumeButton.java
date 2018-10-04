package com.mygdx.eater.actors.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.eater.utils.PreferencesManager;

public class ResumeButton extends BasicButton {
    public interface ResumeListener {
        public void onResume();
    }
    private ResumeButton.ResumeListener listener;

    public ResumeButton(int x, int y, int width, int height, ResumeButton.ResumeListener listener) {
        super();
        Skin skin = new Skin();
        skin.addRegions(atlas);
        Button.ButtonStyle style = new Button.ButtonStyle();
        style.up = skin.getDrawable("play2_normal");
        style.down = skin.getDrawable("play2_pressed");

        setStyle(style);
        setSize(width, height);
        setPosition(x - width/2, y - height/2);

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
        listener.onResume();
    }
}
