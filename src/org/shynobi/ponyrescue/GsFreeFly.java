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
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import java.util.Random;

/** Application state responsible for random background camera movement.
 *
 * @author Piotr SQLek Skólski
 */
public class GsFreeFly extends AbstractAppState {
    
    private final static float DEFAULT_HEIGHT = 30;
    
    private final Random random = new Random();
    
    private GsGame gsGame;
    
    private Camera cam;
    
    private float angle = 0;
    private float angleSpeed = 0;
    private float height = DEFAULT_HEIGHT;
    
    @Override
    public void update(float tpf) {
        if (!isEnabled())
            return;
        
        float heightDelta = (float)random.nextGaussian();
        height = (DEFAULT_HEIGHT
                + height*(8-tpf)
                + heightDelta*(tpf+1))/10;
        
        cam.setLocation(new Vector3f(36,height,36));
        cam.lookAt(new Vector3f(0,height,0), Vector3f.UNIT_Y);
        System.err.println(height);
    }
    
    @Override
    public void initialize(AppStateManager sManager, Application app) {
        super.initialize(sManager, app);
        
        cam = app.getCamera();
        
        gsGame = sManager.getState(GsGame.class);
        
        setEnabled(true);
    }
    
}
