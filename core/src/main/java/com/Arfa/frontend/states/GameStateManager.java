package com.Arfa.frontend.states;

import java.util.Stack;

public class GameStateManager {
    private final Stack<GameState> states;

    public GameStateManager() {
        states = new Stack<>();
    }

    public void push(GameState state) {
        states.push(state);
    }

    public void pop() {
        if (!states.isEmpty()) {
            GameState top = states.pop();
            top.dispose();
        }
    }

    public void set(GameState state) {
        if (!states.isEmpty()) {
            pop();
        }
        push(state);
    }

    public void update(float delta) {
        if (!states.isEmpty()) {
            states.peek().update(delta);
        }
    }

    public void render(com.badlogic.gdx.graphics.g2d.SpriteBatch batch) {
        if (!states.isEmpty()) {
            states.peek().render(batch);
        }
    }
}

