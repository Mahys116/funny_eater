package com.mygdx.eater.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by marat on 02.02.18.
 */

public class AssetManager {
    private static TextureAtlas atlas;

    public static TextureAtlas getAtlas() {
        atlas = new TextureAtlas("eat.atlas");
        return atlas;
    }

}
