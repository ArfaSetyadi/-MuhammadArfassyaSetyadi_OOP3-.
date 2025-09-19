package Model;

import Model.Player;
import Model.Score;
import Repository.PlayerRepository;
import Repository.ScoreRepository;
import java.util.UUID;
public class Main {
    public static void main(String[] args) {
        PlayerRepository playerRepo = new PlayerRepository();
        ScoreRepository scoreRepo = new ScoreRepository();
        Player player1 = new Player("Stelle");
        Player player2 = new Player("gamerLooxmaxxing");
        Player player3 = new Player ("Stelle123");
        Player player4 = new Player ("Banananana");

        // Create and save Player

        player1.updateHighScore(1500);
        player1.addCoins(250);
        player1.addDistance(5000);

        player2.updateHighScore(3200);
        3
        player2.addCoins(750);
        player2.addDistance(12000);
        // Create update stats for player 3 and 4 (as you wish)

        // Create and save score with these following
        requirements;
        // Score 1: for player 2, has score 1500, coins 250, dan
        distance 5000
        // Score 2: for Player 4, has score 3200, coins 750, dan
        distance 12000
        // Score 3: for Player 1, has score 4000, coins 400, dan
        distance 32000
        // Score 4: for Player 4, has score 1800, coins 300, dan
        distance 6000
        // Score 5: for Player 3, has score 2400, coins 240, dan
        distance 2400
        // Score 6: for Player 2, has score 6200, coins 320, dan
        distance 5000
        // Score 7: for Player 4, has score 1800, coins 60, dan
        distance 1200
        // Score 8: for Player 1, has score 2100, coins 200, dan
        distance 7000
        // Score 9: for Player 1, has score 8000, coins 720, dan
        distance 6200
        // Score 10: for Player 3,has score 1900, coins 210, dan
        distance 4200

        System.out.println("=== TESTING CS3 ===");

        System.out.println("Find player by ID:");
        // Show the detail of Player 3

        System.out.println("All players:");
        // Show all player

        // Sort all player based on highscore

        System.out.println("Scores for player1:");
        // Find Score of Player 1
    }
}
