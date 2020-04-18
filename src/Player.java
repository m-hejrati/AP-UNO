/**
 * this class hold information of player.
 *
 * @author Mahdi Hejrati 9723100
 * @since 2020.04.15
 */

import java.util.ArrayList;
import java.util.Random;

public class Player {
    String name;
    int number;   //each player has a unique name to being diagnosed easier
    ArrayList<Card> cardList;   // list of his card
    Card lastCard;   // last card he put on table

    /**
     * constructor for player
     * @param number code or number of player
     * @param name name of  player
     */
    public Player(int number, String name) {
        this.number = number;
        this.name = name;
        cardList = new ArrayList<>();
    }

    /**
     * this method give seven card to each player at the beginning of the game randomly.
     * @param myTable get card from table
     */
    public void setFirstCards (Table myTable){
        for (int i = 0; i < 7 ; i++){
            // this function is used to make a random number between 0 to size of all current cards on table
            Random rnd = new Random();
            int index = rnd.nextInt(myTable.getCardList().size());
            // add card to player
            cardList.add(myTable.getCardList().get(index));
            // remove card from table
            myTable.getCardList().remove(index);
        }
    }

    /**
     * getter for cards
     * @return cards of player
     */
    public ArrayList<Card> getCardList() {
        return cardList;
    }

    /**
     * getter for number
     * @return code of player
     */
    public int getNumber() {
        return number;
    }

    /**
     * getter for name
     * @return name of player
     */
    public String getName() {
        return name;
    }

    /**
     * getter for last card.
     * @return last card that player put on table
     */
    public Card getLastCard() {
        return lastCard;
    }

    /**
     * setter for last card
     * @param lastCard last card that player put on table
     */
    public void setLastCard(Card lastCard) {
        this.lastCard = lastCard;
    }
}
