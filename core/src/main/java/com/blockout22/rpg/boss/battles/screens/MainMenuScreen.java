package com.blockout22.rpg.boss.battles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.blockout22.rpg.boss.battles.Statics;
import com.blockout22.rpg.boss.battles.mobs.Player;
import com.blockout22.rpg.boss.battles.screens.helper.ScreenStage;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSlider;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class MainMenuScreen extends ScreenStage {

    private VisTextButton play;
    private VisTextButton options;
    private VisSlider uiScale;
    private VisLabel version;

    public MainMenuScreen(Player player) {
        super(player);
        play = new VisTextButton(Statics.getBundle().get("clickToPlay"));
        options = new VisTextButton(Statics.getBundle().get("options"));
        version = new VisLabel("Build: " + Statics.getVersion());
        uiScale = new VisSlider(0.1f, 10f, 0.1f, false);
        uiScale.setValue(Statics.getPreferences().getFloat(Statics.UI_SCALE));

        version.setFontScale(0.6f);

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

        options.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Statics.setScreen(Statics.OPTIONS_SCREEN);
            }
        });

        rootTable.add(play).fillX().pad(5).row();
        rootTable.add(options).fillX().pad(5).row();
//        rootTable.add(uiScale).fillX().pad(5).row();
        rootTable.add(version).pad(5).bottom().left().expand();
//        rootTable.add().expand();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
}
