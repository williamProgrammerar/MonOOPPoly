package Controller;

import Model.*;
import View.Piece;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.*;


public class BoardController {
    private Game game;

    private final PieceController pv = new PieceController();

    private final List<Piece> pieces = new ArrayList<>();

    private Map<String, SpaceController> spaceControllerMap = new HashMap<String, SpaceController>();

    private Map<Integer, PlayerCardsController> playerCardsControllerMap = new HashMap<Integer, PlayerCardsController>();

    // everything involving controlling the dice should be moved here and removed from the Game class.
    @FXML
    private Button dice1;
    @FXML
    private Button dice2;
    @FXML
    private GridPane boardGrid;
    @FXML
    StackPane monopolyScene;

    public void initGame(Game game) {
        this.game = game;
        initSpaceControllerMap();
        initSpaces();

        initPlayerCardsControllerMap();
        initPlayers();
    }

    /**
     * Goes through every space on the board and assigns a controller to each space.
     */
    private void initSpaceControllerMap() {
        for (Space space : game.getBoard().getSpaceList()) {
            SpaceController spaceController = new SpaceController(space);
            spaceControllerMap.put(space.getSpaceName(), spaceController);
        }
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
            SpaceController i = spaceControllerMap.get(space.getSpaceName());
            boardGrid.add(i, c, r);

            if (c == 0) {
                i.setRotate(90);
                i.spaceText.setRotate(-90);
            } else if (c == 10) {
                i.setRotate(-90);
                i.spaceText.setRotate(90);
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
            PlayerCardsController playerCardsController = new PlayerCardsController(player);
            playerCardsControllerMap.put(player.getPlayerId(), playerCardsController);
        }
    }

    /**
     * Method initiates all the players.
     * It creates a visual piece for each player and places all the pieces on the board.
     */
    private void initPlayers() {
        List<Player> players = game.getPlayers();
        Deque<Pos> alignmentDeque = new LinkedList<Pos>();
        alignmentDeque.add(Pos.TOP_LEFT);
        alignmentDeque.add(Pos.TOP_RIGHT);
        alignmentDeque.add(Pos.BOTTOM_LEFT);
        alignmentDeque.add(Pos.BOTTOM_RIGHT);

        for (Player player : players) {
            pieces.add(new Piece(pv.createPiece(), player));
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
        dice1.setText(String.valueOf(game.getDice().getDice1()));
        dice2.setText(String.valueOf(game.getDice().getDice2()));
        game.move(game.getDice().getSum());
        updateAllPieces();
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

    /**
     * Converts the players position to corresponding row and column.
     * @param position the players position
     * @param piece the players piece image
     */
    public void positionToGrid(int position, ImageView piece) {
        int col, row;

        switch (position) {
            case 1 -> {
                col = 9;
                row = 10;
            }
            case 2 -> {
                col = 8;
                row = 10;
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
        }
        boardGrid.getChildren().remove(piece);
        boardGrid.add(piece, col, row);
    }

    public void buyProperty() {
        if (game.getCurrentSpace() instanceof Property) {
            Property property = (Property) game.getCurrentSpace();
            Player player = game.getCurrentPlayer();
            player.buyProperty(property);
            spaceControllerMap.get(property.getSpaceName()).setOwner(player);
            playerCardsControllerMap.get(player.getPlayerId()).updateCapital(player);
        }
    }
}
