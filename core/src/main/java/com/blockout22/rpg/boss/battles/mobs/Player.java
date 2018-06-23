package com.blockout22.rpg.boss.battles.mobs;

import com.blockout22.rpg.boss.battles.Statics;

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

        //used  to check if any file data needs to be saved
        boolean requiresFlush = false;

        //checks if value exists, if false then create it
        if (!Statics.getPreferences().contains(Statics.PLAYER_MAX_HEALTH_XP)) {
            Statics.getPreferences().putLong(Statics.PLAYER_MAX_HEALTH_XP, healthLevel.levelToXp(10));
            requiresFlush = true;
        }

        if(!Statics.getPreferences().contains(Statics.PLAYER_ACCURACY_XP)){
            Statics.getPreferences().putLong(Statics.PLAYER_ACCURACY_XP, 0);
            requiresFlush = true;
        }

        if(!Statics.getPreferences().contains(Statics.PLAYER_STRENGTH_XP)){
            Statics.getPreferences().putLong(Statics.PLAYER_STRENGTH_XP, 0);
            requiresFlush = true;
        }

        if(!Statics.getPreferences().contains(Statics.PLAYER_DODGE_XP)){
            Statics.getPreferences().putLong(Statics.PLAYER_DODGE_XP, 0);
            requiresFlush = true;
        }

        if(!Statics.getPreferences().contains(Statics.PLAYER_SPEED)){
            Statics.getPreferences().putLong(Statics.PLAYER_SPEED, 5000);
            requiresFlush = true;
        }

        if(!Statics.getPreferences().contains(Statics.PLAYER_XP_BANK)){
            Statics.getPreferences().putLong(Statics.PLAYER_XP_BANK, 0);
            requiresFlush = true;
        }

        if(requiresFlush){
            Statics.getPreferences().flush();
        }

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

//        System.err.println(accuracyLevel.levelToXp(1) + "£" + accuracyLevel.levelToXp(2) + " : " + accuracyLevel.levelToXp(3));
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
        xpBank += xp;
        Statics.getPreferences().putLong(Statics.PLAYER_XP_BANK, xpBank);
        Statics.getPreferences().flush();
    }

    public long getXpBank(){
        return xpBank;
    }

    public class XpData{
        private long threshold = 83;
        private long xp;
        private long level = 1;

        public XpData()
        {

        }

        public void addXp(long amt){
            this.xp += amt;
            getLevel();
        }

        public void setXp(long xp){
            this.xp = xp;
        }

        /**
         * calculates the level you would be at which the xp;
         */
        public long xpToLevel(long xp){
            long level = (long)(1 + Math.sqrt(1 + 8 * xp / threshold)) / 2;
            return level;
        }

        /**
         * calculates the xp you would need to get to that level;
         */
        public long levelToXp(long level){
            long xp = (long)(((Math.pow(level, 2) - level) * threshold)) / 2;
            return xp;
        }

        public long getXp()
        {
            return xp;
        }

        public long getLevel(){
            level = xpToLevel(xp);
            return level;
        }
    }
}
