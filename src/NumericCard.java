/**
 * this class hold information of numeric black card.
 *
 * @author Mahdi Hejrati 9723100
 * @since 2020.04.15
 */

public class NumericCard extends Card{

    private int number;   // each card have a number between 0 to 9
    private int series;   // we have two series of each type of black card

    public NumericCard(int colorNum, int number, int series) {
        super();
        // set its color, it can be red, green, blue or yellow
        setColorNum(colorNum);

        this.number = number;
        this.series = series;

        setNext(1);
        // numeric card has point equal to their number.
        setPoint(number);
    }

    /**
     * getter for number
     * @return number written on card
     */
    public int getNumber() {
        return number;
    }
}
