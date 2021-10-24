package controller;

import model.*;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.util.List;

/**
 * TradeController allows inputs from users in order to process a trade.
 *
 * @author williamProgrammerar
 */
public class TradeController {
    private final BoardController boardController;

    private final Trade trade = new Trade();

    private @FXML
    AnchorPane tradePane;

    private @FXML
    AnchorPane menuPane;

    private @FXML
    ComboBox<PlayerItem> selectPlayer;

    private @FXML
    Text errorMessage;

    private @FXML
    Text playerMenuText;

    @FXML
    private ComboBox<PropertyItem> pComboBoxA;

    @FXML
    private Spinner<Integer> cSpinnerA;

    private int currencyOfferA;

    @FXML
    private ComboBox<PropertyItem> pComboBoxB;

    @FXML
    private Spinner<Integer> cSpinnerB;

    private int currencyOfferB;

    private Player selectedPlayer;

    private Player currentPlayer;

    @FXML
    private Text playerAOfferText;

    @FXML
    private Text playerBOfferText;

    /**
     * Constructor that is called when TradeController is created. Gives this class access to the existing boardController class.
     *
     * @param boardController the existing boardController.
     */
    public TradeController(BoardController boardController) {
        this.boardController = boardController;
    }

    /**
     * loadTrade is called from boardController to update previous trade information accordingly.
     *
     * @param player the player who initiated the trade
     * @param players list of players playing the game
     */
    public void loadTrade(Player player, List<Player> players) {
        clearSelectedPlayer();
        currentPlayer = player;
        setUpComboBox(currentPlayer, players);
        initPlayerMenuText();
        errorMessage(false);
    }

    private void clearSelectedPlayer() {
        selectedPlayer = null;
    }

    /**
     * setUpComboBox is responsible for setting up the comboBox used for selecting players.
     *
     * @param currentPlayer player who started the trade, will be excluded from the comboBox so that they won't be able to select themselves.
     * @param players list of players playing the game.
     */
    private void setUpComboBox(Player currentPlayer, List<Player> players) {
        clearComboBox();
        addPlayersToComboBox(currentPlayer,players);
        convertComboBox();
        addListener();
    }

    private void clearComboBox() {
        selectPlayer.getItems().clear();
    }

    /**
     * addPlayersToComboBox goes through the list of players playing the game and checks if they are eligible to be added to the comboBox.
     *
     * @param currentPlayer the player who started the trade.
     * @param players list of players playing the game.
     */
    private void addPlayersToComboBox(Player currentPlayer, List<Player> players) {
        for (Player player : players) {
            if (!player.equals(currentPlayer) && !player.isBankrupt()) {
                selectPlayer.getItems().add(new PlayerItem(player));
            }
        }
    }

    /**
     * convertComboBox uses the playerItem to access and display a player's name in the comboBox.
     */
    private void convertComboBox() {
        selectPlayer.setConverter(new StringConverter<>() {
            @Override
            public String toString(PlayerItem playerItem) {
                return playerItem.getPlayerName();
            }

            @Override
            public PlayerItem fromString(String s) {
                return null;
            }
        });
    }

