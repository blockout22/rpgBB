package com.blockout22.rpg.boss.battles.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.blockout22.rpg.boss.battles.Statics;
import com.blockout22.rpg.boss.battles.mobs.Player;
import com.blockout22.rpg.boss.battles.screens.helper.ScreenStage;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSlider;
import com.kotcrab.vis.ui.widget.VisTextButton;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class MainMenuScreen extends ScreenStage {

    private VisTextButton play;
    private VisTextButton playstore;
    private VisTextButton upgrade;
    private VisTextButton options;
    private VisLabel version;

    public MainMenuScreen(Player player) {
        super(player);
        play = new VisTextButton(Statics.getBundle().get("clickToPlay"));
        playstore = new VisTextButton(Statics.getBundle().get("getOnAndroid"));
        upgrade = new VisTextButton(Statics.getBundle().get("upgrade"));
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

        playstore.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String link = "https://play.google.com/store/apps/details?id=com.blockout22.rpg.boss.battles";
//                    Desktop.getDesktop().browse(new URL(link).toURI());
                Gdx.net.openURI(link);
            }
        });

        upgrade.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String link = "https://play.google.com/store/apps/details?id=com.blockout22.rpg.boss.battles";
//                    Desktop.getDesktop().browse(new URL(link).toURI());
                Gdx.net.openURI(link);
            }
        });

        options.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Statics.setScreen(Statics.OPTIONS_SCREEN);
            }
        });

        rootTable.add(play).fillX().pad(5).row();

        if(Gdx.app.getType() != Application.ApplicationType.Android)
        {
            rootTable.add(playstore).fillX().pad(5).row();
        }

        if(Statics.isFree){
            rootTable.add(upgrade).fillX().pad(5).row();
        }

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
