package com.mygdx.eater.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.mygdx.eater.utils.AssetManager;

/**
 * Created by marat on 05.02.18.
 */

public class Character extends Actor {
    private final float vertical_position;
    int size;
    final TextureRegion face_top;
    final TextureRegion face_bottom;
    final TextureRegion face_middle;
    float x;
    float y;

    public Character() {
        TextureAtlas atlas = new AssetManager().getFace();
        Skin skin = new Skin();
        skin.addRegions(atlas);

        face_top = skin.getRegion("face_top");
        face_middle = skin.getRegion("face_middle");
        face_bottom = skin.getRegion("face_bottom50");
        size = Gdx.graphics.getWidth()/5;
        vertical_position = size*2;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(Gdx.input.isTouched()) {
            if ((Gdx.graphics.getHeight() - Gdx.input.getY()) <= (vertical_position - size/2)) {
                x = Gdx.input.getX();
                y = Gdx.graphics.getHeight() - Gdx.input.getY();
            }
        }
        batch.draw(face_top, getX()-size/2,vertical_position, size, size/2);
        batch.draw(face_middle, getX()-size/2, getY(), size,vertical_position-getY());
        batch.draw(face_bottom,getX()-size/2,(float) getY(), size, size/2);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }
}
