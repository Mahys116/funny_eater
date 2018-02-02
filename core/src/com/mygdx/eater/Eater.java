package com.mygdx.eater;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.eater.screens.MenuScreen;
import com.mygdx.eater.utils.AssetManager;

public class Eater extends Game {
	public SpriteBatch batch;
	BitmapFont font;
	TextureAtlas atlas;
	public Skin skin;


	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.getData().setScale(4);
		atlas = new AssetManager().getAtlas();
		skin = new Skin();
		skin.addRegions(atlas);
		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
