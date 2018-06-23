package com.blockout22.rpg.boss.battles.mobs.bosses;

import com.badlogic.gdx.utils.Timer;
import com.blockout22.rpg.boss.battles.SuccessfulHitCallback;
import com.blockout22.rpg.boss.battles.mobs.Mob;
import com.blockout22.rpg.boss.battles.mobs.Stats;

public class MobOmegaAfterlife extends Mob{
    public MobOmegaAfterlife() {
        super("Omega Afterlife", new Stats(125, 50, 50, 50));
        setAttackSpeed(2500);
        setRewardXp(200);
        setDefaultInfo();


        setSuccessfulHitcallback(new SuccessfulHitCallback() {
            @Override
            public void callback(final long damage, final Mob other) {
                if(damage >= 25){
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            other.damage(damage / 2);
                        }
                    }, 0.5f);
                }
            }
        });

        //template may be used for future boss
//        setSuccessfulHitcallback(new SuccessfulHitCallback() {
//            @Override
//            public void callback(final long damage, final Mob other) {
//                if(damage >= 25){
//                    Timer.schedule(new Timer.Task() {
//                        public long dmg = damage / 2;
//
//                        @Override
//                        public void run() {
//                            other.damage(dmg);
//                            dmg = dmg / 2;
//
//                            if(dmg < 10){
//                                cancel();
//                            }
//
//                            System.out.println("Looping Task");
//
//                        }
//                    }, 0.5f, 0.1f, 1000);
//                }
//            }
//        });
    }
}
