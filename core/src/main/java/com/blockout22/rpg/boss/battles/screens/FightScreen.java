package com.blockout22.rpg.boss.battles.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;
import com.blockout22.rpg.boss.battles.FloatingText;
import com.blockout22.rpg.boss.battles.Statics;
import com.blockout22.rpg.boss.battles.mobs.Mob;
import com.blockout22.rpg.boss.battles.mobs.Player;
import com.blockout22.rpg.boss.battles.screens.helper.BackListener;
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

    private boolean mobLowHp = false;
    private boolean playerLowHp = false;

    public FightScreen(final Player player, final Mob mob){
        super(player);
        this.mob = mob;
        player.reset();
        mob.reset();

        bottomBar = new Table();
        mobStack = new Stack();
        playerStack = new Stack();

        mobHealthLabel = new VisLabel(mob.getStats().getCurrentHealth() + "/" + mob.getStats().getMaxhealth());

        playerHealthLabel = new VisLabel(getPlayer().getStats().getCurrentHealth() + "/" + getPlayer().getStats().getMaxhealth());

        mobName = new VisLabel(mob.getName());
        mobHealth = new VisProgressBar(0, mob.getStats().getMaxhealth(), 0.1f, false);
        mobHealth.setValue(mob.getStats().getCurrentHealth());

        playerHealth = new VisProgressBar(0, player.getStats().getMaxhealth(), 0.1f, false);
        playerHealth.setValue(player.getStats().getCurrentHealth());

        attackButton = new VisTextButton(Statics.getBundle().get("attack"));
        backConfirm = new VisTextButton(Statics.getBundle().get("backScreen"));

        dialog = new VisDialog(Statics.getBundle().get("areYouSure"));

        attackButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                attackButton.setDisabled(true);
                long dmg = getPlayer().hit(mob);
                FloatingText t = new FloatingText(dmg > 0 ? "-" + dmg : Statics.getBundle().get("missed"), 1000);
                t.setPosition(getViewport().getWorldWidth() * 0.9f, getViewport().getWorldHeight() * 0.9f);
                t.setDeltaY(50);
                t.animate();
                getStage().addActor(t);
                if(dmg > 0){
                    mobHealthLabel.setText(mob.getStats().getCurrentHealth() + "/" + mob.getStats().getMaxhealth());
                }
                lastPlayerHit = System.currentTimeMillis();
            }
        });

        backConfirm.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
//                Color c = dialog.getColor();
//                c.a = 0;
//                dialog.setColor(c);
                dialog.show(getStage());
            }
        });

        setOnBackListener(new BackListener() {
            @Override
            public void action() {
//                System.out.println("ACTION!!!!");
                dialog.show(getStage());
            }
        });

        mobStack.add(mobHealth);
        mobStack.add(mobHealthLabel);

        mobHealthLabel.setAlignment(Align.center);

        playerStack.add(playerHealth);
        playerStack.add(playerHealthLabel);
        playerHealthLabel.setAlignment(Align.center);

        rootTable.add(mobName).top().pad(5).row();
        rootTable.add(mobStack).top().fillX().expand().pad(5).row();
        rootTable.add(playerStack).fillX().pad(5).row();
        rootTable.add(bottomBar).fillX();
        bottomBar.add(attackButton).center();
        bottomBar.add(backConfirm).expand().right();

        VisTextButton back = new VisTextButton(Statics.getBundle().get("yes"));
//        dialog.setKeepWithinParent(true);
        dialog.button(back);
        dialog.button(Statics.getBundle().get("no"));

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Statics.backScreen();
            }
        });

        lastMobHit = System.currentTimeMillis();
        lastPlayerHit = System.currentTimeMillis();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
//        damageDeltToMob.setPosition(mobHealthLabel.getX() + mobHealthLabel.getPrefWidth(), 0);

        if(!mob.isDead() && !getPlayer().isDead()){
            //checks if mob cool down time has passed
            if(TimeUtils.timeSinceMillis(lastMobHit) > mob.getAttackSpeed()){
                long dmg = mob.hit(getPlayer());

                FloatingText t = new FloatingText(dmg > 0 ? "-" + dmg : Statics.getBundle().get("missed"), 1000);
                t.setPosition(getViewport().getWorldWidth() * 0.9f, getViewport().getWorldHeight() * 0.2f);
                t.setDeltaY(50);
                t.animate();
                getStage().addActor(t);

                if(dmg > 0){
                    playerHealthLabel.setText(getPlayer().getStats().getCurrentHealth() + "/" + getPlayer().getStats().getMaxhealth());
                }

                lastMobHit = System.currentTimeMillis();
            }

            if(attackButton.isDisabled()){
                long t = getPlayer().getAttackSpeed() - (TimeUtils.timeSinceMillis(lastPlayerHit));
                attackButton.setText(((t / 1000) + 1) + " " + Statics.getBundle().get("seconds"));
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
                playerHealth.clearActions();
                mobHealth.clearActions();
                Statics.backScreen();
                MessageScreen ms = (MessageScreen)Statics.MESSAGE_SCREEN;
//                Statics.setScreen(ms.setText("you died!"));
                Statics.setScreen(ms.setText(Statics.getBundle().get("diedText")));
            }else if(mob.isDead()){
                playerHealth.clearActions();
                mobHealth.clearActions();
                getPlayer().rewardXp(mob.getRewardXp());
                MessageScreen ms = (MessageScreen)Statics.MESSAGE_SCREEN;
                //go back a screen to prevent player from clicking back onto the fight screen
                Statics.backScreen();
//                Statics.setScreen(ms.setText("you won!\nyou gained " + mob.getRewardXp() + " xp"));
                Statics.setScreen(ms.setText(Statics.getBundle().format("winText",mob.getRewardXp())));
            }
        }

        if(playerLowHp){
            if(!playerHealth.hasActions()){
                playerHealth.addAction(Actions.forever(Actions.sequence(
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                playerHealth.setColor(1, 0, 0, 1);
                            }
                        }),
                        Actions.delay(0.5f),
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                playerHealth.setColor(Color.WHITE);
                            }
                        }),
                        Actions.delay(0.5f)
                )));
            }
        }

        if(mobLowHp){
            if(!mobHealth.hasActions()){
                mobHealth.addAction(Actions.forever(Actions.sequence(
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                mobHealth.setColor(1, 0, 0, 1);
                            }
                        }),
                        Actions.delay(0.5f),
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                mobHealth.setColor(Color.WHITE);
                            }
                        }),
                        Actions.delay(0.5f)
                )));
            }
        }


        //checks if health is less than 30%
        if((Float.valueOf(getPlayer().getStats().getCurrentHealth()) / Float.valueOf(getPlayer().getStats().getMaxhealth()) * 100) <= 30){
            playerLowHp = true;
            playerHealthLabel.setColor(Color.RED);
        }

        if((Float.valueOf(mob.getStats().getCurrentHealth()) / Float.valueOf(mob.getStats().getMaxhealth()) * 100) <= 30){
            mobLowHp = true;
            mobHealthLabel.setColor(Color.RED);
        }

        mobHealth.setValue(mob.getStats().getCurrentHealth());
        playerHealth.setValue(getPlayer().getStats().getCurrentHealth());
    }
}
