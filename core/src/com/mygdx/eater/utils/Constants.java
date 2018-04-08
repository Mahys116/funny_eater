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

    public static String[] getCharacters() {
        return CHARACTERS_NAME;
    }

    public static String[] getFood() {
        return FOOD_NAME;
    }

    public static int[] getFoodScoreForCharacter(String name) {
        int i = java.util.Arrays.asList(CHARACTERS_NAME).indexOf(name);
        if (i < 0) i = 0;
        return FOOD_SCORE[i];
    }

}
