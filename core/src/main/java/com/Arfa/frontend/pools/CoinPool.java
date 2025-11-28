package com.Arfa.frontend.pools;

import com.Arfa.frontend.Coin;
import com.badlogic.gdx.math.Vector2;

public class CoinPool extends ObjectPool<Coin>{
    @Override
    protected Coin createObject(){
        return new Coin(new Vector2(0,0));
    }
    @Override
    protected void  resetObject(){
        coin.setActive(false)
    }

    public obtain(float x, float y)

}
