package com.mygdx.eater.actors.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.eater.utils.AssetManager;

/**
 * Created by marat on 02.10.18.
 */

public class Background extends Actor {
    private final Sprite backgroundSprite;

    public Background (float width, float height) {
        setSize(width,height);
        setPosition(0,0);

        Texture backgroundTexture = new Texture("background1.png");
        backgroundSprite = new Sprite(backgroundTexture);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(backgroundSprite, getX(), getY(), getWidth(), getHeight());
    }
}
