package com.blockout22.rpg.boss.battles;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.kotcrab.vis.ui.VisUI;

public class RPGBossBattles extends Game {

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


        Skin skin = new Skin(VisUI.SkinScale.X2.getSkinFile());
        System.out.println(Gdx.app.getVersion());

//        freeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
//        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
//        parameter.size = 70;
//        BitmapFont font = freeTypeFontGenerator.generateFont(parameter);
//        skin.add("default-font", font, BitmapFont.class);
//        System.out.println(skin.getFont("small-font"));
//        System.out.println(skin.getFont("default-font"));
//        skin.remove("small-font", BitmapFont.class);
//        skin.remove("default-font", BitmapFont.class);
//        System.out.println(skin.getFont("small-font"));
//        System.out.println(skin.getFont("default-font"));
//        BitmapFont newFont = new BitmapFont(Gdx.files.internal("skins/fonts/custom.fnt"));
//        skin.add("default-font", newFont, BitmapFont.class);
//        skin.add("small-font", newFont, BitmapFont.class);
//        System.out.println(skin.getFont("small-font"));
//        System.out.println(skin.getFont("default-font"));

        VisUI.load(skin);

//        System.out.println(VisUI.getSkin().getFont("default-font"));

        Statics.init(this);

        R = (float)Math.random();
        G = (float)Math.random();
        B = (float)Math.random();

//        System.out.println("R: " + R   + " G: " + G + " B: " + B);

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
	    VisUI.dispose();
	    Statics.dispose();
	}
}
