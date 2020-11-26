package three;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopNPrinter<T extends Comparable<T>> {

    protected List<T> getMostPopularObjects(List<T> objects, int N) {
        List<T> allObjects = new ArrayList<T>(objects);
        Collections.sort(allObjects);
        return objects.subList(0, N);
    }

    // prints the top N most popular beers in a nice way
    public void prettyPrintMostPopularObjects(List<T> objects, int N, String OBJECT) {
        List<T> topObjects = getMostPopularObjects(objects, N);
        System.out.println("====TOP " + OBJECT + "====");
        int i = 1;
        for (Comparable o : topObjects)
            System.out.println(i++ + ") " + o);
    }





}
