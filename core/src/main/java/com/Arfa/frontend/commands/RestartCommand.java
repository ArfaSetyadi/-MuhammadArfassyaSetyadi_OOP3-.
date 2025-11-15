package com.Arfa.frontend.commands;

import com.Arfa.frontend.GameManager;
import com.Arfa.frontend.Player;

public class RestartCommand implements Command {
    private final Player player;
    private final GameManager gameManager;

    public RestartCommand(Player player, GameManager gameManager) {
        this.player = player;
        this.gameManager = gameManager;
    }

    @Override
    public void execute() {
        player.reset();
        gameManager.setScore(0);
    }
}
