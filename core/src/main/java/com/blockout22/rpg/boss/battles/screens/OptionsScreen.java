package com.blockout22.rpg.boss.battles.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.blockout22.rpg.boss.battles.Statics;
import com.blockout22.rpg.boss.battles.mobs.Player;
import com.blockout22.rpg.boss.battles.screens.helper.ScreenStage;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class OptionsScreen extends ScreenStage{

    private VisLabel title;
    private VisLabel damageVibrateLabel, splashscreenLabel;
    private VisCheckBox damageVibrate, splashscreen;
    private VisTextButton back;

    private VisTable scrollTable;
    private VisScrollPane scrollPane;

    public OptionsScreen(Player player) {
        super(player);

        title = new VisLabel(Statics.getBundle().get("optionsTitle"));

        damageVibrateLabel = new VisLabel(Statics.getBundle().get("damageVibrate") + " ");
        damageVibrate = new VisCheckBox("", Statics.getPreferences().getBoolean(Statics.PLAYER_VIRBRATE_DAMAGE));

        splashscreenLabel = new VisLabel(Statics.getBundle().get("showSplash") + " ");
        splashscreen = new VisCheckBox("", Statics.getPreferences().getBoolean(Statics.SHOW_SPLASH));

        back = new VisTextButton(Statics.getBundle().get("backScreen"));

        scrollTable = new VisTable();
        scrollPane = new VisScrollPane(scrollTable);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollbarsOnTop(false);
//        scrollPane.layout();

        damageVibrate.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Statics.getPreferences().putBoolean(Statics.PLAYER_VIRBRATE_DAMAGE, damageVibrate.isChecked());
                Statics.getPreferences().flush();
            }
        });

        splashscreen.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Statics.getPreferences().putBoolean(Statics.SHOW_SPLASH, splashscreen.isChecked());
                Statics.getPreferences().flush();
            }
        });

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Statics.backScreen();
            }
        });

        if(Gdx.app.getType() != Application.ApplicationType.Desktop) {
            scrollTable.add(damageVibrateLabel).pad(5);
            scrollTable.add(damageVibrate).row();
        }

        scrollTable.add(splashscreenLabel).pad(5);
        scrollTable.add(splashscreen);
        scrollTable.add().expand();

        rootTable.add(title).pad(5).row();
        rootTable.addSeparator();
        rootTable.add(scrollPane).fillY().row();
        rootTable.add(back).expand().bottom().right();
    }
}
