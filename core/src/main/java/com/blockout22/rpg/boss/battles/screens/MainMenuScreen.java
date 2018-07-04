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

import java.io.IOException;

public class MainMenuScreen extends ScreenStage {

    private VisTextButton play;
    private VisTextButton options;
    private VisLabel version;

    public MainMenuScreen(Player player) {
        super(player);
        play = new VisTextButton(Statics.getBundle().get("clickToPlay"));
        options = new VisTextButton(Statics.getBundle().get("options"));
        version = new VisLabel(Statics.getBundle().get("build") + " " + Statics.getVersion());

        play.getLabel().setFontScale(1f);

//        version.setFontScale(0.13f);

        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Statics.setScreen(Statics.GAME_SCREEN);
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

        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean update = false;
                try {
                    update = Statics.hasUpdate();
                } catch (IOException e) {
                    update = false;
                }

                if(update){
                    version.setText(version.getText() + " [" + Statics.getBundle().get("newUpdate") + "]");
                }
            }
        }).start();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
}
