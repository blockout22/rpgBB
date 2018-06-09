package com.blockout22.rpg.boss.battles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.blockout22.rpg.boss.battles.Statics;
import com.blockout22.rpg.boss.battles.mobs.Player;
import com.blockout22.rpg.boss.battles.screens.helper.ScreenStage;
import com.kotcrab.vis.ui.widget.VisSlider;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class MainMenuScreen extends ScreenStage {

    private VisTextButton play;
    private VisTextButton options;
    private VisSlider uiScale;

    public MainMenuScreen(Player player) {
        super(player);
        play = new VisTextButton("Click To Play");
        options = new VisTextButton("Options");
        uiScale = new VisSlider(0.1f, 10f, 0.1f, false);
        uiScale.setValue(Statics.getPreferences().getFloat(Statics.UI_SCALE));

        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Statics.setScreen(Statics.GAME_SCREEN);
            }
        });

        uiScale.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
//                System.out.println("changed: " + uiScale.getValue());
//                ScreenStage.getViewport().setMinWorldWidth(1920 / uiScale.getValue());
//                ScreenStage.getViewport().setMinWorldHeight(1080 / uiScale.getValue());
//                ScreenStage.getViewport().update((int)(Gdx.graphics.getWidth()* uiScale.getValue()), (int)(Gdx.graphics.getHeight() * uiScale.getValue()), true);
            }
        });

        rootTable.add(play).fillX().pad(5).row();
        rootTable.add(options).fillX().pad(5).row();
//        rootTable.add(uiScale).fillX().pad(5).row();
        rootTable.add().expand();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
}
