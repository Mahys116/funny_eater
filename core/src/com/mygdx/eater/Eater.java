package com.mygdx.eater;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.eater.screens.MenuScreen;
import com.mygdx.eater.utils.AssetManager;
import com.mygdx.eater.utils.PreferencesManager;

public class Eater extends Game {
	public SpriteBatch batch;
	public Music music;

	@Override
	public void create () {
		batch = new SpriteBatch();

		PreferencesManager.incGameRuns();
		Gdx.app.log("game_runs", String.format("%d", PreferencesManager.getGameRuns()));
		music = Gdx.audio.newMusic(Gdx.files.internal("sounds/theme.ogg"));
		music.setLooping(true);
		if (PreferencesManager.getSound()) {
			music.play();
		}
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
