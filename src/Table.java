/**
 * this class hold information of card during the game .
 *
 * @author Mahdi Hejrati 9723100
 * @since 2020.04.15
 */

import java.util.ArrayList;
import java.util.Random;

public class Table {

    private ArrayList<Card> cardList;   // list of card on the repository
    private Card onTableCard;   // card on the table in fact the last card played
    private int onTableColor;   // color of on table card
    private boolean firstFlag;   // this flag show us the starts of the game

    /**
     * constructor for table
     */
    public Table (){

        firstFlag = true;
        cardList = new ArrayList<>();

        // create cards
        makeCards();

        //choose card that will be on the table at the beginning of the game between numeric and motion card
        Random rnd = new Random();
        // random between 9 and 108
        int index = rnd.nextInt(100) + 9;
        onTableCard = cardList.get(index);
        onTableColor = onTableCard.getColorNum();
        // remove it from repository
        cardList.remove(index);
    }

    /**
     * getter for card list
     * @return list of card in repository
     */
    public ArrayList<Card> getCardList() {
        return cardList;
    }

    /**
     * getter for on table card
     * @return the last card played
     */
    public Card getOnTableCard() {
        return onTableCard;
    }

    /**
     * setter for on table card
     * @param onTableCard the last card played
     */
    public void setOnTableCard(Card onTableCard) {
        this.onTableCard = onTableCard;
    }

    /**
     * getter for on table color
     * @return color of on table card
     */
    public int getOnTableColor() {
        return onTableColor;
    }

    /**
     * setter for on table color
     * @param onTableColor color of on table card
     */
    public void setOnTableColor(int onTableColor) {
        this.onTableColor = onTableColor;
    }

    /**
     * setter for first flag
     * @param firstFlag first flag
     */
    public void setFirstFlag(boolean firstFlag) {
        this.firstFlag = firstFlag;
    }

    /**
     * getter for first flag
     * @return first flag
     */
    public boolean getFirstFlag() {
        return firstFlag;
    }

    /**
     * add cards to repository list of card
     * @param card card to add
     */
    public void addCardToList(Card card){
        cardList.add(card);
    }

    /**
     * create cards at the beginning of the game
     */
    public void makeCards (){
        // 8 black card in 2 different type
        for (int i = 1 ; i <= 4; i++)
            for (int j = 1 ; j <= 2; j++)
                cardList.add(new BlackCard(j, i));

        // 24 motion card in 3 different type
        for (int i = 1 ; i <= 4; i++)
            for (int j = 1 ; j <= 3; j++)
                for (int k = 1 ; k <= 2; k++)
                    cardList.add(new MotionCard(i, j, k));

        // 4 numeric card with 0 number in 4 color
        for (int i = 1 ; i <= 4; i++)
            cardList.add(new NumericCard(i, 0, 1));

        // 72 numeric card in 4 color with number of 1 to 9
        for (int i = 1 ; i <= 4; i++)
            for (int j = 1 ; j <= 9; j++)
                for (int k = 1 ; k <= 2; k++)
                cardList.add(new NumericCard(i, j, k));
    }
}