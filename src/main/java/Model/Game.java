package Model;

import Observers.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Game class is responsible for handling the game rules, such as turn order and
 * winning/losing etc.
 *
 * @author williamProgrammerar
 * @author rhedinh
 * @author JonEmilsson
 * @author Hedquist
 */
public class Game {
    private final Dice dice = new Dice();
    private final Board board = new Board();
    private final Jail jail = new Jail(50, dice);
    private List<Player> players = new ArrayList<>();
    private Space currentSpace;
    private Player currentPlayer;
    private Space selectedSpace;
    private boolean hasMoved = false;
    List<Observer> observers = new ArrayList<>();

    public Game(GameSettings gameSettings)  {
       this.players.addAll(gameSettings.getPlayers());
       updateCurrentPlayer();
    }

    /**
     * Moves the current player sum spaces.
     */
    public void move(int spaces) {
        if(!hasMoved) {
            if (!jail.jailTurn(currentPlayer)) {
                currentPlayer.move(spaces);
                currentSpace = board.getSpace(currentPlayer.getPosition());

                receiveGoPay(currentPlayer);
                inspectCurrentSpace();

                hasMoved = true;

                System.out.println("Player" + currentPlayer.getPlayerId() + " landed on: " + currentSpace.getSpaceName());
                System.out.println(currentPlayer.getPosition());

            }
        }
    }

    /**
     * If the player has passed go this turn, receive appropriate payment
     * @param currentPlayer
     */
    private void receiveGoPay(Player currentPlayer) {
        if(currentPlayer.HasPassedGo()) {
            int salary = 200;
            currentPlayer.setCapital(currentPlayer.getCapital() + salary); //this should maybe be a variable, you could change it in settings
            System.out.println(currentPlayer.getName() + " passed GO and received " + salary + "kr");
        }
    }

    /**
     * inspectCurrentSpace() checks what type the current space is.
     * If the current space is a property, owned by another player and not mortgaged then the player
     * has to pay rent to the owner of the property.
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
        } else if(currentSpace.getSpaceName().equals("U")) {
            currentPlayer.moveTo(10, false);
            jail.addToJail(currentPlayer);
        }
    }

    public void endTurn () {
        if (dice.isHasRolled()) {
            next();
            dice.setHasRolled(false);
            hasMoved = false;
            checkBankruptcy();
        }
    }

    /**
     * Should prevent currentPlayer from moving until they are debt free,
     * currently just instantly makes players bankrupt if they start a turn while in debt.
     * Calls next() to pass the turn if the player declares bankruptcy.
     */
    private void checkBankruptcy() {
        if(currentPlayer.getCapital() < 1) {
            System.out.println("You cannot move while in debt!");
            //TODO if time available, add way to sell items before bankruptcy
            //Allow selling
            //After selling, call checkBankruptcy again

            //If option to become bankrupt is selected do this
            currentPlayer.setBankrupt();
            next();
        }
    }

    private boolean isCurrentSpaceProperty() {
        return currentSpace instanceof Property;
    }

    private boolean isOwnedByCurrentPlayer(Property property) {
        return currentPlayer.getProperties().contains(property);
    }

    /**
     * This method makes sure that the a house is bought and then built, it takes a locale which the player whiches to
     * buy houses on then makes sure that player is eligble, then tries to build and draw the correct amount
     * @param locale The locale that the player whishes to buy houses on.
     */
    public void buyHouse(Locale locale){
        if (currentPlayer.hasMonopoly(locale)){

            try {
                locale.buildHouse();
                currentPlayer.setCapital(currentPlayer.getCapital() - locale.getHouseCost());
            }
            catch (IllegalArgumentException ignored){
            }
        }
        else{
            System.out.println("You do not own all properties within this section");
        }
    }


    private boolean isCurrentSpaceTax() {
        return currentSpace instanceof Tax;
    }

    private boolean isCurrentSpaceChance() {
        return currentSpace instanceof Chance;
    }


    /**
     * Places the current player (index 0) in a temporary variable.
     * Player index 0 in the player list is then removed, which leads to a new current player who is not bankrupt.
     * Then adds the player stored in the temporary variable to the back of the list.
     * Finally checks how many players are left and ends the game if there is only one.
     */
    public void next() {
        Player temporaryPlayer = players.get(0);
        players.remove(0);
        players.add(temporaryPlayer);
        updateCurrentPlayer();

        if(currentPlayer.isBankrupt()) {
            next();
        }
        if(currentPlayer.equals(temporaryPlayer)) {
            endGame(temporaryPlayer);
        }
    }

    private void updateCurrentPlayer(){
        currentPlayer = players.get(0);
    }

    /**
     * Should prompt a popup announcing the winning player,
     * however currently only prints it and terminates the program
     * @param winningPlayer
     */
    private void endGame(Player winningPlayer) {
        //TODO insert popup here for view with controller
        System.out.println("GAME OVER! " + winningPlayer.getName() + " wins!");
        //on pressing continue/accept button:
        System.exit(0);
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
    public Space getSelectedSpace() { return selectedSpace; }

    /**
     * This notifes observers that a space has been selected and sets the selected space to that space.
     * @param selectedSpace
     */
    public void setSelectedSpace(Space selectedSpace) {
        this.selectedSpace = selectedSpace;
        notifyAllObservers();
    }

    /**
     * Notifies all observer of a change
     */
    public void notifyAllObservers() {
        for (Observer observer: observers){
            observer.update();
        }

    }
    /**
     * This method attaches an observer to this class.
     */
    public void attach(Observer observer){
        observers.add(observer);
    }

}
