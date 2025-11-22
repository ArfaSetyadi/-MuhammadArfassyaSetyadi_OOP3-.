package com.Arfa.frontend.obstacles;

import com.Arfa.frontend.Player;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public abstract class BaseObstacle {
    protected float x;
    protected float y;
    protected float width;
    protected float height;

    protected Rectangle collider;
    protected float speedMultiplier = 1f;

    protected BaseObstacle(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.collider = new Rectangle(x, y, w, h);
    }

    public abstract void update(float delta);

    public void update(float delta, Player player) {
        update(delta);
    }

    public abstract void render(ShapeRenderer s);

    public boolean isColliding(Rectangle other) {
        return collider.overlaps(other);
    }

    public boolean isOffScreenCamera(float cameraLeftEdge) {
        return x + width < cameraLeftEdge - 50f;
    }

    public Rectangle getCollider() {
        return collider;
    }

    public float getX() { return x; }
    public float getY() { return y; }

    protected void updateCollider() {
        collider.setPosition(x, y);
    }

    public void setSpeedMultiplier(float m) {
        this.speedMultiplier = m;
    }
}
