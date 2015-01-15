package io.deepreader.java.commons.util;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User: Danyang
 * Date: 1/15/2015
 * Time: 11:48
 */
public class StringUtils {
    public static String join(List<String> lst, String delimiter) {
        return lst.stream().collect(Collectors.joining(delimiter));
    }
}
