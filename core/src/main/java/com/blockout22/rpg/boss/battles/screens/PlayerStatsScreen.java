package com.blockout22.rpg.boss.battles.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.blockout22.rpg.boss.battles.Statics;
import com.blockout22.rpg.boss.battles.mobs.Player;
import com.blockout22.rpg.boss.battles.screens.helper.ScreenStage;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class PlayerStatsScreen extends ScreenStage {

    private final Player player;
    private VisLabel stats;
    private VisLabel info;
    private VisScrollPane statsScroll;
    private VisScrollPane xpScroll;
    private String playerInfo;

    private VisTable xpTable;

    private VisTextButton addHealthXp;
    private VisTextButton addAccuracyXp;
    private VisTextButton addStrengthXp;
    private VisTextButton addDodgeXp;

    private VisTextButton addHealthLevel;
    private VisTextButton addAccuracyLevel;
    private VisTextButton addStrengthLevel;
    private VisTextButton addDodgeLevel;

    private VisTextButton back;

//    private VisLabel xpBank;
    private VisLabel accuracyXp;
    private VisLabel strengthXp;
    private VisLabel dodgeXp;
    private VisLabel healthXp;

    public PlayerStatsScreen(final Player player) {
        super(player);
        this.player = player;

        stats = new VisLabel();
        stats.setWrap(true);
        stats.setAlignment(Align.center);

        xpTable = new VisTable();
        addHealthXp = new VisTextButton("+");
        addAccuracyXp = new VisTextButton("+");
        addStrengthXp = new VisTextButton("+");
        addDodgeXp = new VisTextButton("+");

        String addLevel = Statics.getBundle().get("addLevel");
        addHealthLevel = new VisTextButton(addLevel);
        addAccuracyLevel = new VisTextButton(addLevel);
        addStrengthLevel = new VisTextButton(addLevel);
        addDodgeLevel = new VisTextButton(addLevel);

        back = new VisTextButton(Statics.getBundle().get("backScreen"));

//        xpBank = new VisLabel("Xp Bank: " + player.getXpBank());
        healthXp = new VisLabel();
        accuracyXp = new VisLabel();
        strengthXp = new VisLabel();
        dodgeXp = new VisLabel();

        info = new VisLabel(playerInfo);

        statsScroll = new VisScrollPane(info);
        xpScroll = new VisScrollPane(xpTable);
        statsScroll.setFadeScrollBars(false);
        xpScroll.setFadeScrollBars(false);

        addHealthXp.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                long healthExp = player.getHealthXpData().getXp();
                long expBank = player.getXpBank();
                if(expBank <= 0){
                    return;
                }

                healthExp += 1;
                player.getHealthXpData().setXp(healthExp);
                Statics.getPreferences().putLong(Statics.PLAYER_MAX_HEALTH_XP, healthExp);
                player.rewardXp(-1);

                player.getStats().setMaxhealth(player.getHealthXpData().xpToLevel(healthExp));

                updateInfo();
                validateButtonStates();
            }
        });

        addHealthLevel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                long healthExp = player.getHealthXpData().getXp();
                //the amount of xp required to get to the next level
                long reqHealthXp = player.getHealthXpData().levelToXp(player.getHealthXpData().getLevel() + 1) - player.getHealthXpData().getXp();
                long expBank = player.getXpBank();
                if(expBank < reqHealthXp){
                    return;
                }

                healthExp += reqHealthXp;
                player.getHealthXpData().setXp(healthExp);
                Statics.getPreferences().putLong(Statics.PLAYER_MAX_HEALTH_XP, healthExp);
                player.rewardXp(-reqHealthXp);

                player.getStats().setMaxhealth(player.getHealthXpData().xpToLevel(healthExp));

                updateInfo();
                validateButtonStates();
            }
        });

        addAccuracyXp.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                long accuracyExp = player.getAccuracyXpData().getXp();
                long expBank = player.getXpBank();
                if(expBank <= 0){
                    return;
                }

                accuracyExp += 1;
                player.getAccuracyXpData().setXp(accuracyExp);
                Statics.getPreferences().putLong(Statics.PLAYER_ACCURACY_XP, accuracyExp);
                player.rewardXp(-1);

                player.getStats().setAccuracy(player.getAccuracyXpData().xpToLevel(accuracyExp));

                updateInfo();
                validateButtonStates();
            }
        });

        addAccuracyLevel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                long accExp = player.getAccuracyXpData().getXp();
                //the amount of xp required to get to the next level
                long reqAccXp = player.getAccuracyXpData().levelToXp(player.getAccuracyXpData().getLevel() + 1) - player.getAccuracyXpData().getXp();
                long expBank = player.getXpBank();
                if(expBank < reqAccXp){
                    return;
                }

                accExp += reqAccXp;
                player.getAccuracyXpData().setXp(accExp);
                Statics.getPreferences().putLong(Statics.PLAYER_ACCURACY_XP, accExp);
                player.rewardXp(-reqAccXp);

                player.getStats().setAccuracy(player.getAccuracyXpData().xpToLevel(accExp));

                updateInfo();
                validateButtonStates();

            }
        });

        addStrengthXp.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                long strengthExp = player.getStrengthXpData().getXp();
                long expBank = player.getXpBank();
                if(expBank <= 0){
                    return;
                }

                strengthExp += 1;
                player.getStrengthXpData().setXp(strengthExp);
                Statics.getPreferences().putLong(Statics.PLAYER_STRENGTH_XP, strengthExp);
                player.rewardXp(-1);

                player.getStats().setStrength(player.getStrengthXpData().xpToLevel(strengthExp));

                updateInfo();
                validateButtonStates();
            }
        });

        addStrengthLevel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                long strExp = player.getStrengthXpData().getXp();
                //the amount of xp required to get to the next level
                long reqStrXp = player.getStrengthXpData().levelToXp(player.getStrengthXpData().getLevel() + 1) - player.getStrengthXpData().getXp();
                long expBank = player.getXpBank();
                if(expBank < reqStrXp){
                    return;
                }

                strExp += reqStrXp;
                player.getStrengthXpData().setXp(strExp);
                Statics.getPreferences().putLong(Statics.PLAYER_STRENGTH_XP, strExp);
                player.rewardXp(-reqStrXp);

                player.getStats().setStrength(player.getStrengthXpData().xpToLevel(strExp));

                updateInfo();
                validateButtonStates();

            }
        });

        addDodgeXp.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                long dodgeExp = player.getDodgeXpData().getXp();
                long expBank = player.getXpBank();
                if(expBank <= 0){
                    return;
                }

                dodgeExp += 1;
                player.getDodgeXpData().setXp(dodgeExp);
                Statics.getPreferences().putLong(Statics.PLAYER_DODGE_XP, dodgeExp);
                player.rewardXp(-1);

                player.getStats().setDodge(player.getDodgeXpData().xpToLevel(dodgeExp));

                updateInfo();
                validateButtonStates();
            }
        });

        addDodgeLevel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                long dodgeExp = player.getDodgeXpData().getXp();
                //the amount of xp required to get to the next level
                long reqDodgeXp = player.getDodgeXpData().levelToXp(player.getDodgeXpData().getLevel() + 1) - player.getDodgeXpData().getXp();
                long expBank = player.getXpBank();
                if(expBank < reqDodgeXp){
                    return;
                }

                dodgeExp += reqDodgeXp;
                player.getDodgeXpData().setXp(dodgeExp);
                Statics.getPreferences().putLong(Statics.PLAYER_DODGE_XP, dodgeExp);
                player.rewardXp(-reqDodgeXp);

                player.getStats().setDodge(player.getDodgeXpData().xpToLevel(dodgeExp));

                updateInfo();
                validateButtonStates();

            }
        });

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Statics.backScreen();
            }
        });

