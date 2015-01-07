package io.deepreader.java.commons.util;

import java.io.*;

/**
 * User: Danyang
 * Date: 1/7/2015
 * Time: 16:20
 */
public class IOHandler {
    public static String read(String fileName){
        String returnValue = "";
        FileReader fileReader = null;
        try{
            fileReader = new FileReader(fileName);
            BufferedReader buffReader = new BufferedReader(fileReader);
            String line = "";
            while((line = buffReader.readLine())!=null){
                returnValue += line+"\n";
            }
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
        finally{
            if(fileReader!=null)
                try{
                    fileReader.close();
                }
                catch(IOException e){
                    System.out.println(e.getMessage());
                }

        }
        return returnValue;
    }
    public static String readline(InputStream in){
        String returnValue = "";
        InputStreamReader inputStremReader = null;
        try{
            inputStremReader = new InputStreamReader(in);
            BufferedReader buffReader = new BufferedReader(inputStremReader);
            String line = buffReader.readLine();
            returnValue = line;
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
        /* Do not close the stream
         finally{
            if(inputStremReader!=null)
                try{
                    inputStremReader.close();
                }
                catch(IOException e){
                    System.out.println(e.getMessage());
                }
        }
         */
        return returnValue;

    }

    public static boolean write(String fileName, String content){
        FileWriter fileWriter = null;
        boolean flag = false;
        try{
            fileWriter = new FileWriter(fileName);
            BufferedWriter buffWriter = new BufferedWriter(fileWriter);
            buffWriter.write(content);
            buffWriter.close();
            flag=true;
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
        finally{
            if(fileWriter!=null){
                try{

                    fileWriter.close();
                }
                catch(IOException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        return flag;
    }


    public static void serialize(String path, Serializable obj) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(path);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(obj);
        out.close();
        fileOut.close();
    }

    public static Object deserialize(String path) throws IOException, ClassNotFoundException {
        Object o;
        FileInputStream fileIn = new FileInputStream(path);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        o = in.readObject();
        in.close();
        fileIn.close();
        return o;
    }
}