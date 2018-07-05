package com.blockout22.rpg.boss.battles.mobs.bosses;

import com.badlogic.gdx.graphics.Texture;
import com.blockout22.rpg.boss.battles.mobs.Mob;
import com.blockout22.rpg.boss.battles.mobs.Stats;

public class MobHallowFalcon extends Mob{

    public MobHallowFalcon() {
        super("The Hallow Falcon", new Stats(100, 50, 35, 45), new Texture("badlogic.jpg"));
        setAttackSpeed(2500);
        calcRewardXpFromStats();
//        setRewardXp(150);
        setDefaultInfo();
    }
}
