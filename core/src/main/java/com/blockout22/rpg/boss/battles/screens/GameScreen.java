package com.blockout22.rpg.boss.battles.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.blockout22.rpg.boss.battles.Statics;
import com.blockout22.rpg.boss.battles.mobs.Player;
import com.blockout22.rpg.boss.battles.screens.helper.ScreenStage;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class GameScreen extends ScreenStage {

    private VisTextButton train;
    private VisTextButton boss;
    private VisTextButton playerStats;
    private VisTextButton back;

    public GameScreen(Player player){
        super(player);
        train = new VisTextButton("Train");
        boss = new VisTextButton("Fight Boss");
        playerStats = new VisTextButton("Player Stats");
        back = new VisTextButton("Back");

        train.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //
                Statics.setScreen(Statics.TRAINING_SCREEN);
            }
        });

        boss.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //
            }
        });

        playerStats.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //
            }
        });

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Statics.backScreen();
            }
        });


        rootTable.add(train).fillX().pad(5).row();
        rootTable.add(boss).fillX().pad(5).row();
        rootTable.add(playerStats).fillX().pad(5).row();
        rootTable.add(back).expand().bottom().right();
    }
}
