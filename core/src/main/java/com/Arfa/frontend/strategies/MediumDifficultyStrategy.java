package com.Arfa.frontend.strategies;

import java.util.HashMap;
import java.util.Map;

public class MediumDifficultyStrategy implements DifficultyStrategy {

    @Override
    public float getGravity() {
        return 2000f;
    }

    @Override
    public float getForce() {
        return 4500f;
    }

    @Override
    public float getMaxVerticalSpeed() {
        return 700f;
    }

    @Override
    public float getSpeed() {
        return 300f;
    }

    @Override
    public Map<String, Float> getObstacleWeights() {
        Map<String, Float> w = new HashMap<>();
        w.put("VerticalLaser", 1.0f);
        w.put("HorizontalLaser", 1.0f);
        w.put("HomingMissile", 0.6f);
        return w;
    }

    @Override
    public float getSpawnInterval() {
        return 2.0f;
    }

    @Override
    public int getDensity() {
        return 1;
    }

    @Override
    public float getMinGap() {
        return 250f;
    }
}
