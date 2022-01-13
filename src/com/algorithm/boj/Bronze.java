package com.algorithm.boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 백준 문제 풀기 전 기초 20제
 * https://www.acmicpc.net/workbook/view/2196
 */
public class Bronze {

    /**
     * 나부 함대 데이
     * https://www.acmicpc.net/problem/9654
     */
    public void basic01() {
        System.out.println("SHIP NAME      CLASS          DEPLOYMENT IN SERVICE");
        System.out.println("N2 Bomber      Heavy Fighter  Limited    21        ");
        System.out.println("J-Type 327     Light Combat   Unlimited  1         ");
        System.out.println("NX Cruiser     Medium Fighter Limited    18        ");
        System.out.println("N1 Starfighter Medium Fighter Unlimited  25        ");
        System.out.println("Royal Cruiser  Light Combat   Limited    4         ");
    }

    public void basic02() {
        System.out.println("|\\_/|");
        System.out.println("|q p|   /}");
        System.out.println("( 0 )\"\"\"\\");
        System.out.println("|\"^\"`    |");
        System.out.println("||_/=\\\\__|");
    }

    public void basic03() {
        System.out.println("58");
        System.out.println("qocodbssla");
    }

    public void basic04() {
        System.out.println("Hello World!");
    }

    public void basic05() {
        Scanner scanner = new Scanner(System.in);
        Integer N = scanner.nextInt();
        for (int i=1; i < N+1; i++) {
            System.out.printf("Hello World, Judge %d!\n", i);
        }
    }

    public void basic06() {
        System.out.println("     /~\\");
        System.out.println("    ( oo|");
        System.out.println("    _\\=/_");
        System.out.println("   /  _  \\");
        System.out.println("  //|/.\\|\\\\");
        System.out.println(" ||  \\ /  ||");
        System.out.println("============");
        System.out.println("|          |");
        System.out.println("|          |");
        System.out.println("|          |");
    }

    public void basic07() {
        Scanner scanner = new Scanner(System.in);
        Integer a = scanner.nextInt();
        Integer b = scanner.nextInt();
        System.out.println(a+b);
    }

    public void basic08() {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
    }

    public void basic09() {
        Scanner scanner = new Scanner(System.in);
        Integer A = scanner.nextInt();
        Integer B = scanner.nextInt();
        Integer C = scanner.nextInt();
        System.out.println((A+B)%C);
        System.out.println(((A%C) + (B%C))%C);
        System.out.println((A*B)%C);
        System.out.println((A%C)*(B%C)%C);
    }

    public void basic10() {
        Scanner scanner = new Scanner(System.in);
        Integer N = scanner.nextInt();
        Integer fiveNum = N / 5;
        N = N % 5;
        if (N % 3 == 0) {
            System.out.println(N/3+fiveNum);
        } else {
            while (fiveNum > 0) {
                fiveNum--;
                N += 5;
                if (N%3 == 0) {
                    System.out.println(N/3+fiveNum);
                    return;
                }
            }
            System.out.println(-1);
        }
    }

    public void basic11() {
        Scanner scanner = new Scanner(System.in);
        Integer N = scanner.nextInt();
        for (int i = 1; i <= N; i++) {
            System.out.println(i);
        }
    }

    public void basic12() {
        Scanner scanner = new Scanner(System.in);
        Integer N = scanner.nextInt();
        for (int i = 1; i < 10; i++) {
            System.out.printf("%d * %d = %d%n", N, i, N*i);
        }
    }

    public void basic13() {
        Scanner scanner = new Scanner(System.in);
        Integer N = scanner.nextInt();
        for (int i = 1; i <= N; i++) {
            System.out.println(" ".repeat(N - i) + "*".repeat(i));
        }
    }

    public void basic14() {
        Scanner scanner = new Scanner(System.in);
        Integer month = scanner.nextInt();
        Integer day = scanner.nextInt();
        ArrayList<String> week = new ArrayList<>(Arrays.asList("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"));
        ArrayList<Integer> daysInMonth = new ArrayList<>(Arrays.asList(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31));

        Integer sumDay = -1;
        for (int i=0; i < month-1; i++) {
            sumDay += daysInMonth.get(i);
        }
        sumDay += day;

        System.out.println(week.get(sumDay % 7));
    }

    /**
     * 평균은 넘겠지
     * https://www.acmicpc.net/problem/4344
     */
    public void basic15() {
        Scanner scanner = new Scanner(System.in);
        Integer N = scanner.nextInt();
        for (int i=0; i < N; i++) {
            String[] line = scanner.nextLine().split(" ");
            int num = Integer.parseInt(line[0]);

            int sum = 0;
            for (int j=1; j<num; j++) {
                sum += Integer.parseInt(line[j]);
            }

            float avg = sum / num;
            int aboveAvgNum = 0;
            for (int k=1; k<num; k++) {
                if (Integer.parseInt(line[k]) > avg) {
                    aboveAvgNum += 1;
                }
            }

            System.out.printf("%.3f", aboveAvgNum / num * 100);
        }
    }

}
