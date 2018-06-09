package com.blockout22.rpg.boss.battles;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class RPGBossBattles extends Game {
    @Override
    public void create() {
        setScreen(new FirstScreen());
    }
}