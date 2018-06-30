package com.blockout22.rpg.boss.battles.screens.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.blockout22.rpg.boss.battles.mobs.Player;
import com.blockout22.rpg.boss.battles.ui.HealthBar;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisProgressBar;
import com.kotcrab.vis.ui.widget.VisTextButton;

import java.util.Random;

public class UITester extends ScreenStage {

    public UITester(Player player) {
        super(player);

        testHealthBar();
//        testProgressBar();
//        testButton();


        rootTable.add().row();
        rootTable.add().grow();

        setBackground(new Texture(Gdx.files.internal("white.png")));
//        rootTable.debugAll();
    }

    private void testButton()
    {
        VisTextButton button = new VisTextButton("Button Here:");
//        button.getLabel().setFontScale(0.3f);
        button.getLabel().layout();
        rootTable.add(button).fillX().row();
    }

    private void testProgressBar() {
        final VisProgressBar bar = new VisProgressBar(1, 100, 1, false);
        bar.setValue(0);
        bar.setAnimateDuration(1);
        bar.setColor(Color.BROWN);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                bar.setValue(new Random().nextInt(100));
            }
        }, 2, 2, 41);

        final VisLabel label = new VisLabel("Hello World");
        label.setColor(Color.BLACK);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                label.setFontScale((float)Math.random());
            }
        }, 2, 2, 41);

        rootTable.add(bar).row();
        rootTable.add(label).row();
    }

    private void testHealthBar()
    {
        final HealthBar hb = new HealthBar();
        hb.setValue(100);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                hb.setValue(new Random().nextInt(100));
                if(hb.getValue() <= 30){
                    hb.setForegroundColor(Color.RED);
                }else{
                    hb.setForegroundColor(Color.GREEN);
                }
            }
        }, 2, 2, 100);

        rootTable.add(hb).fillX().row();
    }
}
