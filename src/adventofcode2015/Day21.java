package adventofcode2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day21 {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day21/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day21 day = new Day21();
        System.out.println("Part 1: " + day.run(list, true));
        System.out.println("Part 2: " + day.run(list, false));
    }
    
    private int run(ArrayList<String> list, boolean part1) {
        
        int[][] weapons = new int[][] {{8,4,0}, {10,5,0}, {25,6,0}, {40,7,0}, {74,8,0}};
        int[][] armors = new int[][] {{0,0,0}, {13,0,1}, {31,0,2}, {53,0,3}, {75,0,4},{102,0,5}};
        int[][] rings = new int[][] {{0,0,0}, {25,1,0}, {50,2,0}, {100,3,0}, {20,0,1},{40,0,2},{80,0,3}};
        ringsCombs.add("00");
        combs(7, "", 0);
        
        int minGold = Integer.MAX_VALUE;
        int maxGold = Integer.MIN_VALUE;
        for (int[] weapon : weapons) {
            for (int[] armor : armors) {
                for (String ring : ringsCombs) {
                    int[] ring1 = rings[ring.charAt(0) - '0'];
                    int[] ring2 = rings[ring.charAt(1) - '0'];
                    
                    int playerCost = weapon[0] + armor[0] + ring1[0] + ring2[0];
                    int playerDamage = weapon[1] + armor[1] + ring1[1] + ring2[1];
                    int playerArmor = weapon[2] + armor[2] + ring1[2] + ring2[2];
                    
                    int playerLife = 100;
                    int bossLife = 104;
                    int bossDamage = 8;
                    int bossArmor = 1;
                    
                    boolean isRunning = true;
                    while (isRunning) {
                        
                        // Player Attack
                        int points = playerDamage - bossArmor;
                        points = points <= 0 ? 1 : points;
                        bossLife -= points;
                        
                        // Boss Attack
                        points = bossDamage - playerArmor;
                        points = points <= 0 ? 1 : points;
                        playerLife -= points;
                        
                        if (bossLife <= 0) {
                            minGold = Math.min(playerCost, minGold);
                            isRunning = false;
                        } else if (playerLife <= 0) {
                            maxGold = Math.max(playerCost, maxGold);
                            isRunning = false;
                        }
                    }
                }
            }
        }
        return part1 ? minGold : maxGold;
    }
    
    ArrayList<String> ringsCombs = new ArrayList<>();
    void combs(int size, String str, int from) {
        if (str.length() == 2) {
            ringsCombs.add(str);
            return;
        }
        for (int a = from; a < size; a++) {
            combs(size, str + a, a + 1);
        }
    }
}
