package com.mygdx.eater.utils;

/**
 * Created by marat on 07.02.18.
 */

public class GameState {
    private static volatile GameState instance;
    int state;
    public static final int GAME_INIT = 0;
    public static final int GAME_RUNNING = 1;
    public static final int GAME_PAUSED = 2;
    public static final int GAME_OVER = 5;
    private float speed;

    public static GameState getInstance() {
        GameState localInstance = instance;
        if (localInstance == null) {
            synchronized (GameState.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new GameState();
                }
            }
        }
        return localInstance;
    }

    public GameState() {
        state = GAME_INIT;
    }

    public void pause() {
        state = GAME_PAUSED;
    }

    public void resume() {
        state = GAME_RUNNING;
    }

    public void  end() {
        state = GAME_OVER;
    }

    public void init() { state = GAME_INIT;}

    public int getState() {
        return state;
    }

    public boolean isPaused() { return (state == GAME_PAUSED || state == GAME_OVER);}

    public boolean isInit() {return state == GAME_INIT;}

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public void incSpeed(int i) {
        speed+=i;
    }
}
