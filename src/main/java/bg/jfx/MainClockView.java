package bg.jfx;


import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * Clock View User GUI
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

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public MenuItem getEdit() {
        return edit;
    }

    public MenuItem getAbout() {
        return about;
    }

    /**
     * Text that'll appear in the gui
     */
    private Text time, amPm, title = new Text("BG FX Clock");

    MenuBar menuBar;

    Menu options = new Menu("Options");
    MenuItem edit = new Menu("Edit"), about = new Menu("About");

    /**
     * Constructor for View
     * @param stage from Model
     * @param controller from Model
     */
    public MainClockView(Stage stage, Controller controller)
    {
        this.stage = stage;
        edit.setOnAction( e ->{
            System.out.println("test");
        });


        //initialize the scene
        initScene();

        stage.setScene(scene);

        stage.show();
    }

    /**
     * Set's up defualt
     * Initializes
     */
    private void initScene()
    {
        //init and place-holders these will be dynamic
        time = new Text("00:00:00");
        amPm = new Text("AM");

        //adds menu
        menuBar = new MenuBar();
        options.getItems().addAll(edit, about);
        menuBar.getMenus().addAll(options);



        scene = new Scene(vbox = new VBox(),500,300);

        vbox.getChildren().addAll(menuBar, title, time, amPm);

        editProperties();

    }

    /**
     * Adds Javafx css to nodes
     * From the css file in the resource folder
     * also any minor properties before init is done
     */
    private void editProperties()
    {
        //get the css file and use it on the nodes
        scene.getStylesheets().add("ClockView.css");

        title.getStyleClass().add("bg-title");
        time.getStyleClass().add("bg-time");
        amPm.getStyleClass().add("bg-am-pm");
        vbox.getStyleClass().add("bg-vbox");
        edit.getStyleClass().add("bg-edit");
        about.getStyleClass().add("bg-about");

        vbox.setAlignment(Pos.TOP_CENTER);


        stage.setResizable(false);
        //adds icon
        stage.getIcons().add(new javafx.scene.image.Image("file:src/main/resources/ClockLogo.png"));

        stage.setTitle("BG FX Clock");



    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }

    public VBox getVbox() {
        return vbox;
    }

    public Text getTime() {
        return time;
    }

    public Text getAmPm() {
        return amPm;
    }

    public Text getTitle() {
        return title;
    }
}


