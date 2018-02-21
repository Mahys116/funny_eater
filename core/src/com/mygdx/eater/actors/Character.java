package com.mygdx.eater.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.eater.utils.AssetManager;
import com.mygdx.eater.utils.GameState;

/**
 * Created by marat on 05.02.18.
 */

public class Character extends Actor {
    private final float vertical_position;
    int size;
    final TextureRegion face_top;
    final TextureRegion face_bottom;
    final TextureRegion face_middle;
    final Rectangle mouth;
    final Rectangle tooth_l;
    final Rectangle tooth_r;
    float x;
    float y;



    public Character(int characterSize) {
        TextureAtlas atlas = new AssetManager().getFace();
        Skin skin = new Skin();
        skin.addRegions(atlas);

        face_top = skin.getRegion("face_top");
        face_middle = skin.getRegion("face_middle");
        face_bottom = skin.getRegion("face_bottom50");
        size = characterSize;
        vertical_position = size*2;

        mouth = new Rectangle();
        mouth.setSize(size-2, size/3);
        mouth.setPosition(getX()-size/2+1, getY());

        tooth_l = new Rectangle();
        tooth_l.setSize( 1, size/2);
        tooth_l.setPosition(getX()-size/2, getY());

        tooth_r = new Rectangle();
        tooth_r.setSize( 1, size/2);
        tooth_r.setPosition(getX()+size/2, getY());

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(face_middle, getX()-size/2, getY(), size,vertical_position-getY());
    }

    public void draw_top(Batch batch){
        batch.draw(face_bottom,getX()-size/2,(float) getY(), size, size/2);
        batch.draw(face_top, getX()-size/2,vertical_position, size, size/2);
    }

    @Override
    public void act(float delta) {
        if(Gdx.input.isTouched()) {
            if ( !GameState.getInstance().isPaused() &&
                    (Gdx.graphics.getHeight() - Gdx.input.getY()) <= (vertical_position - size/2)) {
                x = Gdx.input.getX();
                y = Gdx.graphics.getHeight() - Gdx.input.getY();
                mouth.setPosition(getX()-size/2+1, getY());
                tooth_l.setPosition(getX()-size/2, getY());
                tooth_r.setPosition(getX()+size/2, getY());
            }
        }

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
