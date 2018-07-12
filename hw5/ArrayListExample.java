package arraylistexample2;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListExample2 {

    private ArrayList<Integer> list = new ArrayList<Integer>();

    public static void main(String[] args) {
        new ArrayListExample2();
    }

    public ArrayListExample2() {
        for (int i = 0; i < 10; i++) {
            list.add((int) (Math.random() * 100));
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();

        list.add(3, 999);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
        
        list.set(4, 1234);
        list.remove(10);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();        
        
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
        
        Collections.shuffle(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }

}
















