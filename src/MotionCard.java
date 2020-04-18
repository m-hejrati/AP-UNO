/**
 * this class hold information of motion black card.
 *
 * @author Mahdi Hejrati 9723100
 * @since 2020.04.15
 */

public class MotionCard extends Card{

    private int type;   // type of motion card, 1: skip ,2: reverse, 3: draw +2
    private int series;   // we have two series of each type of black card

    public MotionCard(int colorNum, int type, int series) {
        super();
        // set its color, it can be red, green, blue or yellow
        setColorNum(colorNum);

        this.type = type;
        this.series = series;
        // type 2: reverse direction of game
        if (type == 2)
            setNext(-1);
        // and type1 and type3 skip one player
        else
            setNext(2);
        // motion card has 20 points
        setPoint(20);
    }

    /**
     * getter for type
     * @return type of motion card
     */
    public int getType() {
        return type;
    }
}