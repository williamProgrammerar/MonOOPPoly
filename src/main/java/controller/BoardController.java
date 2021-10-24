package controller;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import observers.Observable;
import view.SpaceView;
import javafx.scene.paint.Color;
import model.*;
import model.Locale;
import observers.Observer;
import view.*;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * BoardController is the main controller responsible for handling most of the user input.
 *
 * @author williamProgrammerar
 * @author rhedinh
 * @author JonEmilsson
 * @author Hedquist
 */
public class BoardController implements Observer {
    private Game game;

    private final PieceController pieceController = new PieceController();

    private final List<PieceView> pieceViews = new ArrayList<>();

    private final Map<String,SpaceView > spaceViewMap = new HashMap<>();
    private final RentViewManager rentViewManager = new RentViewManager();
    private final Map<Integer, PlayerCardsController> playerCardsControllerMap = new HashMap<>();
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

    @FXML
    Button endTurnButton;

    /**
     * initGame initiates everything necessary for the game to run.
     * initGame is called once when SetUpPlayerController starts the game.
     *
     * @param game same instance of the Game class as in SetUpPlayerController.
     */
    public void initGame(Game game) {

        this.game = game;
        game.attachObserver(this);
        this.diceView = new DiceView(game.getDice());
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
            spaceViewMap.put(space.getSpaceName(), new SpaceView(space, game));
        }
    }
    /**
     * Goes through every Property on the board and places properties into a RentViewManager which makes sure
     * it creates the appropriate view.
     */
    private void initRentViewMaps() {
        for (Property property : game.getBoard().getPropertyList()) {
            rentViewManager.add(property);
        }
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
        makeEndTurnNotClickable();
    }

    public void showTradeView() {
        clearBoardFlowPane();
        boardFlowPane.getChildren().add(tradeView);
        tradeController.loadTrade(game.getCurrentPlayer(), game.getPlayers());
        makeEndTurnNotClickable();
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
            PieceView pieceView = new PieceView(pieceController.createPiece(), player);
            pieceViews.add(pieceView);
            PlayerCardsController playerCardsController = new PlayerCardsController(pieceView);
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
            System.out.println("Player added to list");
            PlayerCardsController playerCardsController = playerCardsControllerMap.get(player.getPlayerId());
            monopolyScene.getChildren().add(playerCardsController);
            StackPane.setAlignment(playerCardsController, alignmentDeque.remove());
        }

        for (PieceView pieceView : pieceViews) {
            boardGrid.add(pieceView.getPiece(), 10, 10);
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
        game.move(game.getDice().getTotalValue());
        updateAllPieces();
        landedOnProperty();
        landedOnChance();
        updatePlayerCapital();
    }

    /**
     * landedOnProperty checks if the player landed on an unowned property, in order to determine whether or not
     * to show showUnownedPropertyView.
     */
    private void landedOnProperty() {
        if (game.getCurrentSpace() instanceof Property) {
            Property property = (Property) game.getCurrentSpace();
            if (!property.isOwned()) {
                showUnownedPropertyView();
                makeEndTurnNotClickable();
            }
        }
    }

    /**
     * Checks if the current player landed on a chance space.
     */
    private void landedOnChance() {
        if (game.getCurrentSpace() instanceof Chance) {
            chanceCardText.setText("KLICKA HÄR FÖR ATT DRA ETT KORT");
            makeEndTurnNotClickable();
            makeChanceCardClickable();
        }
    }

    private void showChanceCard(MouseEvent mouseEvent) {
        IChanceCard chanceCard = new ChanceCardCreator().getChanceCard();
        chanceCardText.setText(chanceCard.getText());
        chanceCard.doAction(game.getCurrentPlayer());
        playerCardsControllerMap.get(game.getCurrentPlayer().getPlayerId()).updateCapital(game.getCurrentPlayer());
        makeChanceCardNotClickable();
        makeEndTurnClickable();
    }

    private void putAwayChanceCard() {
        chanceCardText.setText("CHANSKORT");
    }

    private void makeChanceCardClickable(){

        chanceCardText.setOnMouseClicked(this::showChanceCard);
    }

    private void makeChanceCardNotClickable(){
        chanceCardText.setOnMouseClicked(null);
    }

    /**
     * endTurn is public so that a button can access this method to end the turn.
     */
    public void endTurn(ActionEvent actionEvent) {
        putAwayChanceCard();
        playerCardsControllerMap.get(game.getCurrentPlayer().getPlayerId()).updateCurrentPlayer(false);
        game.endTurn();
        playerCardsControllerMap.get(game.getCurrentPlayer().getPlayerId()).updateCurrentPlayer(true);
    }

    public void makeEndTurnClickable(){
        endTurnButton.setOnAction(this::endTurn);
        endTurnButton.setVisible(true);
    }

    public void makeEndTurnNotClickable(){
        endTurnButton.setOnAction(null);
        endTurnButton.setVisible(false);
    }

    /**
     * Updates the position of all the visual pieces.
     */
    private void updateAllPieces() {
        for (PieceView pieceView : pieceViews) {
            int playerPosition = pieceView.getPlayer().getPosition();
            ImageView pieceImage = pieceView.getPiece();
            positionToGrid(playerPosition, pieceImage);
        }
    }


    public AnchorPane getPropertyRentView() {
        try {
            return rentViewManager.getPropertyRentViewHashMap().get(game.getCurrentSpace().getSpaceName());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void showSelectedSpace(SpaceView spaceView) {

    }

 
    public void clearBoardFlowPane() {
        boardFlowPane.getChildren().clear();
        updatePlayerCapital();
    }

    /**
     * This is called when a space is selected, making sure that that space is shown and that a SelectedLocaleController
     * is created with the current instance of game.
     */
    private void updateSpaceShown(Space space) {
        clearBoardFlowPane();
        boardFlowPane.getChildren().add(rentViewManager.propertyRentViewHashMap.get(space.getSpaceName()));
        boardFlowPane.getChildren().add(new SelectedLocaleController(game, this));
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
    public void update(Observable observable,Object arg) {
        if (arg instanceof Locale){
            updateSpaceShown((Space) arg);
        }
        updatePlayerCapital();
    }

    /**
     * newPropertyOwner is called when an unowned property gets an owner for the first time.
     * public method so that AuctionController can access it.
     *
     * @param property the property.
     * @param player   the player.
     */
    public void newPropertyOwner(Property property, Player player) {
        spaceViewMap.get(property.getSpaceName()).setOwner(findColor(player));
        updatePlayerCapital();
    }

    private Color findColor(Player player) {
        for (PieceView pieceView : pieceViews) {
            if (pieceView.getPlayer() == player) {
                return pieceView.getColor();
            }
        }
        return null;
    }

    /**
     * updatePropertyOwnership transfers the ownership of an already owned property to another player.
     * public method so that TradeController can access it.
     *
     * @throws Exception exception should never have to be thrown.
     */
    public void updatePropertyOwnership() throws Exception {
        for (Space space : getGame().getBoard().getSpaceList()) {
            if (space instanceof Property) {
                Property property = (Property) space;
                if (property.isOwned()) {
                    Player owner = game.getPlayerUsingID(property.getOwnerId());
                    spaceViewMap.get(property.getSpaceName()).setOwner(findColor(owner));
                }
            }
        }
    }

    /**
     * Updates all players capital.
     * public method so that TradeController can access it.
     */
    public void updatePlayerCapital() {
        for (Player player : game.getPlayers()) {
            playerCardsControllerMap.get(player.getPlayerId()).updateCapital(player);
        }
    }

    public Game getGame() {
        return game;
    }

    /**
     * buildBuilding builds a building on a locale.
     * Public so that SelectedLocaleController can access it.
     *
     * @param locale the locale where house should be built.
     */
    public void buildBuilding(Locale locale) {
        spaceViewMap.get(locale.getSpaceName()).addHouse();
        updatePlayerCapital();
    }


}

