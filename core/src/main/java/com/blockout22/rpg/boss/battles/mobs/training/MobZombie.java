package com.blockout22.rpg.boss.battles.mobs.training;

import com.badlogic.gdx.graphics.Texture;
import com.blockout22.rpg.boss.battles.mobs.Mob;
import com.blockout22.rpg.boss.battles.mobs.Stats;

public class MobZombie extends Mob {
    public MobZombie() {
        super("Zombie", new Stats(15, 4, 5, 3), new Texture("badlogic.jpg"));
        setAttackSpeed(3000);
//        setRewardXp(25);
        calcRewardXpFromStats();
        setDefaultInfo();
    }
}
