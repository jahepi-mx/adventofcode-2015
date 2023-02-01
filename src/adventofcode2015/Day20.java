package adventofcode2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day20 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day20/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day20 day = new Day20();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        int limit = 33100000;
        int n = 0;
        boolean isRunning = true;
        while (isRunning) {
            n++;
            int num = 0;
            for (int i = 1; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    if (n / i == i) {
                        num += 10 * i;
                    } else {
                        num += 10 * i;
                        num += 10 * n / i;
                    }
                }
            }
            if (num >= limit) {
                isRunning = false;
            }
        }
        return n;
    }
    
    private int part2(ArrayList<String> list) {
        int limit = 33100000;
        int n = 0;
        boolean isRunning = true;
        while (isRunning) {
            n++;
            int num = 0;
            for (int i = 1; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    if (n / i == i) {
                        if (n / i <= 50) {
                            num += i * 11;
                        }
                    } else {
                        if (n / i <= 50) {
                            num += i * 11;
                        }
                        if (i <= 50) {
                            num += n / i * 11;
                        }
                    }
                }
            }
            if (num >= limit) {
                isRunning = false;
            }
        }
        return n;
    }
}
