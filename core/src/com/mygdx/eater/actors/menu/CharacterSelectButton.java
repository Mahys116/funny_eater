package com.mygdx.eater.actors.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.eater.utils.AssetManager;

public class CharacterSelectButton extends Button {
    public interface ChoseCharacterListener {
        public void onChose();
    }
    private Skin skin;
    private CharacterSelectButton.ChoseCharacterListener listener;
    public CharacterSelectButton(int x, int y, int width, int height, CharacterSelectButton.ChoseCharacterListener listener) {
        TextureAtlas atlas = AssetManager.getAtlas();
        skin = new Skin();
        skin.addRegions(atlas);
        Button.ButtonStyle style = new Button.ButtonStyle();
        style.up = skin.getDrawable("chose");
        style.down = skin.getDrawable("chose");

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
        listener.onChose();
    }
}
