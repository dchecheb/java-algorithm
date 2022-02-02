package com.algorithm.programmers.lvTwo;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/*
 * 다리를 지나는 트럭
 * https://programmers.co.kr/learn/courses/30/lessons/42583
 */
public class Solution09 {

    @Test
    public void test() {
        Assert.assertEquals(8, solution(2, 10, new int[]{7,4,5,6}));
        Assert.assertEquals(101, solution(100, 100, new int[]{10}));
        Assert.assertEquals(110, solution(100, 100, new int[]{10,10,10,10,10,10,10,10,10,10}));
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> trucks = new LinkedList<>();
        for (int i=0; i<truck_weights.length; i++) { // O(truck 개수) -> 10000회
            trucks.offer(truck_weights[i]);
        }

        LinkedList<Integer> road = new LinkedList<>();
        road.add(trucks.poll());
        int bridge_weight = road.get(0);
        while (!trucks.isEmpty()) { // O(truck 개수) = 10000
            if (weight >= bridge_weight+trucks.peek()) {
                road.addFirst(trucks.poll());
            } else {
                road.addFirst(0);
            }
            int end = road.size() < bridge_length ? 0 : road.get(bridge_length-1);
            int first = road.get(0);
            bridge_weight = bridge_weight - end + first;
        }

        return road.size()+bridge_length;
    }

    class Bridge {
        int trucks_weight;
        int firstTruck;
        int finalTruck;

        public void enter(int w) {

        }

        public void escape(int w) {

        }
    }
}
