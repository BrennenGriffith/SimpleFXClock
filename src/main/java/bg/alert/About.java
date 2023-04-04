package bg.alert;

import static bg.resources.Resources.*;

import javafx.application.HostServices;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * An About Alert that displays information about myself and provides links
 * to my GitHub and LinkedIn profiles.
 * @author Brennen Griffith
 */
public class About extends Alert {
    // instance variables
    private Text name;
    private Hyperlink githubLink;
    private Hyperlink linkedInLink;
    private HostServices hostServices;
    private ButtonType closeButton;

    /**
     * Constructs a new About dialog with the specified HostServices object.
     * This dialog displays information about myself and provides hyperlinks
     * to my GitHub and LinkedIn profiles.
     *
     * @param hostServices the HostServices object to use for opening web pages
     */
    public About(HostServices hostServices) {
        super(AlertType.NONE);
        this.hostServices = hostServices;
        initStyle(StageStyle.UNDECORATED);

        // Set the title and header text of the alert
        setTitle("About");
        setHeaderText("About the Developer");

        // Initialize the content and close button of the alert
        initContent();
        initCloseButton();

        // Apply CSS styling to the alert
        setupCSS();
    }

    /**
     * Initializes the content of the About dialog by creating a VBox with the developer's
     * name and hyperlinks to their GitHub and LinkedIn profiles. The VBox is set to be centered
     * within the dialog.
     */
    private void initContent() {
        // Create the name Text object and the GitHub and LinkedIn Hyperlink objects
        name = new Text("Brennen Griffith ");
        githubLink = new Hyperlink("GitHub");
        githubLink.setOnAction(event -> openWebPage("https://github.com/BrennenGriffith"));
        linkedInLink = new Hyperlink("LinkedIn");
        linkedInLink.setOnAction(event -> openWebPage("https://www.linkedin.com/in/brennen-griffith-7ba849170/"));

        // Add the name, GitHub, and LinkedIn objects to a VBox and set it to be centered
        VBox content = new VBox(10, name, githubLink, linkedInLink);
        content.setAlignment(Pos.CENTER);

        // Set the content of the alert to the VBox
        getDialogPane().setContent(content);
    }

    /**
     * Initializes the close button of the About dialog by adding a "Close" button type
     * to the alert's button types list.
     */
    private void initCloseButton() {
        closeButton= new ButtonType("Close");
        getDialogPane().getButtonTypes().addAll(closeButton);
    }

    /**
     * Applies custom CSS styling to the About dialog, including a background color, font
     * color, and hyperlinks color. It also adds an icon to the dialog and sets the header
     * text font size and style.
     */
    private void setupCSS() {
        getDialogPane().getStylesheets().add(CLOCK_VIEW_CSS);
        getDialogPane().getStyleClass().add("bg-about-background");
        name.getStyleClass().add("bg-about-name");
        githubLink.getStyleClass().add("bg-about-hyperLink");
        linkedInLink.getStyleClass().add("bg-about-hyperLink");
        Button cancelButtonNode = (Button) getDialogPane().lookupButton(closeButton);
        cancelButtonNode.getStyleClass().add("bg-about-hyperLink");
        getDialogPane().lookup(".header-panel").getStyleClass().add("bg-about-header");
        var stage = (Stage) getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(CLOCK_LOGO_PNG));
    }
    //this method opens the web page within the application
    private void openWebPage(String url) {
        hostServices.showDocument(url);
    }

}
