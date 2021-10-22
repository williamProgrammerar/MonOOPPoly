package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Trade class supports two players to trade currency and properties with each other.
 *
 * @author williamProgrammerar
 */
public class Trade {

    private boolean isTradeActive = false;
    private final Map<Player, List<Property>> propertyOffers = new HashMap<>();
    private final Map<Player, Integer> currencyOffers = new HashMap<>();

    private Player[] players;

    /**
     * startTrade has to be called FIRST in order to proceed with the trading.
     */
    public void startTrade(Player playerA, Player playerB) {
        if(!isTradeActive) {
            setUpTrade(playerA, playerB);
        }
    }

    private void setUpTrade(Player playerA, Player playerB) {
        initTraders(playerA, playerB);
        isTradeActive = true;
    }

    private void initTraders(Player playerA, Player playerB) {
        players = new Player[] {playerA, playerB};
    }

    /**
     * closeTrade resets all offers and stops the trade process.
     * Used after a trade is completed to reset all information or can be called externally at anytime during
     * an active trade to cancel a trade.
     */
    public void closeTrade() {
        if(isTradeActive) {
            resetTrade();
        }
    }

    /**
     * resets all trade information.
     */
    private void resetTrade() {
        isTradeActive = false;
        clearOffers();
        clearPlayers();
    }

    private void clearOffers() {
        propertyOffers.clear();
        currencyOffers.clear();
    }

    private void clearPlayers() {
        players = new Player[] {};
    }

    /**
     * addCurrencyToTrade adds a valid amount of money to the trade.
     *
     * @param player Player who tries to add money.
     * @param amount The amount of money they try to add.
     */
    public void addCurrencyToTrade(Player player, int amount) {
        if(canAfford(player, amount) && isValidAmount(amount)) {
            currencyOffers.put(player, amount);
        }
    }

    /**
     * canAfford checks if the player has enough capital to add an amount of money to the trade.
     * "Enough capital" meaning that the amount of money the player tries to add to the trade is less or equals to
     * the total amount of money they have.
     *
     * @param player the player who is checked whether or not they can afford adding a certain amount of money to the trade.
     * @param amount the amount of money that will be compared to the player's capital.
     * @return returns true if the player can afford adding a certain amount of money to the trade.
     */
    private boolean canAfford(Player player, int amount) {
        return player.getCapital() >= amount;
    }

    /**
     * isValidAmount checks if the amount is greater than 0, to prevent player from adding a negative amount of
     * currency to the trade or adding 0 currency.
     *
     * @param amount amount of money player is trying to add
     * @return returns whether or not the amount is valid (amount > 0)
     */
    private boolean isValidAmount(int amount) {
        return amount > 0;
    }

    /**
     * addPropertyToTrade places a property owned by player that's not already added to the trade in the trade.
     *
     * @param player player that attempts to add one of their properties to the trade.
     * @param property the property the player is attempting to add.
     */
    public void addPropertyToTrade(Player player, Property property) {
        if(isOwner(player, property)) {
            if(hasPropertyOffer(player)) {
                addPropertyOffer(player, property);
            }
            else {
                newPropertyOffer(player, property);
            }
        }
    }

    /**
     * isOwner checks whether or not a player owns a certain property.
     *
     * @param player the player who will be checked if he is the owner.
     * @param property the specific property in question.
     * @return returns true if player does own property and false if player does not.
     */
    private boolean isOwner(Player player, Property property) {
        return player.getProperties().contains(property);
    }

    /**
     * hasPropertyOffer checks if a player has a property offer.
     * @param player the player.
     * @return returns whether or not player has a property offer.
     */
    private boolean hasPropertyOffer(Player player) {
        return propertyOffers.containsKey(player);
    }

    /**
     * addPropertyOffer.
     * If the player already has a property offer, addPropertyOffer allows player to add more properties to their offer.
     * @param player the player.
     * @param property their property.
     */
    private void addPropertyOffer(Player player, Property property) {
        if(!propertyOffers.get(player).contains(property)) {
            propertyOffers.get(player).add(property);
        }
    }

