package com.Arfa.frontend;

import com.Arfa.frontend.observers.Observer;
import com.Arfa.frontend.observers.ScoreManager;

public class GameManager {
    private static GameManager instance;
    private final ScoreManager scoreManager;

    private GameManager() {
        scoreManager = new ScoreManager();
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
}
