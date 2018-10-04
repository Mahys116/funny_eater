package com.mygdx.eater.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.eater.stages.GameStage;
import com.mygdx.eater.utils.AssetManager;
import com.mygdx.eater.utils.Constants;
import com.mygdx.eater.utils.GameState;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.eater.utils.PreferencesManager;

import java.util.Random;

public class Food extends Actor {
    private static final int ROLL = 1;
    private static final int BLOCK_LEFT = 2;
    private static final int BLOCK_RIGHT = 3;
    private final static Random random = new Random();
    protected float rotation;
    private String[] FOOD_NAME;
    private int[] FOOD_SCORE;
    private final TextureRegion image;
    private int type;
    private int state;
    public boolean blocked;
    final Rectangle block;
    private Character character;
    private GameStage stage;
    private Sound gulp;
    private Sound gulp_bad;

    public interface IncrementWrapper {
        public void incrementScore(int score);
    }
    private Food.IncrementWrapper incrementWrapper;


    public Food(int size, int y_start, Character face, Food.IncrementWrapper incrementWrapper, GameStage stage) {
        this.stage = stage;
        rotation = 0;
        character = face;
        FOOD_SCORE = Constants.getFoodScoreForCharacter(character.getName());
        FOOD_NAME = Constants.getFood();

        loadSounds();
        TextureAtlas atlas = AssetManager.getFood();
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

    private void loadSounds() {
        gulp = Gdx.audio.newSound(Gdx.files.internal("sounds/gulp.wav"));
        gulp_bad = Gdx.audio.newSound(Gdx.files.internal("sounds/gulp_bad.wav"));
    }

    public int getScore() {
        return FOOD_SCORE[type];
    }

    public void draw(Batch batch, float parentAlpha) {
        if (state == ROLL) {
            batch.draw(image, getX(), getY(), getWidth() / 2, getHeight() / 4, getWidth(), getHeight(), 1, 1, stage.getRotation());
        } else {
            batch.draw(image, getX(), getY(), getWidth() / 2, getHeight() / 4, getWidth(), getHeight(), 1, 1, 0);
        }
    }

    @Override
    public void act(float delta) {
        if (GameState.getInstance().isPaused()) return;
        if (GameState.getInstance().isInit()) remove();
        if (getX() > getWidth()*10 || getY() < 0) remove();

        if (state == ROLL) {
            if (block.overlaps(character.tooth_l)) {
                state = BLOCK_LEFT;
            }else if (block.overlaps(character.tooth_r)) {
                state = BLOCK_RIGHT;
            }else if (block.overlaps(character.mouth)) {
                if (getScore() > 0) {
                    if (PreferencesManager.getSound()) {gulp.play();}
                } else {
                    if (PreferencesManager.getSound()) {gulp_bad.play();}
                }

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

    @Override
    public boolean remove() {
        gulp.dispose();
        gulp_bad.dispose();
        return super.remove();
    }
}
