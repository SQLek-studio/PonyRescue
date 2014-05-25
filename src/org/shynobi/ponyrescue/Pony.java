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

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author Piotr SQLek Skólski
 */
public class Pony extends Node {
    
    private float animation = 0;
    
    private Pony(String name) {
        super(name);
    }
    
    public static Pony create(String name, AssetManager aManager) {
        Pony pony = new Pony(name);
        
        Material bodyM = aManager.loadMaterial("Materials/PonyBody.j3m");
        Material eyesM = aManager.loadMaterial("Materials/PonyEyes.j3m");
        Material maneBackM = aManager.loadMaterial("Materials/PonyManeBack.j3m");
        Material ManeFrontM = aManager.loadMaterial("Materials/PonyManeFront.j3m");
        Material tailM = aManager.loadMaterial("Materials/PonyTail.j3m");
        
        Spatial body = aManager.loadModel("Models/PonyBody.j3o");
        Spatial eyes = aManager.loadModel("Models/PonyEyes.j3o");
        Spatial maneBack = aManager.loadModel("Models/PonyManeBack.j3o");
        Spatial maneFront = aManager.loadModel("Models/PonyManeFront.j3o");
        Spatial tail = aManager.loadModel("Models/PonyTail.j3o");
        
        body.setMaterial(bodyM);
        eyes.setMaterial(eyesM);
        maneBack.setMaterial(maneBackM);
        maneFront.setMaterial(ManeFrontM);
        tail.setMaterial(tailM);
        
        body.setLocalScale(0.25f);
        eyes.setLocalScale(0.25f);
        maneBack.setLocalScale(0.25f);
        maneFront.setLocalScale(0.25f);
        tail.setLocalScale(0.25f);
        
        pony.attachChild(body);
        pony.attachChild(eyes);
        pony.attachChild(maneBack);
        pony.attachChild(maneFront);
        pony.attachChild(tail);
        
        return pony;
    }
    
}
