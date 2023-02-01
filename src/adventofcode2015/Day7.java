package adventofcode2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day7 {
    
    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day7/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day7 day = new Day7();
        System.out.println("Part 1: " + day.run(list, true));
        System.out.println("Part 2: " + day.run(list, false));
    }

    private int run(ArrayList<String> list, boolean part1) {
        HashMap<String, Integer> map = new HashMap<>();
        boolean isRunning = true;
        HashSet<Integer> set = new HashSet<>();
        while (isRunning) {
            int counter = 0;
            for (String line : list) {
                String[] parts = line.split(" ");
                if (parts.length == 5) {
                    int value1 = parts[0].charAt(0) >= '0' && parts[0].charAt(0) <= '9' ? Integer.valueOf(parts[0]) : map.getOrDefault(parts[0], -1);
                    int value2 = parts[2].charAt(0) >= '0' && parts[2].charAt(0) <= '9' ? Integer.valueOf(parts[2]) : map.getOrDefault(parts[2], -1);
                    if (!set.contains(counter) && value1 >= 0 && value2 >= 0) {
                        if (parts[1].indexOf("LSHIFT") >= 0) {
                            map.put(parts[4], value1 << value2);
                        } else if (parts[1].indexOf("RSHIFT") >= 0) {
                            map.put(parts[4], value1 >> value2);
                        } else if (parts[1].indexOf("OR") >= 0) {
                            map.put(parts[4], value1 | value2);
                        } else {
                            map.put(parts[4], value1 & value2);
                        }
                        set.add(counter);
                    }
                } else if (parts.length == 4) {
                    int value1 = map.getOrDefault(parts[1], -1);
                    if (!set.contains(counter) && value1 >= 0) {
                        map.put(parts[3], ~value1 & (1 << 16) - 1);
                        set.add(counter);
                    }
                } else {
                    int value1 = parts[0].charAt(0) >= '0' && parts[0].charAt(0) <= '9' ? Integer.valueOf(parts[0]) : map.getOrDefault(parts[0], -1);
                    if (!set.contains(counter) && value1 >= 0) {
                        map.put(parts[2], parts[2].equals("b") && !part1 ? 46065 : value1);
                        set.add(counter);
                    }
                }
                counter++;
            }
            isRunning = set.size() == list.size() ? false : isRunning;
        }
        return map.get("a");
    }
}
