/**
 *
 * @author Mahdi Hejrati 9723100
 * @since 2020.04.15
 */

import java.util.ArrayList;
import java.util.Random;

public class Player {
    String name;
    int number;
    ArrayList<Card> cardList;

    public Player(int number, String name) {
        this.number = number;
        this.name = name;
        cardList = new ArrayList<>();
    }

    public void setFirstCards (Table myTable){
        for (int i = 0; i < 7 ; i++){
            Random rnd = new Random();
            int index = rnd.nextInt(myTable.getCardList().size());
            cardList.add(myTable.getCardList().get(index));
            myTable.getCardList().remove(index);
        }
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
