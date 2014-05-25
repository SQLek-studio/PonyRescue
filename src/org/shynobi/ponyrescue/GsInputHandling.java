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
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;

/**
 *
 * @author Piotr SQLek Skólski
 */
public class GsInputHandling extends AbstractAppState {

    private PlayerListener wasdListener;
    private PlayerListener arrowsListener;
    
    private boolean wActive = false;
    private boolean aActive = false;
    private boolean sActive = false;
    private boolean dActive = false;
    private boolean spaceActive = false;
    private boolean upActive = false;
    private boolean leftActive = false;
    private boolean downActive = false;
    private boolean rightActive = false;
    private boolean enterActive = false;
    private float wTick = 0;
    private float aTick = 0;
    private float sTick = 0;
    private float dTick = 0;
    private float spaceTick = 0;

    public void setWasdListener(PlayerListener listener) {
        wasdListener = listener;
        //clean flags and ticks of wasd listener
    }

    public void setArrowsListener(PlayerListener listener) {
        arrowsListener = listener;
        //clean flags and ticks of arrow listener
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);
        if (isEnabled() && wasdListener != null) {
            float tickTime = wasdListener.tickTime();
            //if tick <= 0 fall back to calling listener every run
            
            
            if (wActive)
                wTick += tpf;
            if (aActive)
                aTick += tpf;
            if (sActive)
                sTick += tpf;
            if (dActive)
                dTick += tpf;
            if (spaceActive)
                spaceTick += tpf;
            
            while (wTick > tickTime) {
                wTick -= tickTime;
                wasdListener.makeUp(0);
            }
            while (aTick > tickTime) {
                aTick -= tickTime;
                wasdListener.makeLeft(0);
            }
            while (sTick > tickTime) {
                sTick -= tickTime;
                wasdListener.makeDown(0);
            }
            while (dTick > tickTime) {
                dTick -= tickTime;
                wasdListener.makeRight(0);
            }
            while (spaceTick > tickTime) {
                spaceTick -= tickTime;
                wasdListener.makeAction(0);
            }
        }
        //copy paste second player
    }
    
@Override
        public void initialize(AppStateManager sManager, Application app) {
        super.initialize(sManager, app);
        InputManager inputManager = app.getInputManager();

        inputManager.addMapping("W", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("A", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("S", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("D", new KeyTrigger(KeyInput.KEY_D));

        inputManager.addMapping("ACTION1", new KeyTrigger(KeyInput.KEY_SPACE));

        //add mappings for arrows
        
        //add code to set tick to zero on key realease
        //and emit first callback on key press
        
        inputManager.addListener(new ActionListener() {
            @Override
        public void onAction(String name, boolean keyPressed, float tpf) {
                wActive = keyPressed;
            }
        }, "W");
        inputManager.addListener(new ActionListener() {
            @Override
        public void onAction(String name, boolean keyPressed, float tpf) {
                sActive = keyPressed;
            }
        }, "S");
        inputManager.addListener(new ActionListener() {
            @Override
        public void onAction(String name, boolean keyPressed, float tpf) {
                spaceActive = keyPressed;
            }
        }, "ACTION1");
        inputManager.setCursorVisible(false);
        this.setEnabled(true);
    }
}
