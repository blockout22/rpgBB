package com.blockout22.rpg.boss.battles;

import com.blockout22.rpg.boss.battles.mobs.Mob;

public interface SuccessfulHitCallback {

    /**
     * The damage dealt to the mob and the mob which had taken damage
     * @param damage
     * @param other
     */
    void callback(long damage, Mob other);
}
