package com.Arfa.frontend.commands;

import com.Arfa.frontend.Player;
import com.badlogic.gdx.Gdx;

public class JetpackCommand implements Command {
    private Player player;

    public JetpackCommand(Player player){
        this.player = player;
    }
    @Override
    public void execute(){
        player.fly(Gdx.graphics.getDeltaTime());
    }
}
