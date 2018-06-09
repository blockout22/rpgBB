package com.blockout22.rpg.boss.battles.mobs;

import java.util.Random;

public abstract class Mob {

    private final Stats stats;
    private final String name;
    private boolean isDead = false;
    private Random r = new Random();
    private long rewardXp = 0;

    //how often the mob will try to hit the player in milliseconds
    private long attackSpeed = 5000;
    private String info = "This is suppose to have information about the mob you clicked on!";

    public Mob(String name, Stats stats){
        this.name = name;
        this.stats = stats;
    }

    public void setDefaultInfo()
    {
        setInfo("Attacks the player every " +  (getAttackSpeed() / 1000) + " seconds hitting a maximum of " + getStats().getStrength() + " and has " + getStats().getMaxhealth() + " Health." +
                "\nThis mob rewards you with " + getRewardXp() + "xp When killed");
    }

    public void setRewardXp(long rewardXp) {
        this.rewardXp = rewardXp;
    }

    public long getRewardXp() {
        return rewardXp;
    }

    public void setAttackSpeed(long attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    /**
     * hits the player with a random number based on stats
     */
    public long hit(Mob other){
        long damage = 0;
        if(!this.isDead){
            damage = r.nextInt((int) (getStats().strength + 1));
            other.damage(damage);
        }
        return damage;
    }

    /**
     * the amount of damage to deal to the mob
     */
    public void damage(long amt){
        this.stats.setCurrentHealth(this.stats.getCurrentHealth() - amt);
        if(stats.getCurrentHealth() <= 0){
            setDead(true);
        }
    }

    /**
     * resets the player stats after dead
     */
    public void reset(){
        this.stats.setCurrentHealth(this.stats.getMaxhealth());
        setDead(false);
    }

    private void setDead(boolean value){
        this.isDead = value;
    }

    public boolean isDead() {
        return isDead;
    }

    public long getAttackSpeed() {
        return attackSpeed;
    }

    public Stats getStats() {
        return stats;
    }

    public String getName() {
        return name;
    }

    public void setInfo(String info){
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
