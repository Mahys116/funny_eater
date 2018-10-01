package com.mygdx.eater.actors.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.eater.utils.AssetManager;

public class MainMenuButton extends Button {
    public interface MainMenuListener {
        public void onMain();
    }
    private Skin skin;
    private MainMenuButton.MainMenuListener listener;
    public MainMenuButton(int x, int y, int width, int height, MainMenuButton.MainMenuListener listener) {
        TextureAtlas atlas = new AssetManager().getAtlas();
        skin = new Skin();
        skin.addRegions(atlas);
        Button.ButtonStyle style = new Button.ButtonStyle();
        style.up = skin.getDrawable("back_normal");
        style.down = skin.getDrawable("back_pressed");

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
        listener.onMain();
    }
}
