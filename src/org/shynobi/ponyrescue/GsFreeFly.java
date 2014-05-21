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
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.noise.basic.PermutedNoise;
import com.jme3.renderer.Camera;
import com.jme3.noise.common.ImprovedPerlin;

/** Application state responsible for random background camera movement.
 *
 * @author Piotr SQLek Skólski
 */
public class GsFreeFly extends AbstractAppState {
    
    private final static float DEFAULT_HEIGHT = 30;
    
    private final PermutedNoise heightNoise = new PermutedNoise();
    private final PermutedNoise angleNoise = new PermutedNoise();
    
    private GsGame gsGame;
    
    private Camera cam;
    
    private float time = 0;
    
    @Override
    public void update(float tpf) {
        if (!isEnabled())
            return;
        
        time += tpf;
        
        
        float height = (heightNoise.value(time/8)+1)*5+5;
        float angle = (angleNoise.value(time/8)*FastMath.PI);
        
        cam.setLocation(new Vector3f(-36*FastMath.cos(angle),height,36*FastMath.sin(angle)));
        cam.lookAt(new Vector3f(0,height,0), Vector3f.UNIT_Y);
        //System.err.println(height);
    }
    
    @Override
    public void initialize(AppStateManager sManager, Application app) {
        super.initialize(sManager, app);
        
        cam = app.getCamera();
        
        gsGame = sManager.getState(GsGame.class);
        
        setEnabled(true);
    }
    
}
