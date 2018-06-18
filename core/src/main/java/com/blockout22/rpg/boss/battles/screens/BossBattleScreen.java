package com.blockout22.rpg.boss.battles.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.blockout22.rpg.boss.battles.Statics;
import com.blockout22.rpg.boss.battles.mobs.Mob;
import com.blockout22.rpg.boss.battles.mobs.Player;
import com.blockout22.rpg.boss.battles.screens.helper.ScreenStage;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class BossBattleScreen extends ScreenStage{

    private Table scrollTable;
    private VisScrollPane scrollPane;
    private VisTextButton back;

    public BossBattleScreen(final Player player) {
        super(player);
        scrollTable = new Table();
        scrollPane = new VisScrollPane(scrollTable);
        back = new VisTextButton(Statics.getBundle().get("backScreen"));
        scrollPane.layout();
        scrollPane.setFadeScrollBars(false);

        for(int i = 0; i < Statics.bossMobs.length; i++){
            final Mob mob = Statics.bossMobs[i].getMob();
            //check if the user is using the free version
            boolean free = Statics.isFree;
            VisTextButton t = new VisTextButton(Statics.bossMobs[i].getMob().getName() + (free && !Statics.bossMobs[i].isFree() ? " (Paid Version)" : ""));
            if(free && !Statics.bossMobs[i].isFree()){
                t.setDisabled(true);
            }
            VisTextButton tip = new VisTextButton("i");

            t.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Statics.setScreen(new FightScreen(player, mob));
                }
            });

            final int finalI = i;
            tip.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Statics.setScreen(new InfoScreen(player, Statics.getBundle().get("infoabout") + mob.getName(), mob.getInfo()));
                }
            });

            scrollTable.add(t).fillX().pad(5);
            scrollTable.add(tip).pad(5);
            scrollTable.row();
        }

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Statics.backScreen();
            }
        });

        scrollTable.row();
        scrollTable.add().expand();
        rootTable.add(scrollPane).grow().row();
        rootTable.add(back).expand().bottom().right();

    }
}
