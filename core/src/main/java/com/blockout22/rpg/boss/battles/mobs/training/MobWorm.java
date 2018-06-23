package com.blockout22.rpg.boss.battles.mobs.training;

import com.blockout22.rpg.boss.battles.mobs.Mob;
import com.blockout22.rpg.boss.battles.mobs.Stats;

public class MobWorm extends Mob {

    public MobWorm() {
        super("Worm", new Stats(2, 1, 1, 0));
        setAttackSpeed(-1);
        setRewardXp(5);
        setDefaultInfo();
    }
}
