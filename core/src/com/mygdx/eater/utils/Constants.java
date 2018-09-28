package com.mygdx.eater.utils;

public class Constants {
    private final static String[] CHARACTERS_NAME = {"face", "vegan", "pirate", "skeleton", "beaver", "rabbit", "android"};
    private final static String[] FOOD_NAME = {"meat","hen","cheese","egg","cupcake","cookie","candy","bread","grapes","orange","bone","log","poison"};
    private final static int[] FACE_SCORE = {3, 3, 2, 3, 2, 2, 2, 3, 2, 2, -5, -5, -10};
    private final static int[] VEGAN_SCORE = {3, 3, 2, 3, 2, 2, 2, 3, 2, 2, -5, -5, -10};
    private final static int[] PIRATE_SCORE = {3, 3, 2, 3, 2, 2, 2, 3, 2, 2, -5, -5, -10};
    private final static int[] SKELETON_SCORE = {3, 3, 2, 3, 2, 2, 2, 3, 2, 2, -5, -5, -10};
    private final static int[] BEAVER_SCORE = {3, 3, 2, 3, 2, 2, 2, 3, 2, 2, -5, -5, -10};
    private final static int[] RABBIT_SCORE = {3, 3, 2, 3, 2, 2, 2, 3, 2, 2, -5, -5, -10};
    private final static int[] ANDROID_SCORE = {3, 3, 2, 3, 2, 2, 2, 3, 2, 2, -5, -5, -10};
    private final static int[][] FOOD_SCORE = {FACE_SCORE, VEGAN_SCORE, PIRATE_SCORE, BEAVER_SCORE, SKELETON_SCORE, RABBIT_SCORE, ANDROID_SCORE};
    private final static String[] CHARACTERS_DESCRIPTIONS = {
        "Just Steve. Eats edible, doesn't inedible.",
        "Steve's brother. A vegetarian, but eats animal foods. He likes vegetables very much.",
        "He likes to steal candy from children.",
        "Skeleton with an allergy to gluten, so be careful with bread.",
        "Do not eat meat, loves sticks.",
        "Easter bunny. Hunts eggs and love sweets.",
        "With equal success grinds almost any food. But bones and sticks break down the mechanism."
    };
    private final static String[] CHARACTERS_FULL_NAME = {"Steeve", "Jo", "Sweet Tooth", "mr Bones", "Beaver", "Easter Bunny", "Mk-44"};

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

    public static int[] getFoodScoreForCharacter(String name) {
        int i = java.util.Arrays.asList(CHARACTERS_NAME).indexOf(name);
        if (i < 0) i = 0;
        return FOOD_SCORE[i];
    }

}
