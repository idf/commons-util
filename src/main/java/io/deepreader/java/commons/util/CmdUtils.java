package io.deepreader.java.commons.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * User: Danyang
 * Date: 1/15/2015
 * Time: 22:00
 */
public class CmdUtils {
    public static void exec(String cmd) throws IOException {
        System.out.println("Executing: ");
        System.out.println(cmd);
        Process process = Runtime.getRuntime().exec(cmd);

        BufferedReader stdout = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
        BufferedReader stderr = new BufferedReader(
                new InputStreamReader(process.getErrorStream()));

        String output;
        StringBuilder sb_output = new StringBuilder("Cmd output:\n");
        for(String line; (line = stdout.readLine())!=null;)
            sb_output.append(line).append("\n");

        output = sb_output.toString();
        System.out.println(output);

        int exitStatus = 0;
        try {
            exitStatus = process.waitFor();
        } catch (InterruptedException e) {
            System.err.println(Displayer.display(e));
        }
        System.out.println("Cmd exit status: " + exitStatus);
        if (exitStatus != 0) {
            StringBuilder sb = new StringBuilder();
            for(String line; (line = stderr.readLine())!= null;)
                sb.append(line).append("\n");

            System.err.println("Cmd exited with error status.  "+exitStatus +
                    " stderr:\n" + sb.toString());
            stdout.close();
            stderr.close();
            throw new Error("Clustering failed");
        }
        stdout.close();
        stderr.close();
    }
}
