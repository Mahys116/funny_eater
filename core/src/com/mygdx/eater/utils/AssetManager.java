package com.mygdx.eater.utils;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetManager {

    public static TextureAtlas getAtlas() {
        return new TextureAtlas("eat.atlas");
    }

    public static TextureAtlas getFace() {
        return new TextureAtlas("character.atlas");
    }

    public static TextureAtlas getFood() {
        return new TextureAtlas("food.atlas");
    }

    public static BitmapFont getFont() {
        return new BitmapFont();
    }

}
