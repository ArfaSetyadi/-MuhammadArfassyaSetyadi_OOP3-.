package com.Arfa.frontend.obstacles;

import com.Arfa.frontend.Player;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class VerticalLaser extends BaseObstacle {

    public VerticalLaser(float x, float y) {
        super(x, y, 12f, 200f);
    }

    @Override
    public void update(float delta) {
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
