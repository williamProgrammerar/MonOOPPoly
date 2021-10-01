package Model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Dice dice = new Dice();
    private final Board board = new Board();
    private List<Player> players = new ArrayList<>();

    public Game(int amount) throws Exception {
       players.addAll(choosePlayers(amount));

    }

    public List<Player> choosePlayers(int amount) throws Exception {
        int playerId = 1;
        List<Player> players = new ArrayList<>();
        for(int i = 0; i < amount; i++){
            players.add(new Player(playerId++));
        }
        return players;
    }

    public int choosePiece(){
        return 1;
    }

    /**
     * Moves the current player sum spaces.
     */
    public void move() {
        Space currentSpace;
        Player currentPlayer = players.get(0);
        int sum = dice.getSum();

        currentPlayer.move(sum);
        currentSpace = board.getSpace(currentPlayer.getPosition());
        System.out.println("Player" + currentPlayer.getPlayerId() + " landed on: " + currentSpace.getSpaceName());
        System.out.println(currentPlayer.getPosition());
    }

    /**
     * Places the current player (index 0) in a temporary variable.
     * Player index 0 in the player list is then removed, which leads to a new current player.
     * Finally adds the player stored in the temporary variable to the back of the list.
     */
    public void next() {
        Player temporaryPlayer = players.get(0);
        players.remove(0);
        players.add(temporaryPlayer);
    }

    public Dice getDice() {
        return dice;
    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
