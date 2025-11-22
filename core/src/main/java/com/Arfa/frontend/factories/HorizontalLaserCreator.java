package com.Arfa.frontend.factories;

import com.Arfa.frontend.obstacles.HorizontalLaser;
import com.Arfa.frontend.obstacles.BaseObstacle;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class HorizontalLaserCreator implements ObstacleFactory.ObstacleCreator {
    private final List<HorizontalLaser> inUse = new CopyOnWriteArrayList<>();

    @Override
    public BaseObstacle create(float groundTopY, float spawnX, float playerHeight, Random rng) {
        HorizontalLaser h = new HorizontalLaser(spawnX, groundTopY + 200f);
        inUse.add(h);
        return h;
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
    public List<HorizontalLaser> getInUse() {
        return inUse;
    }

    @Override
    public boolean supports(BaseObstacle obstacle) {
        return obstacle instanceof HorizontalLaser;
    }

    @Override
    public String getName() {
        return "HorizontalLaser";
    }
}
