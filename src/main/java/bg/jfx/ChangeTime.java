package bg.jfx;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.HBox;

public class ChangeTime extends Alert {
    private Button militaryTimeButton;
    private Button standardTimeButton;
    private TimeFormatSelection timeFormatSelection;

    public ChangeTime(Controller controller) {
        super(AlertType.NONE);

        setTitle("Time Format Selection");
        setHeaderText("Choose Time Format");
        setContentText("Select the time format you prefer:");

        militaryTimeButton = new Button("Military Time");
        standardTimeButton = new Button("Standard Time");

        DialogPane dialogPane = getDialogPane();
        dialogPane.getButtonTypes().add(ButtonType.CANCEL);

        HBox buttonsBox = new HBox(10, militaryTimeButton, standardTimeButton);
        dialogPane.setContent(buttonsBox);

        militaryTimeButton.setOnAction(event -> {
            timeFormatSelection = TimeFormatSelection.MILITARY_TIME;
            setResult(ButtonType.OK);

            controller.switchStates();

            hide();
        });

        standardTimeButton.setOnAction(event -> {
            timeFormatSelection = TimeFormatSelection.STANDARD_TIME;
            setResult(ButtonType.OK);
            controller.switchStates();
            hide();
        });
    }

    public TimeFormatSelection getTimeFormatSelection() {
        return timeFormatSelection;
    }

    public enum TimeFormatSelection {
        MILITARY_TIME,
        STANDARD_TIME
    }
}
