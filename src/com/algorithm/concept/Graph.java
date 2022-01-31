package com.algorithm.concept;

import java.util.LinkedList;
import java.util.Queue;

class Graph {
    int[][] graph;
    int[] dx = { 0, -1, 0, 1 };
    int[] dy = { 1, 0, -1, 0 };
    boolean[][] visited;

    /**
     * BFS에서 사용할 Node 클래스
     * 위치, 가중치 정보를 Node 인스턴스에 저장하고 queue 에 담는 방식으로 사용함.
     * https://velog.io/@alstjdwo1601/Java-%EC%BD%94%EB%94%A9%ED%85%8C%EC%8A%A4%ED%8A%B8-%EA%B4%80%EB%A0%A8-%ED%8C%81
     */
    class Node {
        int x;
        int y;
        int depth;

        public Node(int _x, int _y, int _depth) {
            this.x = _x;
            this.y = _y;
            this.depth = _depth;
        }
    }

    /**
     * BFS
     * x, y는 그래프의 크기이자 목적지
     * 0,0 으로부터 x,y까지의 최단 경로 출력
     * depth는 가중
     */
    void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0,0,1));

        while(!q.isEmpty()) {
            Node node = q.poll();
            visited[node.x][node.y] = true;

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (0 <= nx && nx < x && 0 < ny && ny < y) {
                    if (graph[nx][ny] == 1 && !visited[nx][ny]) {
                        q.add(new Node(nx, ny, node.depth + 1));
                    }
                }

            }

            // x, y 에 방문했다면 출력
            if (visited[x - 1][y - 1]) {
                System.out.println(node.depth);
                break;
            }
        }
    }

    /**
     * DFS 예시
     */
    void dfs(Node node, int[][] graph, boolean[][] visited) {
        if (visited[node.x][node.y] || graph[node.x][node.y] != node.depth) return;
        visited[node.x][node.y] = true;

        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        int m = 5;
        int n = 5;

        for (int i=0; i<4; i++) {
            int nx = node.x + dx[i];
            int ny = node.y + dy[i];
            if (0<=nx && nx < m && 0<=ny && ny<n && !visited[nx][ny]) {
                dfs(new Node(nx,ny,graph[node.x][node.y]), graph, visited);
            }
        }
    }

}