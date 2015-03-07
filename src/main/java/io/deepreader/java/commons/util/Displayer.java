package io.deepreader.java.commons.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Collectors;

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
    
    public static String display(Map<?, ?> map) {
        return display(map, ": ", "\n");
    }

    public static  String display(Map<?, ?> map, String between, String delimiter) {
        return map.entrySet().stream()
                .map(e -> e.getKey()+between+e.getValue())
                .collect(Collectors.joining(delimiter));
    }

    public static void outPrintln(Map<?, ?> map) {
        map.entrySet().forEach(e -> System.out.println(e.getKey()+": "+e.getValue()));
    }

    public static String toString(Object aObject) {
        return toString(aObject, System.getProperty("line.separator"));
    }

    public static String toString(Object aObject, String delimiter) {
        StringBuilder result = new StringBuilder();  // faster than StringBuffer
        result.append( aObject.getClass().getName());
        result.append( " Object {" );
        result.append(delimiter);

        //determine fields declared in aObject class only (no fields of superclass)
        Field[] fields = aObject.getClass().getDeclaredFields();

        //print field names paired with their values
        for ( Field field : fields  ) {
            result.append(" ");
            try {
                field.setAccessible(true);
                result.append( field.getName() );
                result.append(": ");
                //requires access to private field:
                result.append( field.get(aObject) );
            } catch (IllegalAccessException e) {
                System.out.println(e);
            }
            result.append(delimiter);
        }
        result.append("}");

        return result.toString();
    }

    public static String display(Exception e){
        StringWriter msg = new StringWriter();
        e.printStackTrace(new PrintWriter(msg));
        return msg.toString();
    }

    public static String display(int[][] mat) {
        return display(mat, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    public static String display(int[][] mat, int row_limit, int col_limit) {
        StringBuilder sb = new StringBuilder();  // faster than StringBuffer
        int m = mat.length;
        if(m==0)
            return null;
        int n = mat[0].length;
        for(int i=0; i<Math.min(m, row_limit); i++) {
            for(int j=0; j<Math.min(n, col_limit); j++) {
                sb.append(mat[i][j]);
                if(j!=Math.min(n, col_limit)-1)
                    sb.append(" ");  // you need join instead
            }
            sb.append("\n");
        }
        return sb.toString().trim();
    }
}
