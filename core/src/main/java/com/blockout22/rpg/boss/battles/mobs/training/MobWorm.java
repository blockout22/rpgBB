package com.blockout22.rpg.boss.battles.mobs.training;

import com.badlogic.gdx.graphics.Texture;
import com.blockout22.rpg.boss.battles.mobs.Mob;
import com.blockout22.rpg.boss.battles.mobs.Stats;

public class MobWorm extends Mob {

    public MobWorm() {
        super("Worm", new Stats(2, 1, 1, 0), new Texture("square.png"));
        setAttackSpeed(-1);
//        setRewardXp(5);
        calcRewardXpFromStats();
        setDefaultInfo();
    }
}
