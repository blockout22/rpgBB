package com.blockout22.rpg.boss.battles.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.blockout22.rpg.boss.battles.Statics;
import com.blockout22.rpg.boss.battles.mobs.Player;
import com.blockout22.rpg.boss.battles.screens.helper.ScreenStage;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class MessageScreen extends ScreenStage {

    private VisLabel text;
    private VisTextButton back;

    public MessageScreen(Player player) {
        super(player);

        text = new VisLabel();
        back = new VisTextButton("Back");

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Statics.backScreen();
            }
        });

        rootTable.add(text).expand().bottom().row();
        rootTable.add(back).expand().bottom().right();
    }

    public MessageScreen setText(String t){
        text.setText(t);
        return this;
    }
}
