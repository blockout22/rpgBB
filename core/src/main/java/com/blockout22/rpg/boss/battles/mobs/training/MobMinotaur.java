package com.blockout22.rpg.boss.battles.mobs.training;

import com.badlogic.gdx.graphics.Texture;
import com.blockout22.rpg.boss.battles.mobs.Mob;
import com.blockout22.rpg.boss.battles.mobs.Stats;

public class MobMinotaur extends Mob{
    public MobMinotaur() {
        super("Minotaur", new Stats(45, 12, 14, 10), new Texture("square.png"));
        setAttackSpeed(3000);
        calcRewardXpFromStats();
        setDefaultInfo();
    }
}
