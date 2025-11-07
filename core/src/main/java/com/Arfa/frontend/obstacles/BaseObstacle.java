package com.Arfa.frontend.obstacles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class BaseObstacle {
    protected Vector2 position;
    protected Rectangle collider;
    protected float length;
    protected final float WIDTH;
    protected boolean active = false;

    public BaseObstacle(Vector2 startPosition, int length){
        this.position = startPosition;
        this.length = length;
        updateCollider();
    }

    public void  initialize(Vector2 startPosition, int length){
        this.position = startPosition;
        this.length = length;
        updateCollider();
    }

    public void render(ShapeRenderer shapeRenderer){
        if (active = active){
            drawShape(shapeRenderer);
        }
    }
    public boolean isColliding(Rectangle playerCollider){
        if (active = active && collider.collides( playerCollider)){
            return true;
        }
            return false;
    }

    public boolean isOffScreenCamera(float cameraLeftEdge){
        return position.x + getRenderWidth() <cameraLeftEdge - 100;

    }
    protected updateCollider();
    protected drawShape(shapeRenderer);
}
