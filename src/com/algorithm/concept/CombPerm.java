package com.algorithm.concept;

public class CombPerm {

    public static void main(String[] args) {
        int[] arr = {1,2,3};
        int n = 3;
        int r = 3;
        int[] output = new int[r];
        boolean[] visited = new boolean[n];

        permutation(arr, output, visited, 0, n, r);
    }

    /**
     * 순열 구현
     * cc https://bcp0109.tistory.com/14
     */
    static void permutation(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {
        if (depth == r) {
            for (int i: output) {
                System.out.print(i);
            }
            System.out.println();
            return;
        }
        for (int i=0; i<n; i++) {
            if (visited[i] != true) {
                visited[i] = true;
                output[depth] = arr[i];
                permutation(arr, output, visited, depth+1, n, r);
                visited[i] = false;
            }
        }
    }
}
