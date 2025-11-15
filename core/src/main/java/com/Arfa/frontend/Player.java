package com.Arfa.frontend;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private Vector2 position;
    private Vector2 velocity;
    private float gravity = 2000f;
    private float force = 4500f;
    private float maxVerticalSpeed = 700f;
    private Rectangle collider;
    private float width = 64f;
    private float height = 64f;
    private float baseSpeed = 300f;
    private float distanceTraveled = 0f;
    private boolean isDead = false;
    private Vector2 startPosition;
    private boolean pendingFly = false;

    public Player(Vector2 startPosition) {
        this.startPosition = new Vector2(startPosition);
        this.position = new Vector2(startPosition);
        collider = new Rectangle(position.x, position.y, width, height);
        velocity = new Vector2(baseSpeed, 0);
    }

    public void update(float delta, boolean isFlyingInput) {
        if (!isDead) {
            distanceTraveled += velocity.x * delta;
            applyGravity(delta);
            if (isFlyingInput || pendingFly) {
                fly(delta);
                pendingFly = false;
            }
            position.x += velocity.x * delta;
            position.y += velocity.y * delta;
        }
        updateCollider();
    }

    public void fly() {
        if (!isDead) pendingFly = true;
    }

    public void fly(float delta) {
        if (!isDead) velocity.y += force * delta;
    }

    private void applyGravity(float delta) {
        velocity.y -= gravity * delta;
        velocity.x = baseSpeed;
        if (velocity.y < -maxVerticalSpeed) velocity.y = -maxVerticalSpeed;
        if (velocity.y > maxVerticalSpeed) velocity.y = maxVerticalSpeed;
    }

    private void updateCollider() {
        collider.setPosition(position.x, position.y);
    }

    public void checkBoundaries(Ground ground, float ceilingY) {
        if (ground.isColliding(collider)) {
            position.y = ground.getTopY();
            velocity.y = 0;
        }
        if (position.y + height > ceilingY) {
            position.y = ceilingY - height;
            velocity.y = 0;
        }
    }

    public void renderShape(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(0f, 1f, 0f, 1f);
        shapeRenderer.rect(position.x, position.y, width, height);
    }

    public void die() {
        isDead = true;
        velocity.x = 0;
        velocity.y = 0;
    }

    public void reset() {
        isDead = false;
        position.set(startPosition);
        velocity.set(baseSpeed, 0);
        distanceTraveled = 0f;
    }

    public boolean isDead() {
        return isDead;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }


    public Rectangle getCollider() {
        return collider;
    }

    public float getDistanceTraveled() {
        return distanceTraveled / 10f;
    }
}
