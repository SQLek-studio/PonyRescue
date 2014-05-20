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

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 *
 * @author piotr
 */
public class Game extends SimpleApplication {

    @Override
    public void simpleInitApp() {
        Material groundMaterial = new Material(assetManager, 
        "Common/MatDefs/Misc/Unshaded.j3md");
        groundMaterial.setColor("Color", ColorRGBA.Green);
        
        Material towerMaterial = new Material(assetManager, 
        "Common/MatDefs/Misc/Unshaded.j3md");
        towerMaterial.setColor("Color", ColorRGBA.Orange);
        
        Geometry ground = new Geometry("Ground",new Box(10,0.1f,10));
        ground.setMaterial(groundMaterial);
        rootNode.attachChild(ground);
        
        Geometry tower = new Geometry("Tower",new Box(1,4,1));
        tower.setMaterial(towerMaterial);
        rootNode.attachChild(tower);
        
        cam.setLocation(new Vector3f(7,7,7));
        cam.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);
    }
    
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
    
}
