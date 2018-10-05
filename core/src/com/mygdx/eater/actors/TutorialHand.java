package com.mygdx.eater.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.eater.utils.AssetManager;
import com.mygdx.eater.utils.GameState;

public class TutorialHand extends Actor {
    public int direction;
    private float max_y;
    private TextureRegion image;
    private Character character;
    public TutorialHand(float size, float x, float y, Character character) {
        direction = -1;
        new AssetManager();
        TextureAtlas atlas = AssetManager.getAtlas();
        Skin skin = new Skin();
        skin.addRegions(atlas);
        image = skin.getRegion("hand");
        setWidth(size);
        setHeight(size);
        setBounds(0, 0, getWidth(), getHeight());
        setX(x);
        setY(y);
        max_y = y;
        this.character = character;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(Gdx.input.isTouched()) {
            if (GameState.getInstance().isInit() &&
                    (Gdx.graphics.getHeight() - Gdx.input.getY()) <= (character.vertical_position)) {
                GameState.getInstance().resume();
                setVisible(false);
            }
        }
        if (!GameState.getInstance().isInit()) return;

        if (getY() > max_y){ direction = -1; }
        else if (getY() <= 10) { direction = 1; }

        setY(getY()+delta*150*direction);
        character.moveTo(getX(), getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (!GameState.getInstance().isInit()) return;
        batch.draw(image, getX()-getWidth()/3,getY()-getHeight()/5,getWidth(),getHeight());

    }
}
