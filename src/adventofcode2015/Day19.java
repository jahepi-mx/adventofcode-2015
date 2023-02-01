package adventofcode2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day19 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day19/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day19 day = new Day19();
        System.out.println("Part 1: " + day.run(list, true));
        System.out.println("Part 2: " + day.run(list, false));
    }

    HashMap<String, ArrayList<String>> map = new HashMap<>();
    HashMap<String, String> map2 = new HashMap<>();
    
    private int run(ArrayList<String> list, boolean part1) {
        String str = "";
        for (String line : list) {
            if (line.indexOf("=>") >= 0) {
                String[] parts = line.split(" => ");
                if (!map.containsKey(parts[0])) {
                    map.put(parts[0], new ArrayList<>());
                }
                map.get(parts[0]).add(parts[1]);
                map2.put(parts[1], parts[0]);
            } else {
                str = line;
            }
        }
        return part1 ? part1(str) : part2(str, "e", 0);
    }
    
    private int part1(String str) {
        HashSet<String> set = new HashSet<String>();
        char prev = ' ';
        for (int a = 0; a < str.length(); a++) {
            String str1 = str.charAt(a) + "";
            if (map.containsKey(str1)) {
                for (String key : map.get(str1)) {
                    set.add(str.substring(0, a) + key + str.substring(a + 1, str.length()));
                }
            }
            str1 = prev + "" + str.charAt(a);
            if (map.containsKey(str1)) {
                for (String key : map.get(str1)) {
                    set.add(str.substring(0, a - 1) + key + str.substring(a + 1, str.length()));
                }
            }
            prev = str.charAt(a);
        }
        return set.size();
    }
    
    private int part2(String str, String target, int step) {
        if (str.equals(target)) {
            return step;
        }
        int res = Integer.MAX_VALUE;
        for (String key : map2.keySet()) {
            int index = str.indexOf(key);
            if (index >= 0) {
                String tmp = str.substring(0, index) + map2.get(key) + str.substring(index + key.length(), str.length());
                res = Math.min(res, part2(tmp, target, step + 1));
                if (res < Integer.MAX_VALUE) {
                    return res;
                }
            }
        }
        return res;
    }
}
