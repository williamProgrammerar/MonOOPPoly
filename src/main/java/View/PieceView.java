package View;

import Model.Game;
import Model.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class PieceView {

    private final Game game;
    private List<Player> players;

    public PieceView(Game game) {
        this.game = game;
        this.players = game.getPlayers();
        printPlayers(players);
    }

    public ImageView createPiece() {
        return new ImageView(new Image("pic/Asmurf-icon.png"));
    }

    public void printPlayers(List<Player> players) {
        for (Player player : players) {
            System.out.println(player.getPlayerId());
        }
    }

}
