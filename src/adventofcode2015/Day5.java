package adventofcode2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day5 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day5/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day5 day = new Day5();
        System.out.println("Part 1: " + day.part1(list));
        System.out.println("Part 2: " + day.part2(list));
    }

    private int part1(ArrayList<String> list) {
        String notAllowed = "ab cd pq xy", vowels = "aeiou";
        int res = 0;
        for (String str : list) {
            char prev = '\u0000';
            int count = 0, vowelsCount = 0;
            boolean hasNotAllowedPair = false;
            boolean hasRepeatedChars = false;
            for (char ch : str.toCharArray()) {
                count = ch != prev ? 1 : count + 1;
                hasRepeatedChars = count > 1 ? true : hasRepeatedChars;
                vowelsCount += vowels.indexOf(ch) >= 0 ? 1 : 0;
                hasNotAllowedPair = prev != '\u0000' && notAllowed.indexOf(prev + "" + ch) >= 0 ? true : hasNotAllowedPair;
                prev = ch;
            }
            hasRepeatedChars = count > 1 ? true : hasRepeatedChars;
            res += !hasNotAllowedPair && hasRepeatedChars && vowelsCount >= 3 ? 1 : 0; 
        }
        return res;
    }

    private int part2(ArrayList<String> list) {
        int res = 0;
        for (String str : list) {
            HashMap<String, Integer> map = new HashMap<>();
            char[] chars = str.toCharArray();
            boolean hasRepeatedStr = false;
            boolean hasRepeatedChar = false;
            for (int a = 0; a < chars.length - 1; a++) {
                if (map.containsKey(chars[a] + "" + chars[a + 1]) &&
                        map.get(chars[a] + "" + chars[a + 1]) + 1 != a) {
                    hasRepeatedStr = true;
                }
                if (!map.containsKey(chars[a] + "" + chars[a + 1])) {
                    map.put(chars[a] + "" + chars[a + 1], a);
                }
                hasRepeatedChar = a + 2 < chars.length && chars[a] == chars[a + 2] ? true : hasRepeatedChar;
            }
            res += hasRepeatedChar && hasRepeatedStr ? 1 : 0;
        }
        return res;
    }
}
