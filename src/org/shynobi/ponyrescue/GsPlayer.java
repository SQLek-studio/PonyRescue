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
import com.jme3.renderer.Camera;

/** AppState responsible for camera handling and user interaction.
 * 
 * It will be also an app state for displaying background in menus.
 *
 * @author Piotr SQLek Skólski
 */
public class GsPlayer extends AbstractAppState implements PlayerListener {
    
    private final Camera camera;
    private final GsGame gsGame;
    private final Pony player;
    
    public GsPlayer(Camera camera, GsGame gsGame, boolean first) {
        this.camera = camera;
        this.gsGame = gsGame;
        player = new Pony(first? "PlayerA": "PlayerB");
    }
    
    @Override
    public void initialize(AppStateManager sManager, Application app) {
        player.init(app.getAssetManager());
    }

    @Override
    public void makeAction(float fpf) {
        
    }

    @Override
    public void makeLeft(float fpf) {
        
    }

    @Override
    public void makeRight(float fpf) {
        
    }

    @Override
    public void makeUp(float fpf) {
        
    }

    @Override
    public void makeDown(float fpf) {
        
    }

    @Override
    public float tickTime() {
        return 0;
    }
    
    
    
}
