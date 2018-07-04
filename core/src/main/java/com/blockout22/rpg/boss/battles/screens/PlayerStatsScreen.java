package com.blockout22.rpg.boss.battles.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;
import com.blockout22.rpg.boss.battles.Statics;
import com.blockout22.rpg.boss.battles.mobs.Player;
import com.blockout22.rpg.boss.battles.screens.helper.ScreenStage;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class PlayerStatsScreen extends ScreenStage {

    private final Player player;
    private VisLabel titleTable;
    private VisLabel info;
    private VisScrollPane statsScroll;
    private VisScrollPane xpScroll;
    private String playerInfo;

    private VisTable xpTable;

    private VisTextButton selectedButton = null;

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

    private long prevTime = System.currentTimeMillis();
    private long prevTimeSpeed = System.currentTimeMillis();
    //how fast to add xp when button is held down
    private int xpSpeedIncrease = 1;

    public PlayerStatsScreen(final Player player) {
        super(player);
        this.player = player;

        titleTable = new VisLabel();
        titleTable.setWrap(true);
        titleTable.setAlignment(Align.center);

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
                increaseHealthXp(1);
            }
        });

        addHealthLevel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                long healthExp = player.getHealthXpData().getXp();
                //the amount of xp required to get to the next level
                long reqHealthXp = player.getHealthXpData().levelToXp(player.getHealthXpData().getLevel() + 1) - player.getHealthXpData().getXp();
                long expBank = player.getXpBank();
                if (expBank < reqHealthXp) {
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
                increaseAccuracyXp(1);
            }
        });

        addAccuracyLevel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                long accExp = player.getAccuracyXpData().getXp();
                //the amount of xp required to get to the next level
                long reqAccXp = player.getAccuracyXpData().levelToXp(player.getAccuracyXpData().getLevel() + 1) - player.getAccuracyXpData().getXp();
                long expBank = player.getXpBank();
                if (expBank < reqAccXp) {
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
                increaseStrengthXp(1);
            }
        });

        addStrengthLevel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                long strExp = player.getStrengthXpData().getXp();
                //the amount of xp required to get to the next level
                long reqStrXp = player.getStrengthXpData().levelToXp(player.getStrengthXpData().getLevel() + 1) - player.getStrengthXpData().getXp();
                long expBank = player.getXpBank();
                if (expBank < reqStrXp) {
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
                increaseDodgeXp(1);
            }
        });

        addDodgeLevel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                long dodgeExp = player.getDodgeXpData().getXp();
                //the amount of xp required to get to the next level
                long reqDodgeXp = player.getDodgeXpData().levelToXp(player.getDodgeXpData().getLevel() + 1) - player.getDodgeXpData().getXp();
                long expBank = player.getXpBank();
                if (expBank < reqDodgeXp) {
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

        xpTable.row();
        xpTable.add().expand();

        boolean statView = Statics.getPreferences().getBoolean(Statics.PLAYER_STATS_UI_ORIENTATION);

        if (!statView) {
            setHorizontal();
        } else {
            setVertical();
        }
    }

    private void setHorizontal() {
        rootTable.clearChildren();

        rootTable.add(titleTable).fillX();
        rootTable.row();


        rootTable.add(statsScroll).pad(5).fillX();
        rootTable.row();
        rootTable.addSeparator();
//        rootTable.add(xpScroll).grow().pad(5).fillX();
        rootTable.add(xpScroll).grow().row();

        rootTable.row();
        rootTable.add(back).expand().bottom().right();
    }

    private void setVertical() {
        rootTable.clearChildren();
        rootTable.add(titleTable).fillX();
        rootTable.row();

        VisTable t = new VisTable();
        rootTable.add(t).grow();
        t.add(statsScroll).fillY();
        t.addSeparator(true);
        t.add(xpScroll).fillY();

        rootTable.row();
        rootTable.add(back).bottom().right();
    }


    private void updateInfo() {
        healthXp.setText(Statics.getBundle().format("healthXp", player.getHealthXpData().getXp()));
        accuracyXp.setText(Statics.getBundle().format("accuracyXp", player.getAccuracyXpData().getXp()));
        strengthXp.setText(Statics.getBundle().format("strengthXp", player.getStrengthXpData().getXp()));
        dodgeXp.setText(Statics.getBundle().format("dodgeXp", player.getDodgeXpData().getXp()));

        playerInfo = Statics.getBundle().format("playerInfo", player.getAttackSpeed(), player.getStats().getMaxhealth(), player.getStats().getAccuracy(), player.getStats().getStrength(), player.getStats().getDodge());
        titleTable.setText(Statics.getBundle().format("title", player.getXpBank()));
        info.setText(playerInfo);
    }

    private void validateButtonStates() {
        long healthXp = player.getHealthXpData().levelToXp(player.getHealthXpData().getLevel() + 1) - player.getHealthXpData().getXp();
        long accXp = player.getAccuracyXpData().levelToXp(player.getAccuracyXpData().getLevel() + 1) - player.getAccuracyXpData().getXp();
        long strXp = player.getStrengthXpData().levelToXp(player.getStrengthXpData().getLevel() + 1) - player.getStrengthXpData().getXp();
        long dodgeXp = player.getDodgeXpData().levelToXp(player.getDodgeXpData().getLevel() + 1) - player.getDodgeXpData().getXp();
        long xpBank = player.getXpBank();

        if (xpBank < healthXp) {
            addHealthLevel.setDisabled(true);
        } else {
            addHealthLevel.setDisabled(false);
        }

        if (xpBank < accXp) {
            addAccuracyLevel.setDisabled(true);
        } else {
            addAccuracyLevel.setDisabled(false);
        }

        if (xpBank < strXp) {
            addStrengthLevel.setDisabled(true);
        } else {
            addStrengthLevel.setDisabled(false);
        }

        if (xpBank < dodgeXp) {
            addDodgeLevel.setDisabled(true);
        } else {
            addDodgeLevel.setDisabled(false);
        }

        if (player.getXpBank() <= 0) {
            addHealthXp.setDisabled(true);
            addAccuracyXp.setDisabled(true);
            addStrengthXp.setDisabled(true);
            addDodgeXp.setDisabled(true);
        } else {
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

    @Override
    public void render(float delta) {
        super.render(delta);

        if (addHealthXp.isPressed()) {
            if (TimeUtils.timeSinceMillis(prevTime) > 150) {
                if (selectedButton != addHealthXp) {
                    prevTime = System.currentTimeMillis() + 500;
                    selectedButton = addHealthXp;
//                    increaseHealthXp(1 * xpSpeedIncrease);
                    prevTimeSpeed = System.currentTimeMillis();
                } else {
                    prevTime = System.currentTimeMillis();
                    increaseHealthXp(1 * xpSpeedIncrease);
                    increaseXpSpeed();
                }
            }
        } else if (addAccuracyXp.isPressed()) {
            if (TimeUtils.timeSinceMillis(prevTime) > 150) {
                if (selectedButton != addAccuracyXp) {
                    prevTime = System.currentTimeMillis() + 500;
                    selectedButton = addAccuracyXp;
//                    increaseAccuracyXp(1 * xpSpeedIncrease);
                    prevTimeSpeed = System.currentTimeMillis();
                } else {
                    prevTime = System.currentTimeMillis();
                    increaseAccuracyXp(1 * xpSpeedIncrease);
                    increaseXpSpeed();
                }
            }
        } else if (addStrengthXp.isPressed()) {
            if (TimeUtils.timeSinceMillis(prevTime) > 150) {
                if (selectedButton != addStrengthXp) {
                    prevTime = System.currentTimeMillis() + 500;
                    selectedButton = addStrengthXp;
//                    increaseStrengthXp(1 * xpSpeedIncrease);
                    prevTimeSpeed = System.currentTimeMillis();
                } else {
                    prevTime = System.currentTimeMillis();
                    increaseStrengthXp(1 * xpSpeedIncrease);
                    increaseXpSpeed();
                }
            }
        } else if (addDodgeXp.isPressed()) {
            if (TimeUtils.timeSinceMillis(prevTime) > 150) {
                if (selectedButton != addDodgeXp) {
                    prevTime = System.currentTimeMillis() + 500;
                    selectedButton = addDodgeXp;
//                    increaseDodgeXp(1 * xpSpeedIncrease);
                    prevTimeSpeed = System.currentTimeMillis();
                } else {
                    prevTime = System.currentTimeMillis();
                    increaseDodgeXp(1 * xpSpeedIncrease);
                    increaseXpSpeed();
                }
            }
        } else {
            selectedButton = null;
            xpSpeedIncrease = 1;
        }
    }

    private void increaseXpSpeed() {
        if (TimeUtils.timeSinceMillis(prevTimeSpeed) > 150) {
            xpSpeedIncrease++;

            if (xpSpeedIncrease > 100) {
                xpSpeedIncrease = 100;
            }

            prevTimeSpeed = System.currentTimeMillis();
        }
    }

    private void increaseHealthXp(int amt) {
        long healthExp = player.getHealthXpData().getXp();
        long expBank = player.getXpBank();
        if (expBank < amt) {
            return;
        }

        healthExp += amt;
        player.getHealthXpData().setXp(healthExp);
        Statics.getPreferences().putLong(Statics.PLAYER_MAX_HEALTH_XP, healthExp);
        player.rewardXp(-amt);

        player.getStats().setMaxhealth(player.getHealthXpData().xpToLevel(healthExp));

        updateInfo();
        validateButtonStates();
    }

    private void increaseAccuracyXp(int amt) {
        long accuracyExp = player.getAccuracyXpData().getXp();
        long expBank = player.getXpBank();
        if (expBank < amt) {
            return;
        }

        accuracyExp += amt;
        player.getAccuracyXpData().setXp(accuracyExp);
        Statics.getPreferences().putLong(Statics.PLAYER_ACCURACY_XP, accuracyExp);
        player.rewardXp(-amt);

        player.getStats().setAccuracy(player.getAccuracyXpData().xpToLevel(accuracyExp));

        updateInfo();
        validateButtonStates();
    }

    private void increaseStrengthXp(int amt) {
        long strengthExp = player.getStrengthXpData().getXp();
        long expBank = player.getXpBank();
        if (expBank < amt) {
            return;
        }

        strengthExp += amt;
        player.getStrengthXpData().setXp(strengthExp);
        Statics.getPreferences().putLong(Statics.PLAYER_STRENGTH_XP, strengthExp);
        player.rewardXp(-amt);

        player.getStats().setStrength(player.getStrengthXpData().xpToLevel(strengthExp));

        updateInfo();
        validateButtonStates();
    }

    private void increaseDodgeXp(int amt) {
        long dodgeExp = player.getDodgeXpData().getXp();
        long expBank = player.getXpBank();
        if (expBank < amt) {
            return;
        }

        dodgeExp += amt;
        player.getDodgeXpData().setXp(dodgeExp);
        Statics.getPreferences().putLong(Statics.PLAYER_DODGE_XP, dodgeExp);
        player.rewardXp(-amt);

        player.getStats().setDodge(player.getDodgeXpData().xpToLevel(dodgeExp));

        updateInfo();
        validateButtonStates();
    }
}
