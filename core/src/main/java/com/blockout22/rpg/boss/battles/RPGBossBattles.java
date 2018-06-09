package com.blockout22.rpg.boss.battles;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.kotcrab.vis.ui.VisUI;

public class RPGBossBattles extends Game {

	@Override
	public void create () {

	    //stop the back key doing anything (prevents some glitches in Libgdx)
	    Gdx.input.setCatchBackKey(true);

        VisUI.load(VisUI.SkinScale.X2);

        Statics.init(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0.2f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		super.render();
	}

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
	public void dispose () {
	    VisUI.dispose();
	    Statics.dispose();
	}
}
