package com.blockout22.rpg.boss.battles.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.blockout22.rpg.boss.battles.mobs.Mob;
import com.blockout22.rpg.boss.battles.mobs.Player;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class FightScreenOverlay extends VisTable{

    private VisImage mobImage, playerImage;

    public FightScreenOverlay(Player player, Mob mob){
        mobImage = new VisImage(mob.getImage());
        playerImage = new VisImage(player.getImage());
        mobImage.setAlign(Align.right);

//        add().grow();
//        add(playerImage).pad(5).left();
//        add().expand();
//        add(mobImage).right().pad(5);
    }

    public void damageMob(){
        mobImage.addAction(Actions.repeat(2, Actions.sequence(
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        mobImage.setColor(Color.RED);
                    }
                }),
                Actions.delay(0.2f),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        mobImage.setColor(Color.WHITE);
                    }
                }),
                Actions.delay(0.1f)
        )));
    }

    public void healMob(){
        mobImage.addAction(Actions.repeat(1, Actions.sequence(
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        mobImage.setColor(Color.GREEN);
                    }
                }),
                Actions.delay(0.5f),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        mobImage.setColor(Color.WHITE);
                    }
                })
        )));
    }

    public void damagePlayer(){
        playerImage.addAction(Actions.repeat(2, Actions.sequence(
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        playerImage.setColor(Color.RED);
                    }
                }),
                Actions.delay(0.2f),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        playerImage.setColor(Color.WHITE);
                    }
                }),
                Actions.delay(0.1f)
        )));
    }
}
