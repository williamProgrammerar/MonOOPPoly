import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Monopoly extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/ChalmersMonopoly.fxml"));
        initBoard(stage, root);
    }

    private void initBoard(Stage stage, Parent root) {
        stage.setTitle("Chalmers Monopoly");
        stage.setScene(new Scene(root));
        stage.setMinWidth(800);
        stage.setMinHeight(800);
        stage.show();
    }
}