//        xpTable.add(xpBank).pad(5);
        xpTable.add(healthXp).pad(5);
        xpTable.add(addHealthXp).pad(5);
        xpTable.add(addHealthLevel).pad(5).row();

        xpTable.add(accuracyXp).pad(5);
        xpTable.add(addAccuracyXp).pad(5);
        xpTable.add(addAccuracyLevel).pad(5).row();

        xpTable.add(strengthXp).pad(5);
        xpTable.add(addStrengthXp).pad(5);
        xpTable.add(addStrengthLevel).pad(5).row();

        xpTable.add(dodgeXp).pad(5);
        xpTable.add(addDodgeXp).pad(5);
        xpTable.add(addDodgeLevel).pad(5);

        boolean statView = Statics.getPreferences().getBoolean(Statics.PLAYER_STATS_UI_ORIENTATION);

        if(!statView){
            setHorizontal();
        }else{
            setVertical();
        }
    }

    private void setHorizontal() {
        rootTable.clearChildren();

        rootTable.add(stats).fillX();
        rootTable.row();

        rootTable.add(statsScroll).pad(5).fillX();
        rootTable.row();
        rootTable.addSeparator();
        rootTable.add(xpScroll).pad(5).fillX();

        rootTable.row();
        rootTable.add(back).bottom().right();
    }

    private void setVertical()
    {
        rootTable.clearChildren();
        rootTable.add(stats).fillX();
        rootTable.row();

        VisTable t = new VisTable();
        rootTable.add(t).grow();
        t.add(statsScroll).fillY();
        t.addSeparator(true);
        t.add(xpScroll).fillY();

        rootTable.row();
        rootTable.add(back).bottom().right();
    }


    private void updateInfo()
    {
        healthXp.setText(Statics.getBundle().format("healthXp", player.getHealthXpData().getXp()));
        accuracyXp.setText(Statics.getBundle().format("accuracyXp", player.getAccuracyXpData().getXp()));
        strengthXp.setText(Statics.getBundle().format("strengthXp", player.getStrengthXpData().getXp()));
        dodgeXp.setText(Statics.getBundle().format("dodgeXp", player.getDodgeXpData().getXp()));

        playerInfo = Statics.getBundle().format("playerInfo", player.getAttackSpeed(), player.getStats().getMaxhealth(), player.getStats().getAccuracy(), player.getStats().getStrength(), player.getStats().getDodge());
        stats.setText(Statics.getBundle().format("title", player.getXpBank()));
        info.setText(playerInfo);
    }

    private void validateButtonStates()
    {
        long healthXp = player.getHealthXpData().levelToXp(player.getHealthXpData().getLevel() + 1) - player.getHealthXpData().getXp();
        long accXp = player.getAccuracyXpData().levelToXp(player.getAccuracyXpData().getLevel() + 1) - player.getAccuracyXpData().getXp();
        long strXp = player.getStrengthXpData().levelToXp(player.getStrengthXpData().getLevel() + 1) - player.getStrengthXpData().getXp();
        long dodgeXp = player.getDodgeXpData().levelToXp(player.getDodgeXpData().getLevel() + 1) - player.getDodgeXpData().getXp();
        long xpBank = player.getXpBank();

        if(xpBank < healthXp){
            addHealthLevel.setDisabled(true);
        }else{
            addHealthLevel.setDisabled(false);
        }

        if(xpBank < accXp){
            addAccuracyLevel.setDisabled(true);
        }else{
            addAccuracyLevel.setDisabled(false);
        }

        if(xpBank < strXp){
            addStrengthLevel.setDisabled(true);
        }else{
            addStrengthLevel.setDisabled(false);
        }

        if(xpBank < dodgeXp){
            addDodgeLevel.setDisabled(true);
        }else{
            addDodgeLevel.setDisabled(false);
        }

        if(player.getXpBank() <= 0){
            addHealthXp.setDisabled(true);
            addAccuracyXp.setDisabled(true);
            addStrengthXp.setDisabled(true);
            addDodgeXp.setDisabled(true);
        }else{
            addHealthXp.setDisabled(false);
            addAccuracyXp.setDisabled(false);
            addStrengthXp.setDisabled(false);
            addDodgeXp.setDisabled(false);
        }
    }

    @Override
    public void show() {
        super.show();

        validateButtonStates();
        updateInfo();
    }
}
