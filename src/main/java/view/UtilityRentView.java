package view;

import model.Utility;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;

import java.io.IOException;

public class UtilityRentView extends PropertyRentView {

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

    public UtilityRentView(Utility utility) {
        setUpFxml();

        int[] rent = utility.getRentList();
        setUpUtility(utility, rent);
    }

    private void setUpFxml() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/UtilityRent.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void setUpUtility(Utility utility, int[] rent) {
        PropertyName.setText(utility.getSpaceName());
        PurchasePrice.setText(utility.getPrice()+"kr");
        MortgageValue.setText(utility.getMortgage()+"kr");
        Rent0.setText("If 1 utility is owned the rent is " + rent[0] + " times the sum of the two dices rolled");
        Rent1.setText("If 2 utilities are owned the rent is " + rent[1] + " times the sum of the two dices rolled");
    }
}
