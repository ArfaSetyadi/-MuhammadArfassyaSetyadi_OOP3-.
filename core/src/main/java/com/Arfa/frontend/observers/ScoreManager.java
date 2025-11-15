package com.Arfa.frontend.observers;

import java.util.ArrayList;
import java.util.List;

public class ScoreManager implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    private int score = 0;

    @Override
    public void addObserver(Observer observer) {
        if (observer != null && !observers.contains(observer)) observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(int score) {
        for (Observer o : observers) o.update(score);
    }

    public void setScore(int newScore) {
        if (this.score != newScore) {
            this.score = newScore;
            notifyObservers(this.score);
        }
    }

    public int getScore() {
        return score;
    }
}
