package adventofcode2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Day11 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day11/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day11 day = new Day11();
        System.out.println("Part 1: " + day.run(list, 1));
        System.out.println("Part 2: " + day.run(list, 2));
    }

    private String run(ArrayList<String> list, int limit) {
        String pass = "";
        long n = toDec(list.get(0));
        int passes = 0;
        boolean isRunning = true;
        while (isRunning) {
            pass = toBase26(n++);
            char prev = ' ';
            int counter = 0, counter2 = 0;
            boolean hasOnes = false;
            boolean hasConsecutives = false;
            HashSet<Character> set = new HashSet<>();
            for (char ch : pass.toCharArray()) {
                if (ch - prev == 1) {
                    counter2++;
                } else {
                    hasConsecutives = counter2 == 3 ? true : hasConsecutives;
                    counter2 = 1;
                }
                if (ch != prev) {
                    if (counter == 2) {
                        set.add(prev);
                    }
                    counter = 1;
                } else {
                    counter++;
                }
                hasOnes = ch == 'i' || ch == 'l' ? true : hasOnes;
                prev = ch;
            }
            hasConsecutives = counter2 == 3 ? true : hasConsecutives;
            if (counter == 2) {
                set.add(prev);
            }
            passes += hasConsecutives && !hasOnes && set.size() >= 2 ? 1 : 0;
            isRunning = passes == limit ? false : isRunning;
        }
        return pass;
    }
    
    private long toDec(String s) {
        long pow = s.length() - 1, num = 0;
        for (char ch : s.toCharArray()) {
            num += Math.pow(26, pow--) * (ch - 'a');
        }
        return num;
    }
    
    private String toBase26(long n) {
        String str = "";
        while (n > 0) {
            str = (char) ((n % 26) + 'a') + str; 
            n /= 26;
        }
        return str;
    }
}
