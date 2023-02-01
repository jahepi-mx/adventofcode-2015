package adventofcode2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Day18 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day18/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day18 day = new Day18();
        System.out.println("Part 1: " + day.run(list, true));
        System.out.println("Part 2: " + day.run(list, false));
    }

    private int run(ArrayList<String> list, boolean part1) {
        int height = list.size();
        int width = list.get(0).length();
        int[][] dirs = new int[][] {{1,0}, {1,1}, {0,1}, {-1,1}, {-1,0}, {-1,-1}, {0,-1}, {1,-1}};
        int steps= 100;
        int[] lights = part1 ? new int[] {} : new int[] {0, width - 1, (height - 1) * width, (height - 1) * width + (width - 1)};
        HashSet<Integer> set = new HashSet<>();
        for (int y = 0; y < list.size(); y++) {
            for (int x = 0; x < list.get(y).length(); x++) {
                if (list.get(y).charAt(x) == '#') {
                    set.add(y * width + x);
                }
            }
        }
        for (int pos : lights) {
            set.add(pos);
        }
        while (steps > 0) {
            HashSet<Integer> tmp = new HashSet<>();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int count = 0;
                    for (int[] dir : dirs) {
                        int nx = x + dir[0];
                        int ny = y + dir[1];
                        count += nx >= 0 && nx < width && ny >= 0 && ny < height && set.contains(ny  * width + nx) ? 1 : 0;
                    }
                    if (set.contains(y  * width + x)) {
                        if (count == 3 || count == 2) {
                            tmp.add(y  * width + x);
                        }
                    } else if (count == 3) {
                        tmp.add(y  * width + x);
                    }
                }
            }
            for (int pos : lights) {
                tmp.add(pos);
            }
            set = tmp;
            steps--;
        }
        return set.size();
    }
}
