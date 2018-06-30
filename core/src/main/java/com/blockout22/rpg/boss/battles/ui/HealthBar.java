package com.blockout22.rpg.boss.battles.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisProgressBar;
import com.kotcrab.vis.ui.widget.VisTable;

public class HealthBar extends VisTable{

    private Stack stack;
    private VisProgressBar bar, background, border;
    private VisLabel label;

    public HealthBar(){
        bar = new VisProgressBar(1, 100, 1, false);
        background = new VisProgressBar(1, 100, 1, false);
        border = new VisProgressBar(1, 100, 1, false);
        label = new VisLabel("0/100");
//        label.setFontScale(0.5f);

        label.setColor(Color.BLACK);

        setBackgroundColor(Color.RED);
        border.setColor(Color.GRAY);

        stack = new Stack();

//        ProgressBar.ProgressBarStyle style = VisUI.getSkin().get("healthbarBackground", ProgressBar.ProgressBarStyle.class);
//        background.setStyle(style);
//        border.setStyle(VisUI.getSkin().get("border", ProgressBar.ProgressBarStyle.class));

        background.setAnimateDuration(2);

        stack.addActor(background);
        stack.addActor(bar);
        stack.addActor(border);
        add(stack).fillX().expandX().padLeft(5).padRight(5).row();
        label.setFontScale(0.2f);

        add(label).pad(5).left();
    }

    public void setValue(float value){
        bar.setValue(value);
        background.setValue(value);
        label.setText((int)bar.getValue() + "/" + (int)bar.getMaxValue());
    }

    public float getValue()
    {
        return bar.getValue();
    }

    public void setBackgroundColor(Color backgroundColor){
        background.setColor(backgroundColor);
    }

    public void setForegroundColor(Color foregroundColor) {
        bar.setColor(foregroundColor);
    }
}
