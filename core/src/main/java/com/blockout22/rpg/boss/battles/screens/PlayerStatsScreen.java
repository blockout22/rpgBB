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
    private VisTextButton addStrengthXp;
    private VisTextButton addHealthXp;
    private VisTextButton back;

//    private VisLabel xpBank;
    private VisLabel strengthXp;
    private VisLabel healthXp;

    public PlayerStatsScreen(final Player player) {
        super(player);
        this.player = player;

//        playerInfo = "Attacking Speed: " + player.getAttackSpeed() + " ms"
//                + "\nStrength: " + player.getStats().getStrength()
//                + "\nHealth: " + player.getStats().getMaxhealth();


//        playerInfo = Statics.getBundle().format("playerInfo", player.getAttackSpeed(), player.getStats().getStrength(), player.getStats().getMaxhealth());

        stats = new VisLabel();
        stats.setAlignment(Align.center);

        xpTable = new VisTable();
        addHealthXp = new VisTextButton("+");
        addStrengthXp = new VisTextButton("+");
        back = new VisTextButton(Statics.getBundle().get("backScreen"));

//        xpBank = new VisLabel("Xp Bank: " + player.getXpBank());
        healthXp = new VisLabel();
        strengthXp = new VisLabel();

        info = new VisLabel(playerInfo);

        statsScroll = new VisScrollPane(info);
        xpScroll = new VisScrollPane(xpTable);
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

//                stats.setText("Player Stats [Xp Bank: " + player.getXpBank() + "]");
//                healthXp.setText("Health Xp: " + player.getHealthXpData().getXp());
                healthXp.setText(Statics.getBundle().format("healthXp", player.getHealthXpData().getXp()));

                player.getStats().setMaxhealth(player.getHealthXpData().xpToLevel(healthExp));

//                playerInfo = "Attacking Speed: " + player.getAttackSpeed() + " ms"
//                        + "\nStrength: " + player.getStats().getStrength()
//                        + "\nHealth: " + player.getStats().getMaxhealth();

//                info.setText(playerInfo);

                playerInfo = Statics.getBundle().format("playerInfo", player.getAttackSpeed(), player.getStats().getStrength(), player.getStats().getMaxhealth());
                stats.setText(Statics.getBundle().format("title", player.getXpBank()));
                info.setText(playerInfo);

                if(player.getXpBank() <= 0){
                    addHealthXp.setDisabled(true);
                    addStrengthXp.setDisabled(true);
                }
            }
        });

        addStrengthXp.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                long strengthExp = player.getStrengthXpData().getXp();
                long expBank = player.getXpBank();
                if(expBank <= 0){
//                    addHealthXp.setDisabled(true);
                    return;
                }

                strengthExp += 1;
                player.getStrengthXpData().setXp(strengthExp);
                Statics.getPreferences().putLong(Statics.PLAYER_STRENGTH_XP, strengthExp);
                player.rewardXp(-1);

//                stats.setText("Player Stats [Xp Bank: " + player.getXpBank() + "]");
//                strengthXp.setText("Strength Xp: " + player.getStrengthXpData().getXp());
                strengthXp.setText(Statics.getBundle().format("strengthXp", player.getStrengthXpData().getXp()));

                player.getStats().setStrength(player.getStrengthXpData().xpToLevel(strengthExp));

//                playerInfo = "Attacking Speed: " + player.getAttackSpeed() + " ms"
//                        + "\nStrength: " + player.getStats().getStrength()
//                        + "\nHealth: " + player.getStats().getMaxhealth();

//                info.setText(playerInfo);

                playerInfo = Statics.getBundle().format("playerInfo", player.getAttackSpeed(), player.getStats().getStrength(), player.getStats().getMaxhealth());
                stats.setText(Statics.getBundle().format("title", player.getXpBank()));
                info.setText(playerInfo);

                if(player.getXpBank() <= 0){
                    addHealthXp.setDisabled(true);
                    addStrengthXp.setDisabled(true);
                }
            }
        });

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Statics.backScreen();
            }
        });

//        xpTable.add(xpBank).pad(5);
        xpTable.row();
        xpTable.add(healthXp).pad(5);
        xpTable.add(addHealthXp).pad(5);
        xpTable.row();
        xpTable.add(strengthXp).pad(5);
        xpTable.add(addStrengthXp).pad(5);

        rootTable.add(stats).fillX();
        rootTable.row();
        rootTable.add(statsScroll);
        rootTable.row();
        rootTable.addSeparator();
        rootTable.add(xpScroll).fillX();

        rootTable.row();
        rootTable.add(back).expand().bottom().right();
    }

    @Override
    public void show() {
        super.show();

        if(player.getXpBank() <= 0){
            addHealthXp.setDisabled(true);
            addStrengthXp.setDisabled(true);
        }else{
            addHealthXp.setDisabled(false);
            addStrengthXp.setDisabled(false);
        }

//        playerInfo = "Attacking Speed: " + player.getAttackSpeed() + " ms"
//                + "\nStrength: " + player.getStats().getStrength()
//                + "\nHealth: " + player.getStats().getMaxhealth();

        playerInfo = Statics.getBundle().format("playerInfo", player.getAttackSpeed(), player.getStats().getStrength(), player.getStats().getMaxhealth());
        stats.setText(Statics.getBundle().format("title", player.getXpBank()));
        info.setText(playerInfo);
//        stats.setText("Player Stats [Xp Bank: " + player.getXpBank() + "]");

//        xpBank.setText("Xp Bank: " + player.getXpBank());
        healthXp.setText(Statics.getBundle().format("healthXp", player.getHealthXpData().getXp()));
        strengthXp.setText(Statics.getBundle().format("strengthXp", player.getHealthXpData().getXp()));
//        healthXp.setText("Health Xp: " + player.getHealthXpData().getXp());
//        strengthXp.setText("Strength Xp: " + player.getStrengthXpData().getXp());
    }
}
