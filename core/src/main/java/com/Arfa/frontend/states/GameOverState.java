package com.Arfa.frontend.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class GameOverState implements GameState {
    private final GameStateManager gsm;
    private final BitmapFont font;

    public GameOverState(GameStateManager gsm) {
        this.gsm = gsm;
        this.font = new BitmapFont();
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            gsm.set(new PlayingState(gsm));
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();
        batch.begin();
        String s1 = "GAME OVER";
        String s2 = "Press SPACE to restart";
        BitmapFont.TextBounds b1 = null;
        try {
            b1 = font.getBounds(s1);
        } catch (Exception e) {
            // fallback calculation
        }
        float x1 = w / 2f - (font.getRegion().getRegionWidth() / 2f);
        float y1 = h / 2f + 10f;
        font.draw(batch, s1, w / 2f - 60f, y1);
        font.draw(batch, s2, w / 2f - 80f, y1 - 30f);
        batch.end();
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
