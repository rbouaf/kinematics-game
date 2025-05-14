package com.example.finalProject;

public class HealthManager {

    private static int playerHealth = 100;
    private static int enemyHealthLevel1 = 100;
    private static int enemyHealthLevel2 = 350;
    private static int enemyHealthLevel3 = 1000;

    public static int damageToPlayer(int damageDealt) {
        HealthManager.playerHealth = playerHealth-damageDealt;
        return playerHealth-damageDealt;
    }
    public static int damageToEnemyLevel1(int damageDealt) {
        HealthManager.enemyHealthLevel1 = enemyHealthLevel1-damageDealt;
        return enemyHealthLevel1-damageDealt;
    }
    public static int damageToEnemyLevel2(int damageDealt) {
        HealthManager.enemyHealthLevel2 = enemyHealthLevel2-damageDealt;
        return enemyHealthLevel2-damageDealt;
    }
    public static int damageToEnemyLevel3(int damageDealt) {
        HealthManager.enemyHealthLevel3 = enemyHealthLevel3-damageDealt;
        return enemyHealthLevel3-damageDealt;
    }

    public static int getPlayerHealth() {
        return playerHealth;
    }

    public static void setPlayerHealth(int playerHealth) {
        HealthManager.playerHealth = playerHealth;
    }

    public static int getEnemyHealthLevel1() {
        return enemyHealthLevel1;
    }

    public static void setEnemyHealthLevel1(int enemyHealthLevel1) {
        HealthManager.enemyHealthLevel1 = enemyHealthLevel1;
    }

    public static int getEnemyHealthLevel2() {
        return enemyHealthLevel2;
    }

    public static void setEnemyHealthLevel2(int enemyHealthLevel2) {
        HealthManager.enemyHealthLevel2 = enemyHealthLevel2;
    }

    public static int getEnemyHealthLevel3() {
        return enemyHealthLevel3;
    }

    public static void setEnemyHealthLevel3(int enemyHealthLevel3) {
        HealthManager.enemyHealthLevel3 = enemyHealthLevel3;
    }
}
