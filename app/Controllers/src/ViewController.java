import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewController {

    @FXML
    Button closeButton;

    public final int DUZE_OKNO = 1;
    public final int MALE_OKNO = 2;

    public Object otworzOkno(String resource, int typ)
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(resource));
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = loader.load();
            int width;
            int height;
            if (typ == DUZE_OKNO) {
                width = 908;
                height = 580;
            } else {
                width = 555;
                height = 341;
                primaryStage.initModality(Modality.APPLICATION_MODAL);
            }
            Scene scene = new Scene(root, width, height);

            primaryStage.setTitle("FXML Welcome");
            primaryStage.setScene(scene);
            primaryStage.show();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return loader.getController();
    }
    @FXML
    protected void zamknijOkno(ActionEvent event)
    {
        if(closeButton != null) {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }
    }

    protected void wyswietlKomunikat(String komunikat)
    {
        KomunikatController controller = (KomunikatController)otworzOkno("komunikat.fxml", MALE_OKNO);
    }
}