    /**
     * newPropertyOffer is called when a player that does not have a property offer wishes to make one.
     * @param player the player.
     * @param property their property.
     */
    private void newPropertyOffer(Player player, Property property) {
        List<Property> tmp = new ArrayList<>();
        tmp.add(property);
        propertyOffers.put(player, tmp);
    }

    /**
     * acceptTrade should be called after both players have placed their offers and agreed on the trade.
     * This will then exchange their offers and end the trade process.
     */
    public void acceptTrade() {
        if(isTradeActive) {
            exchangeOffers();
            closeTrade();
        }
    }

    private void exchangeOffers() {
        if(!currencyOffers.isEmpty()) {
            exchangeCurrency();
        }
        if(!propertyOffers.isEmpty()) {
            exchangeProperty();
        }
    }

    /**
     * exchangeCurrency uses two integers: amountA and amountB to represent the amount of money each player
     * is exchanging/transferring to each other.
     */
    private void exchangeCurrency() {
        int amountA, amountB;
        amountA = amountB = 0;

        if(hasCurrencyOffer(players[0])) {
            amountA = getCurrencyOffer(0);
        }
        if(hasCurrencyOffer(players[1])) {
            amountB = getCurrencyOffer(1);
        }

        transferCurrency(0, amountA, amountB);
        transferCurrency(1, amountB, amountA);
    }

    private boolean hasCurrencyOffer(Player player) {
        return currencyOffers.containsKey(player);
    }

    private int getCurrencyOffer(int index) {
        return currencyOffers.get(players[index]);
    }

    /**
     * transferCurrency calculates the total capital a player has after giving and/or receiving a certain amount of money.
     * @param index determines what player's capital will be updated.
     * @param give the amount of money player is giving away, hence why it's reduced from their capital.
     * @param receive the amount of money player is receiving, hence why it's added to their capital.
     */
    private void transferCurrency(int index, int give, int receive) {
        players[index].setCapital(players[index].getCapital() - give + receive);
    }

    /**
     * exchangeProperty uses two lists: propertyListA and propertyListB to represent each player's property offer.
     * They are both set as null in the beginning of the method in case a player does not have a property offer.
     */
    private void exchangeProperty() {
        List<Property> propertyListA, propertyListB;
        propertyListA = propertyListB = null;

        if(hasPropertyOffer(players[0])) {
            propertyListA = getPropertyOffer(0);
        }
        if(hasPropertyOffer(players[1])) {
            propertyListB = getPropertyOffer(1);
        }

        if(propertyListA != null) {
            transferProperty(propertyListA,1,0);
        }
        if(propertyListB != null) {
            transferProperty(propertyListB,0,1);
        }
    }

    private List<Property> getPropertyOffer(int index) {
        return propertyOffers.get(players[index]);
    }

    /**
     * transferProperty takes care of changing the ownership of properties after a trade has been made.
     * @param properties the list of properties that will receive a new owner.
     * @param newOwner the new owner of the properties.
     * @param previousOwner the previous owner of the properties.
     */
    private void transferProperty(List<Property> properties, int newOwner, int previousOwner) {
        for (Property property : properties) {
            property.setOwnerId(players[newOwner].getPlayerId());
            players[newOwner].getProperties().add(property);
            players[previousOwner].getProperties().remove(property);
        }
    }

    // Public getters
    public boolean isTradeActive() {
        return isTradeActive;
    }

    public Map<Player, List<Property>> getPropertyOffers() {
        return propertyOffers;
    }

    public Map<Player, Integer> getCurrencyOffers() {
        return currencyOffers;
    }

    public void setTradeActive(boolean tradeActive) {
        isTradeActive = tradeActive;
    }

    public Player[] getPlayers() {
        return players;
    }
}
