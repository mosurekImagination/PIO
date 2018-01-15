import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Klasa Main z funkcją startującą aplikację
 */
public class Main extends Application  {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ViewController vc = new ViewController();
        vc.otworzOkno("OknoGlowne.fxml", vc.MALE_OKNO);
    }
}
