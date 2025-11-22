package com.Arfa.frontend.strategies;

import java.util.Map;

public interface DifficultyStrategy {

    float getGravity();
    float getForce();
    float getMaxVerticalSpeed();
    float getSpeed();

    Map<String, Float> getObstacleWeights();
    float getSpawnInterval();
    int getDensity();
    float getMinGap();
}
