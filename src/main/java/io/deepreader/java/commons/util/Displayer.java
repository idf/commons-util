package io.deepreader.java.commons.util;

import java.lang.reflect.Field;

/**
 * User: Danyang
 * Date: 11/16/14
 * Time: 3:39 PM
 */
public class Displayer {
    public static <E> String display(Object obj, E attribute) {
        for(Field field : obj.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if(field.get(obj).equals(attribute)) {
                    return obj.getClass().getName()+"'s "+field.getName()+": "+attribute.toString();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return "Error accessing attribute";
    }
}
