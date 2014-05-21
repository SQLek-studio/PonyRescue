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

import com.jme3.app.state.AbstractAppState;

/** Main game state of PonyRescue.
 * 
 * It is responsible for displaying terrain, skybox and tower.
 *
 * @author Piotr SQLek Skólski
 */
public class GsGame extends AbstractAppState {
    
    
    
    /** Computes distance from center on whitch rescuer can be.
     *
     * @param angle From zero inclusive to one exclusive.
     * @param height Height from ground.
     * @return Distance from center for objcect.
     */
    public float freeFlyDistance(float angle, float height) {
        
        return 10;
    }
    
    /**
     *
     * @return Maximum height for object.
     */
    public float getMaxheight() {
        return 50;
    }
    
    /**
     *
     * @return Minimum height for object.
     */
    public float getMinHeight() {
        return 5;
    }
    
}
