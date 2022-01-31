package com.algorithm.programmers.lvTwo;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution08 {

    @Test
    public void testSolution() {
        Assert.assertArrayEquals(new int[]{16}, solution(new String[]{"SL","LR"}));
        Assert.assertArrayEquals(new int[]{1,1,1,1}, solution(new String[]{"S"}));
        Assert.assertArrayEquals(new int[]{4,4}, solution(new String[]{"R", "R"}));
    }

    /**
     * 빛의 경로 사이
     * https://programmers.co.kr/learn/courses/30/lessons/86052
     */
    public int[] solution(String[] grid) {
        List<Integer> result = new ArrayList<>();
        int maxX = grid.length;
        int maxY = grid[0].length();
        boolean[][][] visited = new boolean[maxX][maxY][4];

        for (int x=0; x<maxX; x++) {
            for (int y=0; y<maxY; y++) {
                for (int d=0; d<4; d++) {
                    if (!visited[x][y][d]) {
                        result.add(getCost(grid, visited, x, y, d, maxX, maxY));
                    }
                }
            }
        }
        Collections.sort(result);
        int[] answer = new int[result.size()];
        for (int i=0; i<result.size(); i++) {
            System.out.println(result.get(i));
            answer[i] = result.get(i);
        }

        return answer;
    }

    public int getCost(String[] grid, boolean[][][] visited, int x, int y, int d, int maxX, int maxY) {
        // 0, 1, 2, 3 각각 상하좌우를 의미.
        int[] dx = {-1,1,0,0}; // 상 하 좌 우 로 이동했을 때 x 좌표 위치
        int[] dy = {0,0,-1,1};
        int[] ld = {2, 3, 1, 0}; // l 노드로 들어왔을 떄 다음 방향 index
        int[] rd = {3, 2, 0, 1};
        int cost = 0;

        while (!visited[x][y][d]) {
            visited[x][y][d] = true;
            if (grid[x].charAt(y) == 'L') {
                d = ld[d];    // 상 -> 좌, 하 -> 우
            } else if (grid[x].charAt(y) == 'R') {
                d = rd[d];
            }
            int nx = x + dx[d];
            int ny = y + dy[d];
            x = nx == -1 ? maxX - 1 : nx % maxX;
            y = ny == -1 ? maxY - 1 : ny % maxY;
            cost++;
        }

        return cost;
    }
}
