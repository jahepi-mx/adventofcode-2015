package adventofcode2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day10 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day10/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day10 day = new Day10();
        System.out.println("Part 1: " + day.run(list, 40));
        System.out.println("Part 2: " + day.run(list, 50));
    }

    private int run(ArrayList<String> list, int times) {
        String str = list.get(0);
        for (int a = 0; a < times; a++) {
            StringBuilder sb = new StringBuilder();
            int counter = 0;
            char prev = str.charAt(0);
            for (char ch : str.toCharArray()) {
                if (ch != prev) {
                    sb.append(counter + "" + prev);
                    counter = 1;
                } else {
                    counter++;
                }
                prev = ch;
            }
            sb.append(counter + "" + prev);
            str = sb.toString();
        }
        return str.length();
    }
}
