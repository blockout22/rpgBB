package com.blockout22.rpg.boss.battles;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.I18NBundle;
import com.blockout22.rpg.boss.battles.mobs.MobData;
import com.blockout22.rpg.boss.battles.mobs.Player;
import com.blockout22.rpg.boss.battles.mobs.bosses.MobHallowFalcon;
import com.blockout22.rpg.boss.battles.mobs.bosses.MobOmegaAfterlife;
import com.blockout22.rpg.boss.battles.mobs.training.MobRat;
import com.blockout22.rpg.boss.battles.mobs.training.MobUnicorn;
import com.blockout22.rpg.boss.battles.mobs.training.MobWorm;
import com.blockout22.rpg.boss.battles.mobs.training.MobZombie;
import com.blockout22.rpg.boss.battles.screens.BossBattleScreen;
import com.blockout22.rpg.boss.battles.screens.GameScreen;
import com.blockout22.rpg.boss.battles.screens.MainMenuScreen;
import com.blockout22.rpg.boss.battles.screens.OptionsScreen;
import com.blockout22.rpg.boss.battles.screens.PlayerStatsScreen;
import com.blockout22.rpg.boss.battles.screens.TrainingScreen;
import com.blockout22.rpg.boss.battles.screens.MessageScreen;
import com.blockout22.rpg.boss.battles.screens.helper.ScreenStage;

import java.util.Locale;

public class Statics {

    private static int version = -1;
    private static boolean versionSet = false;

    private static Game game;
    private static Preferences prefs;
    private static I18NBundle bundle;

    private static Player player;
    public static MobData[] trainingMobs = new MobData[4];
    public static MobData[] bossMobs = new MobData[2];

    //change this to false for paid version
    public static final boolean isFree = false;

    private static Array<ScreenStage> screenHistroy;

    public static String BACKGROUND_CLOUDS = "background.png";

    public static ScreenStage
            MAIN_MENU,
            OPTIONS_SCREEN,
            GAME_SCREEN,
            TRAINING_SCREEN,
            MESSAGE_SCREEN,
            PLAYER_STATS_SCREEN,
            BOSS_BATTLE_SCREEN;

    public static final String
            PLAYER_MAX_HEALTH_XP = "max-health",
            PLAYER_ACCURACY_XP = "accuracy",
            PLAYER_STRENGTH_XP = "strength",
            PLAYER_DODGE_XP = "dodge",
            PLAYER_SPEED = "attack-speed",
            PLAYER_XP_BANK = "xp-bank",
            UI_SCALE = "ui-scale",
            PLAYER_STATS_UI_ORIENTATION = "stats-orientation",
            PLAYER_VIRBRATE_DAMAGE = "vibrate-damage";

    public static void init(Game game){
        Statics.game = game;
        prefs = Gdx.app.getPreferences("userdata");

        setupPreferences();

        FileHandle langFile = Gdx.files.internal("lang/bundle");
        Locale locale = Locale.FRENCH;
        bundle = I18NBundle.createBundle(langFile);

        screenHistroy = new Array<ScreenStage>();

//        System.out.println(getPreferences() == null);
        player = new Player();

        trainingMobs[0] = new MobData(false, new MobWorm());
        trainingMobs[1] = new MobData(true, new MobRat());
        trainingMobs[2] = new MobData(true, new MobZombie());
        trainingMobs[3] = new MobData(false, new MobUnicorn());

        bossMobs[0] = new MobData(true, new MobHallowFalcon());
        bossMobs[1] = new MobData(false, new MobOmegaAfterlife());

        MAIN_MENU = new MainMenuScreen(player);
        OPTIONS_SCREEN = new OptionsScreen(player);
        GAME_SCREEN = new GameScreen(player);
        TRAINING_SCREEN = new TrainingScreen(player);
        MESSAGE_SCREEN = new MessageScreen(player);
        PLAYER_STATS_SCREEN = new PlayerStatsScreen(player);
        BOSS_BATTLE_SCREEN = new BossBattleScreen(player);

        setScreen(MAIN_MENU);
    }

    private static void setupPreferences()
    {
        if(!Statics.getPreferences().contains(Statics.PLAYER_VIRBRATE_DAMAGE)){
            Statics.getPreferences().putBoolean(Statics.PLAYER_VIRBRATE_DAMAGE, true);
        }

        if(!Statics.getPreferences().contains(Statics.PLAYER_STATS_UI_ORIENTATION)){
            Statics.getPreferences().putBoolean(Statics.PLAYER_STATS_UI_ORIENTATION, false);
        }

        //checks if value exists, if false then create it
        if (!Statics.getPreferences().contains(Statics.PLAYER_MAX_HEALTH_XP)) {
            XpData tmp = new XpData();
            Statics.getPreferences().putLong(Statics.PLAYER_MAX_HEALTH_XP, tmp.levelToXp(10));
            tmp = null;
        }

        if(!Statics.getPreferences().contains(Statics.PLAYER_ACCURACY_XP)){
            Statics.getPreferences().putLong(Statics.PLAYER_ACCURACY_XP, 0);
        }

        if(!Statics.getPreferences().contains(Statics.PLAYER_STRENGTH_XP)){
            Statics.getPreferences().putLong(Statics.PLAYER_STRENGTH_XP, 0);
        }

        if(!Statics.getPreferences().contains(Statics.PLAYER_DODGE_XP)){
            Statics.getPreferences().putLong(Statics.PLAYER_DODGE_XP, 0);
        }

        if(!Statics.getPreferences().contains(Statics.PLAYER_SPEED)){
            Statics.getPreferences().putLong(Statics.PLAYER_SPEED, 5000);
        }

        if(!Statics.getPreferences().contains(Statics.PLAYER_XP_BANK)){
            Statics.getPreferences().putLong(Statics.PLAYER_XP_BANK, 0);
        }

        Statics.getPreferences().flush();
    }

    public static void backScreen(){
//        System.out.println(screenHistroy.size);
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

    public static int getVersion()
    {
        return Statics.version;
    }

    protected static void setVersion(int version){
        if(!versionSet){
            Statics.version = version;
            versionSet = true;
        }
    }

    public static I18NBundle getBundle(){ return bundle; }
    public static Preferences getPreferences(){
        return prefs;
    }

    public static void dispose(){
        MAIN_MENU.dispose();
        GAME_SCREEN.dispose();
        TRAINING_SCREEN.dispose();
        MESSAGE_SCREEN.dispose();
        PLAYER_STATS_SCREEN.dispose();
        BOSS_BATTLE_SCREEN.dispose();
        prefs.flush();
    }
}
