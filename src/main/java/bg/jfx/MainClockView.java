package bg.jfx;

import static bg.resources.Resources.*;

import bg.nodes.BGMenuBar;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.StageStyle;

/**
 * The MainClockView class represents the GUI of the clock application.
 * It sets up the scene, initializes nodes, and adds JavaFX CSS to nodes.
 *
 * @author Brennen Griffith
 */
public class MainClockView {
    /**
     * Stage: from model
     */
    private Stage stage;
    /**
     * Creating a scene with a vbox inside
     * width: 500px, height 300px
     */
    private Scene scene;
    /**
     * Vertical Box to layout all nodes
     */
    private VBox vbox;


    /**
     * The Text object that displays the time.
     */
    private Text time;

    /**
     * The Text object that displays the AM/PM indicator.
     */
    private Text amPm;
    /**
     * The Text object that displays the title of the clock application.
     */
    private Text title = new Text("BG FX Clock");

    /**
     * The BGMenuBar object that represents the menu bar of the clock application.
     */
    private BGMenuBar menuBar;
    /**
     * The Button object that represents the exit button of the clock application.
     */
    private Button exitButton;
    /**
     * The Menu object that represents the options menu of the clock application.
     */
    private Menu options;
    /**
     * The MenuItem object that represents the edit menu item of the clock application.
     */
    private MenuItem edit = new Menu("Edit");
    /**
     * The MenuItem object that represents the about menu item of the clock application.
     */
    private MenuItem about = new Menu("About");

    /**
     * Constructor for View
     *
     * @param stage      from Model
     * @param controller from Model
     */
    public MainClockView(Stage stage, Controller controller) {
        this.stage = stage;
        //makes the stage not have a border
        stage.initStyle(StageStyle.UNDECORATED);


        //initialize the scene
        initScene();
        //set the scene
        stage.setScene(scene);
        //show the stage
        stage.show();
    }

    /**
     * Set's up defualt
     * Initializes
     */
    private void initScene() {
        //init and place-holders these will be dynamic
        time = new Text("00:00:00");
        amPm = new Text("AM");
        //init options
        options = new Menu("Options");
        //adds edit and about to options
        options.getItems().addAll(edit, about);
        //adds my custom menu bar
        menuBar = new BGMenuBar(options, exitButton = new Button("Exit"));
        //sets the scene
        scene = new Scene(vbox = new VBox(), 500, 300);
        //puts menu bar at the top
        vbox.getChildren().add(0, menuBar);
        //puts the title, time, and am/pm in the center
        vbox.getChildren().addAll(title, time, amPm);
        //adds css to nodes
        editProperties();
        //sets the focus to the scene removes the focus from options
        this.getScene().getRoot().requestFocus();
    }

    /**
     * Adds Javafx css to nodes
     * From the css file in the resource folder
     * also any minor properties before init is done
     */
    private void editProperties() {
        //get the css file and use it on the nodes
        scene.getStylesheets().add(CLOCK_VIEW_CSS);
        //set all the nodes to the css class
        title.getStyleClass().add("bg-title");
        time.getStyleClass().add("bg-time");
        amPm.getStyleClass().add("bg-am-pm");
        vbox.getStyleClass().add("bg-vbox");
        edit.getStyleClass().add("bg-edit");
        about.getStyleClass().add("bg-about");
        exitButton.getStyleClass().add("bg-menu-button");
        menuBar.getStyleClass().add("menu-bar");

        //set the alignment of the vbox
        vbox.setAlignment(Pos.TOP_CENTER);
        //can't resize the window
        stage.setResizable(false);
        //adds icon
        stage.getIcons().add(new Image(CLOCK_LOGO_PNG));
        //sets the title
        stage.setTitle("BG FX Clock");
    }

    /**
     * Return the stage
     * @return stage
     */
    public Stage getStage() {
        return stage;
    }
    /**
     * Return the scene
     * @return scene
     */
    public Scene getScene() {
        return scene;
    }
    /**
     * Return the vbox
     * @return vbox
     */
    public VBox getVbox() {
        return vbox;
    }
    /**
     * Return the time
     * @return time
     */
    public Text getTime() {
        return time;
    }
    /**
     * Return the amPm
     * @return amPm
     */
    public Text getAmPm() {
        return amPm;
    }
    /**
     * Return the title
     * @return title
     */
    public Text getTitle() {
        return title;
    }
    /**
     * Return the menuBar
     * @return menuBar
     */
    public MenuBar getMenuBar() {
        return menuBar;
    }
    /**
     * Return the options
     * @return options
     */
    public MenuItem getEdit() {
        return edit;
    }
    /**
     * Return the about
     * @return about
     */
    public MenuItem getAbout() {
        return about;
    }
    /**
     * Return the exitButton
     * @return exitButton
     */
    public Button getExitButton() {
        return exitButton;
    }
}


