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
import com.mygdx.eater.utils.PreferencesManager;


public class Character extends Actor {
    protected  float vertical_position;
    protected int size;
    protected TextureRegion face_top;
    protected TextureRegion face_bottom;
    protected TextureRegion face_middle;
    final Rectangle mouth;
    final Rectangle tooth_l;
    final Rectangle tooth_r;
    protected float x;
    protected float y;
    protected String name;

    public Character(int characterSize) {
        name = PreferencesManager.getCharacterName();
        TextureAtlas atlas = AssetManager.getFace();
        Skin skin = new Skin();
        skin.addRegions(atlas);

        face_top = skin.getRegion(name+"_top");
        face_middle = skin.getRegion(name+"_middle");
        face_bottom = skin.getRegion(name+"_bottom");
        size = characterSize;
        vertical_position = size*9/4;
        x = Gdx.graphics.getWidth()/2;
        y = vertical_position - size;

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
        batch.draw(face_middle, getX()-size/2, getY()+size/4, size,vertical_position-getY());
    }

    public void draw_top(Batch batch){
        if (name.equals("face")) {
            batch.draw(face_bottom,getX()-size/2, getY(), size, size/2);
            batch.draw(face_top, getX()-size/2-size*45/200, vertical_position, size*200/147, size*239/147);
        } else if (name.equals("vegan")) {
            batch.draw(face_bottom, getX()-size/2, getY(), size, size*77/200);
            batch.draw(face_top, getX()-size/2-size*32/200,vertical_position, size*243/200, size*286/200);

        } else if (name.equals("pirate")) {
            batch.draw(face_bottom, getX()-size/2, getY(), size, size*80/200);
            batch.draw(face_top, getX()-size/2-size*159/200,vertical_position, size*462/200, size*358/200);

        } else if (name.equals("skeleton")) {
            batch.draw(face_bottom, getX()-size/2, getY(), size, size*79/200);
            batch.draw(face_top, getX()-size/2-size*25/200,vertical_position, size*251/200, size*253/200);

        } else {
            batch.draw(face_middle, getX(), getY()+size/2, size,vertical_position-getY());
            batch.draw(face_bottom,getX(), getY()+size/2, size, size/2);
            batch.draw(face_top, getX()-size*45/200,vertical_position, size*200/147, size*239/147);
        }
    }

    @Override
    public void act(float delta) {
        if(Gdx.input.isTouched()) {
            if ( !GameState.getInstance().isPaused() &&
                    (Gdx.graphics.getHeight() - Gdx.input.getY()) <= (vertical_position - size/2)) {
                x = Gdx.input.getX();
                y = Gdx.graphics.getHeight() - Gdx.input.getY();
                moveTo(x, y);
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


    public void moveTo(float x, float y) {
        this.x = x;
        this.y = y;
        mouth.setPosition(x-size/2+1, y);
        tooth_l.setPosition(x-size/2, y);
        tooth_r.setPosition(x+size/2, y);
    }
}
