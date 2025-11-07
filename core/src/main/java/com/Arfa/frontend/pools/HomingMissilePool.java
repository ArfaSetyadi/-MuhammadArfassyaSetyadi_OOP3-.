package com.Arfa.frontend.pools;

import com.Arfa.frontend.obstacles.HomingMissile;
import com.badlogic.gdx.math.Vector2;

public class HomingMissilePool extends ObjectPool<HomingMissile> {

    @Override
    protected HomingMissile createObject() {
        return new HomingMissile(new Vector2(0, 0));
    }

    @Override
    protected void resetObject(HomingMissile missile) {
        missile.setPosition(0, 0);
        missile.setTarget(null);
        missile.setActive(false);
    }

    public HomingMissile obtain(Vector2 position) {
        HomingMissile missile = super.obtain();
        missile.initialize(position, 0);
        missile.setActive(true);
        return missile;
    }
}

