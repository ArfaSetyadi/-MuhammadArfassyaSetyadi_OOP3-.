package com.Arfa.frontend.factories;

import com.Arfa.frontend.obstacles.HomingMissile;
import com.Arfa.frontend.obstacles.BaseObstacle;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class HomingMissileCreator implements ObstacleFactory.ObstacleCreator {
    private final List<HomingMissile> inUse = new CopyOnWriteArrayList<>();

    @Override
    public BaseObstacle create(float groundTopY, float spawnX, float playerHeight, Random rng) {
        HomingMissile m = new HomingMissile(spawnX, groundTopY + 150f, 300f);
        inUse.add(m);
        return m;
    }

    @Override
    public void release(BaseObstacle obstacle) {
        inUse.remove(obstacle);
    }

    @Override
    public void releaseAll() {
        inUse.clear();
    }

    @Override
    public List<HomingMissile> getInUse() {
        return inUse;
    }

    @Override
    public boolean supports(BaseObstacle obstacle) {
        return obstacle instanceof HomingMissile;
    }

    @Override
    public String getName() {
        return "HomingMissile";
    }
}
