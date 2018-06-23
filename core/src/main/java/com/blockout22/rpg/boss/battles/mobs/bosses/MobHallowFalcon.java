package com.blockout22.rpg.boss.battles.mobs.bosses;

import com.blockout22.rpg.boss.battles.mobs.Mob;
import com.blockout22.rpg.boss.battles.mobs.Stats;

public class MobHallowFalcon extends Mob{

    public MobHallowFalcon() {
        super("The Hallow Falcon", new Stats(100, 50, 35, 45));
        setAttackSpeed(2500);
        setRewardXp(150);
        setDefaultInfo();
    }
}
