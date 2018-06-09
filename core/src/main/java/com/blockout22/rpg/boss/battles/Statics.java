package com.blockout22.rpg.boss.battles;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;
import com.blockout22.rpg.boss.battles.mobs.MobData;
import com.blockout22.rpg.boss.battles.mobs.Player;
import com.blockout22.rpg.boss.battles.mobs.training.MobRat;
import com.blockout22.rpg.boss.battles.mobs.training.MobUnicorn;
import com.blockout22.rpg.boss.battles.mobs.training.MobZombie;
import com.blockout22.rpg.boss.battles.screens.GameScreen;
import com.blockout22.rpg.boss.battles.screens.MainMenuScreen;
import com.blockout22.rpg.boss.battles.screens.TrainingScreen;
import com.blockout22.rpg.boss.battles.screens.MessageScreen;
import com.blockout22.rpg.boss.battles.screens.helper.ScreenStage;

public class Statics {

    private static Game game;
    private static Preferences prefs;

    private static Player player;
    public static MobData[] trainingMobs = new MobData[3];

    //change this to false for paid version
    public static final boolean isFree = true;

    private static Array<ScreenStage> screenHistroy;

    public static ScreenStage
            MAIN_MENU,
            GAME_SCREEN,
            TRAINING_SCREEN,
            MESSAGE_SCREEN;

    public static final String
            PLAYER_MAX_HEALTH_XP = "max-health",
            PLAYER_STRENGTH_XP = "strength",
            PLAYER_SPEED = "attack-speed",
            PLAYER_XP_BANK = "xp-bank",
            UI_SCALE = "ui-scale";

    public static void init(Game game){
        Statics.game = game;
        prefs = Gdx.app.getPreferences("userdata");
        System.out.println(prefs == null);
        screenHistroy = new Array<ScreenStage>();

        System.out.println(getPreferences() == null);
        player = new Player();

        trainingMobs[0] = new MobData(true, new MobRat());
        trainingMobs[1] = new MobData(true, new MobZombie());
        trainingMobs[2] = new MobData(false, new MobUnicorn());

        MAIN_MENU = new MainMenuScreen(player);
        GAME_SCREEN = new GameScreen(player);
        TRAINING_SCREEN = new TrainingScreen(player);
        MESSAGE_SCREEN = new MessageScreen(player);

        setScreen(MAIN_MENU);
    }

    public static void backScreen(){
        System.out.println(screenHistroy.size);
        if(screenHistroy.size > 1){
            ScreenStage s = screenHistroy.get(screenHistroy.size - 2);
            screenHistroy.removeIndex(screenHistroy.size - 1);
            screenHistroy.removeIndex(screenHistroy.size - 1);
            setScreen(s);
        }
    }

    public static void setScreen(ScreenStage screen){
        game.setScreen(screen);
        screenHistroy.add(screen);
    }

    public static Preferences getPreferences(){
        return prefs;
    }

    public static void dispose(){
        MAIN_MENU.dispose();
        GAME_SCREEN.dispose();
        TRAINING_SCREEN.dispose();
        MESSAGE_SCREEN.dispose();
        prefs.flush();
    }
}
