package com.Arfa.frontend.pools;

import com.Arfa.frontend.obstacles.HorizontalLaser;
import com.badlogic.gdx.math.Vector2;

public class HorizontalLaserPool extends ObjectPool<HorizontalLaser> {

    @Override
    protected HorizontalLaser createObject() {
        return new HorizontalLaser(new Vector2(800, 0), 100);
    }

    @Override
    protected void resetObject(HorizontalLaser laser) {
        laser.setPosition(800, 0);
        laser.setActive(false);
    }

    public HorizontalLaser obtain(Vector2 position, int length) {
        HorizontalLaser laser = super.obtain();
        laser.initialize(position, length);
        laser.setActive(true);
        return laser;
    }
}
