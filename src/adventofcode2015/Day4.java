package adventofcode2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Day4 {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("input/day4/input.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        Day4 day = new Day4();
        System.out.println("Part 1: " + day.run(list, "00000"));
        System.out.println("Part 2: " + day.run(list, "000000"));
    }

    private int run(ArrayList<String> list, String key) throws IOException, NoSuchAlgorithmException {
        int count = 0;
        MessageDigest md = MessageDigest.getInstance("MD5");
        while (true) {
            byte[] bytesOfMessage = (list.get(0) + count++).getBytes("UTF-8");
            byte[] theMD5digest = md.digest(bytesOfMessage);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < theMD5digest.length; i++) {
                sb.append(Integer.toString((theMD5digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            String md5 = sb.toString();
            if (md5.indexOf(key) == 0) {
                return count - 1;
            }
        }
    }
}
