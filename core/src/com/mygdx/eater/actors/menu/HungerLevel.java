package com.mygdx.eater.actors.menu;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.eater.utils.AssetManager;

/**
 * Created by marat on 23.02.18.
 */

public class HungerLevel extends Actor {
    TextureRegion background;
    TextureRegion line;
    float level;

    public HungerLevel(float x, float y, float width, float height, float start_level) {
        setBounds(x,y,width,height);
        TextureAtlas atlas = new AssetManager().getAtlas();
        Skin skin = new Skin();
        skin.addRegions(atlas);
        level = start_level;
        background = skin.getRegion("window");

        line = skin.getRegion("hunger");

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(background, getX(), getY(), getWidth(), getHeight());
        batch.draw(line, getX(),getY(), getWidth()/10 * level, getHeight());
    }

    public void setLevel(float level) {
        if (level < 0) {
            this.level = 0;
        } else {
            this.level = level;
        }

    }
}
