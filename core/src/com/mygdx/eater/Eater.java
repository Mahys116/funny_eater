package com.mygdx.eater;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.eater.screens.CharacterScreen;
import com.mygdx.eater.screens.GameScreen;
import com.mygdx.eater.screens.MenuScreen;
import com.mygdx.eater.utils.AssetManager;
import com.mygdx.eater.utils.PreferencesManager;

public class Eater extends Game {
	public SpriteBatch batch;
	public Music music;

	private MenuScreen screen_menu;
	private GameScreen screen_game;
	private CharacterScreen screen_character;

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
		screen_menu = new MenuScreen(this);
		screen_game = new GameScreen(this);
		screen_character = new CharacterScreen(this);
		this.setScreen("menu");
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public void setScreen(String screen_name) {
		if (screen_name.equals("menu")) {
			screen_menu.initParams();
			this.setScreen(screen_menu);
		} else if (screen_name.equals("game")) {
			screen_game.stage.initGameParams();
			screen_game.stage.showGameMenu();
			this.setScreen(screen_game);
		} else {
			this.setScreen(screen_character);
		}
	}
}
