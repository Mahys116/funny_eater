package com.mygdx.eater.actors.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.eater.utils.AssetManager;

/**
 * Created by marat on 21.02.18.
 */

public class Label extends Actor {
    private BitmapFont font;
    private String text;

    public Label(int x, int y, String text, int size) {
        setPosition(x,y);
        font = AssetManager.getFont(size);
        this.text = text;
        font.setColor(Color.BLACK);

        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font,text);
        setWidth(glyphLayout.width);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        font.draw(batch, text, getX(), getY());
    }

    public void setText(String text) {
        this.text = text;
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font,text);
        setWidth(glyphLayout.width);
    }

    public void setColor(Color color) {
        font.setColor(color);
    }

}
