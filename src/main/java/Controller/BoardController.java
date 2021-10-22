package Controller;

import Model.*;
import Model.Locale;
import Observers.Observer;
import View.*;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.awt.*;
import java.util.*;
import java.util.List;


public class BoardController implements Observer {
    private Game game;

    private final PieceController pv = new PieceController();

    private final List<Piece> pieces = new ArrayList<>();

    private final Map<String, SpaceView> spaceViewMap = new HashMap<>();

    private final Map<Integer, PlayerCardsController> playerCardsControllerMap = new HashMap<>();

    private final Map<String, LocaleRentView> localeRentViewMap = new HashMap<>();
    private final Map<String, StationRentView> stationRentViewMap = new HashMap<>();
    private final Map<String, UtilityRentView> utilityRentViewMap = new HashMap<>();

    private final Map<Integer, Point> spaceCellMap = new HashMap<>();

    private DiceView diceView;

    private final UnownedPropertyController unownedPropertyController = new UnownedPropertyController(this);
    private final UnownedPropertyView unownedPropertyView = new UnownedPropertyView(unownedPropertyController);
    private final AuctionController auctionController = new AuctionController(this);
    private final AuctionView auctionView = new AuctionView(auctionController);

    private final TradeController tradeController = new TradeController(this);
    private final TradeView tradeView = new TradeView(tradeController);

    @FXML
    private GridPane boardGrid;

    @FXML
    private StackPane monopolyScene;

    @FXML
    private TextArea chanceCardText;

    @FXML
    private FlowPane boardFlowPane;

    @FXML
    private FlowPane diceFlowPane;

    public void initGame(Game game) {

        this.game = game;
        game.attach(this);
        this.diceView = new DiceView(game.getDice());
        showDiceView();
        initSpaceCellMap();
        initSpaceViewMap();
        initSpaces();

        initPlayerCardsControllerMap();
        initPlayers();

        initRentViewMaps();
        showTradeView();
    }


    /**
     * Goes through every space on the board and assigns a controller to each space.
     */
    private void initSpaceViewMap() {

        for (Space space : game.getBoard().getSpaceList()) {
            SpaceView spaceView = new SpaceView(space, game);

            spaceViewMap.put(space.getSpaceName(), spaceView);
        }
    }


    /**
     * Goes through every space on the board and places spaces that are either instances of Locale, Station and Utility
     * in their respective maps.
     */

    private void initRentViewMaps() {
        for (Space space : game.getBoard().getSpaceList()) {
            if (space instanceof Locale) {
                setUpLocaleViewMap((Locale) space);
            } else if (space instanceof Station) {
                setUpStationViewMap((Station) space);
            } else if (space instanceof Utility) {
                setUpUtilityViewMap((Utility) space);
            }
        }
    }

    private void setUpLocaleViewMap(Locale locale) {
        LocaleRentView localeRentView = new LocaleRentView(locale);
        localeRentViewMap.put(locale.getSpaceName(), localeRentView);
    }

    private void setUpStationViewMap(Station station) {
        StationRentView stationRentView = new StationRentView(station);
        stationRentViewMap.put(station.getSpaceName(), stationRentView);
    }

    private void setUpUtilityViewMap(Utility utility) {
        UtilityRentView utilityRentView = new UtilityRentView(utility);
        utilityRentViewMap.put(utility.getSpaceName(), utilityRentView);
    }

    private void initSpaceCellMap() {
        int r = 10;
        int c = 10;

        for (int i = 0; i < game.getBoard().getSpaceList().size(); i++) {
            spaceCellMap.put(i, new Point(r, c));
            if (r == 10 && c != 0) {
                c--;
            } else if (c == 0 && r != 0) {
                r--;
            } else if (r == 0 && c != 10) {
                c++;
            } else {
                r++;
            }
        }
    }

    private void showDiceView() {
        diceFlowPane.getChildren().add(diceView);
    }

