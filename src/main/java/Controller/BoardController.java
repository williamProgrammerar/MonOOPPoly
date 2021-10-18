package Controller;

import Model.*;
import Model.Locale;
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


public class BoardController {
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
        this.diceView  = new DiceView(game.getDice());
        showDiceView();
        initSpaceCellMap();
        initSpaceViewMap();
        initSpaces();

        initPlayerCardsControllerMap();
        initPlayers();

        initRentViewMaps();
    }

    /**
     * Goes through every space on the board and assigns a controller to each space.
     */
    private void initSpaceViewMap() {
        for (Space space : game.getBoard().getSpaceList()) {
            SpaceView spaceView = new SpaceView(space);
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
            }
            else if (space instanceof Station) {
                setUpStationViewMap((Station) space);
            }
            else if (space instanceof Utility) {
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

    private void initSpaceCellMap(){
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
        if (game.getCurrentSpace() instanceof Chance){
            IChanceCard chanceCard = new ChanceCardCreator().getChanceCard();
            chanceCardText.setText(chanceCard.getText());
            chanceCard.doAction(game.getCurrentPlayer());
            playerCardsControllerMap.get(game.getCurrentPlayer().getPlayerId()).updateCapital(game.getCurrentPlayer());
        }
    }

    public void endTurn() {
        game.endTurn();
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
        }
        else if (game.getCurrentSpace() instanceof Station) {
            return getStationRentView();
        }
        else {
            return getUtilityRentView();
        }
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
     * Converts the players position to corresponding row and column.
     *
     * @param position the players position
     * @param piece    the players piece image
     */
    public void positionToGrid(int position, ImageView piece) {
        double col, row;

        /*switch (position) {
            case 1 -> {
                col = 9;
                row = 10;
            }
            case 2 -> {
                col = 8;
                row = 10;
                IChanceCard chanceCard = new ChanceCardCreator().getChanceCard();
                chanceCardText.setText(chanceCard.getText());
            }
            case 3 -> {
                col = 7;
                row = 10;
            }
            case 4 -> {
                col = 6;
                row = 10;
            }
            case 5 -> {
                col = 5;
                row = 10;
            }
            case 6 -> {
                col = 4;
                row = 10;
            }
            case 7 -> {
                col = 3;
                row = 10;
                IChanceCard chanceCard = new ChanceCardCreator().getChanceCard();
                chanceCardText.setText(chanceCard.getText());
            }
            case 8 -> {
                col = 2;
                row = 10;
            }
            case 9 -> {
                col = 1;
                row = 10;
            }
            case 10 -> {
                col = 0;
                row = 10;
            }
            case 11 -> {
                col = 0;
                row = 9;
            }
            case 12 -> {
                col = 0;
                row = 8;
            }
            case 13 -> {
                col = 0;
                row = 7;
            }
            case 14 -> {
                col = 0;
                row = 6;
            }
            case 15 -> {
                col = 0;
                row = 5;
            }
            case 16 -> {
                col = 0;
                row = 4;
            }
            case 17 -> {
                col = 0;
                row = 3;
                IChanceCard chanceCard = new ChanceCardCreator().getChanceCard();
                chanceCardText.setText(chanceCard.getText());
            }
            case 18 -> {
                col = 0;
                row = 2;
            }
            case 19 -> {
                col = 0;
                row = 1;
            }
            case 20 -> {
                col = 0;
                row = 0;
            }
            case 21 -> {
                col = 1;
                row = 0;
            }
            case 22 -> {
                col = 2;
                row = 0;
                IChanceCard chanceCard = new ChanceCardCreator().getChanceCard();
                chanceCardText.setText(chanceCard.getText());
            }
            case 23 -> {
                col = 3;
                row = 0;
            }
            case 24 -> {
                col = 4;
                row = 0;
            }
            case 25 -> {
                col = 5;
                row = 0;
            }
            case 26 -> {
                col = 6;
                row = 0;
            }
            case 27 -> {
                col = 7;
                row = 0;
            }
            case 28 -> {
                col = 8;
                row = 0;
            }
            case 29 -> {
                col = 9;
                row = 0;
            }
            case 30 -> {
                col = 10;
                row = 0;
            }
            case 31 -> {
                col = 10;
                row = 1;
            }
            case 32 -> {
                col = 10;
                row = 2;
                IChanceCard chanceCard = new ChanceCardCreator().getChanceCard();
                chanceCardText.setText(chanceCard.getText());
            }
            case 33 -> {
                col = 10;
                row = 3;
            }
            case 34 -> {
                col = 10;
                row = 4;
            }
            case 35 -> {
                col = 10;
                row = 5;
            }
            case 36 -> {
                col = 10;
                row = 6;
                IChanceCard chanceCard = new ChanceCardCreator().getChanceCard();
                chanceCardText.setText(chanceCard.getText());
            }
            case 37 -> {
                col = 10;
                row = 7;
            }
            case 38 -> {
                col = 10;
                row = 8;
            }
            case 39 -> {
                col = 10;
                row = 9;
            }
            default -> {
                col = 10;
                row = 10;
            }
        }*/
        row = spaceCellMap.get(position).getX();
        col = spaceCellMap.get(position).getY();
        boardGrid.getChildren().remove(piece);
        boardGrid.add(piece, (int) col, (int) row);
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
