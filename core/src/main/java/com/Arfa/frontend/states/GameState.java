package com.Arfa.frontend.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface GameState {
    void update(float deltaTime);
    void render(SpriteBatch batch);
    void dispose();
}
