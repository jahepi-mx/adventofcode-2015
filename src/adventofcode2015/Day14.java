package adventofcode2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day14 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day14/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day14 day = new Day14();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        int max = Integer.MIN_VALUE;
        for (String line : list) {
            String[] parts = line.split(" ");
            int velocity = Integer.valueOf(parts[3]);
            int seconds = Integer.valueOf(parts[6]);
            int restSeconds = Integer.valueOf(parts[13]);
            int amontOfSecs = 2503;
            int total = seconds + restSeconds;
            int mod = amontOfSecs % total;
            int res = amontOfSecs / total * velocity * seconds + (mod > seconds ? velocity * seconds : velocity * mod);
            max = Math.max(res, max);
        }
        return max;
    }

    private int part2(ArrayList<String> list) {
        int amontOfSecs = 2503;
        int finalRes = 0;
        int[] points = new int[list.size()];
        for (int secs = 1; secs <= amontOfSecs; secs++) {
            int index = 0;
            int max = Integer.MIN_VALUE;
            HashMap<Integer, ArrayList<Integer>> maxes = new HashMap<>();
            for (String line : list) {
                String[] parts = line.split(" ");
                int velocity = Integer.valueOf(parts[3]);
                int seconds = Integer.valueOf(parts[6]);
                int restSeconds = Integer.valueOf(parts[13]);
                int total = seconds + restSeconds;
                int mod = secs % total;
                int res = secs / total * velocity * seconds + (mod > seconds ? velocity * seconds : velocity * mod);
                max = Math.max(res, max);
                if (!maxes.containsKey(res)) {
                    maxes.put(res, new ArrayList<>());
                }
                maxes.get(res).add(index++);
            }
            for (int n : maxes.get(max)) {
                finalRes = Math.max(finalRes, ++points[n]);
            }
        }
        return finalRes;
    }
}
