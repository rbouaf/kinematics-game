
package com.example.finalProject;

import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author 2052904
 */
public class SceneController {

    public static int selectedLevel; //keeps track of selected level
    public static int selectedProjectile = 1; //designs which projectile will be used
    public static int passedLevel = 0; //keeps track of passed levels, used to lock advanced levels
    public static Line initialVector;
    public static boolean isDark;

    public static Scene menu() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(50);
        if (!isDark) {
            root.setBackground(new Background(
                    new BackgroundImage(
                            new Image("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images/MenuBG.gif"),
                            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                            new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
                            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
                    ))
            );
        } else {
            root.setBackground(new Background(
                    new BackgroundImage(
                            new Image("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images//images/MenuBGDark.png"),
                            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                            new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
                            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
                    ))
            );
        }
        ImageView title = new ImageView("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images/Title.gif");

        root.getChildren().add(title);
        root.getChildren().add(ButtonDatabase.getPlayButton());
        root.getChildren().add(ButtonDatabase.getOptionButton());
        Scene scene = new Scene(root, 800, 600);
        return scene;
    }

    public static Scene levelSelection() {
        BorderPane pane = new BorderPane();

        HBox levelsBox = new HBox(15);
        levelsBox.setAlignment(Pos.CENTER);

        levelsBox.getChildren().addAll(ButtonDatabase.getLevel1Button(), ButtonDatabase.getLevel2Button(), ButtonDatabase.getLevel3Button());

        VBox levelSelectionBox = new VBox(15);
        levelSelectionBox.setAlignment(Pos.CENTER);

        Text levelSelectionText = new Text(20, 20, "LEVEL SELECTION");
        levelSelectionText.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 20));
        levelSelectionText.setUnderline(true);

        Button backButton = new Button("BACK");

        levelSelectionBox.getChildren().addAll(levelSelectionText, levelsBox, backButton);

        pane.setCenter(levelSelectionBox);
        if (!isDark) {
            pane.setBackground(new Background(
                    new BackgroundImage(
                            new Image("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images//images/WaterBG.png"),
                            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                            new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
                            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
                    ))
            );
        } else {
            pane.setBackground(new Background(
                    new BackgroundImage(
                            new Image("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images//images/WaterBG.png"),
                            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                            new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
                            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
                    ))
            );
        }

        Scene scene = new Scene(pane, 800, 500);

        return scene;
    }

    public static Scene shop() {
        BorderPane pane = new BorderPane();

        HBox levelsBox = new HBox(15);
        levelsBox.setAlignment(Pos.CENTER);

        levelsBox.getChildren().addAll(ButtonDatabase.getGrenadeButton(), ButtonDatabase.getMolotovButton(), ButtonDatabase.getBulletButton());

        VBox levelSelectionBox = new VBox(15);
        levelSelectionBox.setAlignment(Pos.CENTER);

        Text shopBox = new Text(20, 20, "SHOP");
        shopBox.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 20));
        shopBox.setUnderline(true);

        levelSelectionBox.getChildren().addAll(shopBox, levelsBox, playerBankAndInventory.getInventoryDisplay(), playerBankAndInventory.getMoneyDisplay(), ButtonDatabase.getStartButton());

        pane.setCenter(levelSelectionBox);

        if (!isDark) {
            pane.setBackground(new Background(
                    new BackgroundImage(
                            new Image("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images//images/WaterBG.png"),
                            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                            new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
                            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
                    ))
            );
        } else {
            pane.setBackground(new Background(
                    new BackgroundImage(
                            new Image("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images//images/WaterBG.png"),
                            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                            new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
                            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
                    ))
            );
        }

        Scene scene = new Scene(pane, 800, 500);
        return scene;
    }

    public static Scene options() {
        Pane pane = new Pane();
        if (!isDark) {
            pane.setBackground(new Background(
                    new BackgroundImage(
                            new Image("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images//images/WaterBG.png"),
                            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                            new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
                            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
                    ))
            );
        } else {
            pane.setBackground(new Background(
                    new BackgroundImage(
                            new Image("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images/WaterBG.png"),
                            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                            new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
                            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
                    ))
            );
        }
        HBox hbox = new HBox();
        hbox.getChildren().addAll(ButtonDatabase.getDark(), ButtonDatabase.getLight());
        hbox.setLayoutX(300);
        hbox.setLayoutY(250);
        pane.getChildren().add(hbox);
        Scene scene = new Scene(pane, 800, 500);
        return scene;
    }

}
