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
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;

/** AppState responsible for camera handling and user interaction.
 * 
 * It will be also an app state for displaying background in menus.
 *
 * @author Piotr SQLek Skólski
 */
public class GsPlayer extends AbstractAppState implements PlayerListener {
    
    public final static float HEIGHT_INCREMENT = 0.5f;
    public final static float HEIGHT_FALLOF = 0.1f;
    public final static float POSITION_INCREMENT = 0.1f;
    
    public final static String FIRST_PLAYER = "PlayerA";
    public final static String SECOND_PLAYER = "PlayerA";
    
    private final Camera camera;
    private final GsGame gsGame;
    private GsFire gsFire;
    private final boolean isFirstPlayer;
    private Pony player;
    
    private Node rootNode;
    
    private float height;
    private float angle;
    
    private float maxHeight;
    private float minHeight;
    
    public GsPlayer(Camera camera, GsGame gsGame, boolean first) {
        this.camera = camera;
        this.gsGame = gsGame;
        isFirstPlayer = first;
        maxHeight = gsGame.getMaxHeight();
        minHeight = gsGame.getMinHeight();
        height = (maxHeight+minHeight)/2;
    }
    
    @Override
    public void initialize(AppStateManager sManager, Application app) {
        player = Pony.create(isFirstPlayer? FIRST_PLAYER: SECOND_PLAYER,
                app.getAssetManager());
        rootNode = ((SimpleApplication)app).getRootNode();
        rootNode.attachChild(player);
        sManager.getState(GsFreeFly.class).setEnabled(false);
        gsFire = sManager.getState(GsFire.class);
        movePonyCamera();
        setEnabled(true);
    }

    @Override
    public void makeAction(float fpf) {
        height += HEIGHT_INCREMENT;
        if (height > maxHeight)
            height = maxHeight;
        movePonyCamera();
    }

    @Override
    public void update(float tpf) {
        height -= HEIGHT_FALLOF;
        if (height < minHeight)
            height = minHeight;
        movePonyCamera();
    }
    
    @Override
    public void makeLeft(float fpf) {
        angle += POSITION_INCREMENT*fpf;
        movePonyCamera();
    }

    @Override
    public void makeRight(float fpf) {
        angle -= POSITION_INCREMENT*fpf;
        movePonyCamera();
    }

    @Override
    public void makeUp(float fpf) {
        gsFire.userClicked(angle, height);
    }

    @Override
    public void makeDown(float fpf) {
        
    }

    @Override
    public float tickTime() {
        return 0.1f;
    }
    
    private void movePonyCamera() {
        
        angle -= FastMath.floor(angle);
        
        float distance = gsGame.freeFlyDistance(angle, height);
        
        player.setLocalTranslation(
                GsGame.getCircleX(angle, distance),
                height,
                GsGame.getCircleZ(angle, distance));
        
        player.setLocalRotation(new Quaternion().fromAngleAxis(
                (-angle+0.375f)*FastMath.TWO_PI,
                Vector3f.UNIT_Y));
        
        camera.setLocation(new Vector3f(
                GsGame.getCircleX(angle, distance+8),
                height,
                GsGame.getCircleZ(angle, distance+8)));
        
        camera.lookAt(new Vector3f(0,height,0), Vector3f.UNIT_Y);
    }
}
