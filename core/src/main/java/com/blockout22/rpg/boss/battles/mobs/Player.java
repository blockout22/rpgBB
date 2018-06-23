package com.blockout22.rpg.boss.battles.mobs;

import com.blockout22.rpg.boss.battles.Statics;
import com.blockout22.rpg.boss.battles.XpData;

import java.util.Random;

public class Player extends Mob{

    //the amount of xp the player has saved and can use on any stat they choose
    private long xpBank = 0;
    private XpData accuracyLevel, strengthLevel, dodgeLevel, healthLevel;

    public Player() {
        super("Player", new Stats(0, 0, 0, 0));

        accuracyLevel = new XpData();
        strengthLevel = new XpData();
        dodgeLevel = new XpData();
        healthLevel = new XpData();

        long maxhealth_xp = Statics.getPreferences().getLong(Statics.PLAYER_MAX_HEALTH_XP);

        long accuracy_xp = Statics.getPreferences().getLong(Statics.PLAYER_ACCURACY_XP);
        long strength_xp = Statics.getPreferences().getLong(Statics.PLAYER_STRENGTH_XP);
        long dodge_xp = Statics.getPreferences().getLong(Statics.PLAYER_DODGE_XP);

        long speed = Statics.getPreferences().getLong(Statics.PLAYER_SPEED);
        xpBank = Statics.getPreferences().getLong(Statics.PLAYER_XP_BANK);

        healthLevel.setXp(maxhealth_xp);

        accuracyLevel.setXp(accuracy_xp);
        strengthLevel.setXp(strength_xp);
        dodgeLevel.setXp(dodge_xp);

        this.getStats().setAccuracy(accuracyLevel.getLevel());
        this.getStats().setStrength(strengthLevel.getLevel());
        this.getStats().setDodge(dodgeLevel.getLevel());

        this.getStats().setMaxhealth(healthLevel.getLevel());
        this.getStats().setCurrentHealth(getStats().getMaxhealth());
        setAttackSpeed(speed);
    }

    public XpData getStrengthXpData(){
        return strengthLevel;
    }

    public XpData getHealthXpData(){
        return healthLevel;
    }

    public XpData getAccuracyXpData() {
        return accuracyLevel;
    }

    public XpData getDodgeXpData() {
        return dodgeLevel;
    }

    public void rewardXp(long xp){
        if(xp > 0 && xpBank >= 999999999999999999l){
            return;
        }
        xpBank += xp;
        Statics.getPreferences().putLong(Statics.PLAYER_XP_BANK, xpBank);
        Statics.getPreferences().flush();
    }

    public long getXpBank(){
        return xpBank;
    }
}
