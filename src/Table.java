/**
 *
 * @author Mahdi Hejrati 9723100
 * @since 2020.04.15
 */

import java.util.ArrayList;
import java.util.Random;

public class Table {

    private ArrayList<Card> cardList;
    private Card lastCard;
    int nowTurn;

    public Table (){
        cardList = new ArrayList<>();

        makeCards();
        //random between 9 and 108
        Random rnd = new Random();
        int index = rnd.nextInt(100) + 9;
        lastCard = cardList.get(index);
        cardList.remove(index);
    }

    public void setFirstTurn(int number) {
        Random rnd = new Random();
        nowTurn = rnd.nextInt(number) + 1;
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    public Card getLastCard() {
        return lastCard;
    }

    public int getNowTurn() {
        return nowTurn;
    }

    public void makeCards (){
        for (int i = 1 ; i <= 4; i++)
            for (int j = 1 ; j <= 2; j++)
                cardList.add(new BlackCard(j, i));

        for (int i = 1 ; i <= 4; i++)
            for (int j = 1 ; j <= 3; j++)
                for (int k = 1 ; k <= 2; k++)
                    cardList.add(new MotionCard(i, j, k));

        for (int i = 1 ; i <= 4; i++)
            cardList.add(new NumericCard(i, 0, 1));

        for (int i = 1 ; i <= 4; i++)
            for (int j = 1 ; j <= 9; j++)
                for (int k = 1 ; k <= 2; k++)
                cardList.add(new NumericCard(i, j, k));
    }

    public void print (){
        int i = 1;
        for (Card card : cardList){
            System.out.print(i);
            System.out.println(card);
            i++;
        }
    }
}
