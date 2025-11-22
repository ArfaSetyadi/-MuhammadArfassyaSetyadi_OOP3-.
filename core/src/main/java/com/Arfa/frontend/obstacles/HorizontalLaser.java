package com.Arfa.frontend.obstacles;

import com.Arfa.frontend.Player;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class HorizontalLaser extends BaseObstacle {

    private float speed = 120f;

    public HorizontalLaser(float x, float y) {
        super(x, y, 200f, 12f);
    }

    @Override
    public void update(float delta) {
        x -= speed * speedMultiplier * delta;
        updateCollider();
    }

    @Override
    public void update(float delta, Player player) {
        update(delta);
    }

    @Override
    public void render(ShapeRenderer s) {
        s.rect(x, y, width, height);
    }
}
