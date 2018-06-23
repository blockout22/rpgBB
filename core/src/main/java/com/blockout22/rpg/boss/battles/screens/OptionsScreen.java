package com.blockout22.rpg.boss.battles.screens;

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
    private VisLabel damageVibrateLabel;
    private VisCheckBox damageVibrate;
    private VisTextButton back;

    private VisTable scrollTable;
    private VisScrollPane scrollPane;

    public OptionsScreen(Player player) {
        super(player);

        title = new VisLabel(Statics.getBundle().get("optionsTitle"));
        damageVibrateLabel = new VisLabel(Statics.getBundle().get("damageVibrate"));
        damageVibrate = new VisCheckBox("", Statics.getPreferences().getBoolean(Statics.PLAYER_VIRBRATE_DAMAGE));
        back = new VisTextButton(Statics.getBundle().get("backScreen"));

        scrollTable = new VisTable();
        scrollPane = new VisScrollPane(scrollTable);
        scrollPane.layout();

        damageVibrate.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Statics.getPreferences().putBoolean(Statics.PLAYER_VIRBRATE_DAMAGE, damageVibrate.isChecked());
                Statics.getPreferences().flush();
            }
        });

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Statics.backScreen();
            }
        });

//        scrollTable.add().expand();
        scrollTable.add(damageVibrateLabel);
        scrollTable.add(damageVibrate);
        rootTable.add(title).pad(5).row();
        rootTable.addSeparator();
        rootTable.add(scrollPane).row();
//        rootTable.add(damageVibrate).pad(5).row();
        rootTable.add(back).expand().bottom().right();
    }
}
