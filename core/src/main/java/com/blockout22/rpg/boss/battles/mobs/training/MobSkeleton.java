package com.blockout22.rpg.boss.battles.mobs.training;

import com.badlogic.gdx.graphics.Texture;
import com.blockout22.rpg.boss.battles.mobs.Mob;
import com.blockout22.rpg.boss.battles.mobs.Stats;

public class MobSkeleton extends Mob {
    public MobSkeleton() {
        super("Skeleton", new Stats(20, 5, 6, 5), new Texture("badlogic.jpg"));
        setAttackSpeed(3000);
//        setRewardXp(25);
        calcRewardXpFromStats();
        setDefaultInfo();
    }
}
