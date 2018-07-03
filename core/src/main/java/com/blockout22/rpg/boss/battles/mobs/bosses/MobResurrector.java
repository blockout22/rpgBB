package com.blockout22.rpg.boss.battles.mobs.bosses;

import com.blockout22.rpg.boss.battles.OnDeadCallback;
import com.blockout22.rpg.boss.battles.mobs.Mob;
import com.blockout22.rpg.boss.battles.mobs.Stats;

public class MobResurrector extends Mob{

    private Stats defaultStats;
    private boolean hasDied = false;

    public MobResurrector() {
        super("Resurrector", new Stats(100, 50, 50, 50));

        defaultStats = getStats();
        final Stats resStats = new Stats(250, 60, 75, 55);
        setAttackSpeed(2500);
        calcRewardXpFromStats(resStats);
        setDefaultInfo();

        OnDeadCallback onDeadCallback = new OnDeadCallback() {
            @Override
            public boolean action() {
                if(!hasDied){
                    setStats(resStats);
                    hasDied = true;
                    return false;
                }else{
                    //reset all states
                    setStats(defaultStats);
                    hasDied = false;
                    resStats.setCurrentHealth(resStats.getMaxhealth());
                    return true;
                }
            }
        };

        setOnDeadCallback(onDeadCallback);

//        Resurrection res = new Resurrection(resStats);
//        setResurrection(res);
    }
}
