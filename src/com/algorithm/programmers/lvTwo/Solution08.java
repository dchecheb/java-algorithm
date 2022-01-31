package com.algorithm.programmers.lvTwo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution08 {

    /**
     * 빛의 경로 사이
     * https://programmers.co.kr/learn/courses/30/lessons/86052
     */
    public int[] solution(String[] grid) {
        List<Integer> answer = new ArrayList<>();
        int maxX = grid.length;
        int maxY = grid[0].length();
        boolean[][][] visited = new boolean[maxX][maxY][4];

        for (int x=0; x<maxX; x++) {
            for (int y=0; y<maxY; y++) {
                for (int d=0; d<4; d++) {
                    if (!visited[x][y][d]) {
                        answer.add(getCost(grid, visited, x, y, d, maxX, maxY));
                    }
                }
            }
        }
        Collections.sort(answer);

        return answer;
    }

    public int getCost(String[] grid, boolean[][][] visited, int x, int y, int d, int maxX, int maxY) {
        int[] dx = {-1,1,0,0}; // 상 하 좌 우
        int[] dy = {0,0,-1,1}; // 상 하 좌 우
        int cost = 0;

        while (!visited[x][y][d]) {
            cost++;
            visited[x][y][d] = true;
            if (grid[x].charAt(y) == 'L') {
                d = (d + 2) % 4;    // 상 -> 좌, 하 -> 우
            } else if (grid[x].charAt(y) == 'R') {
                if (d == 0) d = 3;  // 상 -> 우
                if (d == 1) d = 2;  // 하 -> 좌
                if (d == 2) d = 1;
                if (d == 3) d = 0;
            }
            x = (x + dx[d]) % maxX;
            y = (y + dy[d]) % maxY;
        }

        return cost;
    }
}
