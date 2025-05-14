package com.example.finalProject;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class playerBankAndInventory {

    private static int money = 500;
    private static int noOfGrenades = 0;
    private static int noOfMolotov = 0;
    private static int noOfBullets = 0;

    private static Text inventoryDisplay = new Text(10, 10, "Grenade: 100$      Molotov Cocktail: 500$      Bullet: 800$\nINVENTORY: "+ playerBankAndInventory.getNoOfGrenades()+ " grenades, "+playerBankAndInventory.getNoOfMolotov()+ " Molotov cocktails, "+playerBankAndInventory.getNoOfBullets()+ " sniper bullets." );

    private static Text moneyDisplay = new Text(10, 10, "Money: "+ playerBankAndInventory.getMoney() +"$");

    public static Text getInventoryDisplay() {
        inventoryDisplay.setFont(Font.font("Courier", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        return inventoryDisplay;
    }

    public static void setInventoryDisplay(Text inventoryDisplay) {
        playerBankAndInventory.inventoryDisplay = inventoryDisplay;
    }

    public static Text getMoneyDisplay() {
        moneyDisplay.setFont(Font.font("Courier", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        return moneyDisplay;
    }

    public void setMoneyDisplay(Text moneyDisplay) {
        playerBankAndInventory.moneyDisplay = moneyDisplay;
    }

    public static void doTransactionGrenades(){
        if (playerBankAndInventory.getMoney()-100 < 0){
            System.out.println("Insufficient Funds, Broke ass Loser");
        }else{
            playerBankAndInventory.setMoney(playerBankAndInventory.getMoney()-100);
            playerBankAndInventory.setNoOfGrenades(playerBankAndInventory.getNoOfGrenades()+1);
            playerBankAndInventory.getMoneyDisplay().setText("Money: "+ playerBankAndInventory.getMoney() +"$");
            playerBankAndInventory.getInventoryDisplay().setText("Grenade: 100$      Molotov Cocktail: 500$      Bullet: 800$\nINVENTORY: "+ playerBankAndInventory.getNoOfGrenades()+ " grenades, "+playerBankAndInventory.getNoOfMolotov()+ " Molotov cocktails, "+playerBankAndInventory.getNoOfBullets()+ " sniper bullets.");
        }
    }

    public static void doTransactionMolotov(){
        if (playerBankAndInventory.getMoney()-500 < 0){
            System.out.println("Insufficient Funds, Broke ass Loser");
        }else{
            playerBankAndInventory.setMoney(playerBankAndInventory.getMoney()-500);
            playerBankAndInventory.setNoOfMolotov(playerBankAndInventory.getNoOfMolotov()+1);
            playerBankAndInventory.getMoneyDisplay().setText("Money: "+ playerBankAndInventory.getMoney() +"$");
            playerBankAndInventory.getInventoryDisplay().setText("Grenade: 100$      Molotov Cocktail: 500$      Bullet: 800$\nINVENTORY: "+ playerBankAndInventory.getNoOfGrenades()+ " grenades, "+playerBankAndInventory.getNoOfMolotov()+ " Molotov cocktails, "+playerBankAndInventory.getNoOfBullets()+ " sniper bullets.");
        }
    }
    public static void doTransactionBullets(){
        if (playerBankAndInventory.getMoney()-800 < 0){
            System.out.println("Insufficient Funds, Broke ass Loser");
        }else{
            playerBankAndInventory.setMoney(playerBankAndInventory.getMoney()-800);
            playerBankAndInventory.setNoOfBullets(playerBankAndInventory.getNoOfBullets()+1);
            playerBankAndInventory.getMoneyDisplay().setText("Money: "+ playerBankAndInventory.getMoney() +"$");
            playerBankAndInventory.getInventoryDisplay().setText("Grenade: 100$      Molotov Cocktail: 500$      Bullet: 800$\nINVENTORY: "+ playerBankAndInventory.getNoOfGrenades()+ " grenades, "+playerBankAndInventory.getNoOfMolotov()+ " Molotov cocktails, "+playerBankAndInventory.getNoOfBullets()+ " sniper bullets.");
        }
    }

    public static int getMoney() {
        return money;
    }

    public static void setMoney(int money) {
        playerBankAndInventory.money = money;
    }

    public static int getNoOfGrenades() {
        return noOfGrenades;
    }

    public static void setNoOfGrenades(int noOfGrenades) {
        playerBankAndInventory.noOfGrenades = noOfGrenades;
    }

    public static int getNoOfMolotov() {
        return noOfMolotov;
    }

    public static void setNoOfMolotov(int noOfMolotov) {
        playerBankAndInventory.noOfMolotov = noOfMolotov;
    }

    public static int getNoOfBullets() {
        return noOfBullets;
    }

    public static void setNoOfBullets(int noOfBullets) {
        playerBankAndInventory.noOfBullets = noOfBullets;
    }
}
