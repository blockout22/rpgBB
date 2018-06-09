package com.blockout22.rpg.boss.battles.mobs;

public class MobData {

    private final boolean isFree;
    private final Mob mob;

    public MobData(boolean free, Mob mob){
        this.isFree = free;
        this.mob = mob;
    }

    public Mob getMob() {
        return mob;
    }

    public boolean isFree() {
        return isFree;
    }
}
