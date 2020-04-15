/**
 *
 * @author Mahdi Hejrati 9723100
 * @since 2020.04.15
 */

public class Show {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    public void print(Player player) {

        if (player.getNumber() == 1) {
            int n = player.getCardList().size();
            System.out.println("You have " + n + " cards:");

            for (int i = 0; i < n; i++) {
                setColor(player.getCardList().get(i).getColorNum());
                System.out.print("# # # # #\t");
            }
            System.out.println();

            for (int i = 0; i < n; i++){
                setColor(player.getCardList().get(i).getColorNum());
                System.out.print("#       #\t");
            }
            System.out.println();

            for (int i = 0; i < n; i++) {
                setColor(player.getCardList().get(i).getColorNum());
                System.out.print("#       #\t");
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
            System.out.println();
        }
    }


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
}