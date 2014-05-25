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
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.effect.ParticleEmitter;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author Piotr SQLek Skólski
 */
public class GsFire extends AbstractAppState {
    
    private class WindowNode {
        Spatial window;
        Pony pony;
        ParticleEmitter cloudEmiter;
        ParticleEmitter fireEmiter;
        AudioNode audioNode;
    }
    
    private WindowNode[] windowNodes;
    private AssetManager aManager;
    private Node windows;
    
    @Override
    public void initialize(AppStateManager sManager, Application app) {
        super.initialize(sManager,app);
        aManager = app.getAssetManager();
        
        windows = sManager.getState(GsGame.class).getWindows();
        windowNodes = new WindowNode[windows.getQuantity()];
        for (int i = 0; i < windowNodes.length; ++i) {
            windowNodes[i] = new WindowNode();
            windowNodes[i].window = windows.getChild(i);
            
            
            
            //Xinef: audio loading here
            //windowNodes[i].audioNode = ???
            
        }
        setEnabled(true);
    }
    
    @Override
    public void update(float tpf) {
        if (!isEnabled())
            return;
        //fire logic go here
    }
    
}
