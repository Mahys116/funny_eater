package com.mygdx.eater.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by marat on 02.02.18.
 */

public class AssetManager {

    public static TextureAtlas getAtlas() {
        TextureAtlas atlas = new TextureAtlas("eat.atlas");
        return atlas;
    }

    public static TextureAtlas getFace() {
        TextureAtlas character = new TextureAtlas("character.atlas");
        return character;
    }

}
