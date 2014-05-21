/*
 * Copyright (C) 2014 Piotr SQLek Skólski
 * Copyright (C) 2014 Jerzy Xinef Redlarski
 * 
 * This file is part of PonyRescue.
 * 
 * PonyRescue is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * PonyRescue is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with PonyRescue. If not, see <http://www.gnu.org/licenses/>.
 */
package org.shynobi.ponyrescue;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.ui.Picture;

/**
 * Main menu app state.
 *
 * @author Piotr SQLek Skólski
 */
public class GsMenuMain extends AbstractAppState {

    private static class Button {

        String name;
        float x, y, width, height;

        Button(String name, float x, float y, float width, float height) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }
    private static final Button[] BUTTONS = {
        new Button("Singleplayer",
            0.25f,
            0.475f,
            0.5f,
            0.125f),
        new Button("Multiplayer",
            0.25f,
            0.325f,
            0.5f,
            0.125f),
        new Button("Credits",
            0.25f,
            0.175f, 
            0.5f, 
            0.125f),
        new Button("Exit",
            0.25f,
            0.025f,
            0.5f,
            0.125f)
    };
    private static final String BUTTON_PREFIX = "Textures/Buttons/";
    private static final String BUTTON_POSTFIX = ".png";
    private static final String BUTTON_ACTIVE = "Active.png";
    private SimpleApplication sApp;
    private int margin = 0;
    private int scale = 1;
    private int selectedButton = 0;
    private Picture[] buttons = new Picture[BUTTONS.length];
    private Picture[] buttonsActive = new Picture[BUTTONS.length];
    private Node buttonNode;

    @Override
    public void initialize(AppStateManager sManager, Application app) {
        super.initialize(sManager, app);
        sApp = (SimpleApplication) app;

        Camera cam = app.getGuiViewPort().getCamera();
        int width = cam.getWidth();
        int height = cam.getHeight();
        margin = (width - height)/2;
        scale = height;

        buttonNode = new Node();
        
        for (int i = 0; i < BUTTONS.length; ++i) {
            buttons[i] = new Picture(BUTTONS[i].name);
            buttons[i].setImage(sApp.getAssetManager(),
                    BUTTON_PREFIX + BUTTONS[i].name + BUTTON_POSTFIX,
                    true);
            buttons[i].setWidth(BUTTONS[i].width * scale);
            buttons[i].setHeight(BUTTONS[i].height * scale);
            buttons[i].setPosition(BUTTONS[i].x * scale + margin,
                    BUTTONS[i].y * scale);
            //sApp.getGuiNode().attachChild(buttons[i]);

            buttonsActive[i] = new Picture(BUTTONS[i].name + "Active");
            buttonsActive[i].setImage(sApp.getAssetManager(),
                    BUTTON_PREFIX + BUTTONS[i].name + BUTTON_ACTIVE,
                    true);
            buttonsActive[i].setWidth(BUTTONS[i].width * scale);
            buttonsActive[i].setHeight(BUTTONS[i].height * scale);
            buttonsActive[i].setPosition(BUTTONS[i].x * scale + margin,
                    BUTTONS[i].y * scale);
            //sApp.getGuiNode().attachChild(buttonsActive[i]);
        }
        sApp.getGuiNode().attachChild(buttonNode);
        reactivateButtons();
    }

    private void reactivateButtons() {
        selectedButton %= BUTTONS.length;
        buttonNode.detachAllChildren();
        for (int i = 0; i < BUTTONS.length; ++i) {
            if (selectedButton == i)
                buttonNode.attachChild(buttonsActive[i]);
            else
                buttonNode.attachChild(buttons[i]);
        }
    }
}
