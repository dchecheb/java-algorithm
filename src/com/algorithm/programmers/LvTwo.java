package com.algorithm.programmers;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;
import java.util.Map;

public class LvTwo {

    @Test
    public void testLvTwo07() {
        Assert.assertEquals(16384, lvTwo07("FRANCE", "french"));
        Assert.assertEquals(43690, lvTwo07("aa1+aa2", "AAAA12"));
        Assert.assertEquals(65536, lvTwo07("E=M*C^2", "e=m*c^2"));
        Assert.assertEquals(65536, lvTwo07("handshake", "shake hands"));
    }

    /**
     * 뉴스 클러스터링
     * https://programmers.co.kr/learn/courses/30/lessons/17677
     */
    public int lvTwo07(String str1, String str2) {
        // 소문자 통일
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        List<String> str1Words = getWords(str1);
        List<String> str2Words = getWords(str2);
        List<String> inter = new ArrayList<>();
        List<String> union = new ArrayList<>();

        // 겹치면 교+합에 추가, 겹치지 않으면 합에만 추가
        for (String word1 : str1Words) {
            if (str2Words.remove(word1)) inter.add(word1);
            union.add(word1);
        }
        for (String word2 : str2Words) union.add(word2);

        if (union.size() == 0) return 65536;
        return (int) Math.floor((float)inter.size() / (float)union.size() * 65536);
    }

    /**
     * lvTwo07
     */
    public List<String> getWords(String s) {
        List<String> result = new ArrayList<>();
        for (int i=0; i<s.length()-1; i++) {
            if (!s.substring(i, i+2).matches("[a-z]+")) continue;
            result.add(s.substring(i, i+2));
        }
        return result;
    }

    @Test
    public void testLvTwo06() {
        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        int[] answer = {1,0,1,1,1};
        Assert.assertArrayEquals(answer, lvTwo06(places));
    }

    /**
     * 거리두기 확인하기
     * https://programmers.co.kr/learn/courses/30/lessons/81302?language=java
     */
    public int[] lvTwo06(String[][] places) {
        int[] answer = {1,1,1,1,1};

        for (int i=0; i<5; i++) {
            String[] p = places[i];

            // 대기실 1개 탐색
            for (int x=0; x<5; x++)
                for (int y=0; y<5; y++)
                    if (p[x].charAt(y) == 'P')
                        if (!isKeepDist(p, x, y)) answer[i] = 0;
        }
        return answer;
    }

    /**
     * lvTwo06
     */
    public boolean isKeepDist(String[] p, int x, int y) {
        for (int i=-2; i<=2; i++) {
            for (int j=-2; j<=2; j++) {
                int dist = i*i + j*j;
                if (dist > 4) continue; // 맨허튼 거리 2 이하만 확인

                int nx = x+i;
                int ny = y+j;
                if (nx<0 || nx>=5 || ny<0 || ny>=5) continue;

                if (p[nx].charAt(ny) == 'P')
                    if (dist == 1) return false;    // P간 거리가 1일 땐 거리두기 fail
                    else if (dist == 2) {           // 대각선 관계
                        if (p[nx].charAt(y) != 'X' || p[x].charAt(ny) != 'X') return false;
                    } else if (dist == 4) {         // 직선 관계
                        if (p[x + i/2].charAt(y + j/2) != 'X') return false;
                    }
            }
        }
        return true;
    }

    /**
     * 단체사진 찍기
     * https://programmers.co.kr/learn/courses/30/lessons/1835
     */
    public int lvTwo05(int n, String[] data) {
        int answer = 40320;
        char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        List<char[]> perms = new ArrayList<>();
        boolean[] visited = new boolean[8];
        permutation(friends, perms, new char[8], visited, 0);

        for (char[] line : perms) {
            for (String d : data) {
                if (!isPossible(line, d)) {
                    answer--;
                    break;
                }
            }
        }
        return answer;
    }

    /**
     * lvTwo05
     */
    public boolean isPossible(char[] line, String d) {
        int dist = Math.abs(indexOf(line, d.charAt(0)) - indexOf(line, d.charAt(2)))-1;
        if (d.charAt(3) == '=') {
            if (dist != Character.getNumericValue(d.charAt(4))) {
                return false;
            }
        } else if (d.charAt(3) == '>') {
            if (dist <= Character.getNumericValue(d.charAt(4))) {
                return false;
            }
        } else {
            if (dist >= Character.getNumericValue(d.charAt(4))) {
                return false;
            }
        }
        return true;
    }

    /**
     * lvTwo05
     */
    public int indexOf(char[] line, char c) {
        for (int i = 0; i < line.length; i++) {
            if (line[i] == c) return i;
        }
        return -1;
    }

    /**
     * lvTwo05
     */
    public void permutation(char[] friends, List<char[]> perms, char[] s, boolean[] visited, int depth) {
        if (depth == 8) {
            perms.add(s.clone());
            return;
        }
        for (int i=0; i<8; i++) {
            if (visited[i] == true) {
                continue;
            }
            visited[i] = true;
            s[depth] = friends[i];
            permutation(friends, perms, s, visited,  depth+1);
            visited[i] = false;
        }
    }

    @Test
    public void testLvTwo05() {
        String[] data = {"N~F=0", "R~T>2"};
        Assert.assertEquals(3648, lvTwo05(2, data));
    }

    @Test
    public void testLvTwo05IsPossible() {
        char[] line = {'a', 'b', 'c', 'd'};
        Assert.assertTrue(isPossible(line, "a~b=0"));
        Assert.assertTrue(isPossible(line, "a~d<4"));
        Assert.assertTrue(isPossible(line, "a~d>1"));
        Assert.assertFalse(isPossible(line, "a~b>1"));
    }

    /**
     * 오픈채팅방
     * https://programmers.co.kr/learn/courses/30/lessons/42888?language=java
     */
    public String[] lvTwo04(String[] record) {
        List<String> answer = new ArrayList<>();
        List<String[]> logs = new ArrayList<>();
        HashMap<String, String> userName = new HashMap<>();
        for (String action : record) {
            String actionType = action.split(" ")[0];
            String uid = action.split(" ")[1];
            switch (actionType) {
                case "Enter":
                    logs.add(new String[]{uid, "님이 들어왔습니다."});
                    userName.put(uid, action.split(" ")[2]);
                    break;
                case "Leave":
                    logs.add(new String[]{uid, "님이 나갔습니다."});
                    break;
                case "Change":
                    userName.put(uid, action.split(" ")[2]);
                    break;
            }
        }

        for (int i=0; i<logs.size(); i++) {
            answer.add(userName.get(logs.get(i)[0]) + logs.get(i)[1]);
        }
        return answer.toArray(new String[0]);
    }

    @Test
    public void testLvTwo04() {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        String[] result = {"Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다."};
        Assert.assertArrayEquals(result, lvTwo04(record));
    }

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

        int[] data = {3,1,2};
//        List<Integer> dataList = Array.asList(data);

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
