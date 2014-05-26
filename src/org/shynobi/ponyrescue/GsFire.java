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
import com.jme3.audio.AudioNode;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author Piotr SQLek Skólski
 */
public class GsFire extends AbstractAppState {
    
    private static final float PONY_LIFE_TIME = 30;
    
    private class WindowNode {
        Spatial window;
        Pony pony;
        ParticleEmitter cloudEmiter;
        ParticleEmitter fireEmiter;
        AudioNode audioNode;
        Vector3f ponySpawn;
        float life = PONY_LIFE_TIME;
    }
    
    private WindowNode[] windowNodes;
    private AssetManager aManager;
    private Node windows;
    private Node rootNode;
    
    @Override
    public void initialize(AppStateManager sManager, Application app) {
        super.initialize(sManager,app);
        aManager = app.getAssetManager();
        rootNode = ((SimpleApplication)app).getRootNode();
        
        Material fireMat = new Material(aManager,
                "Common/MatDefs/Misc/Particle.j3md");
        fireMat.setTexture("Texture", aManager.loadTexture(
                "Particles/Fire.png"));
        
        Material cloudMat = new Material(aManager,
                "Common/MatDefs/Misc/Particle.j3md");
        cloudMat.setTexture("Texture", aManager.loadTexture(
                "Particles/Cloud.png"));
        
        windows = sManager.getState(GsGame.class).getWindows();
        windowNodes = new WindowNode[windows.getQuantity()];
        for (int i = 0; i < windowNodes.length; ++i) {
            windowNodes[i] = new WindowNode();
            windowNodes[i].window = windows.getChild(i);
            
            float angle = 0;
            Vector3f lower = windowNodes[i].window.getLocalTranslation()
                    .add(0, -2.7f, 0);
            windowNodes[i].ponySpawn = windowNodes[i].window.getLocalTranslation();
            
            if (windowNodes[i].window.getName().startsWith("Window_North")){
                lower.addLocal(0, 0, -0.22f);
                windowNodes[i].ponySpawn = windowNodes[i].ponySpawn.add(0, -0.1f, 0.32f);
                angle = 1;
            }
            
            if (windowNodes[i].window.getName().startsWith("Window_South")){
                lower.addLocal(0, 0, 0.22f);
                windowNodes[i].ponySpawn = windowNodes[i].ponySpawn.add(0, -0.1f, -0.32f);
                angle = 0;
            }
            
            if (windowNodes[i].window.getName().startsWith("Windows_East")){
                lower.addLocal(0.22f, 0, 0);
                windowNodes[i].ponySpawn = windowNodes[i].ponySpawn.add(-0.32f, -0.1f, 0);
                angle = 0.5f;
            }
            
            if (windowNodes[i].window.getName().startsWith("Window_West")){
                lower.addLocal(-0.22f, 0, 0);
                windowNodes[i].ponySpawn = windowNodes[i].ponySpawn.add(0.32f, -0.1f, 0);
                angle = -0.5f;
            }
            
            windowNodes[i].fireEmiter = new ParticleEmitter("Fire"+i,
                    ParticleMesh.Type.Triangle, 6);
            windowNodes[i].fireEmiter.setMaterial(fireMat);
            windowNodes[i].fireEmiter.setLocalTranslation(lower);
            windowNodes[i].fireEmiter.setGravity(0, -0.25f, 0);
            windowNodes[i].fireEmiter.setStartColor(new ColorRGBA(0.8f, 0.8f, 0.8f, 0.8f));
            windowNodes[i].fireEmiter.setEndColor(new ColorRGBA(0.8f, 0.4f, 0.4f, 0.5f));
            windowNodes[i].fireEmiter.setStartSize(1.5f);
            windowNodes[i].fireEmiter.setEndSize(0.1f);
            windowNodes[i].fireEmiter.setEnabled(false);
            windowNodes[i].fireEmiter.setLowLife(1f);
            windowNodes[i].fireEmiter.setHighLife(1.6f);
            windowNodes[i].fireEmiter.getParticleInfluencer().setVelocityVariation(0.2f);
            rootNode.attachChild(windowNodes[i].fireEmiter);
            
            windowNodes[i].cloudEmiter = new ParticleEmitter("Cloud"+i,
                    ParticleMesh.Type.Triangle, 6);
            windowNodes[i].cloudEmiter.setMaterial(cloudMat);
            windowNodes[i].cloudEmiter.setLocalTranslation(lower);
            windowNodes[i].cloudEmiter.setGravity(0, -0.25f, 0);
            windowNodes[i].cloudEmiter.setStartColor(new ColorRGBA(0.8f, 0.8f, 0.8f, 0.8f));
            windowNodes[i].cloudEmiter.setEndColor(new ColorRGBA(1f, 1f, 1f, 0.5f));
            windowNodes[i].cloudEmiter.getParticleInfluencer().setVelocityVariation(0.2f);
            windowNodes[i].cloudEmiter.setEnabled(false);
            rootNode.attachChild(windowNodes[i].cloudEmiter);
            
            windowNodes[i].pony = Pony.create("Pony"+i, aManager);
            windowNodes[i].pony.setLocalTranslation(windowNodes[i].ponySpawn);
            windowNodes[i].pony.setLocalRotation(new Quaternion().fromAngleAxis(
                angle*FastMath.PI,
                Vector3f.UNIT_Y));
        
            //rootNode.attachChild(windowNodes[i].pony);
            
            //Xinef: audio loading here
            //windowNodes[i].audioNode = ???
            
        }
    }
    
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        for (int i = 0; i < windowNodes.length; ++i) {
            windowNodes[i].fireEmiter.setEnabled(enabled);
            windowNodes[i].cloudEmiter.setEnabled(enabled);
            
        }
    }
    
    @Override
    public void update(float tpf) {
        if (!isEnabled())
            return;
        //fire logic go here
    }
    
    public void userClicked(float angle, float height) {
        System.err.printf("Clicked %f %f%n", angle, height);
    }
    
}
