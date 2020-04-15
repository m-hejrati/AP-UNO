/**
 *
 * @author Mahdi Hejrati 9723100
 * @since 2020.04.15
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    Scanner input = new Scanner(System.in);
    Table myTable;
    ArrayList<Player> players;

    public Game() {
        players = new ArrayList<>();
        makePlayers();
        myTable = new Table();
        myTable.setFirstTurn(getPlayersNumber());
        for (Player player : players)
            player.setFirstCards(myTable);
    }

    public int getPlayersNumber() {
        return players.size();
    }


    public void printTest () {
        int i = 1;
        for (Card card : myTable.getCardList()) {
            System.out.print(i);
            System.out.print(card + "\t");
            i++;
        }
        System.out.println();

        System.out.println(myTable.getLastCard());

        System.out.println(myTable.getNowTurn());

        for (Player player : players) {
            System.out.print(player.getName());
            System.out.println(player.getCardList());
        }

        Show show = new Show();
        show.print(players.get(0));

    }


    public void makePlayers (){
        System.out.println("Please enter number of players: (between 3 and 5)");
        int n;
        do {
            n = input.nextInt();
        } while (n < 3 || n > 5);

        System.out.println("Please enter your name:");
        players.add(new Player(1, input.next()));
        for (int i = 2 ; i <= n; i++)
            players.add(new Player(i, "com"+(i-1)));
    }

}