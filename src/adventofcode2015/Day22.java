package adventofcode2015;

import java.io.IOException;

public class Day22 {

    public static void main(String[] args) throws IOException {
        Day22 day = new Day22();
        System.out.println("Part 1: " + day.run(true));
        System.out.println("Part 2: " + day.run(false));
    }
 
    private long run(boolean test1) {
        return simulation(50, 500, 71, 0, 0, 0, test1);
    }
   
    private long simulation(int life, int mana, int enemyLife, int shield, int poison, int recharge, boolean test1) {
        int enemyDamage = 10;
        life -= test1 ? 0 : 1;
        if (enemyLife <= 0) {
            return 0;
        }
       
        if (life <= 0) {
            return Integer.MAX_VALUE;
        }
       
        long res = Integer.MAX_VALUE;
       
        /* Magic missile */
        int shieldTmp = shield;
        int poisonTmp = poison;
        int rechargeTmp = recharge;
        
        shieldTmp--;
        int armor = shieldTmp >= 0 ? 7 : 0;
        poisonTmp--;
        int enemyLifeTmp = enemyLife - (poisonTmp >= 0 ? 3 : 0);
        rechargeTmp--;
        int manaTmp = mana + (rechargeTmp >= 0 ? 101 : 0);
        
        if ((manaTmp - 53) >= 0) {
            int lifeTmp = life;
            enemyLifeTmp -= 4;
            
            shieldTmp--;
            armor = shieldTmp >= 0 ? 7 : 0;
            poisonTmp--;
            enemyLifeTmp -= (poisonTmp >= 0 ? 3 : 0);
            rechargeTmp--;
            manaTmp += (rechargeTmp >= 0 ? 101 : 0);
           
            int damage = enemyDamage - armor <= 0 ? 1 : enemyDamage - armor;
            res = Math.min(res, simulation(lifeTmp - damage, manaTmp - 53, enemyLifeTmp, shieldTmp, poisonTmp, rechargeTmp, test1) + 53);
        }
       
        /* Drain */
        shieldTmp = shield;
        poisonTmp = poison;
        rechargeTmp = recharge;
        
        shieldTmp--;
        armor = shieldTmp >= 0 ? 7 : 0;
        poisonTmp--;
        enemyLifeTmp = enemyLife - (poisonTmp >= 0 ? 3 : 0);
        rechargeTmp--;
        manaTmp = mana + (rechargeTmp >= 0 ? 101 : 0);
        
        if ((manaTmp - 73) >= 0) {
            int lifeTmp = life + 2;
            enemyLifeTmp -= 2;
           
            shieldTmp--;
            armor = shieldTmp >= 0 ? 7 : 0;
            poisonTmp--;
            enemyLifeTmp -= poisonTmp >= 0 ? 3 : 0;
            rechargeTmp--;
            manaTmp += (rechargeTmp >= 0 ? 101 : 0);
           
            int damage = enemyDamage - armor <= 0 ? 1 : enemyDamage - armor;
            res = Math.min(res, simulation(lifeTmp - damage, manaTmp - 73, enemyLifeTmp, shieldTmp, poisonTmp, rechargeTmp, test1) + 73);
        }
       
        /* Shield */
        poisonTmp = poison;
        rechargeTmp = recharge;
        shieldTmp = shield;
        
        shieldTmp--;
        armor = shieldTmp >= 0 ? 7 : 0;
        poisonTmp--;
        enemyLifeTmp = enemyLife - (poisonTmp >= 0 ? 3 : 0);
        rechargeTmp--;
        manaTmp = mana + (rechargeTmp >= 0 ? 101 : 0);
        
        if ((manaTmp - 113) >= 0 && shieldTmp <= 0) {
            int lifeTmp = life;
            armor = 7;
           
            poisonTmp--;
            enemyLifeTmp -= (poisonTmp >= 0 ? 3 : 0);
            rechargeTmp--;
            manaTmp += (rechargeTmp >= 0 ? 101 : 0);
           
            int damage = enemyDamage - armor <= 0 ? 1 : enemyDamage - armor;
            res = Math.min(res, simulation(lifeTmp - damage, manaTmp - 113, enemyLifeTmp, 5, poisonTmp, rechargeTmp, test1) + 113);
        }
       
        /* Poison */
        shieldTmp = shield;
        poisonTmp = poison;
        rechargeTmp = recharge;
        
        shieldTmp--;
        armor = shieldTmp >= 0 ? 7 : 0;
        poisonTmp--;
        enemyLifeTmp = enemyLife - (poisonTmp >= 0 ? 3 : 0);
        rechargeTmp--;
        manaTmp = mana + (rechargeTmp >= 0 ? 101 : 0);
        
        if ((manaTmp - 173) >= 0 && poisonTmp <= 0) {
            int lifeTmp = life;
            poisonTmp = 5;
            enemyLifeTmp -= (poisonTmp >= 0 ? 3 : 0);
            shieldTmp--;
            armor = shieldTmp >= 0 ? 7 : 0;
            rechargeTmp--; 
            manaTmp += (rechargeTmp >= 0 ? 101 : 0);
           
            int damage = enemyDamage - armor <= 0 ? 1 : enemyDamage - armor;
            res = Math.min(res, simulation(lifeTmp - damage, manaTmp - 173, enemyLifeTmp, shieldTmp, 5, rechargeTmp, test1) + 173);
        }
       
        /* Recharge */
        shieldTmp = shield;
        poisonTmp = poison;
        rechargeTmp = recharge;
        
        shieldTmp--;
        armor = shieldTmp >= 0 ? 7 : 0;
        poisonTmp--;
        enemyLifeTmp = enemyLife - (poisonTmp >= 0 ? 3 : 0);
        rechargeTmp--;
        manaTmp = mana + (rechargeTmp >= 0 ? 101 : 0);
        
        if ((manaTmp - 229) >= 0 && rechargeTmp <= 0) {
            int lifeTmp = life;

            shieldTmp--;
            armor = shieldTmp >= 0 ? 7 : 0;
            poisonTmp--;
            enemyLifeTmp -= poisonTmp >= 0 ? 3 : 0;
            
            int damage = enemyDamage - armor <= 0 ? 1 : enemyDamage - armor;
            res = Math.min(res, simulation(lifeTmp - damage, manaTmp - 229 + 101, enemyLifeTmp, shieldTmp, poisonTmp, 4, test1) + 229);
        }
       
        return res;
    }
}
