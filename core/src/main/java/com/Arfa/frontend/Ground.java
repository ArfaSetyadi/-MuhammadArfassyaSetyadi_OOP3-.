package com.Arfa.frontend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Ground {

    private static final float GROUND_HEIGHT = 50f;
    private Rectangle collider;

    public Ground() {
        float screenWidth = Gdx.graphics.getWidth();
        collider = new Rectangle(0, 0, screenWidth * 2, GROUND_HEIGHT);
    }

    public void update(float cameraX) {
        float screenWidth = Gdx.graphics.getWidth();
        collider.setX(cameraX - screenWidth / 2f - 500);
        collider.setY(0);
        collider.setWidth(screenWidth * 2);
    }

    public boolean isColliding(Rectangle playerCollider) {
        return collider.overlaps(playerCollider);
    }

    public float getTopY() {
        return GROUND_HEIGHT;
    }

    public void renderShape(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1f); // Gray color
        shapeRenderer.rect(collider.x, collider.y, collider.width, collider.height);
    }
}
