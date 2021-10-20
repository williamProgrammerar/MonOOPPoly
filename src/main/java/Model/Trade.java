package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trade {

    private boolean isTradeOngoing = false;
    private final Map<Player, List<Property>> propertiesInTrade = new HashMap<>();
    private final Map<Player, Integer> currencyInTrade = new HashMap<>();

    private Player[] players;

    /**
     * startTrade when called will initiate the trading process.
     */
    public void startTrade(Player playerA, Player playerB) {
        if(!isTradeOngoing) {
            initTraders(playerA, playerB);
            isTradeOngoing = true;
        }
    }

    private void initTraders(Player playerA, Player playerB) {
        players = new Player[] {playerA, playerB};
    }

    /**
     * cancelTrade when called only works if there is currently a trade active.
     * Cancelling a trade will clear all offers added to it.
     */
    public void closeTrade() {
        if(isTradeOngoing) {
            isTradeOngoing = false;
            propertiesInTrade.clear();
            currencyInTrade.clear();
            players = new Player[] {};
        }
    }

    /**
     * addCurrencyToTrade adds a valid amount of money to the trade.
     *
     * @param player Player who tries to add money.
     * @param amount The amount of money they try to add.
     */
    public void addCurrencyToTrade(Player player, int amount) {
        if(playerCanAfford(player, amount) && isValidAmount(amount)) {
            currencyInTrade.put(player, amount);
        }
    }

    /**
     * playerCanAfford checks if the player has enough capital to add an amount of money to the trade.
     * "Enough capital" meaning that the amount of money the player tries to add to the trade is less or equals to
     * the total amount of money they have.
     *
     * @param player the player who is checked whether or not they can afford adding a certain amount of money to the trade.
     * @param amount the amount of money that will be compared to the player's capital.
     * @return returns true if the player can afford adding a certain amount of money to the trade.
     */
    private boolean playerCanAfford(Player player, int amount) {
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
        if(IsOwnerOfProperty(player, property)) {
            if(hasPropertyOffer(player)) {
                addToPropertyOffer(player, property);
            }
            else {
                List<Property> tmp = new ArrayList<>();
                tmp.add(property);
                propertiesInTrade.put(player, tmp);
            }
        }
    }

    /**
     * playerIsOwnerOfProperty checks whether or not a player owns a certain property.
     *
     * @param player the player who will be checked if he is the owner.
     * @param property the specific property in question.
     * @return returns true if player does own property and false if player does not.
     */
    private boolean IsOwnerOfProperty(Player player, Property property) {
        return player.getProperties().contains(property);
    }

    private boolean hasPropertyOffer(Player player) {
        return propertiesInTrade.containsKey(player);
    }

    private void addToPropertyOffer(Player player, Property property) {
        if(!propertiesInTrade.get(player).contains(property)) {
            propertiesInTrade.get(player).add(property);
        }
    }

    public void acceptTrade() {
        if(!currencyInTrade.isEmpty()) {
            transferCurrency();
        }
        if(!propertiesInTrade.isEmpty()) {
            transferProperty();
        }
        closeTrade();
    }

    private void transferCurrency() {
        int tmp1, tmp2;
        tmp1=tmp2=0;
        if(currencyInTrade.containsKey(players[0])) {
            tmp1 = currencyInTrade.get(players[0]);
        }
        if(currencyInTrade.containsKey(players[1])) {
            tmp2 = currencyInTrade.get(players[1]);
        }
        players[0].setCapital(players[0].getCapital() - tmp1 + tmp2);
        players[1].setCapital(players[1].getCapital() - tmp2 + tmp1);
        System.out.println(tmp1);
        System.out.println(tmp2);
    }

    private void transferProperty() {
        List<Property> tmp1, tmp2;
        tmp1=tmp2=null;
        if(propertiesInTrade.containsKey(players[0])) {
            tmp1 = propertiesInTrade.get(players[0]);
        }
        if(propertiesInTrade.containsKey(players[1])) {
            tmp2 = propertiesInTrade.get(players[1]);
        }
        if(tmp1 != null) {
            for (Property property : tmp1) {
                property.setOwnerId(players[1].getPlayerId());
                players[1].getProperties().add(property);
                players[0].getProperties().remove(property);
            }
        }
        if(tmp2 != null) {
            for (Property property : tmp2) {
                property.setOwnerId(players[0].getPlayerId());
                players[0].getProperties().add(property);
                players[1].getProperties().remove(property);
            }
        }
    }

    public boolean isTradeOngoing() {
        return isTradeOngoing;
    }

    public Map<Player, List<Property>> getPropertiesInTrade() {
        return propertiesInTrade;
    }

    public Map<Player, Integer> getCurrencyInTrade() {
        return currencyInTrade;
    }

    public void setTradeOngoing(boolean tradeOngoing) {
        isTradeOngoing = tradeOngoing;
    }

    public Player[] getPlayers() {
        return players;
    }
}
