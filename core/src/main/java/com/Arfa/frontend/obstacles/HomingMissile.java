package com.Arfa.frontend.obstacles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.Arfa.frontend.Player;

public class HomingMissile extends BaseObstacle {

    private Player target;
    private Vector2 velocity;
    private float speed = 200f;

    private float width = 40f;
    private float height = 20f;

    public HomingMissile(Vector2 startPosition) {
        super(startPosition, 0);
        this.velocity = new Vector2();
        updateCollider();
    }

    @Override
    public void initialize(Vector2 startPosition, int length) {
        super.initialize(startPosition, length);
        velocity.set(0, 0);
        target = null;
    }

    public void setTarget(Player target) {
        this.target = target;
    }

    public boolean isTargetingPlayer() {
        if (target == null) return false;

        float missileCenterX = position.x + width / 2f;
        float playerCenterX = target.getPosition().x + target.getWidth() / 2f;

        return missileCenterX < playerCenterX;
    }

    public void update(float delta) {
        if (!active || target == null) return;

        if (isTargetingPlayer()) {
            Vector2 targetPos = new Vector2(
                target.getPosition().x,
                target.getPosition().y + target.getHeight() / 2f
            );

            velocity.set(targetPos).sub(position).nor().scl(speed);

            position.x += velocity.x * delta;
            position.y += velocity.y * delta;

            updateCollider();
        }
    }

    @Override
    protected void updateCollider() {
        collider = new Rectangle(position.x, position.y, width, height);
    }

    @Override
    protected void drawShape(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(position.x, position.y, width, height);
    }

    @Override
    protected float getRenderWidth() {
        return width;
    }
}
