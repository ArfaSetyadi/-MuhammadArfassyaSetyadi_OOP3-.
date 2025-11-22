package com.Arfa.frontend;

import com.Arfa.frontend.observers.Observer;
import com.Arfa.frontend.observers.ScoreManager;
import com.Arfa.frontend.strategies.MediumDifficultyStrategy;
import com.Arfa.frontend.strategies.DifficultyStrategy;

public class GameManager {
    private static GameManager instance;
    private final ScoreManager scoreManager;
    private DifficultyStrategy difficultyStrategy;

    private GameManager() {
        scoreManager = new ScoreManager();
        difficultyStrategy = new MediumDifficultyStrategy();
    }

    public static GameManager getInstance() {
        if (instance == null) instance = new GameManager();
        return instance;
    }

    public void startGame() {
        scoreManager.setScore(0);
    }

    public void setScore(int s) {
        scoreManager.setScore(s);
    }

    public int getScore() {
        return scoreManager.getScore();
    }

    public void addObserver(Observer o) {
        scoreManager.addObserver(o);
    }

    public void removeObserver(Observer o) {
        scoreManager.removeObserver(o);
    }

    public void setDifficultyStrategy(DifficultyStrategy strategy) {
        if (strategy != null) difficultyStrategy = strategy;
    }

    public DifficultyStrategy getDifficultyStrategy() {
        return difficultyStrategy;
    }
}
