package controller;

import model.Game;
import model.Locale;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * @author JonEmilsson
 * @author williamProgrammerar
 */
public class SelectedLocaleController extends AnchorPane {
    Game game;
    Locale locale;
    private final BoardController boardController;
    @FXML
    private Button mortgageButton;

    Boolean toogle;
    public SelectedLocaleController(Game game, BoardController boardController){
        this.boardController = boardController;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/buyHouse.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.game = game;
        locale = (Locale) game.getSelectedSpace();
        setToogle(locale.isMortgaged());


        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        setMorgageButtonText(locale.isMortgaged());

    }

    /**
     * This method simply calls for game to buy house, when buy house is clicked.
     */
    public void buyHouse(){
        game.buyHouse(locale);
        buildHouse(locale);
    }
    public void mortgageProperty(){

        if(!toogle){
            try {
                game.mortgageLocale();
                setToogle(true);
                mortgageButton.setText("Pay Back Mortgage");

            }catch (IllegalArgumentException ignore){}

        }
        else {
            try {
                game.payBackMortgage();
                setToogle(false);
                mortgageButton.setText("Mortgage");
            }
            catch (IllegalArgumentException ignore){}
        }
    }
    public void setToogle(Boolean toogle) {
        this.toogle = toogle;
    }
    private void setMorgageButtonText(Boolean isMortgaged){
        if (isMortgaged){
            mortgageButton.setText("Pay Back Mortgage");
        }
        else{
            mortgageButton.setText("Mortgage");
        }

    }
    private void buildHouse(Locale locale) {
        if (game.getCurrentPlayer().hasMonopoly(locale) && game.getCurrentPlayer().getCapital() >= locale.getHouseCost()) {
            boardController.buildBuilding(locale);
        }
    }

}
