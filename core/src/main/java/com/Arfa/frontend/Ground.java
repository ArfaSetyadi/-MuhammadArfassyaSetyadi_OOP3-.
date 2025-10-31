package com.Arfa.frontend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import java.awt.*;

public class Ground extends ShapeRenderer {
    private static final float GROUND_HEIGHT = 50f;
    private Rectangle collider;

    public Ground(){
        float screenWidht = Gdx.graphics.getWidth();
        collider= new Rectangle(x=0 , y=0 , screenWidht*2, GROUND_HEIGHT);
    }

    public void update(float cameraX){
        float screenWidht = Gdx.graphics.getWidth();
        collider.setX(cameraX - screenWidht/2f - 500 )
    }


