package io.deepreader.java.commons.util;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * User: Danyang
 * Date: 1/7/2015
 * Time: 16:20
 */
public class IOHandler {
    /**
     * Read all from files
     * @param path path to file
     * @return content of the file
     */
    public static String read(String path) {
        String returnValue = "";
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(path);
            BufferedReader buffReader = new BufferedReader(fileReader);
            String line = "";
            while((line = buffReader.readLine())!=null){
                returnValue += line+"\n";
            }
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
        finally {
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

    /**
     * read a line from InputStream
     * @param in input stream
     * @return a line from the stream
     */
    public static String readLine(InputStream in) {
        String returnValue = "";
        InputStreamReader inputStremReader = null;
        try {
            inputStremReader = new InputStreamReader(in);
            BufferedReader buffReader = new BufferedReader(inputStremReader);
            String line = buffReader.readLine();
            returnValue = line;
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
        return returnValue;

    }

    /**
     * Write the content to a file
     * @param path path to a file
     * @param content content to write
     * @return whether success or not
     */
    public static boolean write(String path, String content) {
        FileWriter fileWriter = null;
        boolean flag = false;
        try {
            fileWriter = new FileWriter(path);
            BufferedWriter buffWriter = new BufferedWriter(fileWriter);
            buffWriter.write(content);
            buffWriter.close();
            flag=true;
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if(fileWriter!=null) {
                try {

                    fileWriter.close();
                }
                catch(IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return flag;
    }

    /**
     * serialization
     * @param path path to file
     * @param obj obj to be serialized
     * @throws IOException
     */
    public static void serialize(String path, Serializable obj) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(path);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(obj);
        out.close();
        fileOut.close();
    }

    /**
     * de-serialization
     * @param path path to file
     * @return obj de-serialized
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object deserialize(String path) throws IOException, ClassNotFoundException {
        Object o;
        FileInputStream fileIn = new FileInputStream(path);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        o = in.readObject();
        in.close();
        fileIn.close();
        return o;
    }

    /**
     * get lines stream
     * @param path path to a file
     * @return stream of lines
     * @throws IOException
     */
    public static Stream<String> getLines(String path) throws IOException {
        return Files.lines(Paths.get(path), Charset.defaultCharset());
    }

    /**
     * Count number of words, separated by " "
     * @param path path to file
     * @return word count
     * @throws IOException
     */
    public static long wordCnt(String path) throws IOException {
        Stream<String> lines = getLines(path);
        return lines.flatMap(line -> Arrays.stream(line.split(" ")))
                .distinct()
                .count();
    }


    public static void safeClose(Closeable c) {
        if (c!=null) {
            try {
                c.close();
            } catch (IOException e) {
                // Silent
            }
        }
    }

    /**
     * Read file from resources folder. Use URI instead of string path to avoid special character issue
     * @param classLoader
     * @param path
     * @return
     * @throws URISyntaxException
     */
    public static File getResourcesFile(ClassLoader classLoader, String path) throws URISyntaxException {
        return new File(classLoader.getResource(path).toURI());
    }

    public static BufferedReader toBufferedReader(FileReader reader) {
        return new BufferedReader(reader);
    }

    public static BufferedReader toBufferedReader(File f) throws FileNotFoundException {
        return toBufferedReader(new FileReader(f));
    }

    public static BufferedReader toBufferedReader(String path) throws FileNotFoundException {
        return toBufferedReader(new File(path));
    }

    public static BufferedReader toBufferedReader(InputStreamReader reader) {
        return new BufferedReader(reader);
    }

    public static BufferedReader toBufferedReader(InputStream in) {
        return toBufferedReader(new InputStreamReader(in));
    }

    public static BufferedWriter toBufferedWriter(FileWriter writer) {
        return new BufferedWriter(writer);
    }

    public static BufferedWriter toBufferedWriter(String path) throws IOException {
        return toBufferedWriter(new FileWriter(path));
    }

    /**
     * Template
     */
    // Template for catch error in lambda
    private Function<BufferedReader, String> f =
        (BufferedReader b) -> {
            try {
                return b.readLine();
            }
            catch(IOException e) {
                throw new RuntimeException(e);
            }
        };
}
