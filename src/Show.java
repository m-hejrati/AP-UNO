/**
 * this class print and show different shapes and cards
 *
 * @author Mahdi Hejrati 9723100
 * @since 2020.04.15
 */

import java.util.*;

public class Show {

    // define constants for colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    /**
     * show all information we need
     * @param player player to show
     * @param players all the players in the game
     * @param myTable table of the game
     */
    public void print(Player player, ArrayList<Player> players, Table myTable) {

        // iterate on all players and show the last card they played
        for (Player allPlayer : players) {
            System.out.print(ANSI_BLACK + allPlayer.getName() + " has " + allPlayer.getCardList().size() + " card ");
            if (allPlayer.getLastCard() != null) {
                System.out.println("and the last card that he played is: ");
                setColor(allPlayer.getLastCard().getColorNum());
                System.out.println("# # # # #\t");
                System.out.println("#       #\t");
                printDiffType(allPlayer.getLastCard());
                System.out.println();
                System.out.println("#       #\t");
                System.out.println("# # # # #\t");
            }else
                System.out.println();
        }

        System.out.println(ANSI_BLACK + "The card in the table is:");
        printTableCard(myTable);

        // if it is one of black card tou should play an specific color
        if(myTable.getOnTableCard() instanceof BlackCard) {
            System.out.print("You should play a ");
            switch (myTable.getOnTableColor()) {
                case 1:
                    System.out.println("red");
                    break;
                case 2:
                    System.out.println("green");
                    break;
                case 3:
                    System.out.println("blue");
                    break;
                case 4:
                    System.out.println("yellow");
                    break;
            }
        }

        // if we want to see the cards of the human player
        if (player.getNumber() == 1) {
            int n = player.getCardList().size();
            System.out.println(ANSI_BLACK + player.getName() + " has " + n + " cards:");
            printMyCards(player);
        }
    }

    /**
     * this method change the font color by getting color number
     * @param colorNum color number
     */
    public void setColor (int colorNum){
        switch (colorNum) {
            case 0:
                System.out.print(ANSI_BLACK);
                break;
            case 1:
                System.out.print(ANSI_RED);
                break;
            case 2:
                System.out.print(ANSI_GREEN);
                break;
            case 3:
                System.out.print(ANSI_BLUE);
                break;
            case 4:
                System.out.print(ANSI_YELLOW);
                break;
        }
    }

    /**
     * this method show the most important part of each card that write type of it
     * @param thisCard card to show
     */
    public void printDiffType(Card thisCard) {

        // if numeric: show its number
        if (thisCard instanceof NumericCard) {
            NumericCard card = (NumericCard) thisCard;
            System.out.print("#   " + card.getNumber() + "   #\t");

        // if motion: show 'skp' for skip card, 'rev' for reverse and '+2' for draw+2 card.
        } else if (thisCard instanceof MotionCard) {
            MotionCard card = (MotionCard) thisCard;
            switch (card.getType()) {
                case 1:
                    System.out.print("#  skp  #\t");
                    break;
                case 2:
                    System.out.print("#  rev  #\t");
                    break;
                case 3:
                    System.out.print("#  +2   #\t");
                    break;
            }
        // if black card: show 'wld' for normal wild card and '+4' for wild draw card.
        } else {
            BlackCard card = (BlackCard) thisCard;
            switch (card.getType()) {
                case 1:
                    System.out.print("#  wld  #\t");
                    break;
                case 2:
                    System.out.print("#  +4   #\t");
                    break;
            }
        }
    }

    /**
     * show card that is on the table
     * @param myTable table
     */
    public void printTableCard(Table myTable){
        setColor(myTable.getOnTableCard().getColorNum());
        System.out.println("# # # # #\t");
        System.out.println("#       #\t");
        printDiffType(myTable.getOnTableCard());
        System.out.println();
        System.out.println("#       #\t");
        System.out.println("# # # # #\t" + ANSI_RESET);
    }

    /**
     * show cards of human player to choose
     * @param player player
     */
    public void printMyCards (Player player){
        int n = player.getCardList().size();

        // number of each card to easier choose
        for (int i = 1; i <= n; i++) {
            System.out.print("   (" + i + ")  \t");
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            setColor(player.getCardList().get(i).getColorNum());
            System.out.print("# # # # #\t");
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            setColor(player.getCardList().get(i).getColorNum());
            System.out.print("#       #\t");
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            Card thisCard = player.getCardList().get(i);
            setColor(thisCard.getColorNum());
            printDiffType(thisCard);
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            setColor(player.getCardList().get(i).getColorNum());
            System.out.print("#       #\t");
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            setColor(player.getCardList().get(i).getColorNum());
            System.out.print("# # # # #\t");
        }
        System.out.println(ANSI_BLACK);
    }

    /**
     * show result at the end of the game
     * @param players all players
     */
    public void result (ArrayList<Player> players){

        // save name and points of each player in map
        HashMap<String, Integer> points = new HashMap<>();
        for (Player player : players)
            points.put(player.getName(), calculatePoint (player));

        // print them from least point to highest.
        while (points.size() != 0) {
            int minValue = Collections.min(points.values());
            Iterator it = points.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if (pair.getValue().equals(minValue)) {
                    System.out.println(pair.getKey() + ": \t" + pair.getValue());
                    it.remove();
                }
            }
        }
    }

    /**
     * calculate points of each player
     * @param player player to calculate point
     * @return point
     */
    public int calculatePoint (Player player){
        int point = 0;
        for (Card card : player.getCardList()){
            point += card.getPoint();
        }
        return point;
    }
}