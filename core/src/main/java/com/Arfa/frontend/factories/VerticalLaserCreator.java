package com.Arfa.frontend.factories;

import com.Arfa.frontend.obstacles.VerticalLaser;
import com.Arfa.frontend.obstacles.BaseObstacle;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class VerticalLaserCreator implements ObstacleFactory.ObstacleCreator {
    private final List<VerticalLaser> inUse = new CopyOnWriteArrayList<>();

    @Override
    public BaseObstacle create(float groundTopY, float spawnX, float playerHeight, Random rng) {
        VerticalLaser v = new VerticalLaser(spawnX, groundTopY + 100f);
        inUse.add(v);
        return v;
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
    public List<VerticalLaser> getInUse() {
        return inUse;
    }

    @Override
    public boolean supports(BaseObstacle obstacle) {
        return obstacle instanceof VerticalLaser;
    }

    @Override
    public String getName() {
        return "VerticalLaser";
    }
}
