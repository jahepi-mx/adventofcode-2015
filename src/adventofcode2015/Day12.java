package adventofcode2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day12 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day12/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day12 day = new Day12();
        System.out.println("Part 1: " + day.run(list.get(0), false, true));
        System.out.println("Part 2: " + day.run(list.get(0), false, false));
    }
    
    private int run(String str, boolean isArr, boolean part1) {
        int tmpSum = 0;
        char[] chars = str.toCharArray();
        String tmpStr = "";
        boolean hasRed = false;
        for (int a = 0; a < chars.length; a++) {
            char ch = chars[a];
            if (ch == '[' || ch == '{') {
                int count = 1, i = ++a;
                String tmp = "";
                while (count > 0) {
                    tmp += chars[i];
                    count += chars[i] == ch ? 1 : 0;
                    count += chars[i++] == (ch == '[' ? ']' : '}') ? -1: 0;
                }
                a = --i;
                tmpSum += run(tmp.substring(0, tmp.length() - 1), ch == '[', part1);
            }
            if (ch == '{' || ch == '"' || ch == '}' || ch == ',' || ch == ':') {
                hasRed = tmpStr.equals("red") ? true : hasRed;
                tmpSum += tmpStr.length() > 0 && ((tmpStr.charAt(0) >= '0' && tmpStr.charAt(0) <= '9') || tmpStr.charAt(0) == '-') ? Integer.valueOf(tmpStr) : 0;
                tmpStr = "";
            } else {
                tmpStr += ch;
            }
        }
        tmpSum += tmpStr.length() > 0 && ((tmpStr.charAt(0) >= '0' && tmpStr.charAt(0) <= '9') || tmpStr.charAt(0) == '-') ? Integer.valueOf(tmpStr) : 0;
        return part1 ? tmpSum : hasRed && !isArr ? 0 : tmpSum;
    }
}
