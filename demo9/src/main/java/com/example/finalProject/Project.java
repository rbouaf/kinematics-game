/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.finalProject;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author 2052904
 */
public class Project extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        // set menu scene
        stage.setResizable(false);
        stage.setScene(SceneController.menu());
        stage.show();

        // play button --> level selection scene
        ButtonDatabase.getPlayButton().setOnAction((ActionEvent event) -> {
            stage.setScene(SceneController.levelSelection());
            stage.show();
        });

        // level button --> shop scene
        ButtonDatabase.getLevel1Button().setOnAction((ActionEvent event) -> {
            if (SceneController.passedLevel >= 0) {
            SceneController.selectedLevel = 1;
            stage.setScene(SceneController.shop());
             }
        });
        ButtonDatabase.getLevel2Button().setOnAction((ActionEvent event) -> {
            if (SceneController.passedLevel >= 1) {
            SceneController.selectedLevel = 2;
            stage.setScene(SceneController.shop());
              }
        });
        ButtonDatabase.getLevel3Button().setOnAction((ActionEvent event) -> {
             if (SceneController.passedLevel >= 2) {
            SceneController.selectedLevel = 3;
            stage.setScene(SceneController.shop());
              }
            stage.show();
        });

        // options menu
        ButtonDatabase.getOptionButton().setOnAction((ActionEvent event) -> {
            stage.setScene(SceneController.options());
            stage.show();
        });

        //dark/light buttons
        ButtonDatabase.getDark().setOnAction((ActionEvent event) -> {
            SceneController.isDark = true;
            stage.setScene(SceneController.options());
            stage.show();
        });
        ButtonDatabase.getLight().setOnAction((ActionEvent event) -> {
            SceneController.isDark = false;
            stage.setScene(SceneController.options());
            stage.show();
        });

        // projectile shop buttons --> add to inventory
        ButtonDatabase.getGrenadeButton().setOnAction((ActionEvent event) -> {
            playerBankAndInventory.doTransactionGrenades();
        });

        ButtonDatabase.getMolotovButton().setOnAction((ActionEvent event) -> {
            playerBankAndInventory.doTransactionMolotov();
        });

        ButtonDatabase.getBulletButton().setOnAction((ActionEvent event) -> {
            playerBankAndInventory.doTransactionBullets();
        });

        //escape 
        stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ESCAPE) {
                    stage.setScene(SceneController.menu());
                    stage.show();
                }
            }
        });

        // start button --> starts game
        ButtonDatabase.getStartButton().setOnAction((ActionEvent event) -> {
            // level 1
            if (SceneController.selectedLevel == 1) {

                Pane root = new Pane();
                root.setPrefSize(1000, 400);
                root.setBackground(new Background(
                        new BackgroundImage(
                                new Image("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images/WaterBG.png"),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                                new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
                                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
                        ))
                );

                // water design
                Rectangle water = new Rectangle();
                water.setX(0);
                water.setY(280);
                water.setWidth(1000);
                water.setHeight(130);
                water.setFill(Color.rgb(139, 63, 155));
                root.getChildren().add(water);

                // player design
                ImageView player = new ImageView("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images/Boat.png");;
                player.setFitWidth(100);
                player.setFitHeight(80);
                player.setX(50);
                player.setY(300 - player.getFitHeight());
                root.getChildren().add(player);

                // initial projectile vector
                Line initialVector = new Line();
                initialVector.setStartX(75);
                initialVector.setStartY(275);
                initialVector.setEndX(175);
                initialVector.setEndY(175);
                root.getChildren().add(initialVector);

                // projectile selection
                Button ballBtn = new Button("Ball");
                Button grenadeBtn = new Button("Grenade");
                Button molotovBtn = new Button("Molotov");
                Button bulletBtn = new Button("Bullet");
                HBox buttonSelect = new HBox();
                buttonSelect.getChildren().addAll(ballBtn, grenadeBtn, molotovBtn, bulletBtn);
                root.getChildren().add(buttonSelect);

                // ball
                Circle c = new Circle(5);
                c.setCenterX(75);
                c.setCenterY(275);
                root.getChildren().add(c);

                //grenade
                ImageView grenade = new ImageView("https://pixelartmaker-data-78746291193.nyc3.digitaloceanspaces.com/image/a6a698052333e51.png"); //link picture here
                grenade.setVisible(false);
                grenade.setFitWidth(20);
                grenade.setFitHeight(20);
                grenade.setX(65);
                grenade.setY(265);
                root.getChildren().add(grenade);

                //molotov
                ImageView molotov = new ImageView("https://www.maxpixel.net/static/photo/1x/Explosive-Flame-Bottle-Fire-Molotov-Cocktail-157193.png"); //link picture here
                molotov.setVisible(false);
                molotov.setFitWidth(20);
                molotov.setFitHeight(20);
                molotov.setX(65);
                molotov.setY(265);
                root.getChildren().add(molotov);

                //bullet
                ImageView bullet = new ImageView("https://pixelartmaker-data-78746291193.nyc3.digitaloceanspaces.com/image/18e5c8c55771aa4.png"); //link picture here
                bullet.setVisible(false);
                bullet.setFitWidth(20);
                bullet.setFitHeight(20);
                bullet.setX(65);
                bullet.setY(265);
                root.getChildren().add(bullet);

                ballBtn.setOnAction((ActionEvent event1) -> {
                    SceneController.selectedProjectile = 1;
                    c.setVisible(true);
                    grenade.setVisible(false);
                    molotov.setVisible(false);
                    bullet.setVisible(false);
                });
                grenadeBtn.setOnAction((ActionEvent event1) -> {
                    SceneController.selectedProjectile = 2;
                    c.setVisible(false);
                    grenade.setVisible(true);
                    molotov.setVisible(false);
                    bullet.setVisible(false);
                });
                molotovBtn.setOnAction((ActionEvent event1) -> {
                    SceneController.selectedProjectile = 3;
                    c.setVisible(false);
                    grenade.setVisible(false);
                    molotov.setVisible(true);
                    bullet.setVisible(false);
                });
                bulletBtn.setOnAction((ActionEvent event1) -> {
                    SceneController.selectedProjectile = 4;
                    c.setVisible(false);
                    grenade.setVisible(false);
                    molotov.setVisible(false);
                    bullet.setVisible(true);
                });

                // enemy design
                ImageView enemy1 = new ImageView("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images/Boat.png");
                enemy1.setFitWidth(300);
                enemy1.setFitHeight(150);
                enemy1.setX(660);
                enemy1.setY(150);
                root.getChildren().add(enemy1);

                // show intial screen
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                // mouse movement handler --> initial velocity
                root.setOnMouseMoved((MouseEvent cursorLocation) -> {

                    // restrict mouse movement area (limit initial velocity)
                    if (cursorLocation.getSceneX() <= 175 && cursorLocation.getSceneY() >= 195) {

                        // update cursor coordinates
                        double mouseX = cursorLocation.getSceneX();
                        double mouseY = cursorLocation.getSceneY();

                        // draw initial vector line
                        initialVector.setStartX(75);
                        initialVector.setStartY(275);
                        initialVector.setEndX(mouseX);
                        initialVector.setEndY(mouseY);
                        stage.show();

                        // mouse click handler --> shoot projectile
                        root.setOnMouseClicked((MouseEvent click) -> {
                            switch (SceneController.selectedProjectile) {
                                case 1 -> {
                                    c.setCenterX(65);
                                    c.setCenterY(265);
                                    c.setVisible(true);
                                    // calculations for range and maxHeight --> for keyframes
                                    double xComponent = (click.getSceneX() - 75);
                                    double yComponent = (275 - click.getSceneY());
                                    double angle = (Math.atan(yComponent / xComponent));
                                    double u = Math.sqrt(xComponent * xComponent + yComponent * yComponent);
                                    double range = u * u * Math.sin(2 * angle) / 9.8;
                                    double maxHeight = yComponent * yComponent / (2 * 9.8);
                                    // timeline for projectile (animation)
                                    Timeline timeline = new Timeline();
                                    timeline.setCycleCount(1);
                                    timeline.setAutoReverse(false);
                                    // initial and final coordinates of projectile
                                    KeyValue xKV = new KeyValue(c.centerXProperty(), range);
                                    KeyValue yKV = new KeyValue(c.centerYProperty(), c.getCenterY() - maxHeight, new Interpolator() {
                                        @Override
                                        // interpolate from parabolic curve to update Y
                                        protected double curve(double t) {

                                            //distance between projectile and center of enemy
                                            double distance = Math.sqrt((Math.pow(c.getCenterX() - (enemy1.getX() + enemy1.getFitWidth() / 2), 2)) + (Math.pow(c.getCenterY() - (enemy1.getY() + enemy1.getFitHeight() / 2), 2)));
                                            if (distance <= 60) {
                                                System.out.println("HIT");
                                                c.setVisible(false);


                                                //Health////////////////////////

                                                Label enemyHealthLabelLevel1 = new Label(HealthManager.damageToEnemyLevel1(20) + "/100");
                                                enemyHealthLabelLevel1.setFont(Font.font(20));
                                                enemyHealthLabelLevel1.setTextFill(Color.RED);
                                                enemyHealthLabelLevel1.setBackground((new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))));
                                                HBox thing = new HBox();
                                                thing.getChildren().add(enemyHealthLabelLevel1);
                                                thing.setLayoutX(700);
                                                thing.setLayoutY(140);
                                                root.getChildren().add(thing);

                                                AnimationTimer enemyHealthAnimationTimerLevel1 = new AnimationTimer() {
                                                    double op = 1;

                                                    @Override
                                                    public void handle(long now) {
                                                        handlee();
                                                    }

                                                    private void handlee() {
                                                        //set the value of opacity
                                                        op -= 0.01;
                                                        enemyHealthLabelLevel1.opacityProperty().set(op);
                                                        if (op <= 0) {
                                                            stop();
                                                        }
                                                    }
                                                };
                                                enemyHealthAnimationTimerLevel1.start();

                                                //////////////////////////


                                                timeline.stop();
                                                if (HealthManager.getEnemyHealthLevel1() <= 20) {
                                                    SceneController.passedLevel++;
                                                    playerBankAndInventory.setMoney(playerBankAndInventory.getMoney() + 1000);
                                                    stage.setScene(SceneController.levelSelection());
                                                    stage.show();
                                                }
                                            }
                                            return -4 * (t - .5) * (t - .5) + 1;
                                        }
                                    });
                                    KeyFrame xKF = new KeyFrame(Duration.millis(2000), xKV);
                                    KeyFrame yKF = new KeyFrame(Duration.millis(2000), yKV);
                                    timeline.getKeyFrames().addAll(xKF, yKF);
                                    timeline.play();
                                }
                                case 2 -> {
                                    if (playerBankAndInventory.getNoOfGrenades() > 0) {
                                        playerBankAndInventory.setNoOfGrenades(playerBankAndInventory.getNoOfBullets() - 1);
                                        grenade.setX(65);
                                        grenade.setY(265);
                                        grenade.setVisible(true);

                                        // calculations for range and maxHeight --> for keyframes
                                        double xComponent = (click.getSceneX() - 75);
                                        double yComponent = (275 - click.getSceneY());
                                        System.out.println(xComponent);
                                        System.out.println(yComponent);
                                        double angle = (Math.atan(yComponent / xComponent));
                                        double u = Math.sqrt(xComponent * xComponent + yComponent * yComponent);
                                        double range = u * u * Math.sin(2 * angle) / 9.8;
                                        double maxHeight = yComponent * yComponent / (2 * 9.8);

                                        // timeline for projectile (animation)
                                        Timeline timeline = new Timeline();
                                        timeline.setCycleCount(1);
                                        timeline.setAutoReverse(false);

                                        // initial and final coordinates of projectile
                                        KeyValue xKV = new KeyValue(grenade.xProperty(), range);
                                        KeyValue yKV = new KeyValue(grenade.yProperty(), grenade.getY() - maxHeight, new Interpolator() {
                                            @Override
                                            // interpolate from parabolic curve to update Y
                                            protected double curve(double t) {

                                                //distance between projectile and center of enemy
                                                double distance = Math.sqrt((Math.pow(grenade.getX() - (enemy1.getX() + enemy1.getFitWidth() / 2), 2)) + (Math.pow(grenade.getY() - (enemy1.getY() + enemy1.getFitHeight() / 2), 2)));
                                                if (distance <= 100) System.out.println("HIT");
                                                c.setVisible(false);


                                                //Health////////////////////////

                                                Label enemyHealthLabelLevel1 = new Label(HealthManager.damageToEnemyLevel1(30) + "/100");
                                                enemyHealthLabelLevel1.setFont(Font.font(20));
                                                enemyHealthLabelLevel1.setTextFill(Color.RED);
                                                enemyHealthLabelLevel1.setBackground((new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))));
                                                HBox thing = new HBox();
                                                thing.getChildren().add(enemyHealthLabelLevel1);
                                                thing.setLayoutX(700);
                                                thing.setLayoutY(140);
                                                root.getChildren().add(thing);

                                                AnimationTimer enemyHealthAnimationTimerLevel1 = new AnimationTimer() {
                                                    double op = 1;

                                                    @Override
                                                    public void handle(long now) {
                                                        handlee();
                                                    }

                                                    private void handlee() {
                                                        //set the value of opacity
                                                        op -= 0.01;
                                                        enemyHealthLabelLevel1.opacityProperty().set(op);
                                                        if (op <= 0) {
                                                            stop();
                                                        }
                                                    }
                                                };
                                                enemyHealthAnimationTimerLevel1.start();

                                                //////////////////////////


                                                timeline.stop();
                                                if (HealthManager.getEnemyHealthLevel1() <= 20) {
                                                    SceneController.passedLevel++;
                                                    playerBankAndInventory.setMoney(playerBankAndInventory.getMoney() + 1000);
                                                    stage.setScene(SceneController.levelSelection());
                                                    stage.show();
                                                }
                                                return -4 * (t - .5) * (t - .5) + 1;
                                            }
                                        });
                                        KeyFrame xKF = new KeyFrame(Duration.millis(2000), xKV);
                                        KeyFrame yKF = new KeyFrame(Duration.millis(2000), yKV);
                                        timeline.getKeyFrames().addAll(xKF, yKF);
                                        timeline.play();
                                    }
                                }
                                case 3 -> {
                                    if (playerBankAndInventory.getNoOfMolotov() > 0) {
                                        playerBankAndInventory.setNoOfMolotov(playerBankAndInventory.getNoOfMolotov() - 1);
                                        molotov.setX(65);
                                        molotov.setY(265);
                                        molotov.setVisible(true);

                                        // calculations for range and maxHeight --> for keyframes
                                        double xComponent = (click.getSceneX() - 75);
                                        double yComponent = (275 - click.getSceneY());
                                        System.out.println(xComponent);
                                        System.out.println(yComponent);
                                        double angle = (Math.atan(yComponent / xComponent));
                                        double u = Math.sqrt(xComponent * xComponent + yComponent * yComponent);
                                        double range = u * u * Math.sin(2 * angle) / 9.8;
                                        double maxHeight = yComponent * yComponent / (2 * 9.8);

                                        // timeline for projectile (animation)
                                        Timeline timeline = new Timeline();
                                        timeline.setCycleCount(1);
                                        timeline.setAutoReverse(false);

                                        // initial and final coordinates of projectile
                                        KeyValue xKV = new KeyValue(molotov.xProperty(), range);
                                        KeyValue yKV = new KeyValue(molotov.yProperty(), molotov.getY() - maxHeight, new Interpolator() {
                                            @Override
                                            // interpolate from parabolic curve to update Y
                                            protected double curve(double t) {

                                                // collision detection
                                                /*old system
                                            if ((grenade.getX() + grenade.getFitWidth() >= enemy1.getX() - 50) && (grenade.getY() >= enemy1.getY() - 50)) {
                                                System.out.println("HIT");
                                                grenade.setVisible(false);
                                                timeline.stop();

                                            }
                                                 */
                                                //distance between projectile and center of enemy
                                                double distance = Math.sqrt((Math.pow(molotov.getX() - (enemy1.getX() + enemy1.getFitWidth() / 2), 2)) + (Math.pow(molotov.getY() - (enemy1.getY() + enemy1.getFitHeight() / 2), 2)));
                                                if (distance <= 200) System.out.println("HIT");
                                                c.setVisible(false);


                                                //Health////////////////////////

                                                Label enemyHealthLabelLevel1 = new Label(HealthManager.damageToEnemyLevel1(20) + "/100");
                                                enemyHealthLabelLevel1.setFont(Font.font(20));
                                                enemyHealthLabelLevel1.setTextFill(Color.RED);
                                                enemyHealthLabelLevel1.setBackground((new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))));
                                                HBox thing = new HBox();
                                                thing.getChildren().add(enemyHealthLabelLevel1);
                                                thing.setLayoutX(700);
                                                thing.setLayoutY(140);
                                                root.getChildren().add(thing);

                                                AnimationTimer enemyHealthAnimationTimerLevel1 = new AnimationTimer() {
                                                    double op = 1;

                                                    @Override
                                                    public void handle(long now) {
                                                        handlee();
                                                    }

                                                    private void handlee() {
                                                        //set the value of opacity
                                                        op -= 0.01;
                                                        enemyHealthLabelLevel1.opacityProperty().set(op);
                                                        if (op <= 0) {
                                                            stop();
                                                        }
                                                    }
                                                };
                                                enemyHealthAnimationTimerLevel1.start();

                                                //////////////////////////


                                                timeline.stop();
                                                if (HealthManager.getEnemyHealthLevel1() <= 20) {
                                                    SceneController.passedLevel++;
                                                    playerBankAndInventory.setMoney(playerBankAndInventory.getMoney() + 1000);
                                                    stage.setScene(SceneController.levelSelection());
                                                    stage.show();
                                                }
                                                return -4 * (t - .5) * (t - .5) + 1;
                                            }
                                        });
                                        KeyFrame xKF = new KeyFrame(Duration.millis(2000), xKV);
                                        KeyFrame yKF = new KeyFrame(Duration.millis(2000), yKV);
                                        timeline.getKeyFrames().addAll(xKF, yKF);
                                        timeline.play();
                                    }
                                }
                                case 4 -> {
                                    if (playerBankAndInventory.getNoOfBullets() > 1) {
                                        playerBankAndInventory.setNoOfBullets(playerBankAndInventory.getNoOfBullets() - 1);
                                        bullet.setX(65);
                                        bullet.setY(265);
                                        bullet.setVisible(true);

                                        // calculations for range and maxHeight --> for keyframes
                                        double xComponent = (click.getSceneX() - 75) * 1.6; //velocity increased for bullet
                                        double yComponent = (275 - click.getSceneY()) * 1.6;
                                        System.out.println(xComponent);
                                        System.out.println(yComponent);
                                        double angle = (Math.atan(yComponent / xComponent));
                                        double u = Math.sqrt(xComponent * xComponent + yComponent * yComponent);
                                        double range = u * u * Math.sin(2 * angle) / 9.8;
                                        double maxHeight = yComponent * yComponent / (2 * 9.8);

                                        // timeline for projectile (animation)
                                        Timeline timeline = new Timeline();
                                        timeline.setCycleCount(1);
                                        timeline.setAutoReverse(false);

                                        // initial and final coordinates of projectile
                                        KeyValue xKV = new KeyValue(bullet.xProperty(), range);
                                        KeyValue yKV = new KeyValue(bullet.yProperty(), bullet.getY() - maxHeight, new Interpolator() {
                                            @Override
                                            // interpolate from parabolic curve to update Y
                                            protected double curve(double t) {

                                                //collision
                                                //distance between projectile and center of enemy
                                                double distance = Math.sqrt((Math.pow(bullet.getX() - (enemy1.getX() + enemy1.getFitWidth() / 2), 2)) + (Math.pow(bullet.getY() - (enemy1.getY() + enemy1.getFitHeight() / 2), 2)));
                                                if (distance <= 100) System.out.println("HIT");
                                                c.setVisible(false);


                                                //Health////////////////////////

                                                Label enemyHealthLabelLevel1 = new Label(HealthManager.damageToEnemyLevel1(20) + "/100");
                                                enemyHealthLabelLevel1.setFont(Font.font(20));
                                                enemyHealthLabelLevel1.setTextFill(Color.RED);
                                                enemyHealthLabelLevel1.setBackground((new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))));
                                                HBox thing = new HBox();
                                                thing.getChildren().add(enemyHealthLabelLevel1);
                                                thing.setLayoutX(700);
                                                thing.setLayoutY(140);
                                                root.getChildren().add(thing);

                                                AnimationTimer enemyHealthAnimationTimerLevel1 = new AnimationTimer() {
                                                    double op = 1;

                                                    @Override
                                                    public void handle(long now) {
                                                        handlee();
                                                    }

                                                    private void handlee() {
                                                        //set the value of opacity
                                                        op -= 0.01;
                                                        enemyHealthLabelLevel1.opacityProperty().set(op);
                                                        if (op <= 0) {
                                                            stop();
                                                        }
                                                    }
                                                };
                                                enemyHealthAnimationTimerLevel1.start();

                                                //////////////////////////


                                                timeline.stop();
                                                if (HealthManager.getEnemyHealthLevel1() <= 20) {
                                                    SceneController.passedLevel++;
                                                    playerBankAndInventory.setMoney(playerBankAndInventory.getMoney() + 1000);
                                                    stage.setScene(SceneController.levelSelection());
                                                    stage.show();
                                                }
                                                return -4 * (t - .5) * (t - .5) + 1;
                                            }
                                        });
                                        KeyFrame xKF = new KeyFrame(Duration.millis(2000), xKV);
                                        KeyFrame yKF = new KeyFrame(Duration.millis(2000), yKV);
                                        timeline.getKeyFrames().addAll(xKF, yKF);
                                        timeline.play();
                                    }
                                }
                                default -> {
                                }
                            }
                        }
                        );
                    }
                });

            } else if (SceneController.selectedLevel == 2) {
                Pane root = new Pane();
                root.setPrefSize(1000, 400);
                root.setBackground(new Background(
                        new BackgroundImage(
                                new Image("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images/WaterBG.png"),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                                new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
                                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
                        ))
                );

                // water design
                Rectangle water = new Rectangle();
                water.setX(0);
                water.setY(280);
                water.setWidth(1000);
                water.setHeight(130);
                water.setFill(Color.rgb(110, 112, 197));
                root.getChildren().add(water);

                // player design
                ImageView player = new ImageView("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images/Boat.png");;
                player.setFitWidth(100);
                player.setFitHeight(80);
                player.setX(50);
                player.setY(300 - player.getFitHeight());
                root.getChildren().add(player);

                // initial projectile vector
                Line initialVector = new Line();
                initialVector.setStartX(75);
                initialVector.setStartY(275);
                initialVector.setEndX(175);
                initialVector.setEndY(175);
                root.getChildren().add(initialVector);

                // projectile selection
                Button ballBtn = new Button("Ball");
                Button grenadeBtn = new Button("Grenade");
                Button molotovBtn = new Button("Molotov");
                Button bulletBtn = new Button("Bullet");
                HBox buttonSelect = new HBox();
                buttonSelect.getChildren().addAll(ballBtn, grenadeBtn, molotovBtn, bulletBtn);
                root.getChildren().add(buttonSelect);

                // ball
                Circle c = new Circle(5);
                c.setCenterX(75);
                c.setCenterY(275);
                root.getChildren().add(c);

                //grenade
                ImageView grenade = new ImageView("https://pixelartmaker-data-78746291193.nyc3.digitaloceanspaces.com/image/a6a698052333e51.png"); //link picture here
                grenade.setVisible(false);
                grenade.setFitWidth(20);
                grenade.setFitHeight(20);
                grenade.setX(65);
                grenade.setY(265);
                root.getChildren().add(grenade);

                //molotov
                ImageView molotov = new ImageView("https://www.maxpixel.net/static/photo/1x/Explosive-Flame-Bottle-Fire-Molotov-Cocktail-157193.png"); //link picture here
                molotov.setVisible(false);
                molotov.setFitWidth(20);
                molotov.setFitHeight(20);
                molotov.setX(65);
                molotov.setY(265);
                root.getChildren().add(molotov);

                //bullet
                ImageView bullet = new ImageView("https://pixelartmaker-data-78746291193.nyc3.digitaloceanspaces.com/image/18e5c8c55771aa4.png"); //link picture here
                bullet.setVisible(false);
                bullet.setFitWidth(20);
                bullet.setFitHeight(20);
                bullet.setX(65);
                bullet.setY(265);
                root.getChildren().add(bullet);

                ballBtn.setOnAction((ActionEvent event1) -> {
                    SceneController.selectedProjectile = 1;
                    c.setVisible(true);
                    grenade.setVisible(false);
                    molotov.setVisible(false);
                    bullet.setVisible(false);
                });
                grenadeBtn.setOnAction((ActionEvent event1) -> {
                    SceneController.selectedProjectile = 2;
                    c.setVisible(false);
                    grenade.setVisible(true);
                    molotov.setVisible(false);
                    bullet.setVisible(false);
                });
                molotovBtn.setOnAction((ActionEvent event1) -> {
                    SceneController.selectedProjectile = 3;
                    c.setVisible(false);
                    grenade.setVisible(false);
                    molotov.setVisible(true);
                    bullet.setVisible(false);
                });
                bulletBtn.setOnAction((ActionEvent event1) -> {
                    SceneController.selectedProjectile = 4;
                    c.setVisible(false);
                    grenade.setVisible(false);
                    molotov.setVisible(false);
                    bullet.setVisible(true);
                });

                // enemy design
                ImageView enemy1 = new ImageView("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images/Boat.png");
                enemy1.setFitWidth(300);
                enemy1.setFitHeight(250);
                enemy1.setX(660);
                enemy1.setY(50);
                root.getChildren().add(enemy1);

                // show intial screen
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                // mouse movement handler --> initial velocity
                root.setOnMouseMoved((MouseEvent cursorLocation) -> {

                    // restrict mouse movement area (limit initial velocity)
                    if (cursorLocation.getSceneX() <= 175 && cursorLocation.getSceneY() >= 195) {

                        // update cursor coordinates
                        double mouseX = cursorLocation.getSceneX();
                        double mouseY = cursorLocation.getSceneY();

                        // draw initial vector line
                        initialVector.setStartX(75);
                        initialVector.setStartY(275);
                        initialVector.setEndX(mouseX);
                        initialVector.setEndY(mouseY);
                        stage.show();

                        // mouse click handler --> shoot projectile
                        root.setOnMouseClicked((MouseEvent click) -> {
                            switch (SceneController.selectedProjectile) {
                                case 1 -> {
                                    c.setCenterX(65);
                                    c.setCenterY(265);
                                    c.setVisible(true);
                                    // calculations for range and maxHeight --> for keyframes
                                    double xComponent = (click.getSceneX() - 75);
                                    double yComponent = (275 - click.getSceneY());
                                    double angle = (Math.atan(yComponent / xComponent));
                                    double u = Math.sqrt(xComponent * xComponent + yComponent * yComponent);
                                    double range = u * u * Math.sin(2 * angle) / 9.8;
                                    double maxHeight = yComponent * yComponent / (2 * 9.8);
                                    // timeline for projectile (animation)
                                    Timeline timeline = new Timeline();
                                    timeline.setCycleCount(1);
                                    timeline.setAutoReverse(false);
                                    // initial and final coordinates of projectile
                                    KeyValue xKV = new KeyValue(c.centerXProperty(), range);
                                    KeyValue yKV = new KeyValue(c.centerYProperty(), c.getCenterY() - maxHeight, new Interpolator() {
                                        @Override
                                        // interpolate from parabolic curve to update Y
                                        protected double curve(double t) {

                                            //distance between projectile and center of enemy
                                            double distance = Math.sqrt((Math.pow(c.getCenterX() - (enemy1.getX() + enemy1.getFitWidth() / 2), 2)) + (Math.pow(c.getCenterY() - (enemy1.getY() + enemy1.getFitHeight() / 2), 2)));
                                            if (distance <= 60) System.out.println("HIT");
                                            c.setVisible(false);


                                            //Health////////////////////////

                                            Label enemyHealthLabelLevel2 = new Label(HealthManager.damageToEnemyLevel2(20) + "/350");
                                            enemyHealthLabelLevel2.setFont(Font.font(20));
                                            enemyHealthLabelLevel2.setTextFill(Color.RED);
                                            enemyHealthLabelLevel2.setBackground((new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))));
                                            HBox thing = new HBox();
                                            thing.getChildren().add(enemyHealthLabelLevel2);
                                            thing.setLayoutX(700);
                                            thing.setLayoutY(140);
                                            root.getChildren().add(thing);

                                            AnimationTimer enemyHealthAnimationTimerLevel2 = new AnimationTimer() {
                                                double op = 1;

                                                @Override
                                                public void handle(long now) {
                                                    handlee();
                                                }

                                                private void handlee() {
                                                    //set the value of opacity
                                                    op -= 0.01;
                                                    enemyHealthLabelLevel2.opacityProperty().set(op);
                                                    if (op <= 0) {
                                                        stop();
                                                    }
                                                }
                                            };
                                            enemyHealthAnimationTimerLevel2.start();

                                            //////////////////////////


                                            timeline.stop();
                                            if (HealthManager.getEnemyHealthLevel2() <= 20) {
                                                SceneController.passedLevel++;
                                                playerBankAndInventory.setMoney(playerBankAndInventory.getMoney() + 1000);
                                                stage.setScene(SceneController.levelSelection());
                                                stage.show();
                                            }
                                            return -4 * (t - .5) * (t - .5) + 1;
                                        }
                                    });
                                    KeyFrame xKF = new KeyFrame(Duration.millis(2000), xKV);
                                    KeyFrame yKF = new KeyFrame(Duration.millis(2000), yKV);
                                    timeline.getKeyFrames().addAll(xKF, yKF);
                                    timeline.play();
                                }
                                case 2 -> {
                                    if (playerBankAndInventory.getNoOfGrenades() > 0) {
                                        playerBankAndInventory.setNoOfGrenades(playerBankAndInventory.getNoOfBullets() - 1);
                                        grenade.setX(65);
                                        grenade.setY(265);
                                        grenade.setVisible(true);

                                        // calculations for range and maxHeight --> for keyframes
                                        double xComponent = (click.getSceneX() - 75);
                                        double yComponent = (275 - click.getSceneY());
                                        System.out.println(xComponent);
                                        System.out.println(yComponent);
                                        double angle = (Math.atan(yComponent / xComponent));
                                        double u = Math.sqrt(xComponent * xComponent + yComponent * yComponent);
                                        double range = u * u * Math.sin(2 * angle) / 9.8;
                                        double maxHeight = yComponent * yComponent / (2 * 9.8);

                                        // timeline for projectile (animation)
                                        Timeline timeline = new Timeline();
                                        timeline.setCycleCount(1);
                                        timeline.setAutoReverse(false);

                                        // initial and final coordinates of projectile
                                        KeyValue xKV = new KeyValue(grenade.xProperty(), range);
                                        KeyValue yKV = new KeyValue(grenade.yProperty(), grenade.getY() - maxHeight, new Interpolator() {
                                            @Override
                                            // interpolate from parabolic curve to update Y
                                            protected double curve(double t) {

                                                // collision detection
                                                /*old system
                                            if ((grenade.getX() + grenade.getFitWidth() >= enemy1.getX() - 50) && (grenade.getY() >= enemy1.getY() - 50)) {
                                                System.out.println("HIT");
                                                grenade.setVisible(false);
                                                timeline.stop();

                                            }
                                                 */
                                                //distance between projectile and center of enemy
                                                double distance = Math.sqrt((Math.pow(grenade.getX() - (enemy1.getX() + enemy1.getFitWidth() / 2), 2)) + (Math.pow(grenade.getY() - (enemy1.getY() + enemy1.getFitHeight() / 2), 2)));
                                                if (distance <= 100) System.out.println("HIT");
                                                c.setVisible(false);


                                                //Health////////////////////////

                                                Label enemyHealthLabelLevel2 = new Label(HealthManager.damageToEnemyLevel2(20) + "/350");
                                                enemyHealthLabelLevel2.setFont(Font.font(20));
                                                enemyHealthLabelLevel2.setTextFill(Color.RED);
                                                enemyHealthLabelLevel2.setBackground((new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))));
                                                HBox thing = new HBox();
                                                thing.getChildren().add(enemyHealthLabelLevel2);
                                                thing.setLayoutX(700);
                                                thing.setLayoutY(140);
                                                root.getChildren().add(thing);

                                                AnimationTimer enemyHealthAnimationTimerLevel2 = new AnimationTimer() {
                                                    double op = 1;

                                                    @Override
                                                    public void handle(long now) {
                                                        handlee();
                                                    }

                                                    private void handlee() {
                                                        //set the value of opacity
                                                        op -= 0.01;
                                                        enemyHealthLabelLevel2.opacityProperty().set(op);
                                                        if (op <= 0) {
                                                            stop();
                                                        }
                                                    }
                                                };
                                                enemyHealthAnimationTimerLevel2.start();

                                                //////////////////////////


                                                timeline.stop();
                                                if (HealthManager.getEnemyHealthLevel2() <= 20) {
                                                    SceneController.passedLevel++;
                                                    playerBankAndInventory.setMoney(playerBankAndInventory.getMoney() + 1000);
                                                    stage.setScene(SceneController.levelSelection());
                                                    stage.show();
                                                }
                                                return -4 * (t - .5) * (t - .5) + 1;
                                            }
                                        });
                                        KeyFrame xKF = new KeyFrame(Duration.millis(2000), xKV);
                                        KeyFrame yKF = new KeyFrame(Duration.millis(2000), yKV);
                                        timeline.getKeyFrames().addAll(xKF, yKF);
                                        timeline.play();
                                    }
                                }
                                case 3 -> {
                                    if (playerBankAndInventory.getNoOfMolotov() > 0) {
                                        playerBankAndInventory.setNoOfMolotov(playerBankAndInventory.getNoOfMolotov() - 1);
                                        molotov.setX(65);
                                        molotov.setY(265);
                                        molotov.setVisible(true);

                                        // calculations for range and maxHeight --> for keyframes
                                        double xComponent = (click.getSceneX() - 75);
                                        double yComponent = (275 - click.getSceneY());
                                        System.out.println(xComponent);
                                        System.out.println(yComponent);
                                        double angle = (Math.atan(yComponent / xComponent));
                                        double u = Math.sqrt(xComponent * xComponent + yComponent * yComponent);
                                        double range = u * u * Math.sin(2 * angle) / 9.8;
                                        double maxHeight = yComponent * yComponent / (2 * 9.8);

                                        // timeline for projectile (animation)
                                        Timeline timeline = new Timeline();
                                        timeline.setCycleCount(1);
                                        timeline.setAutoReverse(false);

                                        // initial and final coordinates of projectile
                                        KeyValue xKV = new KeyValue(molotov.xProperty(), range);
                                        KeyValue yKV = new KeyValue(molotov.yProperty(), molotov.getY() - maxHeight, new Interpolator() {
                                            @Override
                                            // interpolate from parabolic curve to update Y
                                            protected double curve(double t) {

                                                // collision detection
                                                /*old system
                                            if ((grenade.getX() + grenade.getFitWidth() >= enemy1.getX() - 50) && (grenade.getY() >= enemy1.getY() - 50)) {
                                                System.out.println("HIT");
                                                grenade.setVisible(false);
                                                timeline.stop();

                                            }
                                                 */
                                                //distance between projectile and center of enemy
                                                double distance = Math.sqrt((Math.pow(molotov.getX() - (enemy1.getX() + enemy1.getFitWidth() / 2), 2)) + (Math.pow(molotov.getY() - (enemy1.getY() + enemy1.getFitHeight() / 2), 2)));
                                                if (distance <= 200) System.out.println("HIT");
                                                c.setVisible(false);


                                                //Health////////////////////////

                                                Label enemyHealthLabelLevel2 = new Label(HealthManager.damageToEnemyLevel2(20) + "/350");
                                                enemyHealthLabelLevel2.setFont(Font.font(20));
                                                enemyHealthLabelLevel2.setTextFill(Color.RED);
                                                enemyHealthLabelLevel2.setBackground((new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))));
                                                HBox thing = new HBox();
                                                thing.getChildren().add(enemyHealthLabelLevel2);
                                                thing.setLayoutX(700);
                                                thing.setLayoutY(140);
                                                root.getChildren().add(thing);

                                                AnimationTimer enemyHealthAnimationTimerLevel2 = new AnimationTimer() {
                                                    double op = 1;

                                                    @Override
                                                    public void handle(long now) {
                                                        handlee();
                                                    }

                                                    private void handlee() {
                                                        //set the value of opacity
                                                        op -= 0.01;
                                                        enemyHealthLabelLevel2.opacityProperty().set(op);
                                                        if (op <= 0) {
                                                            stop();
                                                        }
                                                    }
                                                };
                                                enemyHealthAnimationTimerLevel2.start();

                                                //////////////////////////


                                                timeline.stop();
                                                if (HealthManager.getEnemyHealthLevel2() <= 20) {
                                                    SceneController.passedLevel++;
                                                    playerBankAndInventory.setMoney(playerBankAndInventory.getMoney() + 1000);
                                                    stage.setScene(SceneController.levelSelection());
                                                    stage.show();
                                                }
                                                return -4 * (t - .5) * (t - .5) + 1;
                                            }
                                        });
                                        KeyFrame xKF = new KeyFrame(Duration.millis(2000), xKV);
                                        KeyFrame yKF = new KeyFrame(Duration.millis(2000), yKV);
                                        timeline.getKeyFrames().addAll(xKF, yKF);
                                        timeline.play();
                                    }
                                }
                                case 4 -> {
                                    if (playerBankAndInventory.getNoOfBullets() > 1) {
                                        playerBankAndInventory.setNoOfBullets(playerBankAndInventory.getNoOfBullets() - 1);
                                        bullet.setX(65);
                                        bullet.setY(265);
                                        bullet.setVisible(true);

                                        // calculations for range and maxHeight --> for keyframes
                                        double xComponent = (click.getSceneX() - 75) * 1.6; //velocity increased for bullet
                                        double yComponent = (275 - click.getSceneY()) * 1.6;
                                        System.out.println(xComponent);
                                        System.out.println(yComponent);
                                        double angle = (Math.atan(yComponent / xComponent));
                                        double u = Math.sqrt(xComponent * xComponent + yComponent * yComponent);
                                        double range = u * u * Math.sin(2 * angle) / 9.8;
                                        double maxHeight = yComponent * yComponent / (2 * 9.8);

                                        // timeline for projectile (animation)
                                        Timeline timeline = new Timeline();
                                        timeline.setCycleCount(1);
                                        timeline.setAutoReverse(false);

                                        // initial and final coordinates of projectile
                                        KeyValue xKV = new KeyValue(bullet.xProperty(), range);
                                        KeyValue yKV = new KeyValue(bullet.yProperty(), bullet.getY() - maxHeight, new Interpolator() {
                                            @Override
                                            // interpolate from parabolic curve to update Y
                                            protected double curve(double t) {

                                                //collision
                                                //distance between projectile and center of enemy
                                                double distance = Math.sqrt((Math.pow(bullet.getX() - (enemy1.getX() + enemy1.getFitWidth() / 2), 2)) + (Math.pow(bullet.getY() - (enemy1.getY() + enemy1.getFitHeight() / 2), 2)));
                                                if (distance <= 100) System.out.println("HIT");
                                                c.setVisible(false);


                                                //Health////////////////////////

                                                Label enemyHealthLabelLevel2 = new Label(HealthManager.damageToEnemyLevel2(20) + "/350");
                                                enemyHealthLabelLevel2.setFont(Font.font(20));
                                                enemyHealthLabelLevel2.setTextFill(Color.RED);
                                                enemyHealthLabelLevel2.setBackground((new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))));
                                                HBox thing = new HBox();
                                                thing.getChildren().add(enemyHealthLabelLevel2);
                                                thing.setLayoutX(700);
                                                thing.setLayoutY(140);
                                                root.getChildren().add(thing);

                                                AnimationTimer enemyHealthAnimationTimerLevel2 = new AnimationTimer() {
                                                    double op = 1;

                                                    @Override
                                                    public void handle(long now) {
                                                        handlee();
                                                    }

                                                    private void handlee() {
                                                        //set the value of opacity
                                                        op -= 0.01;
                                                        enemyHealthLabelLevel2.opacityProperty().set(op);
                                                        if (op <= 0) {
                                                            stop();
                                                        }
                                                    }
                                                };
                                                enemyHealthAnimationTimerLevel2.start();

                                                //////////////////////////


                                                timeline.stop();
                                                if (HealthManager.getEnemyHealthLevel2() <= 20) {
                                                    SceneController.passedLevel++;
                                                    playerBankAndInventory.setMoney(playerBankAndInventory.getMoney() + 1000);
                                                    stage.setScene(SceneController.levelSelection());
                                                    stage.show();
                                                }
                                                return -4 * (t - .5) * (t - .5) + 1;
                                            }
                                        });
                                        KeyFrame xKF = new KeyFrame(Duration.millis(2000), xKV);
                                        KeyFrame yKF = new KeyFrame(Duration.millis(2000), yKV);
                                        timeline.getKeyFrames().addAll(xKF, yKF);
                                        timeline.play();
                                    }
                                }
                                default -> {
                                }
                            }
                        }
                        );
                    }
                });

            } else if (SceneController.selectedLevel == 3) {

                Pane root = new Pane();
                root.setPrefSize(1000, 400);
                root.setBackground(new Background(
                        new BackgroundImage(
                                new Image("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images/WaterBG.png"),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                                new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
                                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
                        ))
                );

                // water design
                Rectangle water = new Rectangle();
                water.setX(0);
                water.setY(280);
                water.setWidth(1000);
                water.setHeight(130);
                water.setFill(Color.rgb(180, 120, 231));
                root.getChildren().add(water);

                // player design
                ImageView player = new ImageView("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images/Boat.png");
                player.setFitWidth(100);
                player.setFitHeight(80);
                player.setX(50);
                player.setY(300 - player.getFitHeight());
                root.getChildren().add(player);

                // initial projectile vector
                Line initialVector = new Line();
                initialVector.setStartX(75);
                initialVector.setStartY(275);
                initialVector.setEndX(175);
                initialVector.setEndY(175);
                root.getChildren().add(initialVector);

                // projectile selection
                Button ballBtn = new Button("Ball");
                Button grenadeBtn = new Button("Grenade");
                Button molotovBtn = new Button("Molotov");
                Button bulletBtn = new Button("Bullet");
                HBox buttonSelect = new HBox();
                buttonSelect.getChildren().addAll(ballBtn, grenadeBtn, molotovBtn, bulletBtn);
                root.getChildren().add(buttonSelect);

                // ball
                Circle c = new Circle(5);
                c.setCenterX(75);
                c.setCenterY(275);
                root.getChildren().add(c);

                //grenade
                ImageView grenade = new ImageView("https://pixelartmaker-data-78746291193.nyc3.digitaloceanspaces.com/image/a6a698052333e51.png"); //link picture here
                grenade.setVisible(false);
                grenade.setFitWidth(20);
                grenade.setFitHeight(20);
                grenade.setX(65);
                grenade.setY(265);
                root.getChildren().add(grenade);

                //molotov
                ImageView molotov = new ImageView("https://www.maxpixel.net/static/photo/1x/Explosive-Flame-Bottle-Fire-Molotov-Cocktail-157193.png"); //link picture here
                molotov.setVisible(false);
                molotov.setFitWidth(20);
                molotov.setFitHeight(20);
                molotov.setX(65);
                molotov.setY(265);
                root.getChildren().add(molotov);

                //bullet
                ImageView bullet = new ImageView("https://pixelartmaker-data-78746291193.nyc3.digitaloceanspaces.com/image/18e5c8c55771aa4.png"); //link picture here
                bullet.setVisible(false);
                bullet.setFitWidth(20);
                bullet.setFitHeight(20);
                bullet.setX(65);
                bullet.setY(265);
                root.getChildren().add(bullet);

                ballBtn.setOnAction((ActionEvent event1) -> {
                    SceneController.selectedProjectile = 1;
                    c.setVisible(true);
                    grenade.setVisible(false);
                    molotov.setVisible(false);
                    bullet.setVisible(false);
                });
                grenadeBtn.setOnAction((ActionEvent event1) -> {
                    SceneController.selectedProjectile = 2;
                    c.setVisible(false);
                    grenade.setVisible(true);
                    molotov.setVisible(false);
                    bullet.setVisible(false);
                });
                molotovBtn.setOnAction((ActionEvent event1) -> {
                    SceneController.selectedProjectile = 3;
                    c.setVisible(false);
                    grenade.setVisible(false);
                    molotov.setVisible(true);
                    bullet.setVisible(false);
                });
                bulletBtn.setOnAction((ActionEvent event1) -> {
                    SceneController.selectedProjectile = 4;
                    c.setVisible(false);
                    grenade.setVisible(false);
                    molotov.setVisible(false);
                    bullet.setVisible(true);
                });

                // enemy design
                ImageView enemy1 = new ImageView("file:C:/Users/User/OneDrive/Developments/IdeaProjects/FinalUpdate/demo9/src/main/resources/images/FinalBoss.png");
                enemy1.setFitWidth(400);
                enemy1.setFitHeight(200);
                enemy1.setX(600);
                enemy1.setY(100);
                root.getChildren().add(enemy1);

                // show intial screen
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                // mouse movement handler --> initial velocity
                root.setOnMouseMoved((MouseEvent cursorLocation) -> {

                    // restrict mouse movement area (limit initial velocity)
                    if (cursorLocation.getSceneX() <= 175 && cursorLocation.getSceneY() >= 195) {

                        // update cursor coordinates
                        double mouseX = cursorLocation.getSceneX();
                        double mouseY = cursorLocation.getSceneY();

                        // draw initial vector line
                        initialVector.setStartX(75);
                        initialVector.setStartY(275);
                        initialVector.setEndX(mouseX);
                        initialVector.setEndY(mouseY);
                        stage.show();

                        // mouse click handler --> shoot projectile
                        root.setOnMouseClicked((MouseEvent click) -> {
                            switch (SceneController.selectedProjectile) {
                                case 1 -> {
                                    c.setCenterX(65);
                                    c.setCenterY(265);
                                    c.setVisible(true);
                                    // calculations for range and maxHeight --> for keyframes
                                    double xComponent = (click.getSceneX() - 75);
                                    double yComponent = (275 - click.getSceneY());
                                    double angle = (Math.atan(yComponent / xComponent));
                                    double u = Math.sqrt(xComponent * xComponent + yComponent * yComponent);
                                    double range = u * u * Math.sin(2 * angle) / 9.8;
                                    double maxHeight = yComponent * yComponent / (2 * 9.8);
                                    // timeline for projectile (animation)
                                    Timeline timeline = new Timeline();
                                    timeline.setCycleCount(1);
                                    timeline.setAutoReverse(false);
                                    // initial and final coordinates of projectile
                                    KeyValue xKV = new KeyValue(c.centerXProperty(), range);
                                    KeyValue yKV = new KeyValue(c.centerYProperty(), c.getCenterY() - maxHeight, new Interpolator() {
                                        @Override
                                        // interpolate from parabolic curve to update Y
                                        protected double curve(double t) {

                                            //distance between projectile and center of enemy
                                            double distance = Math.sqrt((Math.pow(c.getCenterX() - (enemy1.getX() + enemy1.getFitWidth() / 2), 2)) + (Math.pow(c.getCenterY() - (enemy1.getY() + enemy1.getFitHeight() / 2), 2)));
                                            if (distance <= 60) System.out.println("HIT");
                                            c.setVisible(false);


                                            //Health////////////////////////

                                            Label enemyHealthLabelLevel3 = new Label(HealthManager.damageToEnemyLevel3(20) + "/1000");
                                            enemyHealthLabelLevel3.setFont(Font.font(20));
                                            enemyHealthLabelLevel3.setTextFill(Color.RED);
                                            enemyHealthLabelLevel3.setBackground((new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))));
                                            HBox thing = new HBox();
                                            thing.getChildren().add(enemyHealthLabelLevel3);
                                            thing.setLayoutX(700);
                                            thing.setLayoutY(140);
                                            root.getChildren().add(thing);

                                            AnimationTimer enemyHealthAnimationTimerLevel3 = new AnimationTimer() {
                                                double op = 1;

                                                @Override
                                                public void handle(long now) {
                                                    handlee();
                                                }

                                                private void handlee() {
                                                    //set the value of opacity
                                                    op -= 0.01;
                                                    enemyHealthLabelLevel3.opacityProperty().set(op);
                                                    if (op <= 0) {
                                                        stop();
                                                    }
                                                }
                                            };
                                            enemyHealthAnimationTimerLevel3.start();

                                            //////////////////////////


                                            timeline.stop();
                                            if (HealthManager.getEnemyHealthLevel3() <= 20) {
                                                SceneController.passedLevel++;
                                                playerBankAndInventory.setMoney(playerBankAndInventory.getMoney() + 1000);
                                                stage.setScene(SceneController.levelSelection());
                                                stage.show();
                                            }
                                            return -4 * (t - .5) * (t - .5) + 1;
                                        }
                                    });
                                    KeyFrame xKF = new KeyFrame(Duration.millis(2000), xKV);
                                    KeyFrame yKF = new KeyFrame(Duration.millis(2000), yKV);
                                    timeline.getKeyFrames().addAll(xKF, yKF);
                                    timeline.play();
                                }
                                case 2 -> {
                                    if (playerBankAndInventory.getNoOfGrenades() > 0) {
                                        playerBankAndInventory.setNoOfGrenades(playerBankAndInventory.getNoOfBullets() - 1);
                                        grenade.setX(65);
                                        grenade.setY(265);
                                        grenade.setVisible(true);

                                        // calculations for range and maxHeight --> for keyframes
                                        double xComponent = (click.getSceneX() - 75);
                                        double yComponent = (275 - click.getSceneY());
                                        System.out.println(xComponent);
                                        System.out.println(yComponent);
                                        double angle = (Math.atan(yComponent / xComponent));
                                        double u = Math.sqrt(xComponent * xComponent + yComponent * yComponent);
                                        double range = u * u * Math.sin(2 * angle) / 9.8;
                                        double maxHeight = yComponent * yComponent / (2 * 9.8);

                                        // timeline for projectile (animation)
                                        Timeline timeline = new Timeline();
                                        timeline.setCycleCount(1);
                                        timeline.setAutoReverse(false);

                                        // initial and final coordinates of projectile
                                        KeyValue xKV = new KeyValue(grenade.xProperty(), range);
                                        KeyValue yKV = new KeyValue(grenade.yProperty(), grenade.getY() - maxHeight, new Interpolator() {
                                            @Override
                                            // interpolate from parabolic curve to update Y
                                            protected double curve(double t) {

                                                // collision detection
                                                /*old system
                                            if ((grenade.getX() + grenade.getFitWidth() >= enemy1.getX() - 50) && (grenade.getY() >= enemy1.getY() - 50)) {
                                                System.out.println("HIT");
                                                grenade.setVisible(false);
                                                timeline.stop();

                                            }
                                                 */
                                                //distance between projectile and center of enemy
                                                double distance = Math.sqrt((Math.pow(grenade.getX() - (enemy1.getX() + enemy1.getFitWidth() / 2), 2)) + (Math.pow(grenade.getY() - (enemy1.getY() + enemy1.getFitHeight() / 2), 2)));
                                                if (distance <= 100) System.out.println("HIT");
                                                c.setVisible(false);


                                                //Health////////////////////////

                                                Label enemyHealthLabelLevel3 = new Label(HealthManager.damageToEnemyLevel3(20) + "/1000");
                                                enemyHealthLabelLevel3.setFont(Font.font(20));
                                                enemyHealthLabelLevel3.setTextFill(Color.RED);
                                                enemyHealthLabelLevel3.setBackground((new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))));
                                                HBox thing = new HBox();
                                                thing.getChildren().add(enemyHealthLabelLevel3);
                                                thing.setLayoutX(700);
                                                thing.setLayoutY(140);
                                                root.getChildren().add(thing);

                                                AnimationTimer enemyHealthAnimationTimerLevel3 = new AnimationTimer() {
                                                    double op = 1;

                                                    @Override
                                                    public void handle(long now) {
                                                        handlee();
                                                    }

                                                    private void handlee() {
                                                        //set the value of opacity
                                                        op -= 0.01;
                                                        enemyHealthLabelLevel3.opacityProperty().set(op);
                                                        if (op <= 0) {
                                                            stop();
                                                        }
                                                    }
                                                };
                                                enemyHealthAnimationTimerLevel3.start();

                                                //////////////////////////


                                                timeline.stop();
                                                if (HealthManager.getEnemyHealthLevel3() <= 20) {
                                                    SceneController.passedLevel++;
                                                    playerBankAndInventory.setMoney(playerBankAndInventory.getMoney() + 1000);
                                                    stage.setScene(SceneController.levelSelection());
                                                    stage.show();
                                                }
                                                return -4 * (t - .5) * (t - .5) + 1;
                                            }
                                        });
                                        KeyFrame xKF = new KeyFrame(Duration.millis(2000), xKV);
                                        KeyFrame yKF = new KeyFrame(Duration.millis(2000), yKV);
                                        timeline.getKeyFrames().addAll(xKF, yKF);
                                        timeline.play();
                                    }
                                }
                                case 3 -> {
                                    if (playerBankAndInventory.getNoOfMolotov() > 0) {
                                        playerBankAndInventory.setNoOfMolotov(playerBankAndInventory.getNoOfMolotov() - 1);
                                        molotov.setX(65);
                                        molotov.setY(265);
                                        molotov.setVisible(true);

                                        // calculations for range and maxHeight --> for keyframes
                                        double xComponent = (click.getSceneX() - 75);
                                        double yComponent = (275 - click.getSceneY());
                                        System.out.println(xComponent);
                                        System.out.println(yComponent);
                                        double angle = (Math.atan(yComponent / xComponent));
                                        double u = Math.sqrt(xComponent * xComponent + yComponent * yComponent);
                                        double range = u * u * Math.sin(2 * angle) / 9.8;
                                        double maxHeight = yComponent * yComponent / (2 * 9.8);

                                        // timeline for projectile (animation)
                                        Timeline timeline = new Timeline();
                                        timeline.setCycleCount(1);
                                        timeline.setAutoReverse(false);

                                        // initial and final coordinates of projectile
                                        KeyValue xKV = new KeyValue(molotov.xProperty(), range);
                                        KeyValue yKV = new KeyValue(molotov.yProperty(), molotov.getY() - maxHeight, new Interpolator() {
                                            @Override
                                            // interpolate from parabolic curve to update Y
                                            protected double curve(double t) {

                                                // collision detection
                                                /*old system
                                            if ((grenade.getX() + grenade.getFitWidth() >= enemy1.getX() - 50) && (grenade.getY() >= enemy1.getY() - 50)) {
                                                System.out.println("HIT");
                                                grenade.setVisible(false);
                                                timeline.stop();

                                            }
                                                 */
                                                //distance between projectile and center of enemy
                                                double distance = Math.sqrt((Math.pow(molotov.getX() - (enemy1.getX() + enemy1.getFitWidth() / 2), 2)) + (Math.pow(molotov.getY() - (enemy1.getY() + enemy1.getFitHeight() / 2), 2)));
                                                if (distance <= 200) System.out.println("HIT");
                                                c.setVisible(false);


                                                //Health////////////////////////

                                                Label enemyHealthLabelLevel3 = new Label(HealthManager.damageToEnemyLevel3(20) + "/1000");
                                                enemyHealthLabelLevel3.setFont(Font.font(20));
                                                enemyHealthLabelLevel3.setTextFill(Color.RED);
                                                enemyHealthLabelLevel3.setBackground((new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))));
                                                HBox thing = new HBox();
                                                thing.getChildren().add(enemyHealthLabelLevel3);
                                                thing.setLayoutX(700);
                                                thing.setLayoutY(140);
                                                root.getChildren().add(thing);

                                                AnimationTimer enemyHealthAnimationTimerLevel3 = new AnimationTimer() {
                                                    double op = 1;

                                                    @Override
                                                    public void handle(long now) {
                                                        handlee();
                                                    }

                                                    private void handlee() {
                                                        //set the value of opacity
                                                        op -= 0.01;
                                                        enemyHealthLabelLevel3.opacityProperty().set(op);
                                                        if (op <= 0) {
                                                            stop();
                                                        }
                                                    }
                                                };
                                                enemyHealthAnimationTimerLevel3.start();

                                                //////////////////////////


                                                timeline.stop();
                                                if (HealthManager.getEnemyHealthLevel3() <= 20) {
                                                    SceneController.passedLevel++;
                                                    playerBankAndInventory.setMoney(playerBankAndInventory.getMoney() + 1000);
                                                    stage.setScene(SceneController.levelSelection());
                                                    stage.show();
                                                }
                                                return -4 * (t - .5) * (t - .5) + 1;
                                            }
                                        });
                                        KeyFrame xKF = new KeyFrame(Duration.millis(2000), xKV);
                                        KeyFrame yKF = new KeyFrame(Duration.millis(2000), yKV);
                                        timeline.getKeyFrames().addAll(xKF, yKF);
                                        timeline.play();
                                    }
                                }
                                case 4 -> {
                                    if (playerBankAndInventory.getNoOfBullets() > 1) {
                                        playerBankAndInventory.setNoOfBullets(playerBankAndInventory.getNoOfBullets() - 1);
                                        bullet.setX(65);
                                        bullet.setY(265);
                                        bullet.setVisible(true);

                                        // calculations for range and maxHeight --> for keyframes
                                        double xComponent = (click.getSceneX() - 75) * 1.6; //velocity increased for bullet
                                        double yComponent = (275 - click.getSceneY()) * 1.6;
                                        System.out.println(xComponent);
                                        System.out.println(yComponent);
                                        double angle = (Math.atan(yComponent / xComponent));
                                        double u = Math.sqrt(xComponent * xComponent + yComponent * yComponent);
                                        double range = u * u * Math.sin(2 * angle) / 9.8;
                                        double maxHeight = yComponent * yComponent / (2 * 9.8);

                                        // timeline for projectile (animation)
                                        Timeline timeline = new Timeline();
                                        timeline.setCycleCount(1);
                                        timeline.setAutoReverse(false);

                                        // initial and final coordinates of projectile
                                        KeyValue xKV = new KeyValue(bullet.xProperty(), range);
                                        KeyValue yKV = new KeyValue(bullet.yProperty(), bullet.getY() - maxHeight, new Interpolator() {
                                            @Override
                                            // interpolate from parabolic curve to update Y
                                            protected double curve(double t) {

                                                //collision
                                                //distance between projectile and center of enemy
                                                double distance = Math.sqrt((Math.pow(bullet.getX() - (enemy1.getX() + enemy1.getFitWidth() / 2), 2)) + (Math.pow(bullet.getY() - (enemy1.getY() + enemy1.getFitHeight() / 2), 2)));
                                                if (distance <= 100) System.out.println("HIT");
                                                c.setVisible(false);


                                                //Health////////////////////////

                                                Label enemyHealthLabelLevel3 = new Label(HealthManager.damageToEnemyLevel3(20) + "/1000");
                                                enemyHealthLabelLevel3.setFont(Font.font(20));
                                                enemyHealthLabelLevel3.setTextFill(Color.RED);
                                                enemyHealthLabelLevel3.setBackground((new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))));
                                                HBox thing = new HBox();
                                                thing.getChildren().add(enemyHealthLabelLevel3);
                                                thing.setLayoutX(700);
                                                thing.setLayoutY(140);
                                                root.getChildren().add(thing);

                                                AnimationTimer enemyHealthAnimationTimerLevel3 = new AnimationTimer() {
                                                    double op = 1;

                                                    @Override
                                                    public void handle(long now) {
                                                        handlee();
                                                    }

                                                    private void handlee() {
                                                        //set the value of opacity
                                                        op -= 0.01;
                                                        enemyHealthLabelLevel3.opacityProperty().set(op);
                                                        if (op <= 0) {
                                                            stop();
                                                        }
                                                    }
                                                };
                                                enemyHealthAnimationTimerLevel3.start();

                                                //////////////////////////


                                                timeline.stop();
                                                if (HealthManager.getEnemyHealthLevel3() <= 20) {
                                                    SceneController.passedLevel++;
                                                    playerBankAndInventory.setMoney(playerBankAndInventory.getMoney() + 1000);
                                                    stage.setScene(SceneController.levelSelection());
                                                    stage.show();
                                                }
                                                return -4 * (t - .5) * (t - .5) + 1;
                                            }
                                        });
                                        KeyFrame xKF = new KeyFrame(Duration.millis(2000), xKV);
                                        KeyFrame yKF = new KeyFrame(Duration.millis(2000), yKV);
                                        timeline.getKeyFrames().addAll(xKF, yKF);
                                        timeline.play();
                                    }
                                }
                                default -> {
                                }
                            }
                        }
                        );
                    }
                });
            }
        });
    }
}
