package com.blockout22.rpg.boss.battles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.TimeUtils;
import com.blockout22.rpg.boss.battles.Statics;
import com.blockout22.rpg.boss.battles.screens.helper.ScreenStage;
import com.kotcrab.vis.ui.widget.VisLabel;

public class SplashScreen extends ScreenStage{

    private SpriteBatch batch;
    private Image image;
    private Texture texture;
    private VisLabel text;

    private boolean isDisposed = false;
    private boolean isAlive = true;
    private long delay = 2000;
    private long startTime;

    public SplashScreen() {
        super(null);
        batch = new SpriteBatch();
        texture = new Texture("badlogic.jpg");
        image = new Image(texture);

        text = new VisLabel("Powered by LibGDX.");

        startTime = System.currentTimeMillis();

        rootTable.add(image).row();
        rootTable.add(text);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if(!isAlive)
        {
            return;
        }

        if(TimeUtils.timeSinceMillis(startTime) > delay){
            dispose();
            isAlive = false;
            Statics.backScreen();
        }
    }

    public void dispose()
    {
        if(isDisposed){
            return;
        }
        texture.dispose();
        batch.dispose();
        isDisposed = true;
    }
}