    private void addListener() {
        selectPlayer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (!(newValue == null)) {
                updateSelectedPlayer(newValue);
            }
        });
    }

    private void updateSelectedPlayer(PlayerItem player) {
        selectedPlayer = player.getPlayer();
    }

    private void initPlayerMenuText() {
        playerMenuText.setText("Trade menu - " + currentPlayer.getName());
    }

    private void errorMessage(Boolean bool) {
        errorMessage.setVisible(bool);
    }

    /**
     * startTrade makes sure that a player has been selected from selectPlayer comboBox and then displays the trade window.
     * If the player has not selected a player an error message will be displayed.
     *
     * Method is public so that a button in the fxml file "Trade" will be able to call it.
     */
    public void startTrade() {
        if (selectedPlayer != null) {
            switchToTradePane();
            errorMessage(false);
            setUpTradeWindow();
        }
        else { errorMessage(true); }
    }

    private void switchToTradePane() {
        tradePane.toFront();
    }

    /**
     * setUpTradeWindow is responsible for setting up everything related to the trade window, such as comboBoxes,
     * spinners and player info.
     */
    private void setUpTradeWindow() {
        trade.startTrade(currentPlayer, selectedPlayer);
        setUpPropertyComboBox(trade.getPlayers());
        setUpCurrencySpinner(trade.getPlayers());
        playerAOfferText.setText(currentPlayer.getName());
        playerBOfferText.setText(selectedPlayer.getName());
    }

    private void setUpPropertyComboBox(Player[] players) {
        clearPropertyComboBoxes();

        setUpPropertyComboBoxA(players);
        setUpPropertyComboBoxB(players);
    }

    private void clearPropertyComboBoxes() {
        pComboBoxA.getItems().clear();
        pComboBoxB.getItems().clear();
    }

    private void setUpPropertyComboBoxA(Player[] players) {
        if (!players[0].getProperties().isEmpty()) {
            initPropertyComboBox(players[0].getProperties(), pComboBoxA);
            convertPropertyComboBox(pComboBoxA);
            addPropertyListener(players[0], pComboBoxA);
        }
    }

    private void setUpPropertyComboBoxB(Player[] players) {
        if (!players[1].getProperties().isEmpty()) {
            initPropertyComboBox(players[1].getProperties(), pComboBoxB);
            convertPropertyComboBox(pComboBoxB);
            addPropertyListener(players[1], pComboBoxB);
        }
    }

    private void initPropertyComboBox(List<Property> properties, ComboBox<PropertyItem> pComboBox) {
        for (Property property : properties) {
            pComboBox.getItems().add(new PropertyItem(property));
        }
    }

    /**
     * convertPropertyComboBox uses the propertyItem to access and display a property's name in the comboBox.
     *
     * @param pComboBox property comboBox.
     */
    private void convertPropertyComboBox(ComboBox<PropertyItem> pComboBox) {
        pComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(PropertyItem propertyItem) {
                return propertyItem.getPropertyName();
            }

            @Override
            public PropertyItem fromString(String s) {
                return null;
            }
        });
    }

    /**
     * addPropertyListener adds a listener to a comboBox which will then add a property to the player's trade offer when an item is selected.
     *
     * @param player player who's selected a property from their property comboBox.
     * @param pComboBox the player's property comboBox.
     */
    private void addPropertyListener(Player player, ComboBox<PropertyItem> pComboBox) {
        pComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (!(newValue == null)) {
                trade.addPropertyToTrade(player, newValue.getProperty());
            }
        });
    }

    private void setUpCurrencySpinner(Player[] players) {
        initCurrencySpinnerA(players[0]);
        initCurrencySpinnerB(players[1]);
    }

    /**
     * initCurrencySpinnerA initialises the spinner A (the spinner on the left side of the trade window).
     *
     * @param player the player.
     */
    private void initCurrencySpinnerA(Player player) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,player.getCapital());

        valueFactory.setValue(0);

        cSpinnerA.setValueFactory(valueFactory);

        currencyOfferA = cSpinnerA.getValue();

        cSpinnerA.valueProperty().addListener((observableValue, integer, t1) -> {
            currencyOfferA = cSpinnerA.getValue();
            trade.addCurrencyToTrade(player, currencyOfferA);
        });
    }

    /**
     * initCurrencySpinnerB initialises the spinner B (the spinner on the right side of the trade window).
     *
     * @param player the player.
     */
    private void initCurrencySpinnerB(Player player) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,player.getCapital());

        valueFactory.setValue(0);

        cSpinnerB.setValueFactory(valueFactory);

        currencyOfferB = cSpinnerB.getValue();

        cSpinnerB.valueProperty().addListener((observableValue, integer, t1) -> {
            currencyOfferB = cSpinnerB.getValue();
            trade.addCurrencyToTrade(player, currencyOfferB);
        });
    }

    /**
     * switchToMenuPane allows the Player to go back without closing the trade completely.
     * Method is public so that a button in the fxml file "Trade" will be able to call it.
     */
    public void switchToMenuPane() {
        menuPane.toFront();
        trade.closeTrade();
    }

    /**
     * closeTrade clears the boardFlowPane and then switches back to the menuPane.
     * Method is public so that a button in the fxml file "Trade" will be able to call it.
     */
    public void closeTrade() {
        boardController.clearBoardFlowPane();
        switchToMenuPane();
        boardController.makeEndTurnClickable();
    }

    /**
     * Method is public so that a button in the fxml file "Trade" will be able to call it.
     * @throws Exception if there are no properties to update ownership of.
     */
    public void confirmTrade() throws Exception {
        trade.acceptTrade();
        boardController.updatePlayerCapital();
        boardController.updatePropertyOwnership();
        closeTrade();
    }
}