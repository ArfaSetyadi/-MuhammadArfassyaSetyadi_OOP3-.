package com.Arfa.frontend.obstacles;

import com.Arfa.frontend.Player;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class HomingMissile extends BaseObstacle {

    private float speed;
    private Player target;

    public HomingMissile(float x, float y, float speed) {
        super(x, y, 32f, 16f);
        this.speed = speed;
    }

    public void setTarget(Player p) {
        this.target = p;
    }

    @Override
    public void update(float delta, Player player) {
        this.target = player;
        update(delta);
    }

    @Override
    public void update(float delta) {

        if (target != null) {
            float targetY = target.getPosition().y + target.getHeight() / 2f;
            float dy = targetY - (y + height / 2f);
            float vy = Math.signum(dy) * speed * 0.5f * delta;
            y += vy;
        }

        x -= speed * 0.8f * speedMultiplier * delta;
        updateCollider();
    }

    @Override
    public void render(ShapeRenderer s) {
        s.rect(x, y, width, height);
    }
}
