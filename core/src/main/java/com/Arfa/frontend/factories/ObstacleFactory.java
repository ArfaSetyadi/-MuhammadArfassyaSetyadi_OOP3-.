package com.Arfa.frontend.factories;

import java.util.*;
import com.Arfa.frontend.obstacles.BaseObstacle;

public class ObstacleFactory {

    public interface ObstacleCreator {
        BaseObstacle create(float groundTopY, float spawnX, float playerHeight, Random rng);
        void release(BaseObstacle obstacle);
        void releaseAll();
        List<? extends BaseObstacle> getInUse();
        boolean supports(BaseObstacle obstacle);
        String getName();
    }

    private final Map<String, ObstacleCreator> creators = new HashMap<>();
    private final List<ObstacleCreator> weightedSelection = new ArrayList<>();
    private final Random random = new Random();

    public ObstacleFactory() {
        register(new VerticalLaserCreator());
        register(new HorizontalLaserCreator());
        register(new HomingMissileCreator());
    }

    private void register(ObstacleCreator creator) {
        creators.put(creator.getName(), creator);
    }

    public void setWeights(Map<String, Float> weights) {
        weightedSelection.clear();
        for (Map.Entry<String, Float> entry : weights.entrySet()) {
            String name = entry.getKey();
            int weight = Math.max(0, Math.round(entry.getValue()));
            if (creators.containsKey(name)) {
                for (int i = 0; i < weight; i++) {
                    weightedSelection.add(creators.get(name));
                }
            }
        }
    }


    public BaseObstacle createRandomObstacle(float groundTopY, float spawnX, float playerHeight) {
        if (weightedSelection.isEmpty()) {
            List<ObstacleCreator> fallback = new ArrayList<>(creators.values());
            ObstacleCreator c = fallback.get(random.nextInt(fallback.size()));
            return c.create(groundTopY, spawnX, playerHeight, random);
        }

        ObstacleCreator creator = selectWeightedCreator();
        return creator.create(groundTopY, spawnX, playerHeight, random);
    }

    private ObstacleCreator selectWeightedCreator() {
        return weightedSelection.get(random.nextInt(weightedSelection.size()));
    }

    public void releaseObstacle(BaseObstacle obstacle) {
        for (ObstacleCreator creator : creators.values()) {
            if (creator.supports(obstacle)) {
                creator.release(obstacle);
                return;
            }
        }
    }

    public void releaseAllObstacles() {
        for (ObstacleCreator creator : creators.values()) {
            creator.releaseAll();
        }
    }

    public List<BaseObstacle> getAllInUseObstacles() {
        List<BaseObstacle> list = new ArrayList<>();
        for (ObstacleCreator creator : creators.values()) {
            list.addAll(creator.getInUse());
        }
        return list;
    }

    public List<String> getRegisteredCreatorNames() {
        return new ArrayList<>(creators.keySet());
    }
}
