package com.Arfa.frontend.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverState implements GameState{
    private final GameStateManager gameStateManager;
    private final BitmapFont font;

    public GameOverState(GameStateManager gsm, BitmapFont font){
        this.gameStateManager = gsm;
        this.font = new BitmapFont();
    }
    @Override
    public void update(float delta){
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            gsm.set(new PlayingState(gsm));
    }
}

@Override
public void render(SpriteBatch batch){
    font.draw(batch, "GAME OVER" , Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
    font.draw(batch, "Press SPACE to restart" , Gdx.graphics.getWidth() , Gdx.graphics.getHeight());

}
