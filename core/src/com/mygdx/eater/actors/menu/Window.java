package com.mygdx.eater.actors.menu;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.eater.utils.AssetManager;

/**
 * Created by marat on 02.10.18.
 */

public class Window extends Actor {
    private final TextureRegion background;

    public Window (int x, int y, int width, int height) {
        setSize(width,height);
        setPosition(x,y);

        TextureAtlas atlas = AssetManager.getAtlas();
        Skin skin = new Skin();
        skin.addRegions(atlas);

        background = skin.getRegion("window");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(background, getX(), getY(), getWidth(), getHeight());
    }
}
