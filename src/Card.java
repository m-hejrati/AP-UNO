/**
 * abstract class of card that holds common attributes of cards.
 *
 * @author Mahdi Hejrati 9723100
 * @since 2020.04.15
 */

public abstract class Card {

     private int next;   // next player in the game
     private int colorNum;   // color number, 0: black, 1: red, 2: green, 3: blue, 4: yellow
     private int point;   // point of card, motion card: 20, black card: 50, numeric: equal to its number.

    /**
     * constructor for card
     * in normal case next set to 1
     */
    public Card(){
         this.next = +1;
     }

    /**
     * getter for color number
     * @return color number of card
     */
    public int getColorNum() {
        return colorNum;
    }

    /**
     * setter for color number
     * @param colorNum color number of card
     */
    protected void setColorNum(int colorNum) {
        this.colorNum = colorNum;
    }

    /**
     * getter for next
     * @return next player in game
     */
    public int getNext() {
        return next;
    }

    /**
     * setter for next
     * @param next next player in game
     */
    protected void setNext(int next) {
        this.next = next;
    }

    /**
     * getter for point
     * @return point of card
     */
    public int getPoint() {
        return point;
    }

    /**
     * setter for point
     * @param point point of card
     */
    protected void setPoint(int point) {
        this.point = point;
    }
}
