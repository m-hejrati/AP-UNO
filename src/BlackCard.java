/**
 * this class hold information of each black card.
 *
 * @author Mahdi Hejrati 9723100
 * @since 2020.04.15
 */

public class BlackCard extends Card {

    private int type;   // type of black card, 1: wild, 2: wild draw +4
    private int series;   // we have for series of each type of black card

    /**
     * constructor for card
     * in normal case next set to 1
     */
    public BlackCard(int type, int series) {
        super();
        // their color are black
        setColorNum(0);

        this.type = type;
        this.series = series;

        if (type == 1)
            setNext(1);
        else
            setNext(2);
        // black card has 50 points
        setPoint(50);
    }

    /**
     * getter for type
     * @return type of black card
     */
    public int getType() {
        return type;
    }
}