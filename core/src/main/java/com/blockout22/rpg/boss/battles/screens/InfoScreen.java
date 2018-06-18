package com.blockout22.rpg.boss.battles.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.blockout22.rpg.boss.battles.Statics;
import com.blockout22.rpg.boss.battles.mobs.Player;
import com.blockout22.rpg.boss.battles.screens.helper.ScreenStage;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class InfoScreen extends ScreenStage{

    private VisScrollPane scroll;
    private VisLabel visTitle, infoText;
    private VisTextButton back;

    public InfoScreen(Player player, String title, String text) {
        super(player);

        visTitle = new VisLabel(title);
        infoText = new VisLabel();
        scroll = new VisScrollPane(infoText);
        back = new VisTextButton(Statics.getBundle().get("backScreen"));

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Statics.backScreen();
            }
        });

        visTitle.setWrap(true);
        infoText.setWrap(true);

        infoText.setText(text);

        infoText.pack();
        scroll.layout();

        rootTable.add(visTitle).fillX().align(Align.center).row();
        rootTable.add(scroll).fill().expand().row();
        rootTable.add(back).expand().bottom().right();
    }
}
