package View;

import Model.Locale;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;

public class LocaleRentView extends AnchorPane {

    private @FXML
    Rectangle localeColor;

    private @FXML
    Text PropertyName;

    private @FXML
    Text PurchasePrice;

    private @FXML
    Text MortgageValue;

    private @FXML
    Text Rent0;

    private @FXML
    Text RentWithSet;

    private @FXML
    Text Rent1;

    private @FXML
    Text Rent2;

    private @FXML
    Text Rent3;

    private @FXML
    Text Rent4;

    private @FXML
    Text Rent5;

    private @FXML
    Text HouseCost;

    private @FXML
    Text HotelCost;

    public LocaleRentView(Locale locale) {
        setUpFxml();

        int[] rent = locale.getRentList();
        setUpLocale(locale, rent);
    }

    private void setUpFxml() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LocaleRent.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void setUpLocale(Locale locale, int[] rent) {
        PropertyName.setText(locale.getSpaceName());
        PurchasePrice.setText(locale.getPrice()+"kr");
        MortgageValue.setText(locale.getMortgage()+"kr");
        localeColor.setFill(Paint.valueOf(locale.getSectionColour()));
        Rent0.setText(rent[0]+"kr");
        RentWithSet.setText(2*rent[0]+"kr");
        Rent1.setText(rent[1]+"kr");
        Rent2.setText(rent[2]+"kr");
        Rent3.setText(rent[3]+"kr");
        Rent4.setText(rent[4]+"kr");
        Rent5.setText(rent[5]+"kr");
        HouseCost.setText(locale.getHouseCost()+"kr");
        HotelCost.setText(locale.getHouseCost()+"kr");
    }
}
