package adventofcode2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day13 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day13/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day13 day = new Day13();
        System.out.println("Part 1: " + day.run(list, true));
        System.out.println("Part 2: " + day.run(list, false));
    }

    int[][] happiness  = new int[10][10];
    private int run(ArrayList<String> list, boolean part1) {
        int index = 0;
        HashMap<String, Integer> people = new HashMap<>();
        for (String line : list) {
            String[] parts = line.substring(0, line.length() - 1).split(" ");
            if (!people.containsKey(parts[0])) {
                people.put(parts[0], index++);
            }
            if (!people.containsKey(parts[10])) {
                people.put(parts[10], index++);
            }
            happiness[people.get(parts[0])][people.get(parts[10])] = Integer.valueOf(parts[3]) * (parts[2].equals("gain") ? 1 : -1);
        }
        if (!part1) {
            people.put("jahepi", index++);
        }
        return getValue(new int[index], -1, -1);
    }
    
    private int getValue(int[] people, int root, int parent) {
        int max = Integer.MIN_VALUE;
        boolean found = false;
        for (int a = 0; a < people.length; a++) {
            if (people[a] == 0) {
                found = true;
                people[a] = -1;
                int sum = parent >= 0 ? happiness[parent][a] + happiness[a][parent] : 0;
                max = Math.max(max, getValue(people, root == -1 ? a : root, a) + sum);
                people[a] = 0;
            }
        }
        return !found ? happiness[parent][root] + happiness[root][parent] : max;
    }
}