    private void showUnownedPropertyView() {
        clearBoardFlowPane();
        boardFlowPane.getChildren().add(unownedPropertyView);
        unownedPropertyController.setFlowPane(getPropertyRentView());
    }

    public void showAuctionView() {
        clearBoardFlowPane();
        boardFlowPane.getChildren().add(auctionView);
        auctionController.setFlowPane(getPropertyRentView());
        auctionController.startAuction();
    }

    public void showTradeView() {
        clearBoardFlowPane();
        boardFlowPane.getChildren().add(tradeView);
    }
    /**
     * Method goes through all the spaces in board and places them around the edges of a grid
     * in order to give the visual board a similar look to the original Monopoly game.
     */
    private void initSpaces() {
        List<Space> spaceList = game.getBoard().getSpaceList();

        boardGrid.getChildren().clear();


        int r = 10;
        int c = 10;
        for (Space space : spaceList) {
            SpaceView i = spaceViewMap.get(space.getSpaceName());
            boardGrid.add(i, c, r);

            if (c == 0) {
                i.setRotate(90);
                i.getSpaceText().setRotate(-90);
            } else if (c == 10) {
                i.setRotate(-90);
                i.getSpaceText().setRotate(90);
            }

            if (r == 10 && c != 0) {
                c--;
            } else if (c == 0 && r != 0) {
                r--;
            } else if (r == 0 && c != 10) {
                c++;
            } else {
                r++;
            }
        }

    }

    private void initPlayerCardsControllerMap() {
        for (Player player : game.getPlayers()) {
            Piece piece = new Piece(pv.createPiece(), player);
            pieces.add(piece);
            PlayerCardsController playerCardsController = new PlayerCardsController(piece);
            playerCardsControllerMap.put(player.getPlayerId(), playerCardsController);
        }
    }

    private void displayBuyHouseController() {

    }

    /**
     * Method initiates all the players.
     * It creates a visual piece for each player and places all the pieces on the board.
     */
    private void initPlayers() {
        List<Player> players = game.getPlayers();
        Deque<Pos> alignmentDeque = new LinkedList<>();
        alignmentDeque.add(Pos.TOP_LEFT);
        alignmentDeque.add(Pos.TOP_RIGHT);
        alignmentDeque.add(Pos.BOTTOM_LEFT);
        alignmentDeque.add(Pos.BOTTOM_RIGHT);

        for (Player player : players) {
            //pieces.add(new Piece(pv.createPiece(), player));
            System.out.println("Player added to list");
            PlayerCardsController playerCardsController = playerCardsControllerMap.get(player.getPlayerId());
            monopolyScene.getChildren().add(playerCardsController);
            StackPane.setAlignment(playerCardsController, alignmentDeque.remove());
        }
        for (Piece piece : pieces) {
            boardGrid.add(piece.getPiece(), 10, 10);
            System.out.println("Player added to grid");
        }
        playerCardsControllerMap.get(game.getCurrentPlayer().getPlayerId()).updateCurrentPlayer(true);
    }

    /**
     * When the dice button is pressed this method will role the dice, and send the values to the game class.
     * Game class will move the current player and then change the player.
     */
    public void rollDice() {
        game.getDice().rollDice();
        diceView.updateDice();
        moveCurrentPlayer();
    }

    private void moveCurrentPlayer() {
        game.move(game.getDice().getSum());
        updateAllPieces();
        landedOnProperty();
        chanceCardText.setText("CHANCE CARD");
        landedOnChance();
        updatePlayerCapital();
    }

    private void landedOnProperty() {
        if (game.getCurrentSpace() instanceof Property) {
            Property property = (Property) game.getCurrentSpace();
            if (!property.isOwned()) {
                showUnownedPropertyView();
            }
            // TODO if owned then show who payed rent to who
        }
    }

