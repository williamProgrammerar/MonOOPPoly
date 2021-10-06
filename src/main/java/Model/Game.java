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
        if(!jailTurn(currentPlayer)) {
            currentPlayer.move(dice.getSum());
            currentSpace = board.getSpace(currentPlayer.getPosition());

            inspectCurrentSpace();

            System.out.println("Player" + currentPlayer.getPlayerId() + " landed on: " + currentSpace.getSpaceName());
            System.out.println(currentPlayer.getPosition());
        }
    }

    /**
     * inspectCurrentSpace() checks what type the current space is.
     * If the current space is a property, owned by another player and not mortgaged then the player
     * has to pay rent to the owner of the property.
     * @author williamProgrammerar, Hedquist
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
        } else if(isCurrentSpaceTax()) {
            Tax tax = (Tax) currentSpace;
            currentPlayer.setCapital(currentPlayer.getCapital() - tax.getTax());
            System.out.println("Player " + currentPlayer.getPlayerId() + " had to pay tax and has " + currentPlayer.getCapital());
        } else if(isCurrentSpaceChance()) {
            //TODO chance card
        } else if(currentSpace.getSpaceName().equals("GO")) {
            int salary = 200;
            currentPlayer.setCapital(currentPlayer.getCapital() + salary); //this should maybe be a variable, you could change it in settings
            System.out.println("Player " + currentPlayer.getPlayerId() + " passed GO and recieved " + salary);
        } else if(currentSpace.getSpaceName().equals("U")) {
            currentPlayer.moveTo(10, false);
            currentPlayer.setTurnsInJail(0);
            System.out.println("Player " + currentPlayer.getPlayerId() + " failed their exam and has been sent to redo it!");
        }
    }

    private boolean jailTurn(Player currentPlayer) {
        if(currentPlayer.getTurnsInJail() > 0 && board.getSpace(currentPlayer.getPosition()).getSpaceName().equals("OMTENTA")) {
            int jailFine = 50;
            System.out.println("You're stuck at a re-exam, roll doubles or pay " + jailFine + "kr to finish it!");
            if(currentPlayer.getTurnsInJail() < 3) {
                dice.rollDice(); //this needs to be coupled to the view
                if(dice.doubles()) {

                }
            }
        }
        return false;
    }

    private boolean isCurrentSpaceProperty() {
        return currentSpace instanceof Property;
    }

    private boolean isOwnedByCurrentPlayer(Property property) {
        return currentPlayer.getProperties().contains(property);
    }

    private boolean isCurrentSpaceTax() {
        return currentSpace instanceof Tax;
    }

    private boolean isCurrentSpaceChance() {
        return currentSpace instanceof Chance;
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
