package util;

import java.util.List;

public class Util {
    public static void printList(List<?> list){
        for(Object o : list) {
            System.out.println(o.toString());
        }
    }
}
