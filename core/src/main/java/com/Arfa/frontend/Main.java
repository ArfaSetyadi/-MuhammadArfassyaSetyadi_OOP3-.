package com.Arfa.frontend;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Main extends ApplicationAdapter {

    ShapeRenderer shape;
    float x, y;
    float size = 50;

    Color[] colors = {Color.RED, Color.YELLOW, Color.BLUE};
    int colorIndex = 0;

    @Override
    public void create() {
        shape = new ShapeRenderer();

        x = Gdx.graphics.getWidth() / 2f - size / 2f;
        y = Gdx.graphics.getHeight() / 2f - size / 2f;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D))
            x += 3;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A))
            x -= 3;
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W))
            y += 3;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S))
            y -= 3;

        x = Math.max(0, Math.min(x, Gdx.graphics.getWidth() - size));
        y = Math.max(0, Math.min(y, Gdx.graphics.getHeight() - size));

        if (Gdx.input.justTouched()) {
            colorIndex = (colorIndex + 1) % colors.length;
            System.out.println("Color changed to: " + colors[colorIndex]);
        }

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(colors[colorIndex]);
        shape.rect(x, y, size, size);
        shape.end();
    }

    @Override
    public void dispose() {
        shape.dispose();
    }
}
