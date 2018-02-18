package com.mygdx.eater.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.eater.utils.AssetManager;
import com.mygdx.eater.utils.GameState;

import java.util.Random;

public class Food extends Actor {
    private final static Random random = new Random();
    private final static String[] FOOD_NAME = {"cookie", "meat", "bread", "poison"};
    private final static int[] FOOD_SCORE = {1, 1, 1, -100};
    private final TextureRegion image;
    private int type;
    public boolean blocked;

    public Food(int size, int y_start) {
        TextureAtlas atlas = new AssetManager().getFood();
        Skin skin = new Skin();
        skin.addRegions(atlas);

        type = random.nextInt(FOOD_NAME.length);
        blocked = false;
        setPosition(-size, y_start);
        setSize(size, size);
        image = skin.getRegion(FOOD_NAME[type]);
    }

    public int getScore() {
        return FOOD_SCORE[type];
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.draw(image, getX(), getY(),getWidth(),getHeight());
    }

    @Override
    public void act(float delta) {
        if (GameState.getInstance().isPaused()) return;
        if (getX() > getWidth()*10) {
            remove();
        }
        setX(getX() + GameState.getInstance().getSpeed() * delta);
    }
}