    private void landedOnChance() {
        if (game.getCurrentSpace() instanceof Chance) {
            IChanceCard chanceCard = new ChanceCardCreator().getChanceCard();
            chanceCardText.setText(chanceCard.getText());
            chanceCard.doAction(game.getCurrentPlayer());
            playerCardsControllerMap.get(game.getCurrentPlayer().getPlayerId()).updateCapital(game.getCurrentPlayer());
        }
    }

    public void endTurn() {
        playerCardsControllerMap.get(game.getCurrentPlayer().getPlayerId()).updateCurrentPlayer(false);
        game.endTurn();
        playerCardsControllerMap.get(game.getCurrentPlayer().getPlayerId()).updateCurrentPlayer(true);
    }

    /**
     * Updates the position of all the visual pieces.
     */
    public void updateAllPieces() {
        for (Piece piece : pieces) {
            int playerPosition = piece.getPlayer().getPosition();
            ImageView pieceImage = piece.getPiece();
            positionToGrid(playerPosition, pieceImage);
        }
    }

    public AnchorPane getPropertyRentView() {
        if (game.getCurrentSpace() instanceof Locale) {
            return getLocaleRentView();
        } else if (game.getCurrentSpace() instanceof Station) {
            return getStationRentView();
        } else {
            return getUtilityRentView();
        }
    }


    private void showSelectedSpace(SpaceView spaceView) {
    }

    private void updateLocaleRentView() {
        clearBoardFlowPane();
        boardFlowPane.getChildren().add(localeRentViewMap.get(game.getCurrentSpace().getSpaceName()));
    }

    private AnchorPane getLocaleRentView() {
        return localeRentViewMap.get(game.getCurrentSpace().getSpaceName());
    }

    private AnchorPane getStationRentView() {
        return stationRentViewMap.get(game.getCurrentSpace().getSpaceName());

    }

    private AnchorPane getUtilityRentView() {
        return utilityRentViewMap.get(game.getCurrentSpace().getSpaceName());
    }

    public void clearBoardFlowPane() {
        boardFlowPane.getChildren().clear();
        updatePlayerCapital();
    }

    /**
     * This is called when a space is selected, making sure that that space is shown and that a buy house controller
     * is created with the current instance of game.
     */
    private void updateLocaleShown() {
        clearBoardFlowPane();
        boardFlowPane.getChildren().add(localeRentViewMap.get(game.getSelectedSpace().getSpaceName()));
        boardFlowPane.getChildren().add(new SelectedLocaleController(game));
    }


    /**
     * Converts the players position to corresponding row and column.
     *
     * @param position the players position
     * @param piece    the players piece image
     */
    public void positionToGrid(int position, ImageView piece) {
        double col, row;

        row = spaceCellMap.get(position).getX();
        col = spaceCellMap.get(position).getY();
        boardGrid.getChildren().remove(piece);
        boardGrid.add(piece, (int) col, (int) row);
    }

    private void buyHouse() {

    }

    public void buyProperty() {
        if (game.getCurrentSpace() instanceof Property) {
            Property property = (Property) game.getCurrentSpace();
            if (!property.isOwned()) {
                Player player = game.getCurrentPlayer();
                player.buyProperty(property);
                newPropertyOwner(property, player);
            }
        }
    }

    /**
     * This method makes sure that when it gets information from an observer about a space being selected it
     * will visualise that on the screen and call updateLocaleShown.
     */
    @Override
    public void update() {
        if (game.getSelectedSpace() instanceof Locale) {
            updateLocaleShown();

        }
    }


    public void newPropertyOwner(Property property, Player player) {
        spaceViewMap.get(property.getSpaceName()).setOwner(player);
        updatePlayerCapital();
    }

    private void updatePlayerCapital() {
        for (Player player : game.getPlayers()) {
            playerCardsControllerMap.get(player.getPlayerId()).updateCapital(player);
        }
    }

    public Game getGame() {
        return game;
    }
}

