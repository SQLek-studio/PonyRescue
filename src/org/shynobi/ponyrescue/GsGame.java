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
import com.jme3.asset.AssetManager;
import com.jme3.collision.CollisionResults;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/** Main game state of PonyRescue.
 * 
 * It is responsible for displaying terrain, skybox and tower and camera.
 * When it is active then random camera movment is produced
 *
 * @author Piotr SQLek Skólski
 */
public class GsGame extends AbstractAppState {
    
    private Spatial tower;
    private Spatial ground;
    private Spatial sky;
    
    private Node windows;
    private Spatial constrains;
    
    private AmbientLight ambientLight = new AmbientLight();
    
    private final CollisionResults collisions = new CollisionResults();
    
    
    /** Computes distance from center on whitch rescuer can be.
     *
     * @param angle From zero inclusive to one exclusive.
     * @param height Height from ground.
     * @return Distance from center for objcect.
     */
    public float freeFlyDistance(float angle, float height) {
        collisions.clear();
        Ray ray = new Ray(
                new Vector3f(-5,height,-5),
                new Vector3f(getCircleX(angle,1),0,getCircleZ(angle,1)));
        constrains.collideWith(ray, collisions);
        if (collisions.size() > 0)
            return collisions.getClosestCollision().getDistance();
        return 0;
    }
    
    /**
     *
     * @return Maximum height for object.
     */
    public float getMaxHeight() {
        return 28;
    }
    
    /**
     *
     * @return Minimum height for object.
     */
    public float getMinHeight() {
        return 2;
    }
    
    @Override
    public void initialize(AppStateManager sManager, Application app) {
        super.initialize(sManager, app);
        AssetManager aManager = app.getAssetManager();
        
        Material groundM = aManager.loadMaterial("Materials/Ground.j3m");
        Material towerM = aManager.loadMaterial("Materials/Tower.j3m");
        Material skyM = aManager.loadMaterial("Materials/Sky.j3m");
        Material invisible = aManager.loadMaterial("Materials/Invisible.j3m");
        
        ground = aManager.loadModel("Models/Ground.j3o");
        ground.setMaterial(groundM);
        
        tower = aManager.loadModel("Models/Tower.j3o");
        tower.setMaterial(towerM);
        
        constrains = aManager.loadModel("Models/FlyConstrains.j3o");
        constrains.setMaterial(invisible);
        
        windows = (Node)aManager.loadModel("Models/Windows.j3o");
        windows.setMaterial(invisible);
        
        sky = aManager.loadModel("Models/Sky.j3o");
        sky.setMaterial(skyM);
        sky.setQueueBucket(Bucket.Sky);
        
        ((SimpleApplication)app).getRootNode().addLight(ambientLight);
        ((SimpleApplication)app).getRootNode().attachChild(ground);
        ((SimpleApplication)app).getRootNode().attachChild(tower);
        ((SimpleApplication)app).getRootNode().attachChild(constrains);
        ((SimpleApplication)app).getRootNode().attachChild(sky);
        
        setEnabled(true);
    }
    
    public static float getCircleX(float angle, float distance) {
        angle -= 0.125f;
        float A = FastMath.floor(angle);
        angle -= A;
        angle *= FastMath.TWO_PI;
        return -FastMath.cos(angle)*distance;
    }
    
    public static float getCircleZ(float angle, float distance) {
        angle -= 0.125f;
        float A = FastMath.floor(angle);
        angle -= A;
        angle *= FastMath.TWO_PI;
        return -FastMath.sin(angle)*distance;
    }
    
    public Node getWindows() {
        return windows;
    }
    
}
