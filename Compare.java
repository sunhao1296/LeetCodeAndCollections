import java.util.*;

public class Compare {

    Map<Integer,Integer> treemap = new TreeMap<>();
    Map<Integer,Integer> map = new HashMap<>();
    List<Integer> myList = new LinkedList<>();
    public static class subClass implements Comparable<subClass> {
        int index;
        int value;

        @Override
        public int compareTo(subClass s) {
            if (value > s.value)
                return 1;
            if (value < s.value)
                return -1;
            return 0;
        }
    }





}
