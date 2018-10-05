package com.mygdx.eater.actors.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Background extends Actor {
    private final Sprite backgroundSprite0;
    private final Sprite backgroundSprite1;
    private final Sprite backgroundSprite2;
    private int number;

    public Background (float width, float height) {
        setSize(width,height);
        setPosition(0,0);
        number = 0;
        Texture backgroundTexture0 = new Texture("background0.png");
        Texture backgroundTexture1 = new Texture("background1.png");
        Texture backgroundTexture2 = new Texture("background2.png");

        backgroundSprite0 = new Sprite(backgroundTexture0);
        backgroundSprite1 = new Sprite(backgroundTexture1);
        backgroundSprite2 = new Sprite(backgroundTexture2);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (number == 0) {
            batch.draw(backgroundSprite0, getX(), getY(), getWidth(), getHeight());
        } else if (number == 1) {
            batch.draw(backgroundSprite1, getX(), getY(), getWidth(), getHeight());
        } else if (number == 2) {
            batch.draw(backgroundSprite2, getX(), getY(), getWidth(), getHeight());
        }
    }

    public void setBackgroundSprite(int number) {
        this.number = number;
    }
}
