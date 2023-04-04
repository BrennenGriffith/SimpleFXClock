package bg.nodes;

import static bg.resources.Resources.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import javafx.scene.layout.StackPane;

/**
 * The BGMenuBar class is a custom implementation of the JavaFX MenuBar control.
 * It adds an options menu and an exit button to the menu bar, with custom styling
 * and positioning. The options menu can be customized by adding additional menu items.
 * The exit button can be accessed using the getExitButtonPane() method.
 * @author Brennen Griffith
 */
public class BGMenuBar extends MenuBar {
        private Menu optionsMenu;
        private StackPane exitButtonPane;

        /**
         * Constructs a new BGMenuBar with the specified options menu and exit button.
         *
         * @param optionsMenu the options menu to add to the menu bar
         * @param exitButton  the exit button to add to the menu bar
         */
        public BGMenuBar(Menu optionsMenu, Button exitButton) {
            super();
            this.optionsMenu = optionsMenu;
            // Initialize the menu bar and exit button
            initMenuBar();
            initExitButton(exitButton);
        }

        /**
         * Initializes the menu bar by setting its initial position and size, and adding
         * a custom stylesheet to the options menu. It also creates the options menu and adds
         * it to the menu bar.
         */
        private void initMenuBar() {
            // Set the initial position and size of the menu bar
            setTranslateX(-25);
            setUseSystemMenuBar(false);
            setMinWidth(0);
            setMaxWidth(450);



            // Apply a custom stylesheet to the options menu
            getStylesheets().add(CLOCK_VIEW_CSS);

            // Create the options menu and add it to the menu bar

            optionsMenu.getStyleClass().add("bg-options-menu");
            getMenus().add(optionsMenu);

            // Check the options menu's style class and items
            System.out.println("does optionsMenu have bg-options-menu style class? " + optionsMenu.getStyleClass().contains("bg-options-menu"));
            System.out.println("is optionsMenu getItems empty? " + optionsMenu.getItems().isEmpty());
        }

        /**
         * Initializes the exit button by adding it to a StackPane and aligning it to the right.
         * It also sets the size and position of the exit button and exitButtonPane, and adds
         * the exit button to the menu bar using the getChildren().add() method.
         *
         * @param exitButton the exit button to add to the menu bar
         */
        private void initExitButton(Button exitButton) {
            // Add the exit button to a StackPane and align it to the right
            exitButtonPane = new StackPane(exitButton);
            StackPane.setAlignment(exitButton, Pos.TOP_RIGHT);

            // Set the size and position of the exit button and exitButtonPane
            exitButtonPane.setMinWidth(100);
            exitButtonPane.setMinHeight(26);
            exitButton.setMinWidth(60);
            exitButton.setMinHeight(25);
            exitButtonPane.setLayoutX(505);

            // Add the exit button to the menu bar
            getChildren().add(exitButtonPane);

            // Set pick on bounds to false for the exit button and true for the menu bar
            exitButton.setPickOnBounds(false);
            this.setPickOnBounds(true);

        }

        /**
         * Returns the exit button pane, which is a StackPane object that contains
         * the exit button. This pane can be used to customize the exit button's size
         * and position using standard JavaFX control methods.
         *
         * @return the exit button pane
         */
        public StackPane getExitButtonPane() {
            return exitButtonPane;
        }


}

