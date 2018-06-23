package com.blockout22.rpg.boss.battles.mobs;

public class Stats {
    long currentHealth;
    long maxhealth;

    long accuracy;
    long strength;
    long dodge;

    public Stats(long maxhealth, long accuracy, long strength, long dodge){
        this.maxhealth = maxhealth;
        this.currentHealth = maxhealth;

        this.accuracy = accuracy;
        this.strength = strength;
        this.dodge = dodge;
    }

    public long getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(long accuracy) {
        this.accuracy = accuracy;
    }

    public long getDodge() {
        return dodge;
    }

    public void setDodge(long dodge) {
        this.dodge = dodge;
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
