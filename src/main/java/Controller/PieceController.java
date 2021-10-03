package Controller;

import Model.Game;
import Model.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class PieceController {

    private final Game game;
    private List<Player> players;

    public PieceController(Game game) {
        this.game = game;
        this.players = game.getPlayers();
        printPlayers(players);
    }

    // Can later be used to choose image?
    public ImageView createPiece() {
        return new ImageView(new Image("pic/Asmurf-icon.png"));
    }

    public void printPlayers(List<Player> players) {
        for (Player player : players) {
            System.out.println(player.getPlayerId());
        }
    }

}
