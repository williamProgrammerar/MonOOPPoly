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
     * @author williamProgrammerar
     */
    public void move(int spaces) {
        if(!hasMoved) {
            //currentPlayer = players.get(0);
            if (!jailTurn(currentPlayer)) {
                currentPlayer.move(spaces);
                currentSpace = board.getSpace(currentPlayer.getPosition());

                recieveGoPay(currentPlayer);
                inspectCurrentSpace();

                hasMoved = true;

                System.out.println("Player" + currentPlayer.getPlayerId() + " landed on: " + currentSpace.getSpaceName());
                System.out.println(currentPlayer.getPosition());

            }
        }
    }

    /**
     * If the player has passed go this turn, recieve appropriate payment
     * @param currentPlayer
     */
    private void recieveGoPay(Player currentPlayer) {
        if(currentPlayer.HasPassedGo()) {
            int salary = 200;
            currentPlayer.setCapital(currentPlayer.getCapital() + salary); //this should maybe be a variable, you could change it in settings
            System.out.println(currentPlayer.getName() + " passed GO and recieved " + salary + "kr");
        }
    }

    /**
     * inspectCurrentSpace() checks what type the current space is.
     * If the current space is a property, owned by another player and not mortgaged then the player
     * has to pay rent to the owner of the property.
     * @author williamProgrammerar
     * @author Hedquist
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
            currentPlayer.setTurnsInJail(1);
            System.out.println("Player " + currentPlayer.getPlayerId() + " failed their exam and has been sent to redo it!");
        }
    }

    /**
     * Checks if the player is in Jail. If they are, they must roll doubles in order to get out.
     * If they do, move them according to the dice roll.
     * If they fail for 3 turns, pay fine and move according to dice roll.
     * Sets turnsInJail to 0 when they get out.
     * @param currentPlayer
     * @return if the player is in jail or not
     * @author Hedquist
     */
    private boolean jailTurn(Player currentPlayer) {
        if(currentPlayer.getTurnsInJail() > 0 && board.getSpace(currentPlayer.getPosition()).getSpaceName().equals("OMTENTA")) {
            int jailFine = 50;
            System.out.println("You're stuck at a re-exam, roll doubles or pay " + jailFine + "kr to finish it!");
            dice.rollDice(); //this needs to be coupled to the view
            if(dice.isDoubles()) {
                System.out.println("You got out!");

                currentPlayer.move(dice.getSum());
                currentSpace = board.getSpace(currentPlayer.getPosition());

                inspectCurrentSpace();

                System.out.println("Player" + currentPlayer.getPlayerId() + " landed on: " + currentSpace.getSpaceName());
                System.out.println(currentPlayer.getPosition());

                currentPlayer.setTurnsInJail(0);
            } else {
                System.out.println("You're stuck!");
                currentPlayer.setTurnsInJail(currentPlayer.getTurnsInJail() + 1);
                if(currentPlayer.getTurnsInJail()>3) {
                    currentPlayer.setCapital(currentPlayer.getCapital() - jailFine);
                    System.out.println("You paid the bribe and have " + currentPlayer.getCapital());

                    currentPlayer.move(dice.getSum());
                    currentSpace = board.getSpace(currentPlayer.getPosition());

                    inspectCurrentSpace();

                    System.out.println("Player" + currentPlayer.getPlayerId() + " landed on: " + currentSpace.getSpaceName());
                    System.out.println(currentPlayer.getPosition());

                    currentPlayer.setTurnsInJail(0);
                }
            }
            return true;
        }
        return false;
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

    /**
     * getPlayerUsingID checks if there is a player with a specific ID and then returns the player with that ID.
     *
     * @param ID the specific ID that will be used to find a player.
     * @return returns the player who's ID matches the one used for the search.
     * @throws Exception This should never have to be thrown.
     */
    public Player getPlayerUsingID(int ID) throws Exception {
        for (Player player : getPlayers()) {
            if(player.getPlayerId() == ID) { return player; }
        }
        throw new Exception("No player matching the ID");
    }
}
