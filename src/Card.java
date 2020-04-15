/**
 *
 * @author Mahdi Hejrati 9723100
 * @since 2020.04.15
 */

public abstract class Card {
     private int next;
     private int penalty;
     private int colorNum;

     public Card(){
         this.next = +1;
         this.penalty = 0;
     }

    public int getColorNum() {
        return colorNum;
    }

    protected void setColorNum(int colorNum) {
        this.colorNum = colorNum;
    }
}
