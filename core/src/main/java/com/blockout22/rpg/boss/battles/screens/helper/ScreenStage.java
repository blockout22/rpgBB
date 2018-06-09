package com.blockout22.rpg.boss.battles.screens.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.blockout22.rpg.boss.battles.Statics;
import com.blockout22.rpg.boss.battles.mobs.Player;

public class ScreenStage implements Screen {

    private static ExtendViewport viewport;
    private final Stage stage;
    public final Table rootTable;
    private final Player player;

    public ScreenStage(Player player){
        this.player = player;

        if(!Statics.getPreferences().contains(Statics.UI_SCALE)){
            Statics.getPreferences().putFloat(Statics.UI_SCALE, 4.0f);
            Statics.getPreferences().flush();
        }

        float scale = Statics.getPreferences().getFloat(Statics.UI_SCALE);
        viewport = new ExtendViewport(1920 / scale, 1080 / scale);
        stage = new Stage(viewport);
        rootTable = new Table();
        rootTable.setFillParent(true);
        stage.addActor(rootTable);
        stage.setDebugAll(true);
    }

    public static ExtendViewport getViewport(){
        return viewport;
    }

    public Stage getStage() {
        return stage;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
