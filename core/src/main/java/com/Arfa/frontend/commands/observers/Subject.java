package com.Arfa.frontend.commands.observers;

public interface Subject {
    void addObserver(Observer observer);
    void notifyObserver(int score);
}
