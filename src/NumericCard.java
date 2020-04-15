/**
 *
 * @author Mahdi Hejrati 9723100
 * @since 2020.04.15
 */

public class NumericCard extends Card{

    // 1 red, 2 green, 3 blue, 4 yellow
   // private int colorNum;
    // number between 0 to 9
    private int number;
    private int series;

    public NumericCard(int colorNum, int number, int series) {
        super();
        setColorNum(colorNum);
//        this.colorNum = colorNum;
        this.number = number;
        this.series = series;
    }

}
