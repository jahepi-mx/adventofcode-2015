package adventofcode2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day17 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day17/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day17 day = new Day17();
        System.out.println("Part 1: " + day.run(list, true));
        System.out.println("Part 2: " + day.run(list, false));
    }

    int[] nums;
    private int run(ArrayList<String> list, boolean part1) {
        nums = new int[list.size()];
        int index = 0;
        for (String line : list) {
            nums[index++] = Integer.valueOf(line);
        }
        return part1 ? combinations(0, 0) : combinationsWithMin(0, 0, 0, minContainers(0, 0, 0));
    }
    
    private int combinations(int step, int sum) {
        if (step == nums.length) {
            return sum == 150 ? 1 : 0;
        }
        return combinations(step + 1, sum + nums[step]) + combinations(step + 1, sum);
    }
    
    private int minContainers(int step, int sum, int containers) {
        if (step == nums.length) {
            return sum == 150 ? containers : Integer.MAX_VALUE;
        }
        return Math.min(minContainers(step + 1, sum + nums[step], containers + 1), minContainers(step + 1, sum, containers));
    }
    
    private int combinationsWithMin(int step, int sum, int containers, int minContainers) {
        if (step == nums.length) {
            return sum == 150 && containers == minContainers ? 1 : 0;
        }
        return combinationsWithMin(step + 1, sum + nums[step], containers + 1, minContainers) + combinationsWithMin(step + 1, sum, containers, minContainers);
    }
}
