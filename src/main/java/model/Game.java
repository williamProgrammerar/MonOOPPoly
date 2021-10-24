package model;

import observers.Observable;
import observers.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Game class is responsible for
 *
 * @author williamProgrammerar
 * @author rhedinh
 * @author JonEmilsson
 * @author HedQuist
 */

public class Game implements Observable {
    private final RollDice dice = new RollDice(2, 6);
    private final Board board = new Board(dice);
    private final List<Player> players = new ArrayList<>();
    private final Jail jail = new Jail(50, dice);
    private Space currentSpace;


    private Auction auction;
    int salary;
    private Player currentPlayer;
    private Space selectedSpace;
    private boolean hasMoved = false;
    private boolean lock = false;
    List<Observer> observers = new ArrayList<>();

    public Game(GameSettings gameSettings) {
        this.players.addAll(gameSettings.getPlayers());
        this.salary = gameSettings.getSalary();
        updateCurrentPlayer();
    }

    /**
     * Moves the current player sum spaces.
     */
    public void move(int spaces) {
        if (!hasMoved) {
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
     *
     * @param currentPlayer currentPlayer.
     */
    private void receiveGoPay(Player currentPlayer) {
        if (currentPlayer.HasPassedGo()) {
            addPlayerCapital(salary, currentPlayer); //this should maybe be a variable, you could change it in settings
            System.out.println(currentPlayer.getName() + " passed GO and received " + salary + "kr");
        }
    }

    /**
     * inspectCurrentSpace() checks what type the current space is.
     * If the current space is a property, owned by another player and not mortgaged then the player
     * has to pay rent to the owner of the property.
     * If it's a tax it withdraws money from player
     * If its on U it sends player to Omtenta
     */
    private void inspectCurrentSpace() {
        if (isCurrentSpaceProperty()) {
            landedOnProperty();
        } else if (isCurrentSpaceTax()) {
            landedOnTax();
        } else if (isCurrentSpaceU()) {
            landedOnU();
        }
    }

    private void landedOnProperty() {
        Property property = (Property) currentSpace;
        if (property.isOwned() && !isOwnedByCurrentPlayer(property) && !property.isMortgaged()) {
            landedOnOwnedProperty(property);
        }
    }

    private void landedOnOwnedProperty(Property property) {
        subtractPlayerCapital(property.getRent(), currentPlayer);
        System.out.println("Player " + currentPlayer.getPlayerId() + " has " + currentPlayer.getCapital());
        for (Player player : players) {
            if (player.getPlayerId() == property.getOwnerId()) {
                addPlayerCapital(property.getRent(), player);
                System.out.println(property.getRent());
                System.out.println("Player " + player.getPlayerId() + " has " + player.getCapital());
            }
        }
    }

    private void landedOnTax() {
        Tax tax = (Tax) currentSpace;
        subtractPlayerCapital(tax.getTax(), currentPlayer);
        System.out.println("Player " + currentPlayer.getPlayerId() + " had to pay tax and has " + currentPlayer.getCapital());
    }

    private void landedOnU() {
        currentPlayer.moveTo(10, false);
        jail.addToJail(currentPlayer);
    }

    public void addPlayerCapital(int moneyGained, Player player) {
        player.setCapital(player.getCapital() + moneyGained);
        notifyObservers();
    }

    public void subtractPlayerCapital(int moneyLost, Player player) {
        player.setCapital(player.getCapital() - moneyLost);
        notifyObservers();
    }

    public void endTurn() {
        if (dice.isHasRolledDice()) {
            next();
            dice.setHasRolledDice(false);
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
        if (currentPlayer.getCapital() < 1) {
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
     *
     * @param locale The locale that the player whishes to buy houses on.
     */
    public void buyHouse(Locale locale) {
        if (currentPlayer.hasMonopoly(locale)) {

            try {
                locale.buildHouse();
                subtractPlayerCapital(locale.getHouseCost(), currentPlayer);
                System.out.println("House built");
                System.out.println(currentPlayer.getCapital());
            } catch (IllegalArgumentException ignored) {
            }

        } else {
            System.out.println("You do not own all properties within this section");
        }
    }


    private boolean isCurrentSpaceTax() {
        return currentSpace instanceof Tax;
    }

    private boolean isCurrentSpaceU() {
        return currentSpace.getSpaceName().equals("U");
    }


    /**
     * Places the current player (index 0) in a temporary variable.
     * Player index 0 in the player list is then removed, which leads to a new current player.
     * Finally, adds the player stored in the temporary variable to the back of the list.
     */
    public void next() {
        Player temporaryPlayer = players.get(0);
        players.remove(0);
        players.add(temporaryPlayer);
        updateCurrentPlayer();
        if (currentPlayer.isBankrupt()) {
            next();
        }

        if (currentPlayer.equals(temporaryPlayer)) {
            endGame(temporaryPlayer);
        }
    }

    private void updateCurrentPlayer() {
        currentPlayer = players.get(0);
    }

    /**
     * Should prompt a popup announcing the winning player,
     * however currently only prints it and terminates the program.
     *
     * @param winningPlayer the player who won.
     */
    private void endGame(Player winningPlayer) {
        //TODO insert popup here for view with controller
        System.out.println("GAME OVER! " + winningPlayer.getName() + " wins!");
        //on pressing continue/accept button:
        System.exit(0);
    }

    public RollDice getDice() {
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

    public Space getSelectedSpace() {
        return selectedSpace;
    }

    public Auction getAuction() {
        return auction;
    }

    /**
     * getPlayerUsingID checks if there is a player with a specific ID and then returns the player with that ID.
     *
     * @param ID the specific ID that will be used to find a player.
     * @return returns the player who's ID matches the one used for the search.
     * @throws Exception This should never have to be thrown.
     */
    public Player getPlayerUsingID(int ID) {
        for (Player player : getPlayers()) {
            if (player.getPlayerId() == ID) {
                return player;
            }
        }
        throw new IllegalArgumentException("No player matching the ID");
    }


    /**
     * This notifes observers that a space has been selected and sets the selected space to that space.
     *
     * @param selectedSpace
     */
    public void setSelectedSpace(Space selectedSpace) {
        this.selectedSpace = selectedSpace;
        notifyObservers(selectedSpace);
    }

    /**
     * This method makes sure that mortgage gets paid back appropriately
     */
    public void payBackMortgage() {
        if (isPropertyOwnedByPlayer((Property) getSelectedSpace(), currentPlayer) && ((Property) getSelectedSpace()).isMortgaged()) {
            ((Property) getSelectedSpace()).setMortgaged(false);
            subtractPlayerCapital(((Property) getSelectedSpace()).getMortgage(), currentPlayer);
        } else {
            System.out.println("You don't own this property");
            throw new IllegalArgumentException();
        }
    }

    /**
     * This method makes sure you can mortgage a locale.
     */
    public void mortgageLocale() {
        if (isPropertyOwnedByPlayer((Property) getSelectedSpace(), currentPlayer) && !((Property) getSelectedSpace()).isMortgaged()) {
            ((Property) getSelectedSpace()).setMortgaged(true);
            addPlayerCapital(((Property) getSelectedSpace()).getMortgage(), currentPlayer);
        } else {
            System.out.println("You cant mortgage locale you don't own or one you have already mortgaged.");
            throw new IllegalArgumentException();
        }
    }

    private boolean isPropertyOwnedByPlayer(Property property, Player player) {
        return player.getProperties().contains(property);
    }

    @Override
    public void attachObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Object arg) {
        for (Observer observer : observers) {
            observer.update(this, arg);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this, null);
        }
    }

    public void startAuction() {
        this.auction = new Auction();
        auction.startAuction(players, (Property) currentSpace);
    }
}