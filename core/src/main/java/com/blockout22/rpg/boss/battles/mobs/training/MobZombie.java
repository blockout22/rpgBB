package com.blockout22.rpg.boss.battles.mobs.training;

import com.blockout22.rpg.boss.battles.mobs.Mob;
import com.blockout22.rpg.boss.battles.mobs.Stats;

public class MobZombie extends Mob {
    public MobZombie() {
        super("Zombie", new Stats(15, 5));
        setAttackSpeed(3000);
        setRewardXp(25);
        setDefaultInfo();
    }
}
