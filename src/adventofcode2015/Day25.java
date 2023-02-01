package adventofcode2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day25 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day25/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day25 day = new Day25();
        System.out.println("Part 1: " + day.part1(list));
    }

    private long part1(ArrayList<String> list) {
        long prev = 20151125;
        int x = 1, y = 1;
        boolean isRunning = true;
        while (isRunning) {
            y++;
            int tx = x;
            int ty = y;
            for (int a = 0; a < y; a++) {
                prev = prev * 252533l % 33554393;
                if (tx == 3019 && ty == 3010) {
                    return prev;
                }
                tx++;
                ty--;
            }
        }
        return -1;
    }
}
