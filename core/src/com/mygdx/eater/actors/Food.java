package com.mygdx.eater.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.eater.utils.AssetManager;
import com.mygdx.eater.utils.GameState;
import com.badlogic.gdx.math.Rectangle;

import java.lang.reflect.Method;
import java.util.Random;

public class Food extends Actor {
    private static final int ROLL = 1;
    private static final int BLOCK_LEFT = 2;
    private static final int BLOCK_RIGHT = 3;
    private final static Random random = new Random();
    private final static String[] FOOD_NAME = {"cookie", "meat", "bread", "poison"};
    private final static int[] FOOD_SCORE = {1, 1, 1, -100};
    private final TextureRegion image;
    private int type;
    private int state;
    public boolean blocked;
    final Rectangle block;
    private Character character;

    public interface IncrementWrapper {
        public void incrementScore(int score);
    }
    private Food.IncrementWrapper incrementWrapper;


    public Food(int size, int y_start, Character face, Food.IncrementWrapper incrementWrapper) {
        character = face;
        TextureAtlas atlas = new AssetManager().getFood();
        Skin skin = new Skin();
        skin.addRegions(atlas);

        type = random.nextInt(FOOD_NAME.length);
        blocked = false;
        setPosition(-size, y_start);
        setSize(size, size);
        image = skin.getRegion(FOOD_NAME[type]);

        block = new Rectangle();
        block.setSize( size, size);
        block.setPosition(-size, y_start);

        this.incrementWrapper = incrementWrapper;

        state = ROLL;
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
        if (getX() > getWidth()*10 || getY() < 0) remove();

        if (state == ROLL) {
            if (block.overlaps(character.tooth_l)) {
                state = BLOCK_LEFT;
            }else if (block.overlaps(character.tooth_r)) {
                state = BLOCK_RIGHT;
            }else if (block.overlaps(character.mouth)) {
                incrementWrapper.incrementScore(getScore());
                remove();
            }
            setX(getX() + GameState.getInstance().getSpeed() * delta);
        } else if (state == BLOCK_LEFT) {
            setX(getX() - GameState.getInstance().getSpeed() * delta);
            setY(getY() - GameState.getInstance().getSpeed() * delta);
        } else if (state == BLOCK_RIGHT) {
            setX(getX() + GameState.getInstance().getSpeed() * delta);
            setY(getY() - GameState.getInstance().getSpeed() * delta);
        }
        block.setPosition(getX(),getY());
    }
}
