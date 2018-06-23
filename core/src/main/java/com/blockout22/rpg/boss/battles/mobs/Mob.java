package com.blockout22.rpg.boss.battles.mobs;

import com.blockout22.rpg.boss.battles.Statics;
import com.blockout22.rpg.boss.battles.SuccessfulHitCallback;

import java.security.SecureRandom;
import java.util.Random;

public abstract class Mob {

    private final Stats stats;
    private final String name;
    private boolean isDead = false;
    private SecureRandom r = new SecureRandom();
    private long rewardXp = 0;
    private SuccessfulHitCallback hitCallback = null;

    //how often the mob will try to hit the player in milliseconds
    private long attackSpeed = -1;
    private String info = "This is suppose to have information about the mob you clicked on!";

    public Mob(String name, Stats stats){
        this.name = name;
        this.stats = stats;
    }

    public void setDefaultInfo()
    {
        long attackSpeed = getAttackSpeed() / 1000;
        if(attackSpeed > 0) {
            setInfo(Statics.getBundle().format("defaultInfo", (attackSpeed), getStats().getStrength(), getStats().getMaxhealth(), getRewardXp()));
        }else{
            setInfo(Statics.getBundle().format("defaultInfoNoAttack", getStats().getMaxhealth(), getRewardXp()));
        }
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

        float acc = getStats().getAccuracy();
        float dod = other.getStats().getDodge();
        float chance = acc / (acc+dod);

        float m = r.nextFloat();

        long damage = 0;
        if(!this.isDead){
            if(m < chance) {
                damage = r.nextInt((int) (getStats().strength + 1));
                if(hitCallback != null){
                    hitCallback.callback(damage, other);
                }
                other.damage(damage);
            }
        }
        return damage;
    }

    public void setSuccessfulHitcallback(SuccessfulHitCallback callback){
        this.hitCallback = callback;
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
