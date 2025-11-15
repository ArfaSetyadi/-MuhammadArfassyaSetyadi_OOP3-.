package com.Arfa.frontend.commands;

import com.Arfa.frontend.Player;

public class JetpackCommand implements Command {
    private final Player player;

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
