package com.algorithm.programmers;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class LvOne {

    /**
     * 없는 숫자 더하기
     * https://programmers.co.kr/learn/courses/30/lessons/86051
     */
    public int lvOne08(int[] numbers) {
        int answer = 0;
        int idx = 0;
        Arrays.sort(numbers);
        for (int i=0; i<10; i++) {
            if (numbers.length <= idx) {
                answer += i;
            } else if (numbers[idx] != i) {
                answer += i;
            } else {
                idx++;
            }
        }
        return answer;
    }

    /**
     * 없는 숫자 더하기 다른 풀이
     */
    public int lvOne08_v2(int[] numbers) {
        int answer = 45;
        for (int i: numbers) {
            answer -= i;
        }
        return answer;
    }

    @Test
    public void testLvOne08() {
        int[] numbers1 = {1,2,3,4,6,7,8,0};
        int[] numbers2 = {5,8,4,0,6,7,9};
        Assert.assertEquals(14, lvOne08_v2(numbers1));
        Assert.assertEquals(6, lvOne08_v2(numbers2));
    }

    /**
     * 문자 숫자열과 영단어
     * https://programmers.co.kr/learn/courses/30/lessons/81301
     */
    public int lvOne07(String s) {
        String[] str = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for (int i=0; i<10; i++) {
            s = s.replace(str[i], Integer.toString(i));
        }
        return Integer.parseInt(s);
    }

    @Test
    public void testLvOne07() {
        Assert.assertEquals(1478, lvOne07("one4seveneight"));
        Assert.assertEquals(234567, lvOne07("23four5six7"));
        Assert.assertEquals(234567, lvOne07("2three45sixseven"));
        Assert.assertEquals(123, lvOne07("123"));
    }

    public int[] lvOne06(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        int[] reportedCnt = new int[id_list.length];
        HashMap<String, List<String>> map = new HashMap<>();
        for (String id : id_list) {
            map.put(id, new ArrayList<>());
        }

        // 사용자별 신고자 리스트를 채우고 신고 횟수를 카운팅
        for (String r : report){
            String reporter = r.split(" ")[0];
            String reported = r.split(" ")[1];
            List<String> reportUsers = map.get(reporter);
            if (!reportUsers.contains(reported)) {
                reportUsers.add(reported);
                reportedCnt[indexOf(id_list, reported)]++;
            }
        }

        for (int i=0; i< id_list.length; i++) {
            for (String id : map.get(id_list[i])) {
                if (reportedCnt[indexOf(id_list, id)] >= k) {
                    answer[i]++;
                }
            }
        }

        return answer;
    }

    public int indexOf(String[] array, String str) {
        for (int i=0; i<array.length; i++) {
            if (array[i].equals(str)) return i;
        }
        return -1;
    }


    @Test
    public void testLvOne06() {
        String[] id_list1 = {"muzi", "frodo", "apeach", "neo"};
        String[] report1 = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int[] result1 = {2,1,1,0};
        Assert.assertEquals(lvOne06(id_list1, report1, 2), result1);

        String[] id_list2 = {"con", "ryan"};
        String[] report2 = {"ryan con", "ryan con", "ryan con", "ryan con"};
        int[] result2 = {0,0};
        Assert.assertEquals(lvOne06(id_list2, report2, 3), result2);
    }

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