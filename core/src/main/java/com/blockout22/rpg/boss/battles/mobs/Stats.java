package com.blockout22.rpg.boss.battles.mobs;

public class Stats {
    long currentHealth;
    long maxhealth;
    long strength;

    public Stats(long maxhealth, long strength){
        this.maxhealth = maxhealth;
        this.currentHealth = maxhealth;
        this.strength = strength;
    }

    public void setMaxhealth(long maxhealth) {
        this.maxhealth = maxhealth;
    }

    public void setStrength(long strength) {
        this.strength = strength;
    }

    public long getStrength() {
        return strength;
    }

    public void setCurrentHealth(long currentHealth) {
        this.currentHealth = currentHealth;
    }

    public long getMaxhealth() {
        return maxhealth;
    }

    public long getCurrentHealth() {
        return currentHealth;
    }
}
