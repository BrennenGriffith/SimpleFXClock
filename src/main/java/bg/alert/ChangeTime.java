package bg.alert;

import bg.jfx.Controller;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static bg.jfx.Controller.TimeState.*;
import static bg.resources.Resources.*;
/**
 * A custom Alert dialog used to prompt the user to select their preferred time format.
 * Displays two buttons for the user to choose between military time or standard time.
 * @author Brennen Griffith
 */
public class ChangeTime extends Alert {
    /**
     * This button is used to select between military time or standard time.
     */
    private Button militaryTimeButton;
    /**
     * This button is used to select between military time or standard time.
     */
    private Button standardTimeButton;
    private TimeFormatSelection timeFormatSelection;
    private Text selectFormat;

    /**
     * Constructs a new ChangeTime dialog with the specified Controller object.
     * Sets the title and header text of the alert, initializes the content of
     * the dialog, and applies custom CSS styling to it.
     *
     * @param controller the Controller object to use for switching time format
     */

    public ChangeTime(Controller controller) {
        super(AlertType.NONE);
        initStyle(StageStyle.UNDECORATED);


        setHeaderText("Time Format Selection");


        initContent(controller);
        initCloseButton();

        setupCSS();
    }
    /**
     * Initializes the content of the ChangeTime dialog by creating two buttons
     * for the user to select between military time or standard time. Sets the
     * result of the dialog to the selected time format and hides the dialog.
     *
     * @param controller the Controller object to use for switching time format
     */
    private void initContent(Controller controller) {
        militaryTimeButton = new Button("Military Time");
        standardTimeButton = new Button("Standard Time");

        militaryTimeButton.setOnAction(event -> {
            timeFormatSelection = TimeFormatSelection.MILITARY_TIME;
            setResult(ButtonType.OK);

            controller.switchStates(MILITARY);

            hide();
        });

        standardTimeButton.setOnAction(event -> {
            timeFormatSelection = TimeFormatSelection.STANDARD_TIME;
            setResult(ButtonType.OK);
            controller.switchStates(STANDARD);
            hide();
        });

        HBox buttonsBox = new HBox(10, militaryTimeButton, standardTimeButton);
        buttonsBox.setAlignment(Pos.CENTER);
        selectFormat = new Text("Select the time format you prefer:");

        VBox content = new VBox(10, selectFormat, buttonsBox);

        content.setAlignment(Pos.CENTER);

        getDialogPane().setContent(content);
    }
    /**
     * Initializes the close button of the ChangeTime dialog by setting the
     * alert's button types list to only contain a close button.
     */
    private void initCloseButton()
    {
        getDialogPane().getButtonTypes().setAll(ButtonType.CLOSE);
    }

    /**
     * Applies custom CSS styling to the ChangeTime dialog, including a background
     * color, font color, and hyperlink color. It also adds an icon to the dialog
     * and sets the header text font size and style.
     */

    private void setupCSS() {
        getDialogPane().getStylesheets().add(CLOCK_VIEW_CSS);
        getDialogPane().getStyleClass().add("bg-about-background");
        militaryTimeButton.getStyleClass().add("bg-about-hyperLink");
        standardTimeButton.getStyleClass().add("bg-about-hyperLink");
        getDialogPane().lookupButton(ButtonType.CLOSE).getStyleClass().add("bg-about-hyperLink");
        getDialogPane().lookup(".header-panel").getStyleClass().add("bg-about-header");
        selectFormat.getStyleClass().add("bg-about-name");
        ((Stage)getDialogPane().getScene().getWindow()).getIcons().add(new Image(CLOCK_LOGO_PNG));
    }
    /**
     * Returns the current time format selection state of the application.
     * @return the current time format selection state
     */
    public TimeFormatSelection getTimeFormatSelection() {
        return timeFormatSelection;
    }
    /**
     * An enum representing the two possible time format selection states of the application:
     * MILITARY_TIME and STANDARD_TIME.
     */
    public enum TimeFormatSelection {
        MILITARY_TIME,
        STANDARD_TIME
    }
    /**
     * Returns the Military Time button.
     * @return the Military Time button
     */
    public Button getMilitaryTimeButton() {
        return militaryTimeButton;
    }
    /**
     * Returns the Standard Time button.
     * @return the Standard Time button
     */
    public Button getStandardTimeButton() {
        return standardTimeButton;
    }
}
