package adventofcode2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day9 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day9/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day9 day = new Day9();
        System.out.println("Part 1: " + day.run(list, true));
        System.out.println("Part 2: " + day.run(list, false));
    }

    HashMap<String, Integer> locations;
    int[][] distances;
    private int run(ArrayList<String> list, boolean part1) {  
        locations = new HashMap<>();
        distances = new int[10][10];
        int id = 0;
        for (String line : list) {
            String[] parts = line.split(" ");
            if (!locations.containsKey(parts[0])) {
                locations.put(parts[0], id++);
            }
            if (!locations.containsKey(parts[2])) {
                locations.put(parts[2], id++);
            }
            distances[locations.get(parts[0])][locations.get(parts[2])] = Integer.valueOf(parts[4]);
            distances[locations.get(parts[2])][locations.get(parts[0])] = Integer.valueOf(parts[4]);
        }
        return distance(new int[locations.size()], "", part1);
    }
    
    private int distance(int[] nodes, String from, boolean part1) {
        int dist = part1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        boolean found = false;
        for (String loc : locations.keySet()) {
            if (nodes[locations.get(loc)] == 0) {
                found = true;
                nodes[locations.get(loc)] = -1;
                dist = part1 ? 
                        Math.min(dist, distance(nodes, loc, part1) + (from.length() == 0 ? 0 : distances[locations.get(from)][locations.get(loc)])) : 
                        Math.max(dist, distance(nodes, loc, part1) + (from.length() == 0 ? 0 : distances[locations.get(from)][locations.get(loc)]));
                nodes[locations.get(loc)] = 0;
            }
        }
        return found ? dist : 0;
    }
}
