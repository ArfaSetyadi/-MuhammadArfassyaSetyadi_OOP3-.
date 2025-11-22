package com.Arfa.frontend.strategies;

import java.util.HashMap;
import java.util.Map;

public class HardDifficultyStrategy implements DifficultyStrategy {

    @Override
    public float getGravity() {
        return 0.45f;
    }

    @Override
    public float getForce() {
        return 13f;
    }

    @Override
    public float getMaxVerticalSpeed() {
        return 10f;
    }

    @Override
    public float getSpeed() {
        return 4.5f;
    }

    @Override
    public Map<String, Float> getObstacleWeights() {
        Map<String, Float> map = new HashMap<>();
        map.put("VerticalLaser", 2f);
        map.put("HorizontalLaser", 2f);
        map.put("HomingMissile", 1.5f);
        return map;
    }

    @Override
    public float getSpawnInterval() {
        return 0.8f;
    }

    @Override
    public int getDensity() {
        return 2;
    }

    @Override
    public float getMinGap() {
        return 120f;
    }
}
