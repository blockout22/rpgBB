package com.blockout22.rpg.boss.battles.screens.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.blockout22.rpg.boss.battles.Statics;
import com.blockout22.rpg.boss.battles.mobs.Player;
import com.kotcrab.vis.ui.widget.VisTable;

public class ScreenStage implements Screen {

    private BackListener listener = null;

    private static ExtendViewport viewport;
    private final Stage stage;
    public final VisTable rootTable;
    private final Player player;

    private Texture background;

    public ScreenStage(Player player){
        this.player = player;

        if(!Statics.getPreferences().contains(Statics.UI_SCALE)){
            Statics.getPreferences().putFloat(Statics.UI_SCALE, 3.0f);
            Statics.getPreferences().flush();
        }

        float scale = Statics.getPreferences().getFloat(Statics.UI_SCALE);
//        scale = 3.0f;
        viewport = new ExtendViewport(1920 / scale, 1080 / scale);
        stage = new Stage(viewport);
        rootTable = new VisTable();
        rootTable.setFillParent(true);
        stage.addActor(rootTable);
//        stage.setDebugAll(true);

        background = new Texture(Gdx.files.internal(Statics.BACKGROUND_CLOUDS));
    }

    public void setOnBackListener(BackListener listener){
        this.listener = listener;
    }

    public BackListener getListener() {
        return listener;
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
        if(Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            if (getListener() == null) {
                Statics.backScreen();
            } else {
                listener.action();
//            getListener().action();
            }
        }

        stage.act();
        stage.getBatch().setProjectionMatrix(viewport.getCamera().combined);
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldWidth());
        stage.getBatch().end();
        stage.draw();

        stage.getBatch().setColor(Color.WHITE);
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
        background.dispose();
        stage.dispose();
    }
}
