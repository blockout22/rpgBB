package com.blockout22.rpg.boss.battles.screens;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
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
        train = new VisTextButton(Statics.getBundle().get("train"));
        boss = new VisTextButton(Statics.getBundle().get("fightBoss"));
        playerStats = new VisTextButton(Statics.getBundle().get("playerStats"));
        back = new VisTextButton(Statics.getBundle().get("backScreen"));

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
                Statics.setScreen(Statics.BOSS_BATTLE_SCREEN);
            }
        });

        playerStats.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //
                Statics.setScreen(Statics.PLAYER_STATS_SCREEN);
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
