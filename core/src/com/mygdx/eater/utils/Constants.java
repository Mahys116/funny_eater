package com.mygdx.eater.utils;

import java.util.HashMap;

public class Constants {
//    private final static String[] CHARACTERS_NAME = {"face", "vegan", "pirate", "skeleton", "beaver", "rabbit", "android"};
    private final static String[] CHARACTERS_NAME = {"face", "vegan", "pirate", "skeleton"};
    private final static String[] FOOD_NAME = {"meat","hen","cheese","egg","cupcake","cookie","candy","bread","grapes","orange","bone","log","poison"};
    private final static int[] FACE_SCORE = {3, 3, 2, 3, 2, 2, 2, 3, 2, 2, -5, -5, -10};
    private final static int[] VEGAN_SCORE = {-5, -5, 1, 1, 3, 3, 3, 5, 6, 6, -5, 5, -10};
    private final static int[] PIRATE_SCORE = {1, 1, 1, 1, 5, 5, 5, 2, 3, 3, -5, -5, -10};
    private final static int[] SKELETON_SCORE = {5, 5, 0, 0, -5, -5, 1, -10, 2, 2, 5, 5, 5};
    private final static int[] BEAVER_SCORE = {3, 3, 2, 3, 2, 2, 2, 3, 2, 2, -5, -5, -10};
    private final static int[] RABBIT_SCORE = {3, 3, 2, 3, 2, 2, 2, 3, 2, 2, -5, -5, -10};
    private final static int[] ANDROID_SCORE = {3, 3, 2, 3, 2, 2, 2, 3, 2, 2, -5, -5, -10};
    private final static int[][] FOOD_SCORE = {FACE_SCORE, VEGAN_SCORE, PIRATE_SCORE, BEAVER_SCORE, SKELETON_SCORE, RABBIT_SCORE, ANDROID_SCORE};
    private final static String[] CHARACTERS_DESCRIPTIONS = {
        "Just Steve. Eats edible, doesn't \neat inedible.",
        "Steve's brother. A vegetarian, \nbut eats animal foods.",
        "He likes to steal candy from \nchildren.",
        "Skeleton with an allergy to \ngluten, so be careful with bread.",
        "Do not eat meat, loves sticks.",
        "Easter bunny. Hunts eggs and \nlove sweets.",
        "With equal success grinds almost \nany food. But bones and sticks \nbreak down the mechanism."
    };
    private final static String[] CHARACTERS_FULL_NAME = {"Steeve", "Jo", "Sweet Tooth", "mr Bones", "Beaver", "Easter Bunny", "Mk-44"};
    private final static String[] CHARACTERS_CONDITIONS = {
        "DEFAULT",
        "BEST: 50",
        "BEST: 150",
        "TOTAL: 500",
        "LAUNCHES: 50",
        "BEST: 300 or TOTAL: 5000",
        "LAUNCHES: 200"
    };

    public static String[] getCharacters() {
        return CHARACTERS_NAME;
    }

    public static String[] getFood() {
        return FOOD_NAME;
    }

    public static String[] getCharactersDescriptions() {
        return CHARACTERS_DESCRIPTIONS;
    }

    public static String[] getCharactersFullName() {
        return CHARACTERS_FULL_NAME;
    }

    public static String[] getCharactersConditions() {
        return CHARACTERS_CONDITIONS;
    }

    public static int[] getFoodScoreForCharacter(String name) {
        int i = java.util.Arrays.asList(CHARACTERS_NAME).indexOf(name);
        if (i < 0) i = 0;
        return FOOD_SCORE[i];
    }

}
