package com.mygdx.eater.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.ArrayList;
import java.util.Arrays;

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

    public static void incGameRuns() {
        Preferences prefs = Gdx.app.getPreferences("preferences");
        int game_runs = prefs.getInteger("game_runs_count", 0);
        prefs.putInteger("game_runs_count", game_runs+1);
        prefs.flush();
    }

    public static int getGameRuns() {
        Preferences prefs = Gdx.app.getPreferences("preferences");
        return prefs.getInteger("game_runs_count", 0);
    }

    public static int getTotalScore() {
        Preferences prefs = Gdx.app.getPreferences("preferences");
        return prefs.getInteger("total_score", 0);
    }

    public static void incTotalScore(int score){
        Preferences prefs = Gdx.app.getPreferences("preferences");
        int total_score = prefs.getInteger("total_score", 0);
        prefs.putInteger("total_score", total_score + score);
        prefs.flush();
    }

    private static ArrayList<String> getAvailableCharacters() {
        ArrayList<String> array_characters = new ArrayList<String>();
        Preferences prefs = Gdx.app.getPreferences("preferences");
        String available_characters = prefs.getString("available_characters", "face");
        array_characters.addAll(Arrays.asList(available_characters.split(",")));
        return array_characters;
    }

    public static ArrayList<String> updateAvailableCharacters() {
        ArrayList<String> characters = getAvailableCharacters();
        int high_score = getHighScore();
        int total_score = getTotalScore();
        int game_runs = getGameRuns();
        if (!isCharacterAvailable(characters, "vegan") && high_score >= 50) {
            addAvailableCharacter("vegan");
        }
        if (!isCharacterAvailable(characters, "pirate") && high_score >= 150) {
            addAvailableCharacter("pirate");
        }
        if (!isCharacterAvailable(characters, "skeleton") && total_score >= 500) {
            addAvailableCharacter("skeleton");
        }
        if (!isCharacterAvailable(characters, "beaver") && game_runs >= 50) {
            addAvailableCharacter("beaver");
        }
        if (!isCharacterAvailable(characters, "rabbit") && (high_score >= 300 || total_score >= 5000)) {
            addAvailableCharacter("rabbit");
        }
        if (!isCharacterAvailable(characters, "android") && game_runs >= 200) {
            addAvailableCharacter("android");
        }
        return getAvailableCharacters();
    }

    private static void addAvailableCharacter(String character) {
        Preferences prefs = Gdx.app.getPreferences("preferences");
        ArrayList<String> characters = getAvailableCharacters();
        characters.add(character);
        StringBuilder sb = new StringBuilder();
        for(String s : characters) {
            sb.append(s);
            sb.append(",");
        }
        prefs.putString("available_characters", sb.toString());
        prefs.flush();
    }

    private static boolean isCharacterAvailable(ArrayList<String> array, String character) {
        return array.contains(character);
    }


}
