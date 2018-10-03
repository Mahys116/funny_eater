package com.mygdx.eater.actors.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.eater.actors.Character;
import com.mygdx.eater.utils.AssetManager;

public class CharacterView extends Character {
    private final Skin skin;
    private final TextureAtlas atlas;
    private String character_name;

    public CharacterView(int characterSize, String name, float position) {
        super(characterSize);
        character_name = name;
        atlas = AssetManager.getFace();
        skin = new Skin();
        skin.addRegions(atlas);

        face_top = skin.getRegion(name + "_top");
        face_middle = skin.getRegion(name + "_middle");
        face_bottom = skin.getRegion(name + "_bottom");
        size = characterSize;
        vertical_position = size*9/4;
        x = position;
        y = vertical_position - size;
    }

    @Override
    public void act(float delta) {
    }

    public void draw(Batch batch, float parentAlpha) {
        if (character_name.equals("face")) {
            batch.draw(face_bottom,getX(), vertical_position-size*54/147, size, size*54/147);
            batch.draw(face_top, getX()-size*45/200,vertical_position, size*200/147, size*239/147);
        } else if (character_name.equals("vegan")) {
            batch.draw(face_bottom, getX(), vertical_position-size*77/200, size, size*77/200);
            batch.draw(face_top, getX()-size*32/200,vertical_position, size*243/200, size*286/200);

        } else if (character_name.equals("pirate")) {
            batch.draw(face_bottom, getX(), vertical_position-size*80/200, size, size*80/200);
            batch.draw(face_top, getX()-size*159/200,vertical_position, size*462/200, size*358/200);

        } else if (character_name.equals("skeleton")) {
            batch.draw(face_bottom, getX(), vertical_position-size*65/200, size, size*79/200);
            batch.draw(face_top, getX()-size*25/200,vertical_position, size*251/200, size*253/200);

        } else {
            batch.draw(face_bottom, getX(), vertical_position-size/2, size, size/2);
            batch.draw(face_top, getX()-size*45/200, vertical_position, size*200/147, size*239/147);
        }
    }

    public void setCharacter(String name) {
        character_name = name;
        face_middle = skin.getRegion(name + "_middle");
        face_top = skin.getRegion(name + "_top");
        face_bottom = skin.getRegion(name + "_bottom");
    }

    @Override
    public boolean remove() {
        skin.dispose();
        atlas.dispose();
        return super.remove();
    }
}

