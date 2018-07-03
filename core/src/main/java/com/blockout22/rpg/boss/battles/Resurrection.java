package com.blockout22.rpg.boss.battles;

import com.blockout22.rpg.boss.battles.mobs.Stats;

public class Resurrection {

//    private int resAmt = 1;
    private Stats stats;

    public Resurrection(){

    }

    public Resurrection(Stats stats){
        this.stats = stats;
    }

//    /**
//     * how many times the mob can resurrect
//     * @param resAmt
//     */
//    public void setResAmt(int resAmt) {
//        this.resAmt = resAmt;
//    }
//
//    public int getResAmt() {
//        return resAmt;
//    }

    public void setStats(Stats stats){
        this.stats = stats;
    }

    public Stats getStats() {
        return stats;
    }
}
