package com.mygdx.eater.actors.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.eater.actors.Character;
import com.mygdx.eater.utils.AssetManager;

public class CharacterView extends Character {
    private final Skin skin;

    public CharacterView(int characterSize, String name, float position) {
        super(characterSize);
        TextureAtlas atlas = AssetManager.getFace();
        skin = new Skin();
        skin.addRegions(atlas);

        face_top = skin.getRegion(name + "_top");
        face_middle = skin.getRegion(name + "_middle");
        face_bottom = skin.getRegion(name + "_bottom");
        size = characterSize;
        vertical_position = size*2;
        x = position;
        y = vertical_position - size;

    }

    @Override
    public void act(float delta) {
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.draw(face_bottom, getX(), vertical_position-size, size, size/2);
        batch.draw(face_top, getX(),vertical_position, size, size);
    }

    public void setCharacter(String name) {
        face_top = skin.getRegion(name + "_top");
        face_bottom = skin.getRegion(name + "_bottom");
    }
}
