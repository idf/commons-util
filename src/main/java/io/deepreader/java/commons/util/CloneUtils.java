package io.deepreader.java.commons.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 17/07/15.
 */
public class CloneUtils {
    public static <T> List<T> cloneList(List<T> l) {
        return new ArrayList<>(l);
    }

    /**
     * subList returns a view on an existing list. It's not an ArrayList. This method creates a copy of it by using
     * constructor
     * @return
     */
    public static <T> List<T> cloneSublist(List<T> l, int s, int e) {
        return new ArrayList<>(l.subList(s, e));
    }
}
