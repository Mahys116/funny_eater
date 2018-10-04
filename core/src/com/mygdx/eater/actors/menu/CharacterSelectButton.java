package com.mygdx.eater.actors.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.eater.utils.PreferencesManager;

public class CharacterSelectButton extends BasicButton {
    public interface ChoseCharacterListener {
        public void onChose();
    }
    private CharacterSelectButton.ChoseCharacterListener listener;
    public CharacterSelectButton(int x, int y, int width, int height, CharacterSelectButton.ChoseCharacterListener listener) {
        super();
        Skin skin = new Skin();
        skin.addRegions(atlas);
        Button.ButtonStyle style = new Button.ButtonStyle();
        style.up = skin.getDrawable("okl_normal");
        style.down = skin.getDrawable("ok_pressed");
        style.disabled = skin.getDrawable("ok_locked");

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
        listener.onChose();
    }
}
