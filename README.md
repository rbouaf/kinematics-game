![GameDemo]()
## 420-204-RE - Vanier College

An educative simulation game using kinematics concepts.
## User Interface

### Menu Screen

Upon opening the game, the main menu screen presents two options: "Play" and "Options". Selecting "Play" navigates the user to the [Level Selection Screen](#level-selection-screen), while "Options" leads to the options screen.

### Level Selection Screen

This screen allows players to choose a level or return to the [Menu Screen](#menu-screen) via a "Back" button. Initially, only level 1 is accessible. Subsequent levels are unlocked upon successful completion of the preceding one.

### Shop Screen

The shop provides players with the opportunity to purchase in-game items by clicking their respective icons. The player's funds and inventory are managed by the `playerBankAndInventory.java` class. Three types of projectiles are available: grenades, Molotov cocktails, and bullets, each possessing unique properties. Grenades inflict high damage, Molotov cocktails have a larger hitbox but deal less damage, and bullets travel in a straight line due to a high initial velocity, offering greater precision at a higher cost. The shop introduces a strategic layer to the game, requiring players to manage their resources effectively. A "Start" button allows players to begin the selected level at any time.

### In-Game

The in-game interface displays the player's ship on the left and the enemy's ship on the right, separated by a significant distance. A vector originates from the player's ship and dynamically extends to the user's mouse cursor, indicating the intended initial velocity and angle of the shot. Clicking the mouse launches a projectile based on projectile motion principles. The objective is to deplete the enemies' health points to zero.

![Example from level 1](path/to/level1_screenshot.png)
*(Example from level 1)*

![Example from level 3](path/to/level3_screenshot.png)
*(Example from level 3)*

## Implementation Part-1: Algorithm and Application Logic

This section details the core logic governing the in-game mechanics. Class-specific functionalities are elaborated in [Implementation Part-2](#implementation-part-2-integration-of-individual-parts).

### Projectile Movement

-   Mouse movement triggers an event handler that continuously updates a vector extending from the player's ship to the cursor. This vector visually represents the projectile's initial velocity. A constraint is implemented to prevent excessively high initial velocities:

    ```java
    root.setOnMouseMoved((MouseEvent cursorLocation) -> {
        if (cursorLocation.getSceneX() <= 175 && cursorLocation.getSceneY() >= 175)
        {
            // Code to update the velocity vector
        }
    });
    ```

-   A mouse click (shoot action) triggers another event handler. The x and y components of the current velocity vector are captured and used to calculate the projectile's trajectory. The projectile's animation is handled within this event handler.

-   The `Timeline` class is employed to animate the projectile's parabolic path. `KeyFrame` objects define the starting and final coordinates of the projectile (the final x-coordinate is determined using kinematic equations), and an `Interpolator` continuously calculates the y-coordinate based on kinematic equations.

    ```java
    KeyValue xKV = new KeyValue(c.centerXProperty(), 75+xDistance); // xDistance is the calculated range.
    KeyValue yKV = new KeyValue(c.centerYProperty(), 100, new Interpolator() {
        @Override
        protected double curve(double t) {
            return -4*t*t + yComponent/10*t;
        }
    });
    KeyFrame xKF = new KeyFrame(Duration.millis(2000), xKV);
    KeyFrame yKF = new KeyFrame(Duration.millis(2000), yKV);
    // c is the projectile
    ```

    The `Interpolator` implements the equation for the y-coordinate as a function of time within the `curve()` method. The velocity scaling for different projectile types is adjusted accordingly. The created `KeyFrame` objects are then added to a `Timeline` and played. Further adjustments to scaling factors like acceleration and velocity are needed for optimal game balance.

### Collision Logic

-   Collision detection is implemented using `If...Then` statements. If the projectile's hitbox coordinates overlap with the enemy's or player's hitbox coordinates (defined rectangular regions for each entity in each level), a collision is registered, and the enemy's health points are reduced.

    ```java
    If (c.centerXProperty() >= enemy1.getX() && c.centerYProperty >= enemy1.getY() ...) {
        // Collision detected, reduce enemy health
    }
    ```

-   Different projectiles have varying properties: standard projectiles are unlimited and free but have the smallest hitbox and damage; grenades have a larger hitbox and higher damage; Molotov cocktails have the largest hitbox but lower damage; and bullets offer the highest precision.

-   Upon a successful hit, a health bar appears above the enemy, displaying their remaining health. A level is completed when all enemies' health points are depleted. The initial design included a more complex collision system with rebound effects using vectors, which was ultimately cut due to unforeseen circumstances (explained in [Challenges](#challenges)).

## Implementation Part-2: Integration of Individual Parts

The project's codebase is structured into several Java classes:

-   **`SceneController.java`:** This class manages the creation and retrieval of all game scenes. It contains public static methods that return the created `Scene` objects. This design allows for easy scene switching from the main class. For instance, to switch to the level selection screen:

    ```java
    stage.setScene(SceneController.levelSelection());
    ```

-   **`ButtonDatabase.java`:** This class serves as a repository for all `Button` objects used across different scenes. Buttons are declared as private static variables and accessed via public static getter methods. This approach ensures that buttons are accessible from any part of the program during runtime. Button properties (e.g., size) are dynamically set within the getter methods to address issues encountered when declaring them directly within the `SceneController`. Example:

    ```java
    public static Button getOptionButton() {
        ImageView options = new ImageView("URL_TO_OPTIONS_ICON");
        optionButton.setGraphic(options);
        return optionButton;
    }
    ```

-   **`Project.java`:** This is the main class of the application. It houses event handlers that manage user interactions, such as level selection:

    ```java
    ButtonDatabase.getLevel3Button().setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            SceneController.selectedLevel = 3;
            stage.setScene(SceneController.shop());
            stage.show();
        }
    });
    ```

    The in-game level scenes are instantiated directly within the main class due to their dynamic and continuous updates, unlike the static nature of other scenes managed by `SceneController`. All core game logic and algorithm implementations are handled within this class using event handlers.

-   **`playerBankAndInventory.java`:** This class manages the player's in-game currency and inventory. All data is stored as static variables, meaning it resets upon each program execution. Item purchases deduct funds and increment the corresponding item count. The three purchasable items are grenades, Molotov cocktails, and bullets. Due to the project's intended short play duration, features like saving or database integration were not implemented.

## Implementation Part-3: Project Reports

### Project Description

"The Sea Men" is an educational game and physics simulation designed to illustrate the principles of projectile motion and kinematics. Players assume the role of a ship captain tasked with defeating enemy ships by accurately launching cannonballs across the map. The game simulates real-world projectile behavior based on kinematic formulas.

### Wireframes

**Menu screen:**

![Menu Screen Wireframe](path/to/menu_wireframe.png)

**Options screen:**

![Options Screen Wireframe](path/to/options_wireframe.png)

**Level Selection Screen:**

![Level Selection Screen Wireframe](path/to/level_select_wireframe.png)

**Shop screen:**

![Shop Screen Wireframe](path/to/shop_wireframe.png)

**In-game screen:**

![In-game Screen Wireframe](path/to/ingame_wireframe.png)

### Diagrams

#### Use Case Diagram

![Use Case Diagram](path/to/use_case_diagram.png)

#### Class Diagram

![Class Diagram](path/to/class_diagram.png)


### Challenges

#### Coding Challenges

One of the initial hurdles was implementing seamless scene transitions and ensuring the reusability of scenes for "Back" button functionality. This was resolved by creating the `SceneController` class, as detailed in [Implementation Part-2](#implementation-part-2-integration-of-individual-parts).

Another challenge involved managing button accessibility across different parts of the application. The `ButtonDatabase` class, with its static button variables and getter methods, provided a solution, ensuring buttons were accessible throughout the program's runtime. An additional issue with this approach was the inability to directly modify button properties. This was addressed by setting button properties within their respective getter methods.

Implementing accurate kinematics within JavaFX proved challenging due to the platform's animation capabilities. After exploring various methods, the `Timeline` class was adopted. Fine-tuning the scaling of projectile velocity and translating real-world kinematic equations into functional in-game mechanics also required significant effort.

#### Unforeseen Challenges

A major setback was the unexpected and prolonged absence of one team member without any communication. This significantly disrupted the project's planned workflow and resource allocation. Despite this, the remaining team members adapted and successfully completed the core objectives, albeit with some compromises on additional features. This absence directly impacted the final product, necessitating the removal of planned functionalities such as a camera that tracks projectiles and a more sophisticated collision system with knockback and rebound effects, which were tasks assigned to the absent teammate.

In conclusion, the team effectively overcame numerous coding challenges. Some difficulties arose from the limitations of JavaFX for the type of game being developed. Reflecting on the experience, the team believes that using Python with the Pygame library would have streamlined development. However, at the project's inception, not all team members possessed Python proficiency.
