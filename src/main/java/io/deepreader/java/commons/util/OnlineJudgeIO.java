package io.deepreader.java.commons.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by Daniel on 26/10/15.
 */
public class OnlineJudgeIO {
    static class Solution {
        int solve(List<Integer> A) {
            return A.get(0);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Solution solution = new Solution();

        Scanner in = new Scanner(new File("path/to/0.in"));
        // Scanner in = new Scanner(System.in);  // redirect
        PrintWriter out = new PrintWriter(System.out);

        int tests = Integer.valueOf(in.nextLine());
        for (int i = 0; i < tests; i++) {
            List<Integer> A = Arrays.stream(in.nextLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            // int[] A = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
            // long[] A = Arrays.stream(in.nextLine().split(" ")).mapToLong(Long::valueOf).toArray();
            out.println(solution.solve(A));
        }
        out.flush();
    }
}
