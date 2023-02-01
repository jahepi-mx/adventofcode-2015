package adventofcode2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day15 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day15/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day15 day = new Day15();
        System.out.println("Part 1: " + day.run(list, true));
        System.out.println("Part 2: " + day.run(list, false));
    }

    int[][] values;
    int[] calories;
    private int run(ArrayList<String> list, boolean part1) {
        values = new int[list.size()][5];
        calories = new int[list.size()];
        int index = 0;
        for (String line : list) {
            String[] parts = line.split(" ");
            values[index][0] = Integer.valueOf(parts[2].substring(0, parts[2].length() - 1));
            values[index][1] = Integer.valueOf(parts[4].substring(0, parts[4].length() - 1));
            values[index][2] = Integer.valueOf(parts[6].substring(0, parts[6].length() - 1));
            values[index][3] = Integer.valueOf(parts[8].substring(0, parts[8].length() - 1));
            values[index++][4] = Integer.valueOf(parts[10].substring(0, parts[10].length()));
        }
        return recipe(100, 0, part1);
    }
    
    private int recipe(int calories, int step, boolean part1) {
        if (calories < 0) {
            return 0;
        }
        if (step == values.length - 1) {
            int total = 1;
            this.calories[step] = calories;
            for (int a = 0; a < 4; a++) {
                int tmp = 0, checkSum = 0;
                for (int b = 0; b < this.calories.length; b++) {
                    tmp += values[b][a] * this.calories[b];
                    checkSum += this.calories[b] * values[b][4];
                }
                if (!part1 && checkSum != 500) {
                    break;
                }
                total *= tmp < 0 ? 0 : tmp;
            }
            return total;
        }
        int max = Integer.MIN_VALUE;
        for (int calorie = 1; calorie <= calories; calorie++) {
            if (this.calories[step] == 0) {
                this.calories[step] = calorie;
                max = Math.max(max, recipe(calories - calorie, step + 1, part1));
                this.calories[step] = 0;
            }
        }
        return max;
    }
}
