package com.blockout22.rpg.boss.battles.mobs.training;

import com.badlogic.gdx.graphics.Texture;
import com.blockout22.rpg.boss.battles.mobs.Mob;
import com.blockout22.rpg.boss.battles.mobs.Stats;

public class MobRat extends Mob {

    public MobRat() {
        super("Rat", new Stats(5, 1, 2, 1), new Texture("badlogic.jpg"));
        setAttackSpeed(5000);
//        setRewardXp(15);
        calcRewardXpFromStats();
        setDefaultInfo();
//        setInfo("Attacks the player every " +  (getAttackSpeed() / 1000) + " seconds hitting a maximum of " + getStats().getStrength() + " and has " + getStats().getMaxhealth() + " Health.");
    }
}
