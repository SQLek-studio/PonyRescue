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
 *
 * @author Piotr SQLek Skólski
 */
public class GsMenuCredits extends AbstractAppState implements PlayerListener {
    
    private final static int CREDITS_COLDOWN = 20;
    
    private float coldown = 0;
    
    private Picture picture;
    private Node guiNode;
    private AppStateManager sManager;

    @Override
    public void makeAction(float fpf) {
        gotoMainMenu();
    }

    @Override
    public void makeLeft(float fpf) {
        gotoMainMenu();
    }

    @Override
    public void makeRight(float fpf) {
        gotoMainMenu();
    }

    @Override
    public void makeUp(float fpf) {
        gotoMainMenu();
    }

    @Override
    public void makeDown(float fpf) {
        gotoMainMenu();
    }

    @Override
    public float tickTime() {
        return 1;
    }
    
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (!enabled)
            return;
        
        guiNode.attachChild(picture);
        
    }
    
    private void gotoMainMenu() {
        if (coldown > 0)
            return;
        if (picture != null)
            guiNode.detachChild(picture);
        sManager.getState(GsInputHandling.class).setWasdListener(sManager.getState(GsMenuMain.class));
        sManager.getState(GsInputHandling.class).setArrowsListener(sManager.getState(GsMenuMain.class));
        setEnabled(false);
        sManager.getState(GsMenuMain.class).setEnabled(true);
    }
    
    @Override
    public void initialize(AppStateManager sManager, Application app) {
        super.initialize(sManager, app);
        this.sManager = sManager;
        guiNode = ((SimpleApplication)app).getGuiNode();
        
        Camera cam = app.getGuiViewPort().getCamera();
        int width = cam.getWidth();
        int height = cam.getHeight();
        
        picture = new Picture("Credits");
        picture.setImage(app.getAssetManager(),"Interfaces/Credits.png",true);
        picture.setWidth(width-20);
        picture.setHeight(height-20);
        picture.setPosition(10,10);
    }
    
    @Override
    public void update(float tpf) {
        if (!isEnabled())
            return;
        coldown = Math.max(coldown-tpf, 0);
    }
}
