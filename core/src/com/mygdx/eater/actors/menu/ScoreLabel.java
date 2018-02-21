package com.mygdx.eater.actors.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.eater.utils.AssetManager;

/**
 * Created by marat on 21.02.18.
 */

public class ScoreLabel extends Actor {
    private BitmapFont font;
    private int score;

    public ScoreLabel(int x, int y) {
        setPosition(x, y);
        font = AssetManager.getFont();
        font.getData().setScale(4);
        font.setColor(Color.BLACK);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        font.draw(batch, String.format("%d", score), getX(), getY());
    }

    public void setScore(int newScore) {
        score = newScore;
    }

}
