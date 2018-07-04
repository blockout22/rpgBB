package com.blockout22.rpg.boss.battles;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.blockout22.rpg.boss.battles.screens.SplashScreen;
import com.kotcrab.vis.ui.VisUI;

public class RPGBossBattles extends Game {

    private Skin skin;

    private float R;
    private float G;
    private float B;

    private ParticleEffect effect;

    public RPGBossBattles(int version){
        Statics.setVersion(version);
    }


	@Override
	public void create () {

	    //stop the back key doing anything (prevents some glitches in Libgdx)
	    Gdx.input.setCatchBackKey(true);
//        skin = new Skin(VisUI.SkinScale.X2.getSkinFile());
//        skin = new Skin(Gdx.files.internal("skins/rpgSkin.json"));
        skin = new Skin(Gdx.files.internal("skins/visSkin/uiskin.json"));
//        FileHandle file = Gdx.files.internal("skins/visSkin/test/test.json");
//        skin = new Skin(file);
        VisUI.load(skin);
        Statics.init(this);

        R = (float)Math.random();
        G = (float)Math.random();
        B = (float)Math.random();

//        effect = new ParticleEffect();
//        effect.load(Gdx.files.internal("particles/hover"), Gdx.files.internal("particles"));
//        effect.start();
//        effect.setPosition(250, 250);

	}

	@Override
	public void render () {
        Gdx.gl.glClearColor(R, G, B, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        super.render();

//		Statics.GAME_SCREEN.getStage().getBatch().begin();
//		effect.draw(Statics.GAME_SCREEN.getStage().getBatch(), Gdx.graphics.getDeltaTime());
//        Statics.GAME_SCREEN.getStage().getBatch().end();
//
//        if(effect.isComplete()){
//            effect.setPosition((float)(Math.random() * 1920 / 3), (float)(Math.random() * 1080 / 3));
//            effect.reset();
//        }
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
//	    particleAtlas.dispose();
//	    effect.dispose();
        skin.dispose();
	    VisUI.dispose();
	    Statics.dispose();
	}
}
