package adventofcode2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Day3 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day3/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day3 day = new Day3();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        HashSet<Integer> set = new HashSet<>();
        int x = 500, y = 500;
        set.add(y * 2000 + x);
        for (char dir : list.get(0).toCharArray()) {
            y += dir == '^' ? 1 : 0;
            y += dir == 'v' ? -1 : 0;
            x += dir == '<' ? -1 : 0;
            x += dir == '>' ? 1 : 0;
            set.add(y * 2000 + x);
        }
        return set.size();
    }

    private int part2(ArrayList<String> list) {
        HashSet<Integer> set = new HashSet<>();
        int x = 500, y = 500, x2 = 500, y2 = 500, turn = 0;
        set.add(y * 2000 + x);
        for (char dir : list.get(0).toCharArray()) {
            if (turn++ % 2 == 0) {
                y += dir == '^' ? 1 : 0;
                y += dir == 'v' ? -1 : 0;
                x += dir == '<' ? -1 : 0;
                x += dir == '>' ? 1 : 0;
                set.add(y * 2000 + x);
            } else {
                y2 += dir == '^' ? 1 : 0;
                y2 += dir == 'v' ? -1 : 0;
                x2 += dir == '<' ? -1 : 0;
                x2 += dir == '>' ? 1 : 0;
                set.add(y2 * 2000 + x2);
            }
        }
        return set.size();
    }
}
