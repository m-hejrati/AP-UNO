/**
 *
 * @author Mahdi Hejrati 9723100
 * @since 2020.04.15
 */

public class MotionCard extends Card{

    // 1 red, 2 green, 3 blue, 4 yellow
//    private int colorNum;
    // 1 skip,2 reverse, 3 draw
    private int type;
    private int series;

    public MotionCard(int colorNum, int type, int series) {
        super();
        setColorNum(colorNum);
//        this.colorNum = colorNum;
        this.type = type;
        this.series = series;
    }

}