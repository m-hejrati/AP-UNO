/**
 * this is the most important class of project that create the game and run it
 * @author Mahdi Hejrati 9723100
 * @since 2020.04.15
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {

    Scanner input = new Scanner(System.in);
    Table myTable;
    int nowTurn;   // with this variable we determine player to play
    int direction;   // direction in which game iterate. clockwise(downward) or anticlockwise(upward)
    ArrayList<Player> players;   // list of players

    /**
     * constructor for game. it provide all needed thing.
     */
    public Game() {
        myTable = new Table();

        // at the beginning game iterate clockwise
        direction = 1;

        players = new ArrayList<>();

        // create players
        makePlayers();

        // at the beginning, we determine starter randomly.
        Random rnd = new Random();
        nowTurn = rnd.nextInt(getPlayersNumber());

        // and finally set 7 card ro each player randomly
        for (Player player : players)
            player.setFirstCards(myTable);
    }

    /**
     * getter for player number
     * @return number of each player
     */
    public int getPlayersNumber() {
        return players.size();
    }

    /**
     * make players by asking user
     */
    public void makePlayers() {

        System.out.println("Please enter number of players: (between 3 to 5)");
        int n;
        do {
            n = input.nextInt();
        } while (n < 3 || n > 5);

        System.out.println("Please enter your name:");
        players.add(new Player(1, input.next()));

        for (int i = 2; i <= n; i++)
            players.add(new Player(i, "com" + (i - 1)));
    }

    /**
     * this method run the game
     */
    public void run() {
        Show show = new Show();

        // iterate until the game has not finish yet
        while (!isFinish()) {

            // do an specific work in the first movement
            if (myTable.getFirstFlag()) {
                firstMovement(players.get(nowTurn));
                myTable.setFirstFlag(false);
                continue;
            }

            System.out.println();

            // show direction of game in every movement
            if (direction == 1)
                System.out.println("Direction: clockwise (downward)");
            else
                System.out.println("Direction: anticlockwise (upward)");

            //show required information
            show.print(players.get(nowTurn), players, myTable);

            // print whose turn is it now
            System.out.println(players.get(nowTurn).getName() +  " turn:");

            // and play
            play(players.get(nowTurn));

            // press something to continue
            System.out.println("Please write a number and press enter to continue");
            String tmp = input.next();

        }
        // show the result at the end
        System.out.println("\nThe game finished");
        show.result(players);
    }

    /**
     * check if the game is finish or not by checking number of cards of each player
     * @return true if finish or false if not
     */
    public boolean isFinish() {
        for (Player player : players)
            if (player.getCardList().size() == 0)
                return true;
        return false;
    }

    /**
     * playing the game by getting information from user
     * @param player player to play
     */
    public void play(Player player) {
        Random rnd = new Random();
        Show show = new Show();

        // hold possibles card to play in an array
        ArrayList<Card> possibles1 = new ArrayList<>();
        if (possibleCard(player.getCardList(), possibles1)) {

            int choosed = selection(player, possibles1);
            changeCards(player, choosed);

            System.out.println(player.getName() + " put this card on the table:");
            show.printTableCard(myTable);

            // if he put black card change the next color
            if (myTable.getOnTableCard() instanceof BlackCard)
                changeColor(player);

            // give penalty to next player
            getDrawPenalty(player);

        }else {
            // get one penalty if he does not have required card
            int delete = rnd.nextInt(myTable.getCardList().size());
            player.getCardList().add(myTable.getCardList().get(delete));
            myTable.getCardList().remove(delete);
            System.out.println(player.getName() + " get one penalty");

            // if he is human show his card to choose
            if (player.getNumber() == 1)
                show.printMyCards(player);

            // hold possibles card to play in an array
            ArrayList<Card> possibles2 = new ArrayList<>();
            if (possibleCard(player.getCardList(), possibles2)) {

                int choosed = selection(player, possibles2);
                changeCards(player, choosed);

                System.out.println(player.getName() + " put his card on the table");

                if (myTable.getOnTableCard() instanceof BlackCard)
                    changeColor(player);

                getDrawPenalty(player);
            }
        }

        // determine next turn to play
        if (myTable.getOnTableCard().getNext() == -1) {
            direction *= -1;
            // add players.size to avoid negative numbers
            nowTurn = (players.size() + nowTurn + direction) % players.size();
        } else
            // add players.size to avoid negative numbers
            nowTurn = (players.size() + nowTurn + direction * myTable.getOnTableCard().getNext()) % players.size();
    }

    /**
     * determine possible card to play
     * @param cardList player list of cards
     * @param possibles list of possibles
     * @return return true if it is possible to move or false if not
     */
    public boolean possibleCard(ArrayList<Card> cardList, ArrayList<Card> possibles) {
        for (Card card : cardList) {
            // if they have the same color
            if (card.getColorNum() == myTable.getOnTableColor()) {
                if (!possibles.contains(card))
                    possibles.add(card);

            // or both have the same type
            } else if (myTable.getOnTableCard() instanceof NumericCard && card instanceof NumericCard) {
                if (((NumericCard) card).getNumber() == ((NumericCard) myTable.getOnTableCard()).getNumber())
                    if (!possibles.contains(card))
                        possibles.add(card);

            // or both have the same type
            } else if (myTable.getOnTableCard() instanceof MotionCard && card instanceof MotionCard) {
                if (((MotionCard) card).getType() == ((MotionCard) myTable.getOnTableCard()).getType())
                    if (!possibles.contains(card))
                        possibles.add(card);
            }
        }

        // if there is not any possible color, add black color to possibles
        if (possibles.size() == 0)
            for (Card card : cardList)
                if (card.getColorNum() == 0)
                    if (!possibles.contains(card))
                        possibles.add(card);

        return (possibles.size() != 0);
    }

    /**
     * change location of card
     * @param player player
     * @param choosed choosed card
     */
    public void changeCards (Player player, int choosed){
        // set choose card to last used
        player.setLastCard(player.cardList.get(choosed));
        // add on table card to repository
        myTable.addCardToList(myTable.getOnTableCard());
        // set the new on table card
        myTable.setOnTableCard(player.cardList.get(choosed));
        // remove from his card list
        player.getCardList().remove(choosed);
        // update on table color
        myTable.setOnTableColor(myTable.getOnTableCard().getColorNum());
    }

    /**
     * select which card we want to play
     * @param player player to play
     * @param possibles possible cards
     * @return index of choose card
     */
    public int selection(Player player, ArrayList<Card> possibles){
        int choosed = 0;

        if (player.getNumber() == 1) {
            System.out.println("Please choose one of your card to play: (just enter a number)");

            choosed = input.nextInt() - 1;
            while(!possibles.contains(player.getCardList().get(choosed))) {
                System.out.println("You can not choose this card, please try another one:");
                choosed = input.nextInt() - 1;
            }

        } else {
            for (int i = 0; i < player.getCardList().size(); i++) {
                choosed = i;
                if (possibles.contains(player.getCardList().get(choosed)))
                    break;
            }
        }
        return choosed;
    }

    /**
     * change on table color when some one use black card
     * @param player player to change color
     */
    public void changeColor(Player player){

        if (player.getNumber() == 1) {
            System.out.println("Choose the color of next card: (just enter a number)");
            System.out.println("1) red    2) green    3) blue    4) yellow  \n");
        }
        int color = 0;

        if (player.getNumber() == 1) {
            color = input.nextInt();
            while (!(color <= 4 && color >= 1)) {
                System.out.println("choose a number between 1 to 4");
                color = input.nextInt();
            }

        } else {
            // choose the first color of his card except black
            for (int i = 0; i < player.getCardList().size(); i++) {
                color = player.getCardList().get(i).getColorNum();
                if (color != 0)
                    break;
            }
            if (color == 0) {
                Random rnd = new Random();
                color = rnd.nextInt(4) + 1;
            }
        }

        System.out.print("Color changed to ");
        switch (color) {
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

        myTable.setOnTableColor(color);
    }

    /**
     * get penalty to player
     * @param player player who give penalty
     */
    public void getPenalty(Player player){
        Random rnd = new Random();
        // get card from repo randomly
        int delete = rnd.nextInt(myTable.getCardList().size());
        player.getCardList().add(myTable.getCardList().get(delete));
        myTable.getCardList().remove(delete);
    }

    /**
     * do specific work for the first movement of the game
     * @param player player
     */
    public void firstMovement (Player player){

        // if it is draw type get two penalty
        if (myTable.getOnTableCard () instanceof MotionCard) {
            MotionCard card = (MotionCard) myTable.getOnTableCard();
            if (card.getType() == 3) {
                for (int i = 0; i < 2; i++)
                    getPenalty(player);
                System.out.println(player.getName() +" get two penalty");
            }

            // if it is motion card change the player who starts the game
            if (card.getNext() == -1) {
                direction *= -1;
                // add size to avoid negative numbers
                nowTurn = (players.size() + nowTurn + direction) % players.size();
            } else
                // add size to avoid negative numbers
                nowTurn = (players.size() + nowTurn + direction ) % players.size();
        }
    }

    /**
     * this method get penalty to the next player of who play draw card
     * @param player player who play draw
     */
    public void getDrawPenalty (Player player){

        // if it is an specific type of motion card get 2 penalty
        if (myTable.getOnTableCard() instanceof MotionCard) {
            MotionCard card = (MotionCard) myTable.getOnTableCard();
            if (card.getType() == 3) {
                int index = (players.indexOf(player) + direction + players.size()) % players.size();
                for (int i = 0; i < 2; i++)
                    getPenalty(players.get(index));
                System.out.println(players.get(index).getName() + " get two penalty");
            }

        // if it is an specific type of black card get 4 penalty
        } else if (myTable.getOnTableCard() instanceof BlackCard) {
            BlackCard card = (BlackCard) myTable.getOnTableCard();
            if (card.getType() == 2) {
                int index = (players.indexOf(player) + direction + players.size()) % players.size();
                for (int i = 0; i < 4; i++)
                    getPenalty(players.get(index));
                System.out.println(players.get(index).getName() + " get four penalty");
            }
        }
    }
}