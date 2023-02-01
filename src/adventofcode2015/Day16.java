package adventofcode2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day16 {
    
    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day16/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day16 day = new Day16();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        int res = 0;
        HashMap<String, Integer> map = new HashMap<>();
        map.put("children", 3);
        map.put("cats", 7);
        map.put("samoyeds", 2);
        map.put("pomeranians", 3);
        map.put("akitas", 0);
        map.put("vizslas", 0);
        map.put("goldfish", 5);
        map.put("trees", 3);
        map.put("cars", 2);
        map.put("perfumes", 1);
        for (String line : list) {
            String[] parts = line.split(" ");
            String key1 = parts[2].substring(0, parts[2].length() - 1);
            int key1Num = Integer.valueOf(parts[3].substring(0, parts[3].length() - 1));
            String key2 = parts[4].substring(0, parts[4].length() - 1);
            int key2Num = Integer.valueOf(parts[5].substring(0, parts[5].length() - 1));
            String key3 = parts[6].substring(0, parts[6].length() - 1);
            int key3Num = Integer.valueOf(parts[7]);
            res++;
            String[] keys = new String[] {key1, key2, key3};
            int[] values = new int[] {key1Num, key2Num, key3Num};
            boolean flag = true;
            for (int a = 0; a < keys.length; a++) {
                flag = flag && values[a] == map.get(keys[a]);
            }
            if (flag) {
                return res;
            }
        }
        return res;
    }

    private int part2(ArrayList<String> list) {
        int res = 0;
        HashMap<String, Integer> map = new HashMap<>();
        map.put("children", 3);
        map.put("cats", 7);
        map.put("samoyeds", 2);
        map.put("pomeranians", 3);
        map.put("akitas", 0);
        map.put("vizslas", 0);
        map.put("goldfish", 5);
        map.put("trees", 3);
        map.put("cars", 2);
        map.put("perfumes", 1);
        for (String line : list) {
            String[] parts = line.split(" ");
            String key1 = parts[2].substring(0, parts[2].length() - 1);
            int key1Num = Integer.valueOf(parts[3].substring(0, parts[3].length() - 1));
            String key2 = parts[4].substring(0, parts[4].length() - 1);
            int key2Num = Integer.valueOf(parts[5].substring(0, parts[5].length() - 1));
            String key3 = parts[6].substring(0, parts[6].length() - 1);
            int key3Num = Integer.valueOf(parts[7]);
            res++;
            String[] keys = new String[] {key1, key2, key3};
            int[] values = new int[] {key1Num, key2Num, key3Num};
            boolean flag = true;
            for (int a = 0; a < keys.length; a++) {
                if (keys[a].equals("cats")) {
                    flag = flag && values[a] > map.get(keys[a]);
                } else if (keys[a].equals("trees")) {
                    flag = flag && values[a] > map.get(keys[a]);
                } else if (keys[a].equals("pomeranians")) {
                    flag = flag && values[a] < map.get(keys[a]);
                } else if (keys[a].equals("goldfish")) {
                    flag = flag && values[a] < map.get(keys[a]);
                } else {
                    flag = flag && values[a] == map.get(keys[a]);
                }
            }
            if (flag) {
                return res;
            }
        }
        return res;
    }
}