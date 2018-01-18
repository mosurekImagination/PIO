import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Klasa Main z funkcja startujaca aplikacje
 */
public class Main extends Application  {
    public static void main(String[] args) {
        launch(args);
    }

    /**
     *
     * Funckja poczatkowa aplikacji, otwierajaca okno glowne.
     */
    @Override
    public void start(Stage primaryStage) {
        ViewController vc = new ViewController();
        vc.otworzOkno("OknoGlowne.fxml", vc.MALE_OKNO);
    }
}
