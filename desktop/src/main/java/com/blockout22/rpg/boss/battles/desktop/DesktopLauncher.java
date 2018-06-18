package com.blockout22.rpg.boss.battles.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.particleeditor.ParticleEditor;
import com.blockout22.rpg.boss.battles.NameGenerator;
import com.blockout22.rpg.boss.battles.RPGBossBattles;

import java.awt.EventQueue;

import javax.swing.UIManager;

/** Launches the desktop (LWJGL) application. */
public class DesktopLauncher {
    public static void main(String[] args) {
//        try{
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        }catch(Exception e){}
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new ParticleEditor();
//            }
//        });
        String[] name = NameGenerator.getNames(1);
        System.out.println(name[0]);
        createApplication();
    }

    private static LwjglApplication createApplication() {
        return new LwjglApplication(new RPGBossBattles(), getDefaultConfiguration());
    }

    private static LwjglApplicationConfiguration getDefaultConfiguration() {
        LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        configuration.title = "BossBattles";
        configuration.width = 640;
        configuration.height = 480;
        for (int size : new int[] { 128, 64, 32, 16 }) {
//            configuration.addIcon("libgdx" + size + ".png", FileType.Internal);
        }
        return configuration;
    }
}