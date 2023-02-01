package adventofcode2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day8 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day8/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day8 day = new Day8();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        int res = 0;
        for (String str : list) {
            char[] chars = str.toCharArray();
            int count = 0;
            for (int a = 1; a < chars.length - 1; a++) {
                if (chars[a] == '\\') {
                    a += chars[a + 1] == 'x' ? 3 : 1; 
                }
                count++;
            }
            res += chars.length - count;
        }
        return res;
    }

    private int part2(ArrayList<String> list) {
        int res = 0;
        for (String str : list) {
            char[] chars = str.toCharArray();
            int count = 0;
            for (int a = 1; a < chars.length - 1; a++) {
                if (chars[a] == '\\') {
                    count += chars[a + 1] == 'x' ? 4 : 3;
                    a += chars[a + 1] == 'x' ? 3 : 1; 
                }
                count++;
            }
            count += 6;
            res += count - chars.length;
        }
        return res;
    }
}
