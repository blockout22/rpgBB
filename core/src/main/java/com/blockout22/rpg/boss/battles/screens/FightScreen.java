package com.blockout22.rpg.boss.battles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.blockout22.rpg.boss.battles.FloatingText;
import com.blockout22.rpg.boss.battles.Statics;
import com.blockout22.rpg.boss.battles.mobs.Mob;
import com.blockout22.rpg.boss.battles.mobs.Player;
import com.blockout22.rpg.boss.battles.mobs.Stats;
import com.blockout22.rpg.boss.battles.screens.helper.BackListener;
import com.blockout22.rpg.boss.battles.screens.helper.ScreenStage;
import com.blockout22.rpg.boss.battles.ui.HealthBar;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class FightScreen extends ScreenStage {

    private FightScreenOverlay overlay;
    private Mob mob;

    private Table bottomBar;
    private VisLabel mobName; // mobHealthLabel, playerHealthLabel;
//    private VisProgressBar mobHealth, playerHealth;
    private HealthBar mobHealth, playerHealth;
    private VisTextButton attackButton, backConfirm;
    private VisDialog dialog;

    private VisImage mobImage, playerImage;

    private boolean canVibrate = false;

    private Stack mobStack, playerStack;

    private String ATTACK_STRING = "Attack";

    private long lastMobHit, lastPlayerHit;

    private boolean mobLowHp = false;
    private boolean playerLowHp = false;
    private long lastPlayerHealth;
    private long lastMobHealth;

    private Stats mobStats = null;

    private boolean hasMobDiedTask = false;

    public FightScreen(final Player player, final Mob mob){
        super(player);
        overlay = new FightScreenOverlay(player, mob);
        this.mob = mob;
        this.mobStats = mob.getStats();
        player.reset();
        mob.reset();

        hasMobDiedTask = false;

        canVibrate = Statics.getPreferences().getBoolean(Statics.PLAYER_VIRBRATE_DAMAGE);

        bottomBar = new Table();
        mobStack = new Stack();
        playerStack = new Stack();

//        mobHealthLabel = new VisLabel(mobStats.getCurrentHealth() + "/" + mobStats.getMaxhealth());
        mobHealth = new HealthBar(0, mobStats.getMaxhealth(), 0.1f);
//        playerHealthLabel = new VisLabel(getPlayer().getStats().getCurrentHealth() + "/" + getPlayer().getStats().getMaxhealth());
        playerHealth = new HealthBar(0, getPlayer().getStats().getMaxhealth(), 0.1f);

        mobName = new VisLabel(mob.getName());
//        mobHealth = new VisProgressBar(0, mobStats.getMaxhealth(), 0.1f, false);
        mobHealth.setValue(mobStats.getCurrentHealth());
//        mobHealth.setAnimateDuration(1);

//        playerHealth = new VisProgressBar(0, player.getStats().getMaxhealth(), 0.1f, false);
        playerHealth.setValue(player.getStats().getCurrentHealth());
//        playerHealth.setAnimateDuration(1);

        attackButton = new VisTextButton(Statics.getBundle().get("attack"));
        backConfirm = new VisTextButton(Statics.getBundle().get("backScreen"));

        dialog = new VisDialog(Statics.getBundle().get("areYouSure"));

        mobImage = new VisImage(mob.getImage());
        playerImage = new VisImage(getPlayer().getImage());

        attackButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                attackButton.setDisabled(true);
                long dmg = getPlayer().hit(mob);

                if(dmg <= 0){
                    playerMissed();
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

        setOnBackListener(new BackListener() {
            @Override
            public void action() {
                dialog.show(getStage());
            }
        });

        mobStack.add(mobHealth);
//        mobStack.add(mobHealthLabel);

//        mobHealthLabel.setAlignment(Align.center);

        playerStack.add(playerHealth);
//        playerStack.add(playerHealthLabel);
//        playerHealthLabel.setAlignment(Align.center);

        rootTable.add(mobName).top().pad(5).row();
        rootTable.add(mobStack).top().fillX().pad(5).row();
        rootTable.add(overlay).grow().left().row();
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

        lastPlayerHealth = getPlayer().getStats().getCurrentHealth();
        lastMobHealth = mobStats.getCurrentHealth();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(!mob.isDead() && !getPlayer().isDead()){
            if(mob.getAttackSpeed() > 0) {
                //checks if mob cool down time has passed
                if (TimeUtils.timeSinceMillis(lastMobHit) > mob.getAttackSpeed()) {
                    long dmg = mob.hit(getPlayer());
                    if(dmg <= 0){
                        mobMissed();
                    }
                    lastMobHit = System.currentTimeMillis();
                }
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
                Statics.setScreen(ms.setText(Statics.getBundle().get("diedText")));

                dispose();
            }else if(mob.isDead()){
                if(!hasMobDiedTask) {
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            playerHealth.clearActions();
                            mobHealth.clearActions();
                            getPlayer().rewardXp(mob.getRewardXp());
                            MessageScreen ms = (MessageScreen) Statics.MESSAGE_SCREEN;
                            //go back a screen to prevent player from clicking back onto the fight screen
                            Statics.backScreen();
                            Statics.setScreen(ms.setText(Statics.getBundle().format("winText", mob.getRewardXp())));

                            dispose();
                        }
                    }, 0.50f);
                    hasMobDiedTask = true;
                }
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
//            playerHealthLabel.setColor(Color.RED);
        }else{
//            playerHealthLabel.setColor(Color.WHITE);
            playerHealth.clearActions();
            playerHealth.setColor(Color.WHITE);
            playerLowHp = false;
        }

        if((Float.valueOf(mobStats.getCurrentHealth()) / Float.valueOf(mobStats.getMaxhealth()) * 100) <= 30){
            mobLowHp = true;
//            mobHealthLabel.setColor(Color.RED);
        }else{
//            mobHealthLabel.setColor(Color.WHITE);
            mobHealth.clearActions();
            mobHealth.setColor(Color.WHITE);
            mobLowHp = false;
        }

        //check if mob has changed its stats during the fight
        if(!mobStats.compare(mob.getStats())){
            mobStats = mob.getStats();
            mobHealth.setRange(0, mobStats.getMaxhealth());
            mobHealth.setValue(mobStats.getCurrentHealth());
        }



        //checks if the player just lost some health
        if(lastPlayerHealth != getPlayer().getStats().getCurrentHealth()){
            boolean lower = getPlayer().getStats().getCurrentHealth() - lastPlayerHealth < 0 ? true : false;

            if(lower){
                overlay.damagePlayer();
            }

            if(canVibrate){
                Gdx.input.vibrate(100);
            }
            playerHealth.setValue(getPlayer().getStats().getCurrentHealth());

            playerHealthChanged();

            lastPlayerHealth = getPlayer().getStats().getCurrentHealth();
        }

        //checks if the mob just lost some health
        if(lastMobHealth != mobStats.getCurrentHealth()){

            boolean lower = mob.getStats().getCurrentHealth() - lastMobHealth < 0 ? true : false;

            if(lower){
                overlay.damageMob();
            }else{
                overlay.healMob();
            }
            mobHealth.setValue(mobStats.getCurrentHealth());

            mobHealthChanged();

            lastMobHealth = mobStats.getCurrentHealth();
        }

    }

    //displays missed icon on the mobs health when the player hits a 0
    private void playerMissed(){
        FloatingText t = new FloatingText(Statics.getBundle().get("missed"), 1000);
        t.setPosition(getViewport().getWorldWidth() * 0.9f, getViewport().getWorldHeight() * 0.9f);
        t.setDeltaY(50);
        t.animate();
        getStage().addActor(t);
    }

    //displays missed icon on the players health when the mob hits a 0;
    private void mobMissed(){
        FloatingText t = new FloatingText(Statics.getBundle().get("missed"), 1000);
        t.setPosition(getViewport().getWorldWidth() * 0.9f, getViewport().getWorldHeight() * 0.2f);
        t.setDeltaY(50);
        t.animate();
        getStage().addActor(t);
    }

    private void playerHealthChanged(){
        long dmg = lastPlayerHealth - getPlayer().getStats().getCurrentHealth();

        //NOTE this method isn't being called if damage is 0
        FloatingText t = new FloatingText(dmg != 0 ? dmg < 0 ? "+" : "-" + dmg : Statics.getBundle().get("missed"), 1000);
        t.setPosition(getViewport().getWorldWidth() * 0.9f, getViewport().getWorldHeight() * 0.2f);
        t.setDeltaY(50);
        t.animate();
        getStage().addActor(t);

        if (dmg != 0) {
//            playerHealthLabel.setText(getPlayer().getStats().getCurrentHealth() + "/" + getPlayer().getStats().getMaxhealth());
        }
    }

    private void mobHealthChanged()
    {
        long dmg = lastMobHealth - mobStats.getCurrentHealth();
        FloatingText t = new FloatingText(dmg != 0 ? dmg < 0 ? "+"  + -dmg : "-" + dmg : Statics.getBundle().get("missed"), 1000);
        //NOTE this method isn't being called if damage is 0
//        FloatingText t = new FloatingText(dmg > 0 ? "-" + dmg : Statics.getBundle().get("missed"), 1000);
        t.setPosition(getViewport().getWorldWidth() * 0.9f, getViewport().getWorldHeight() * 0.9f);
        t.setDeltaY(50);
        t.animate();
        getStage().addActor(t);
        if(dmg != 0){
//            mobHealthLabel.setText(mobStats.getCurrentHealth() + "/" + mobStats.getMaxhealth());
        }
    }
}
