/**
 *  Main class of the project
 *
 * @author Mahdi Hejrati 9723100
 * @since 2020.04.15
 */

public class Main {
    // define constants for colors
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {

        System.out.println(ANSI_PURPLE + "UNO Game");
        System.out.println("By Mahdi Hejrati 9723100 \n" + ANSI_RESET);

        // create the game
        Game myGame = new Game();

        // run the game
        myGame.run();
    }
}