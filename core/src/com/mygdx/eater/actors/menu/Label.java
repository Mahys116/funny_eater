package com.mygdx.eater.actors.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.eater.utils.AssetManager;

/**
 * Created by marat on 21.02.18.
 */

public class Label extends Actor {
    private BitmapFont font;
    private String text;

    public Label(int x, int y, String text) {
        setPosition(x,y);
        font = AssetManager.getFont();
        this.text = text;
        font.getData().setScale(4);
        font.setColor(Color.BLACK);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        font.draw(batch, text, getX(), getY());
    }

}
