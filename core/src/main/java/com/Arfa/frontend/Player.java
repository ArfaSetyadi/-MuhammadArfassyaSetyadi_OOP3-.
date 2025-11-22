package com.Arfa.frontend;

import com.Arfa.frontend.strategies.DifficultyStrategy;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {

    private Vector2 position;
    private Vector2 velocity;

    private DifficultyStrategy difficulty;

    private Rectangle collider;
    private float width = 64f;
    private float height = 64f;

    private float distanceTraveled = 0f;
    private boolean isDead = false;
    private boolean pendingFly = false;

    private Vector2 startPosition;

    public Player(Vector2 startPosition, DifficultyStrategy difficulty) {
        this.startPosition = new Vector2(startPosition);
        this.position = new Vector2(startPosition);
        this.difficulty = difficulty;

        this.velocity = new Vector2(difficulty.getSpeed(), 0);

        this.collider = new Rectangle(position.x, position.y, width, height);
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
        if (!isDead) {
            velocity.y += difficulty.getForce() * delta;
        }
    }

    private void applyGravity(float delta) {
        velocity.y -= difficulty.getGravity() * delta;

        velocity.x = difficulty.getSpeed();

        float maxSpeed = difficulty.getMaxVerticalSpeed();
        if (velocity.y < -maxSpeed) velocity.y = -maxSpeed;
        if (velocity.y > maxSpeed) velocity.y = maxSpeed;
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
        velocity.set(0, 0);
    }

    public void reset() {
        isDead = false;
        position.set(startPosition);
        velocity.set(difficulty.getSpeed(), 0);
        distanceTraveled = 0f;
    }

    public boolean isDead() { return isDead; }

    public Vector2 getPosition() { return position; }

    public Rectangle getCollider() { return collider; }

    public float getHeight() { return height; }

    public float getWidth() { return width; }

    public float getDistanceTraveled() {
        return distanceTraveled / 10f;
    }
}
