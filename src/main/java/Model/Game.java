package Model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Dice dice = new Dice();
    private final Board board = new Board();
    private List<Player> players = new ArrayList<>();
    private Space currentSpace;
    private Player currentPlayer;


    public Game(GameSettings gameSettings)  {
       this.players.addAll(gameSettings.getPlayers());
       System.out.println(players.get(0).getName());

    }

    public int choosePiece(){
        return 1;
    } // Temporary, might delete

    /**
     * Moves the current player sum spaces.
     * @author williamProgrammerar
     */
    public void move() {
        currentPlayer = players.get(0);
        currentPlayer.move(dice.getSum());
        currentSpace = board.getSpace(currentPlayer.getPosition());

        inspectCurrentSpace();

        System.out.println("Player" + currentPlayer.getPlayerId() + " landed on: " + currentSpace.getSpaceName());
        System.out.println(currentPlayer.getPosition());
    }

    /**
     * inspectCurrentSpace() checks what type the current space is.
     * If the current space is a property, owned by another player and not mortgaged then the player
     * has to pay rent to the owner of the property.
     * @author williamProgrammerar
     */
    private void inspectCurrentSpace() {
        if (isCurrentSpaceProperty()) {
            Property property = (Property) currentSpace;
            if(property.isOwned() && !isOwnedByCurrentPlayer(property) && !property.isMortgaged()) {
                currentPlayer.setCapital(currentPlayer.getCapital() - property.getRent());
                System.out.println("Player " + currentPlayer.getPlayerId() + " has " + currentPlayer.getCapital());
                for (Player player : players) {
                    if(player.getPlayerId() == property.getOwnerId()) {
                        player.setCapital(player.getCapital() + property.getRent());
                        System.out.println("Player " + player.getPlayerId() + " has "+ player.getCapital());
                    }
                }
            }
        }
    }

    private boolean isCurrentSpaceProperty() {
        return currentSpace instanceof Property;
    }

    private boolean isOwnedByCurrentPlayer(Property property) {
        return currentPlayer.getProperties().contains(property);
    }

    /**
     * Places the current player (index 0) in a temporary variable.
     * Player index 0 in the player list is then removed, which leads to a new current player.
     * Finally adds the player stored in the temporary variable to the back of the list.
     * @author williamProgrammerar
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

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Space getCurrentSpace() {
        return currentSpace;
    }
}
