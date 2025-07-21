import java.util.*;

public class GameEngine {
    private Dice dice;

    public GameEngine() {
        dice = new Dice();
    }

    public void playRound(List<Player> players) {
        Map<Player, Integer> rolls = new HashMap<>();
        int maxRoll = 0;

        System.out.println("\n--- New Round ---");

        for (Player p : players) {
            int roll = dice.roll();
            rolls.put(p, roll);
            System.out.println(p.getName() + " rolled: " + roll);
            if (roll > maxRoll) {
                maxRoll = roll;
            }
        }

        // Identify winners
        List<Player> roundWinners = new ArrayList<>();
        for (Map.Entry<Player, Integer> entry : rolls.entrySet()) {
            if (entry.getValue() == maxRoll) {
                roundWinners.add(entry.getKey());
            }
        }

        // Award win(s)
        if (roundWinners.size() == 1) {
            System.out.println(" " + roundWinners.get(0).getName() + " wins the round!");
            roundWinners.get(0).incrementWins();
        } else {
            System.out.print(" It's a tie between: ");
            for (Player p : roundWinners) {
                System.out.print(p.getName() + " ");
                p.incrementWins();
            }
            System.out.println();
        }
    }

    public void showResults(List<Player> players) {
        System.out.println("\n=== Final Results ===");
        for (Player p : players) {
            System.out.println(p.getName() + ": " + p.getWins() + " wins");
        }

        // Determine overall winner
        int maxWins = players.stream().mapToInt(Player::getWins).max().orElse(0);
        List<Player> winners = new ArrayList<>();

        for (Player p : players) {
            if (p.getWins() == maxWins) {
                winners.add(p);
            }
        }

        if (winners.size() == 1) {
            System.out.println(" Overall winner: " + winners.get(0).getName());
        } else {
            System.out.print(" It's a draw between: ");
            for (Player p : winners) {
                System.out.print(p.getName() + " ");
            }
            System.out.println();
        }
    }
}
