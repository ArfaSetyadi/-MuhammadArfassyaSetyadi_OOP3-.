package com.Arfa.frontend.strategies;

import java.util.HashMap;
import java.util.Map;

public class EasyDifficultyStrategy implements DifficultyStrategy {

    @Override
    public float getGravity() {
        return 0.25f;
    }

    @Override
    public float getForce() {
        return 11f;
    }

    @Override
    public float getMaxVerticalSpeed() {
        return 8f;
    }

    @Override
    public float getSpeed() {
        return 2f;
    }

    @Override
    public Map<String, Float> getObstacleWeights() {
        Map<String, Float> map = new HashMap<>();
        map.put("VerticalLaser", 1f);
        map.put("HorizontalLaser", 1f);
        map.put("HomingMissile", 0.5f);
        return map;
    }

    @Override
    public float getSpawnInterval() {
        return 1.5f;
    }

    @Override
    public int getDensity() {
        return 1;
    }

    @Override
    public float getMinGap() {
        return 150f;
    }
}
