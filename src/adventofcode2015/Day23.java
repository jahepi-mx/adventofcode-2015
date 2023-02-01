package adventofcode2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day23 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day23/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day23 day = new Day23();
        System.out.println("Part 1: " + day.run(list, true));
        System.out.println("Part 2: " + day.run(list, false));
    }

    private long run(ArrayList<String> list, boolean part1) {
        long[] registers = part1 ? new long[2] : new long[] {1, 0};
        int pointer = 0;
        while (pointer >= 0 && pointer < list.size()) {
            String ins = list.get(pointer).replace(",", "");
            String[] parts = ins.split(" ");
            if (parts[0].equals("inc")) {
                registers[parts[1].charAt(0) - 'a']++;
                pointer++;
            } else if (parts[0].equals("hlf")) {
                registers[parts[1].charAt(0) - 'a'] /= 2;
                pointer++;
            } else if (parts[0].equals("tpl")) {
                registers[parts[1].charAt(0) - 'a'] *= 3;
                pointer++;
            } else if (parts[0].equals("jmp")) {
                int offset = Integer.valueOf(parts[1]);
                pointer += offset;
            } else if (parts[0].equals("jie")) {
                int offset = Integer.valueOf(parts[2]);
                if (registers[parts[1].charAt(0) - 'a'] % 2 == 0) {
                    pointer += offset;
                } else {
                    pointer++;
                }
            } else if (parts[0].equals("jio")) {
                int offset = Integer.valueOf(parts[2]);
                if (registers[parts[1].charAt(0) - 'a'] == 1) {
                    pointer += offset;
                } else {
                    pointer++;
                }
            } 
        }
        return registers[1];
    }
}
