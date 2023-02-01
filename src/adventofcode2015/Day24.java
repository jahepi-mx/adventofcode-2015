package adventofcode2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day24 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day24/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day24 day = new Day24();
        System.out.println("Part 1: " + day.run(list, 3));
        System.out.println("Part 2: " + day.run(list, 4));
    }

    int[] weights;
    private long run(ArrayList<String> list, int div) {
        weights = new int[list.size()];
        int sum = 0;
        for (int a = 0; a < list.size(); a++) {
            sum += Integer.valueOf(list.get(a));
            weights[a] = Integer.valueOf(list.get(a));
        }
        sum /= div;
        find(sum, 0, 0, 1, 0);
        return map.get(min);
    }
    
    int min = Integer.MAX_VALUE;
    HashMap<Integer, Long> map = new HashMap<>();
    private void find(int target, int sum, int index, long product, int count) {
         if (target == sum) {
             min = Math.min(min, count);
             map.put(min, Math.min(map.getOrDefault(min, Long.MAX_VALUE), product < 0 ? Long.MAX_VALUE : product));
        } else if (sum < target && index < weights.length) {
            find(target, sum, index + 1, product, count);
            find(target, sum + weights[index], index + 1, product * weights[index], count + 1);
        }
    }
}
