package com.blockout22.rpg.boss.battles.mobs;

import com.badlogic.gdx.graphics.Texture;
import com.blockout22.rpg.boss.battles.OnDeadCallback;
import com.blockout22.rpg.boss.battles.Resurrection;
import com.blockout22.rpg.boss.battles.Statics;
import com.blockout22.rpg.boss.battles.SuccessfulHitCallback;

import java.security.SecureRandom;

public abstract class Mob {

    private Stats stats;
    private final String name;
    private boolean isDead = false;
    private SecureRandom r = new SecureRandom();
    private long rewardXp = 0;
    private SuccessfulHitCallback hitCallback = null;
//    private Resurrection resurrection = null;
    private OnDeadCallback onDeadCallback;

    private Texture image;

    //how often the mob will try to hit the player in milliseconds
    private long attackSpeed = -1;
    private String info = "This is suppose to have information about the mob you clicked on!";

    public Mob(String name, Stats stats, Texture image){
        this.name = name;
        this.stats = stats;
        this.image = image;
    }

    public Texture getImage() {
        return image;
    }

    public void dispose()
    {
        image.dispose();
    }

    //    /**
//     * gives the mob a resurrection property which will allow the mob to come back to life the set arguments
//     * @param resurrection
//     */
//    public void setResurrection(Resurrection resurrection){
//        this.resurrection = resurrection;
//    }
//
//    public Resurrection getResurrection() {
//        return resurrection;
//    }

    public void setOnDeadCallback(OnDeadCallback onDeadCallback){
        this.onDeadCallback = onDeadCallback;
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

    public void calcRewardXpFromStats()
    {
        long x = getStats().getMaxhealth() + (getStats().getAccuracy() / 2) + (getStats().getStrength()) + (getStats().getDodge() / 2);
        setRewardXp(x);
    }

    public void calcRewardXpFromStats(Stats stats)
    {
        long x = stats.getMaxhealth() + (stats.getAccuracy() / 2) + (stats.getStrength()) + (stats.getDodge() / 2);
        setRewardXp(x);
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
                if(damage == 0){
                    damage = 1;
                }

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
//            if(resurrection != null){
//                Stats stats = getResurrection().getStats();
//                setStats(stats);
//
//                setResurrection(null);
//            }else {
                setDead(true);
//            }
        }
    }

    /**
     * resets the player stats after dead
     */
    public void reset(){
        this.stats.setCurrentHealth(this.stats.getMaxhealth());
        setDead(false);
        System.out.println("Called");
    }

    private void setDead(boolean value){
        if(value){
            if(onDeadCallback != null){
                value =  onDeadCallback.action();
            }
        }
        this.isDead = value;
    }

    public boolean isDead() {
        return isDead;
    }

    public long getAttackSpeed() {
        return attackSpeed;
    }

    public void setStats(Stats stats){
        this.stats = stats;
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
