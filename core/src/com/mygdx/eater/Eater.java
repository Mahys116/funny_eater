package com.mygdx.eater;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.eater.screens.MenuScreen;
import com.mygdx.eater.utils.AssetManager;
import com.mygdx.eater.utils.PreferencesManager;

public class Eater extends Game {
	public SpriteBatch batch;
	TextureAtlas atlas;
	public Skin skin;


	@Override
	public void create () {
		batch = new SpriteBatch();
		atlas = new AssetManager().getAtlas();
		skin = new Skin();
		skin.addRegions(atlas);
		PreferencesManager.incGameRuns();
		Gdx.app.log("game_runs", String.format("%d", PreferencesManager.getGameRuns()));
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
