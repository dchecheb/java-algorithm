package com.algorithm.programmers;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class LvTwo {

    /**
     * 메뉴 리뉴얼
     * 최장 공통 부분수열 문제(LCS)
     * https://programmers.co.kr/learn/courses/30/lessons/72411
     */
    public String[] lvTwo01(String[] orders, int[] course) {
        List<String> result = new ArrayList<>();
        int lenOrder = orders.length;
        HashMap<String, Integer> orderedCourse= new HashMap<>();

        for (int i=0; i<lenOrder-1; i++) {
            for (int j=i+1; j<lenOrder; j++) {
                String lcsStr = getLcsStr(orders[i], orders[j]);
                if (!contains(course, lcsStr.length()) || lcsStr.length() < 2) {
                    continue;
                }
                if (orderedCourse.containsKey(lcsStr)) {
                    orderedCourse.replace(lcsStr, orderedCourse.get(lcsStr)+1);
                } else {
                    orderedCourse.put(lcsStr, 1);
                }
            }
        }

        return result.toArray(String[]::new);
    }

    public String getLcsStr(String a, String b) {
        int[][] lcsArray = lcsArray(a, b);
        String lcsStr = "";
        int y = a.length();
        int x = b.length();

        while(x > 0 && y > 0) {
            if (lcsArray[x-1][y] == lcsArray[x][y]) {
                x -= 1;
            } else if (lcsArray[x][y-1] != lcsArray[x][y]) {
                y -= 1;
            } else {
                lcsStr += b.charAt(x-1);
                x -= 1;
                y -= 1;
            }
        }

        char[] lcsChar = lcsStr.toCharArray();
        Arrays.sort(lcsChar);

        return new String(lcsChar);
    }

    public int[][] lcsArray(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();
        int[][] dp = new int[lenB+1][lenA+1];

        for (int i=1; i<lenB+1; i++) {
            for (int j=1; j<lenA+1; j++) {
                if (b.charAt(i-1) == a.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp;
    }

    /**
     * lvTwo01
     * 존재하는 course 개수인지 체크
     */
    public boolean contains(int[] src, int dst) {
        for (int i=0; i < src.length; i++) {
            if (src[i] == dst) {
                return true;
            }
        }
        return false;
    }

    /**
     * lvTwo02
     */
    class Node {
        int m;
        int n;
        int x;
        int y;
        int picNum;
        public Node(int m, int n, int x, int y, int picNum) {
            this.m = m;
            this.n = n;
            this.x = x;
            this.y = y;
            this.picNum = picNum;
        }
    }

    static int picCnt;


    /**
     * 카카오프렌즈 컬러링북
     * @param m
     * @param n
     * @param picture
     * @return
     */
    public int[] lvTwo02(int m, int n, int[][] picture) {
        int[] answer = new int[2];
        boolean[][] visited = new boolean[m][n];

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (picture[i][j] > 0 && !visited[i][j]) {
                    picCnt = 0;
                    dfs(new Node(m,n,i,j,picture[i][j]), picture, visited);
                    answer[0]++;
                    answer[1] = Math.max(answer[1], picCnt);
                }
            }
        }

        return answer;
    }

    void dfs(Node node, int[][] picture,  boolean[][] visited) {
        if (visited[node.x][node.y] || picture[node.x][node.y] != node.picNum) return;
        visited[node.x][node.y] = true;
        picCnt++;

        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        for (int i=0; i<4; i++) {
            int nx = node.x + dx[i];
            int ny = node.y + dy[i];
            if (0<=nx && nx < node.m && 0<=ny && ny<node.n && !visited[nx][ny]) {
                dfs(new Node(node.m,node.n,nx,ny,picture[node.x][node.y]), picture, visited);
            }
        }
    }

    public int lvTwo3(int n, String[] data) {
        int answer = 0;


        return answer;
    }

}
