package View;

import Model.Station;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

public class StationRentView extends PropertyRentView  {

    private @FXML
    Text PropertyName;

    private @FXML
    Text PurchasePrice;

    private @FXML
    Text MortgageValue;

    private @FXML
    Text Rent0;

    private @FXML
    Text Rent1;

    private @FXML
    Text Rent2;

    private @FXML
    Text Rent3;

    private @FXML
    ImageView image;

    public StationRentView(Station station) {
        setUpFxml();

        int[] rent = station.getRentList();
        setUpStation(station, rent);
    }

    private void setUpFxml() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/StationRent.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void setUpStation(Station station, int[] rent) {
        PropertyName.setText(station.getSpaceName());
        PurchasePrice.setText(station.getPrice()+"kr");
        MortgageValue.setText(station.getMortgage()+"kr");
        Rent0.setText(rent[0]+"kr");
        Rent1.setText(rent[1]+"kr");
        Rent2.setText(rent[2]+"kr");
        Rent3.setText(rent[3]+"kr");
        image.setImage(new Image("pic/Tram.png"));
    }

}
