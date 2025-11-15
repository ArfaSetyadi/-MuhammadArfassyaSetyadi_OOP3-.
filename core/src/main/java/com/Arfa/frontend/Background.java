package com.Arfa.frontend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Background {
    private Texture backgroundTexture;
    private TextureRegion backgroundRegion;
    private float width;
    private float height;
    private float currentCameraX = 0f;

    public Background() {
        backgroundTexture = new Texture(Gdx.files.internal("background.png"));
        backgroundRegion = new TextureRegion(backgroundTexture);
        width = 2688f;
        height = 1536f;
    }

    public void update(float cameraX) {
        this.currentCameraX = cameraX;
    }

    public void render(SpriteBatch batch) {
        int screenW = Gdx.graphics.getWidth();
        int screenH = Gdx.graphics.getHeight();
        float scale = (float) screenH / height;
        float scaledW = width * scale;
        float scaledH = height * scale;
        float leftEdge = currentCameraX - screenW / 2f;
        int firstTile = (int) Math.floor(leftEdge / scaledW) - 1;
        int lastTile = (int) Math.ceil((leftEdge + screenW) / scaledW) + 1;
        batch.begin();
        for (int i = firstTile; i <= lastTile; i++) {
            float x = i * scaledW;
            batch.draw(backgroundRegion, x, 0f, scaledW, scaledH);
        }
        batch.end();
    }

    public void dispose() {
        if (backgroundTexture != null) {
            backgroundTexture.dispose();
            backgroundTexture = null;
        }
    }
}
