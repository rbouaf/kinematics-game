/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.finalProject;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 *
 * @author 2052904
 */
public class ButtonDatabase {

    //opens level select
    private static Button playButton = new Button();
    private static Button optionButton = new Button();

    //opens shop before starting level
    private static Button level1Button = new Button();
    private static Button level2Button = new Button();
    private static Button level3Button = new Button();

    private static Button grenadeButton = new Button();
    private static Button molotovButton = new Button();
    private static Button bulletButton = new Button();
    
    //start button in shop (launches level)
    private static Button startButton = new Button();
    
    //dark mode buttons
    private static Button dark = new Button("DARK");
    private static Button light = new Button("LIGHT");


    public static Button getPlayButton() {
        ImageView play = new ImageView("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images/PlayButton.png");

        playButton.setGraphic(play);

        return playButton;
    }

    public static Button getOptionButton() {
        ImageView options = new ImageView("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images/OptionsButton.png");

        optionButton.setGraphic(options);
        return optionButton;
    }

    public static void setOptionButton(Button optionButton) {
        ButtonDatabase.optionButton = optionButton;


    }

    public void setPlayButton(Button playButton) {
        this.playButton = playButton;
    }

    public static Button getLevel1Button() {
        level1Button.setGraphic(new ImageView("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images/Level1Button.png"));
        return level1Button;
    }
    public static void setLevel1Button(Button level1Button) {
        ButtonDatabase.level1Button = level1Button;
    }

    public static Button getLevel2Button() {
        level2Button.setGraphic(new ImageView("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images/Level2Button.png"));
        return level2Button;
    }

    public static void setLevel2Button(Button level2Button) {
        ButtonDatabase.level2Button = level2Button;
    }

    public static Button getLevel3Button() {
        level3Button.setGraphic(new ImageView("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images/Level3Button.png"));
        return level3Button;
    }

    public static void setLevel3Button(Button level3Button) {
        ButtonDatabase.level3Button = level3Button;
    }

    public static Button getGrenadeButton() {
        grenadeButton.setGraphic(new ImageView("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images/GrenadeButton.png"));
        grenadeButton.setPrefSize(100, 100);
        return grenadeButton;
    }

    public static void setGrenadeButton(Button grenadeButton) {
        ButtonDatabase.grenadeButton = grenadeButton;
    }

    public static Button getMolotovButton() {
        molotovButton.setGraphic(new ImageView("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images/MolotovButton.png"));
        molotovButton.setPrefSize(100, 100);
        return molotovButton;
    }

    public static void setMolotovButton(Button molotovButton) {
        ButtonDatabase.molotovButton = molotovButton;
    }

    public static Button getBulletButton() {
        bulletButton.setGraphic(new ImageView("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images/BulletButton.png"));
        bulletButton.setPrefSize(100, 100);
        return bulletButton;
    }

    public static void setBulletButton(Button bulletButton) {
        ButtonDatabase.bulletButton = bulletButton;
    }

    public static Button getStartButton() {
        startButton.setGraphic(new ImageView("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images/PlayButton.png"));
        return startButton;
    }

    public static void setStartButton(Button startButton) {
        ButtonDatabase.startButton = startButton;
    }


    //TEXTFIELD DATABASE

    public static Button getDark() {
        dark.setPrefSize(100, 100);
        return dark;
    }

    public static void setDark(Button dark) {
        ButtonDatabase.dark = dark;
    }

    public static Button getLight() {
        light.setPrefSize(100, 100);
        return light;
    }

    public static void setLight(Button light) {
        ButtonDatabase.light = light;
    }



    
    
    
}
