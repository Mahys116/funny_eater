package com.mygdx.eater.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

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

    public static BitmapFont getFont(int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("2204.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        return font;
    }

    public static BitmapFont getScoreFont(int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("2204.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 4;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        return font;
    }

}
