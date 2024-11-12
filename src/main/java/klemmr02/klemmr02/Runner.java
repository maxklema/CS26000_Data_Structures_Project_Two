package klemmr02.klemmr02;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Runner {

    public static void main(String[] args) {

        MemoLinkedList myLinkedList = new MemoLinkedList();

        Memo one = new Memo(1,10,10,"Test","Test","Test","Black","White");
        Memo two = new Memo(2,20,20,"Test","Test","Test","Black","White");
        Memo three = new Memo(3,30,30,"Test","Test","Test","Black","White");
        Memo four = new Memo(4,40,40,"Test","Test","Test","Black","White");
        Memo five = new Memo(5,50,50,"Test","Test","Test","Black","White");
        Memo six = new Memo(6,60,60,"Test","Test","Test","Black","White");
        Memo seven = new Memo(7,70,70,"Test","Test","Test","Black","White");
        Memo eight = new Memo(8,80,80,"Test","Test","Test","Black","White");
        Memo nine = new Memo(9,90,90,"Test","Test","Test","Black","White");
        Memo ten = new Memo(10,10,10,"Test","Test","Test","Black","White");
        Memo eleven = new Memo(11,11,11,"Test","Test","Test","Black","White");

        myLinkedList.insertAfter(one);
        myLinkedList.insertAfter(two);
        myLinkedList.insertAfter(three);
        myLinkedList.insertAfter(four);
        myLinkedList.insertAfter(five);
        myLinkedList.insertAfter(six);
        myLinkedList.insertAfter(seven);
        myLinkedList.insertAfter(eight);
        myLinkedList.insertAfter(nine);
        myLinkedList.insertAfter(ten);
        myLinkedList.insertAfter(eleven);

//        myLinkedList.deleteOddNodes();



        myLinkedList.printReverse();

//        System.out.println("MANY ITEMS: " + myLinkedList.getManyItems());

//        MemoNode memoNode = myLinkedList.getHead();
//        int manyItems = myLinkedList.getManyItems();
//        for (int i = 0; i < manyItems; i++) {
//
//            //create vbox to organize memo information
//            System.out.println(memoNode.getMemo().getMemoID());
//            memoNode = memoNode.getLink();
//        }



    }
}
