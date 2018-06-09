package com.blockout22.rpg.boss.battles.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.blockout22.rpg.boss.battles.Statics;
import com.blockout22.rpg.boss.battles.mobs.Mob;
import com.blockout22.rpg.boss.battles.mobs.Player;
import com.blockout22.rpg.boss.battles.screens.helper.ScreenStage;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisProgressBar;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class FightScreen extends ScreenStage {

    private Mob mob;

    private Table bottomBar;
    private VisLabel mobName, mobHealthLabel, playerHealthLabel;
    private VisProgressBar mobHealth, playerHealth;
    private VisTextButton attackButton, backConfirm;
    private VisDialog dialog;

    private Stack mobStack, playerStack;

    private String ATTACK_STRING = "Attack";

    private long lastMobHit, lastPlayerHit;

    public FightScreen(final Player player, final Mob mob){
        super(player);
        this.mob = mob;
        bottomBar = new Table();
        mobStack = new Stack();
        playerStack = new Stack();

        mobHealthLabel = new VisLabel("Health Goes here");
        playerHealthLabel = new VisLabel("Player Health here");

        mobName = new VisLabel(mob.getName());
        mobHealth = new VisProgressBar(0, mob.getStats().getMaxhealth(), 0.1f, false);
        mobHealth.setValue(mob.getStats().getCurrentHealth());

        playerHealth = new VisProgressBar(0, player.getStats().getMaxhealth(), 0.1f, false);
        playerHealth.setValue(player.getStats().getCurrentHealth());

        attackButton = new VisTextButton("Attack");
        backConfirm = new VisTextButton("Back");

        dialog = new VisDialog("Are you sure you want to go back?");

        attackButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                attackButton.setDisabled(true);
                long dmg = getPlayer().hit(mob);
                if(dmg > 0){
                    mobHealthLabel.setText(mob.getStats().getCurrentHealth() + "/" + mob.getStats().getMaxhealth());
                }
                lastPlayerHit = System.currentTimeMillis();
            }
        });

        backConfirm.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dialog.show(getStage());
            }
        });

        mobStack.add(mobHealth);
        mobStack.add(mobHealthLabel);

        playerStack.add(playerHealth);
        playerStack.add(playerHealthLabel);

        rootTable.add(mobName).top().pad(5).row();
        rootTable.add(mobStack).top().fillX().expand().pad(5).row();
        rootTable.add(playerStack).fillX().pad(5).row();
        rootTable.add(bottomBar).fillX().pad(5);
        bottomBar.add(attackButton).center().pad(5);
        bottomBar.add(backConfirm).expand().right().pad(5);

        VisTextButton back = new VisTextButton("Yes");
        dialog.button(back);
        dialog.button("No");

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Statics.backScreen();
            }
        });

        lastMobHit = System.currentTimeMillis();
        lastPlayerHit = System.currentTimeMillis();

        player.reset();
        mob.reset();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(!mob.isDead() && !getPlayer().isDead()){
            //checks if mob cool down time has passed
            if(TimeUtils.timeSinceMillis(lastMobHit) > mob.getAttackSpeed()){
                long dmg = mob.hit(getPlayer());

                if(dmg > 0){
                    playerHealthLabel.setText(getPlayer().getStats().getCurrentHealth() + "/" + getPlayer().getStats().getMaxhealth());
                }

                lastMobHit = System.currentTimeMillis();
            }

            if(attackButton.isDisabled()){
                long t = getPlayer().getAttackSpeed() - (TimeUtils.timeSinceMillis(lastPlayerHit));
                attackButton.setText(((t / 1000) + 1) + " Seconds");
            }

            //checks if players cool down time has passed
            if(TimeUtils.timeSinceMillis(lastPlayerHit) > getPlayer().getAttackSpeed()){
                attackButton.setDisabled(false);
                attackButton.setText(ATTACK_STRING);
            }
        }else{
            //check if player is dead first, show player died else if the mob died show win screen

            if(getPlayer().isDead()){
                //go back a screen to prevent player from clicking back onto the fight screen
                Statics.backScreen();
                MessageScreen ms = (MessageScreen)Statics.MESSAGE_SCREEN;
                Statics.setScreen(ms.setText("you died!"));
            }else if(mob.isDead()){
                getPlayer().rewardXp(mob.getRewardXp());
                MessageScreen ms = (MessageScreen)Statics.MESSAGE_SCREEN;
                //go back a screen to prevent player from clicking back onto the fight screen
                Statics.backScreen();
                Statics.setScreen(ms.setText("you gained " + mob.getRewardXp() + " xp"));
            }
        }
        mobHealth.setValue(mob.getStats().getCurrentHealth());
        playerHealth.setValue(getPlayer().getStats().getCurrentHealth());
    }
}
