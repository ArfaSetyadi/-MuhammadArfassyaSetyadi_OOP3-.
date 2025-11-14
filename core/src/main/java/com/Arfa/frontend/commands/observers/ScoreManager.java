package com.Arfa.frontend.commands.observers;

public class ScoreManager implements Subject{
    private final List<Observer> observers;
    private int score;
    public ScoreManager();
}
