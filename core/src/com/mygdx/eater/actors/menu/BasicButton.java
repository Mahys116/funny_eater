package com.mygdx.eater.actors.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.mygdx.eater.utils.AssetManager;

/**
 * Created by marat on 04.10.18.
 */

public class BasicButton extends Button {
    protected final Sound click;
    protected final TextureAtlas atlas;

    public BasicButton() {
        click = Gdx.audio.newSound(Gdx.files.internal("sounds/click.wav"));
        atlas = new AssetManager().getAtlas();

    }

    public boolean remove() {
        click.dispose();
        atlas.dispose();
        return super.remove();
    }
}
