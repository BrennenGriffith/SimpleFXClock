package bg.jfx;
import javafx.application.HostServices;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class About extends Alert {
    private Text name;
    private Hyperlink githubLink;
    private Hyperlink linkedInLink;

    private HostServices hostServices;

    public About(HostServices hostServices) {
        super(AlertType.NONE);

        this.hostServices = hostServices;

        setTitle("About");
        setHeaderText("About the Developer");

        name = new Text("Brennen Griffith ");
        githubLink = new Hyperlink("GitHub");
        githubLink.setOnAction(event -> openWebPage("https://github.com/BrennenGriffith"));
        linkedInLink = new Hyperlink("LinkedIn");
        linkedInLink.setOnAction(event -> openWebPage("https://www.linkedin.com/in/brennen-griffith-7ba849170/"));



        VBox content = new VBox(10, name, githubLink, linkedInLink);

        content.setAlignment(Pos.CENTER);
        getDialogPane().setContent(content);

        // Add a close button type
        ButtonType closeButtonType = new ButtonType("Close");
        getDialogPane().getButtonTypes().addAll(closeButtonType);

    }


    private void openWebPage(String url)
    {
        hostServices.showDocument(url);
    }

}
