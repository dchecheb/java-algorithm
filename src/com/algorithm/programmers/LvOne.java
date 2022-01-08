package com.algorithm.programmers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
     * 신규 아이디 추
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

    public boolean contains(int[] src, int dst) {
        for (int i=0; i < src.length; i++) {
            if (src[i] == dst) {
                return true;
            }
        }
        return false;
    }
}