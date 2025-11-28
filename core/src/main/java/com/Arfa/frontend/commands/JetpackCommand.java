package com.Arfa.frontend.commands;

import com.Arfa.frontend.Player;

/**
 * Concrete command for jetpack action
 */
public class JetpackCommand implements Command {
    private Player player;

    public JetpackCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        if (!player.isDead()) {
            player.fly();
        }
    }
}
