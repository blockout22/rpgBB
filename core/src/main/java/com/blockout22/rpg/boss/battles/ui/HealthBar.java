package com.blockout22.rpg.boss.battles.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisProgressBar;
import com.kotcrab.vis.ui.widget.VisTable;

public class HealthBar extends VisTable{

    private Stack stack;
    private VisProgressBar foreground, background, border;
    private VisLabel label;
    private int labelValue = -1;

    public HealthBar(float min, float max, float stepSize){
        ProgressBar.ProgressBarStyle fgStyle = VisUI.getSkin().get("health-bar-foreground", ProgressBar.ProgressBarStyle.class);

        Pixmap pixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(1, 0, 0, 0.0f));
        pixmap.fill();

        TextureRegionDrawable fgDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        fgStyle.background = fgDrawable;
        foreground = new VisProgressBar(min, max, stepSize, false, fgStyle);

        pixmap = new Pixmap(1, 32, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(1, 0, 0, 1));
        pixmap.fill();

        TextureRegionDrawable bgDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        ProgressBar.ProgressBarStyle bgStyle = VisUI.getSkin().get("health-bar-background", ProgressBar.ProgressBarStyle.class);
        bgStyle.knobBefore = bgDrawable;
        bgStyle.knob = bgDrawable;

        background = new VisProgressBar(min, max, stepSize, false, bgStyle);
        border = new VisProgressBar(min, max, stepSize, false);
        label = new VisLabel((int)foreground.getValue() + "/" + (int)foreground.getMaxValue());
        label.setAlignment(Align.center);
//        label.setFontScale(0.8f);

        stack = new Stack();

        foreground.setAnimateDuration(0.25f);
        background.setAnimateDuration(1);

        stack.addActor(background);
        stack.addActor(foreground);
        stack.addActor(label);
        add(stack).fillX().expandX().padLeft(5).padRight(5).row();
//        label.setColor(Color.BLACK);

//        add(label).pad(5).left();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(labelValue != (int)foreground.getValue()){
            labelValue = (int)foreground.getVisualValue();
            label.setText((int)labelValue + "/" + (int)foreground.getMaxValue());
        }
    }

    public void setRange(float min, float max){
        foreground.setRange(min, max);
        background.setRange(min, max);
    }

    public void setValue(float value){
        foreground.setValue(value);
        background.setValue(value);
    }

    public float getValue()
    {
        return foreground.getValue();
    }

    public void setBackgroundColor(Color backgroundColor){
//        background.setColor(backgroundColor);
    }

    public void setForegroundColor(Color foregroundColor) {
//        bar.setColor(foregroundColor);
    }
}
