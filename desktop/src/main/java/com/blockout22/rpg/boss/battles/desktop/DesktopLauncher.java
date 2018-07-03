package com.blockout22.rpg.boss.battles.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.particleeditor.ParticleEditor;
import com.blockout22.rpg.boss.battles.NameGenerator;
import com.blockout22.rpg.boss.battles.RPGBossBattles;
import com.blockout22.rpg.boss.battles.XpData;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.text.DecimalFormat;

import javax.swing.UIManager;

/** Launches the desktop (LWJGL) application. */
public class DesktopLauncher {
    public static void main(String[] args) {
        createApplication();
    }

    private static LwjglApplication createApplication() {
        int version = -1;
        try {
            InputStream file = DesktopLauncher.class.getResourceAsStream("/version");
            BufferedReader br;
            if(file != null) {
                br = new BufferedReader(new InputStreamReader(file));
            }else{
                br = new BufferedReader(new FileReader(new File("version")));
            }

            String line = br.readLine();
            version = Integer.valueOf(line);

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new LwjglApplication(new RPGBossBattles(version), getDefaultConfiguration());
    }

    private static LwjglApplicationConfiguration getDefaultConfiguration() {
        LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        configuration.title = "BossBattles";
        configuration.width = 1920 / 3;
        configuration.height = 1920 / 3;
        for (int size : new int[] { 128, 64, 32, 16 }) {
//            configuration.addIcon("libgdx" + size + ".png", FileType.Internal);
        }
        return configuration;
    }
}