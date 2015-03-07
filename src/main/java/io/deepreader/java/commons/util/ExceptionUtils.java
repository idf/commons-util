package io.deepreader.java.commons.util;

/**
 * User: Danyang
 * Date: 3/7/2015
 * Time: 13:30
 */
public class ExceptionUtils {
    public static void stifleCompileTime(Exception e) {
        throw new RuntimeException(e);
    }
}
