package com.blockout22.rpg.boss.battles;

public class XpData{
    private long threshold = 83;
    private long xp;
    private long level = 1;

    public XpData()
    {

    }

    public void addXp(long amt){
        this.xp += amt;
        getLevel();
    }

    public void setXp(long xp){
        this.xp = xp;
    }

    /**
     * calculates the level you would be at which the xp;
     */
    public long xpToLevel(long xp){
        long level = (long)(1 + Math.sqrt(1 + 8 * xp / threshold)) / 2;
        return level;
    }

    /**
     * calculates the xp you would need to get to that level;
     */
    public long levelToXp(long level){
        long xp = (long)(((Math.pow(level, 2) - level) * threshold)) / 2;
        return xp;
    }

    public long getXp()
    {
        return xp;
    }

    public long getLevel(){
        level = xpToLevel(xp);
        return level;
    }
}
