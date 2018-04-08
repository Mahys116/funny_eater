package com.mygdx.eater.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PreferencesManager {

    public static String getCharacterName() {
        Preferences prefs = Gdx.app.getPreferences("preferences");
        return prefs.getString("current_character", "face");
    }

    public static int getHighScore() {
        Preferences prefs = Gdx.app.getPreferences("preferences");
        return prefs.getInteger("high_score", 0);
    }

    public static void setHighScore(int score){
        Preferences prefs = Gdx.app.getPreferences("preferences");
        prefs.putInteger("high_score", score);
        prefs.flush();
    }

    public static void setCharacterName(String name) {
        Preferences prefs = Gdx.app.getPreferences("preferences");
        prefs.putString("current_character", name);
        prefs.flush();
    }

}
