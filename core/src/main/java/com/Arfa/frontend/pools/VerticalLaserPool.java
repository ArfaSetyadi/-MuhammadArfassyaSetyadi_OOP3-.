package com.Arfa.frontend.pools;

import com.Arfa.frontend.obstacles.VerticalLaser;
import com.badlogic.gdx.math.Vector2;

public class VerticalLaserPool extends ObjectPool<VerticalLaser> {

    @Override
    protected VerticalLaser createObject() {
        return new VerticalLaser(new Vector2(800, 0), 100);
    }

    @Override
    protected void resetObject(VerticalLaser laser) {
        laser.setPosition(800, 0);
        laser.setActive(false);
    }

    public VerticalLaser obtain(Vector2 position, int length) {
        VerticalLaser laser = super.obtain();
        laser.initialize(position, length);
        laser.setActive(true);
        return laser;
    }
}

