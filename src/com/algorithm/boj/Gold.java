package com.algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Gold {

    /**
     * LCS
     * https://www.acmicpc.net/problem/9251
     */
    public void gold01() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();
        int lenA = a.length();
        int lenB = b.length();
        int[][] lcs = new int[lenB+1][lenA+1];

        for (int i=1; i<lenB+1; i++) {
            for (int j=1; j<lenA+1; j++) {
                if (b.charAt(i-1) == a.charAt(j-1)) {
                    lcs[i][j] = lcs[i-1][j-1] + 1;
                } else {
                    lcs[i][j] = Math.max(lcs[i][j-1], lcs[i-1][j]);
                }
            }
        }

        System.out.println(lcs[lenB][lenA]);
    }
}
