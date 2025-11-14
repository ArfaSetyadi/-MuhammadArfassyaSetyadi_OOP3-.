package com.Arfa.frontend.commands;

import com.Arfa.frontend.GameManager;
import com.Arfa.frontend.Player;

public class RestartCommand {
    public Player player;
    public GameManager gameManager;

    public RestartCommand(Player player , GameManager gameManager){
        this.player = player;
        this.gameManager = gameManager;

        public void execute(){
            player.reset();
        }
    }
}
