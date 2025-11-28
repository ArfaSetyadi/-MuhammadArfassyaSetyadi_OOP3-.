package com.Arfa.frontend;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Coin {
    private Vector2 position;
    private Vector2 velocity;
    private Rectangle collider;
    private final float radius;
    private boolean active;
    private float bobOffset;
    private float bobSpeed;

    public coin(Vector2 startPosition, Rectangle collider){
        this.collider=collider;
    }
    public update(float delta){
        bobOffset=bobSpeed*delta;
        updatecollider();
    }
    public renderShape(ShapeRenderer shapeRenderer){
        drawY = position.y + (float)(Math.sin(bobOffset) * 5f);
        shapeRenderer.circle();
        shapeRenderer.setColor(1f, 1f, 0f, 1f);
        if (active) return();
    }
    public isColliding(Rectangle playerCollider){
        return active && collider.overlaps(playerCollider);
    }
}


