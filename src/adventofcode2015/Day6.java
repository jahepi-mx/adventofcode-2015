package adventofcode2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day6 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day6/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day6 day = new Day6();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        int[] map = new int[1000 * 1000];
        int res = 0;
        for (String line : list) {
            String[] parts = line.split(" ");
            int x1 = Integer.valueOf(parts[(line.indexOf("toggle") >= 0 ? 1 : 2)].split(",")[0]);
            int y1 = Integer.valueOf(parts[(line.indexOf("toggle") >= 0 ? 1 : 2)].split(",")[1]);
            int x2 = Integer.valueOf(parts[(line.indexOf("toggle") >= 0 ? 3 : 4)].split(",")[0]);
            int y2 = Integer.valueOf(parts[(line.indexOf("toggle") >= 0 ? 3 : 4)].split(",")[1]);
            for (int y = y1; y <= y2; y++) {
                for (int x = x1; x <= x2; x++) {
                    if (line.indexOf("turn on") >= 0) {
                        res += map[y * 1000 + x] ^ 1;
                        map[y * 1000 + x] = 1;
                    } else if (line.indexOf("turn off") >= 0) {
                        res -= map[y * 1000 + x] & 1;
                        map[y * 1000 + x] = 0;
                    } else {
                        map[y * 1000 + x] ^= 1;
                        res += map[y * 1000 + x] == 0 ? -1 : 1;
                    }
                }
            }
        }
        return res;
    }

    private int part2(ArrayList<String> list) {
        int res = 0;
        int[] map = new int[1000 * 1000];
        for (String line : list) {
            String[] parts = line.split(" ");
            int x1 = Integer.valueOf(parts[(line.indexOf("toggle") >= 0 ? 1 : 2)].split(",")[0]);
            int y1 = Integer.valueOf(parts[(line.indexOf("toggle") >= 0 ? 1 : 2)].split(",")[1]);
            int x2 = Integer.valueOf(parts[(line.indexOf("toggle") >= 0 ? 3 : 4)].split(",")[0]);
            int y2 = Integer.valueOf(parts[(line.indexOf("toggle") >= 0 ? 3 : 4)].split(",")[1]);
            for (int y = y1; y <= y2; y++) {
                for (int x = x1; x <= x2; x++) {
                    if (line.indexOf("turn on") >= 0) {
                        map[y * 1000 + x]++;
                        res++;
                    } else if (line.indexOf("turn off") >= 0) {
                        if (map[y * 1000 + x] > 0) {
                            map[y * 1000 + x]--;
                            res--;
                        }
                    } else {
                        map[y * 1000 + x] += 2;
                        res += 2;
                    }
                }
            }
        }
        return res;
    }
}
