package io.deepreader.java.commons.util;

import java.lang.reflect.Field;
import java.util.Map;

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
    
    public static <K, V> String display(Map<K, V> map) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry entry: map.entrySet()) {
            sb.append(entry.getKey().toString()+": "+entry.getValue().toString()+"\n");
        }
        return sb.toString();
    }

    public static String toString(Object aObject) {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append( aObject.getClass().getName() );
        result.append( " Object {" );
        result.append(newLine);

        //determine fields declared in aObject class only (no fields of superclass)
        Field[] fields = aObject.getClass().getDeclaredFields();

        //print field names paired with their values
        for ( Field field : fields  ) {
            result.append("  ");
            try {
                field.setAccessible(true);
                result.append( field.getName() );
                result.append(": ");
                //requires access to private field:
                result.append( field.get(aObject) );
            } catch ( IllegalAccessException ex ) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }
}
