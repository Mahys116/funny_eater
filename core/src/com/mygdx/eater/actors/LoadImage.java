package com.mygdx.eater.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LoadImage extends Actor {
    private final Sprite box;

    public LoadImage(float x, float y, int size) {
        Texture texture = new Texture("giggle_box.png");

        box = new Sprite(texture);
        setPosition(x,y);
        setSize(size,size);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(box, getX(), getY(), getWidth(), getHeight());
    }
}
