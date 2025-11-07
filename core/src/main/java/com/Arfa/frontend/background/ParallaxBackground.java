package com.Arfa.frontend.background;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

public class ParallaxBackground {

    private Texture layer1;
    private Texture layer2;
    private Texture layer3;

    private float speed1 = 0.2f;
    private float speed2 = 0.5f;
    private float speed3 = 1f;

    public ParallaxBackground() {
        layer1 = new Texture("bg_layer1.png");
        layer2 = new Texture("bg_layer2.png");
        layer3 = new Texture("bg_layer3.png");
    }

    public void render(SpriteBatch batch, float cameraX) {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        float x1 = -((cameraX * speed1) % width);
        float x2 = -((cameraX * speed2) % width);
        float x3 = -((cameraX * speed3) % width);

        batch.draw(layer1, x1, 0, width, height);
        batch.draw(layer1, x1 + width, 0, width, height);

        batch.draw(layer2, x2, 0, width, height);
        batch.draw(layer2, x2 + width, 0, width, height);

        batch.draw(layer3, x3, 0, width, height);
        batch.draw(layer3, x3 + width, 0, width, height);
    }

    public void dispose() {
        layer1.dispose();
        layer2.dispose();
        layer3.dispose();
    }
}

