package adventofcode2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day2 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day2/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day2 day = new Day2();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        int res = 0;
        for (String line : list) {
            String[] parts = line.split("x");
            int l = Integer.valueOf(parts[0]);
            int w = Integer.valueOf(parts[1]);
            int h = Integer.valueOf(parts[2]);
            res += 2 * l * w + 2 * w * h + 2 * h * l + Math.min(l * w, Math.min(l * h, w * h));
        }
        return res;
    }

    private int part2(ArrayList<String> list) {
        int res = 0;
        for (String line : list) {
            String[] parts = line.split("x");
            int l = Integer.valueOf(parts[0]);
            int w = Integer.valueOf(parts[1]);
            int h = Integer.valueOf(parts[2]);
            int[] sides = new int[] {l, w, h};
            Arrays.sort(sides);
            res += l * w * h + 2 * (sides[0] + sides[1]);
        }
        return res;
    }
}
