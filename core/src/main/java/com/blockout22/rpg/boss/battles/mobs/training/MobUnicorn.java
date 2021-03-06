package com.blockout22.rpg.boss.battles.mobs.training;

import com.badlogic.gdx.graphics.Texture;
import com.blockout22.rpg.boss.battles.mobs.Mob;
import com.blockout22.rpg.boss.battles.mobs.Stats;

public class MobUnicorn extends Mob {
    public MobUnicorn() {
        super("Unicorn", new Stats(17, 4, 5, 5), new Texture("badlogic.jpg"));
        setAttackSpeed(3000);
//        setRewardXp(30);
        calcRewardXpFromStats();
        setDefaultInfo();
    }
}
