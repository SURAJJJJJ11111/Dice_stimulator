import java.util.*;

public class Executor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Player> players = new ArrayList<>();

        System.out.print("Enter number of players: ");
        int numPlayers = sc.nextInt();
        sc.nextLine();  // consume newline

        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name of Player " + i + ": ");
            String name = sc.nextLine();
            players.add(new Player(name));
        }

        GameEngine engine = new GameEngine();
        int round = 1;

        while (true) {
            System.out.println("\n========== Round " + round + " ==========");
            engine.playRound(players);
            round++;

            System.out.print("\n Do you want to play another round? (y/n): ");
            String choice = sc.next().toLowerCase();
            if (!choice.equals("y")) {
                break;
            }
        }

        engine.showResults(players);
        sc.close();
    }
}
