package com.algorithm.programmers;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class LvTwo {

    /**
     * 문자열 압축
     * https://programmers.co.kr/learn/courses/30/lessons/60057?language=java
     */
    static int lvTwo3(String s) {
        int answer = s.length();

        for (int i=1; i<=s.length()/2; i++) {
            int eqCnt = 0;
            int minLen = 0;
            for (int j=i; j<=s.length()-i; j+=i) {
                String src = s.substring(j-i, j);
                String dst = s.substring(j, j+i);
                if (s.substring(j-i, j).equals(s.substring(j, j+i))) {
                    eqCnt++;
                } else if (j==0) {
                    minLen = s.length();
                    break;
                } else {
                    minLen += eqCnt == 0 ? i : 1+i;
                    eqCnt = 0;
                }
                if (j+2*i > s.length()) {
                    minLen += eqCnt == 0 ? s.length() - j : 1+i;
                }
            }
            answer = Math.min(answer, minLen);
        }

        return answer;
    }

    /**
     * lvTwo03 Answer
     */
    public int lvTwo03Answer(String s) {
        int min = s.length();

        for (int i = 1; i <= s.length() / 2; i++) {
            int compLeng = compression(s, i).length();
            min = Math.min(min, compLeng);
        }

        return min;
    }

    /**
     * lvTwo03 Answer
     */
    private String compression(String str, int i) {

        int count = 1;
        String compression = "";
        String pattern = "";

        for (int j = 0; j <= str.length() + i; j += i) {

            String nowStr;

            // 전 문자열과 비교할 현재 문자열
            if (j >= str.length()) { // 현재 문자열이 없을 때
                nowStr = "";
            } else if (str.length() < j + i) { // 마지막 현재 문자열일 때
                nowStr = str.substring(j);
            } else {
                nowStr = str.substring(j, j + i); // 그 외
            }

            // 1. 전 문자열이랑 똑같은지 비교한다. (맨 처음이면 비교 X)
            if (j != 0) {
                if (nowStr.equals(pattern)) { // 똑같으면
                    count++;
                } else if (count >= 2) { // 다르고 count가 2회 이상이면 압축 가능
                    compression += count + pattern;
                    count = 1;
                } else { // 압축 불가능하면 그냥 그대로 문자열 이어붙이기
                    compression += pattern;
                }
            }
            // 2. i 길이만큼 문자열을 자른다.
            pattern = nowStr;
        }

        return compression;
    }

    @Test
    public void testLvTwo03() {
        Assert.assertEquals(7, lvTwo3("aabbaccc"));
        Assert.assertEquals(9, lvTwo3("ababcdcdababcdcd"));
        Assert.assertEquals(8, lvTwo3("abcabcdede"));
        Assert.assertEquals(14, lvTwo3("abcabcabcabcdededededede"));
        Assert.assertEquals(17, lvTwo3("xababcdcdababcdcd"));
        Assert.assertEquals(7, lvTwo03Answer("aabbaccc"));
        Assert.assertEquals(9, lvTwo03Answer("ababcdcdababcdcd"));
        Assert.assertEquals(8, lvTwo03Answer("abcabcdede"));
        Assert.assertEquals(14, lvTwo03Answer("abcabcabcabcdededededede"));
        Assert.assertEquals(17, lvTwo03Answer("xababcdcdababcdcd"));
        Assert.assertEquals(9, lvTwo03Answer("abcabdede"));
    }


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

}
