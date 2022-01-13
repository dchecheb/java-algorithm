package com.algorithm.programmers;

import java.util.*;

public class LvOne {

    /**
     * 숫자 문자열과 영단
     * https://programmers.co.kr/learn/courses/30/lessons/81301
     */
    public int lvOne01(String s) {

        String[] num = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] word = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for (int i = 0; i < 10; i++) {
            s = s.replace(word[i], num[i]);
        }
        return Integer.parseInt(s);
    }

    /**
     * 신규 아이디 추가
     * https://programmers.co.kr/learn/courses/30/lessons/72410
     */
    public String lvOne02(String new_id) {
        new_id = new_id.toLowerCase();
        new_id = new_id.replaceAll("[^a-z0-9-_. ]", "");
        new_id = new_id.replaceAll("[.]{2,}", ".");
        new_id = new_id.replaceAll("^(\\.)|(\\.)$","");
        if (new_id.isEmpty()) {
            new_id = "a";
        }

        if (new_id.length() > 15) {
            new_id = new_id.substring(0,15).replaceAll("(\\.)$","");
        }

        if (new_id.length() < 3) {
            while (new_id.length() < 3) {
                new_id += new_id.charAt(new_id.length()-1);
            }
        }

        return new_id;
    }

    /**
     * 로또의 최고순위와 최저순위
     * https://programmers.co.kr/learn/courses/30/lessons/77484
     */
    public int[] lvOne03(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int minMatchCnt = 0;
        int maxMatchCnt = 0;

        for (int i=0; i<6; i++) {
            if (lottos[i] == 0) {
                maxMatchCnt += 1;
                continue;
            }
            if (contains(win_nums, lottos[i])) {
                minMatchCnt += 1;
                maxMatchCnt += 1;
            }
        }

        answer[0] = maxMatchCnt < 2 ? 6 : 7 - maxMatchCnt;
        answer[1] = minMatchCnt < 2 ? 6 : 7 - minMatchCnt;
        return answer;
    }

    /**
    * lvOne03
    * 당첨 번호 리스트에 있는지 확
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
     * 키패드 누르
     * https://programmers.co.kr/learn/courses/30/lessons/67256?language=java
     */
    public String lvOne04(int[] numbers, String hand) {
        String answer = "";

        int lx = 3;
        int ly = 0;
        int rx = 3;
        int ry = 2;

        for (int i=0; i < numbers.length; i++) {
            int[] next = getXY(numbers[i]);
            int nx = next[0];
            int ny = next[1];

            if (ny == 0) {
                answer += "L";
            } else if (ny == 2) {
                answer += "R";
                rx = nx;
                ry = ny;
            } else if (ny == 1) {
                int lDist = Math.abs(nx - lx) + Math.abs(ny - ly);
                int rDist = Math.abs(nx - rx) + Math.abs(ny - ry);
                if (lDist > rDist) {
                    answer += "R";
                } else if (lDist < rDist) {
                    answer += "L";
                } else {
                    answer += hand.substring(0,1).toUpperCase();
                }
            }
            if (answer.charAt(answer.length()-1) == 'L') {
                lx = nx;
                ly = ny;
            } else {
                rx = nx;
                ry = ny;
            }
        }
        return answer;
    }

    /**
     * lvOne04
     * 키패드의 x, y 좌표
      */
    public int[] getXY(int num) {
        if (num == 0) {
            return new int[]{3, 1};
        }
        return new int[]{(num-1)/3, (num-1)%3};
    }

    public int lvOne05(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> basket = new Stack<>();

        for (int popIdx : moves) {
            popIdx -= 1;
            for (int i=0; i < board.length; i++) {
                if (board[i][popIdx] != 0) {
                    // 인형 뽑기
                    int toy = board[i][popIdx];
                    board[i][popIdx] = 0;

                    // 바구니에 넣기
                    if (!basket.empty()) {
                        if (basket.peek() == toy) {
                            answer += 2;
                            basket.pop();
                            break;
                        }
                    }
                    basket.push(toy);
                    break;
                }
            }
        }
        return answer;
    }
}