/**
 *
 * @author Mahdi Hejrati 9723100
 * @since 2020.04.15
 */

public class BlackCard extends Card {

//    private int colorNum;
    // type 1 wild, type2 wild draw
    private int type;
    private int series;

    public BlackCard(int type, int series) {
        super();
        setColorNum(0);
//        colorNum = 0;
        this.type = type;
        this.series = series;
    }

}