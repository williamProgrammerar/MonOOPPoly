package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Jail class responsible for handling Jail functionality
 *
 * @author Hedquist
 */

public class Jail {
    private final Map<Player, Integer> jailedPlayers = new HashMap<>();
    private final int jailFine;
    private final Dice dice;

    public Jail(int jailFine, Dice dice) {
        this.jailFine = jailFine;
        this.dice = dice;
    }

    /**
     * Checks if the player is in jail
     * If they are, checks if they rolled doubles
     * if they did, lets them out
     * If third turn in jail, lets them out for a fee
     * @param player player to check
     * @return if the player should move this turn or not
     */
    public boolean jailTurn(Player player) {
        if(isInJail(player)) {
            System.out.println("You're stuck at a re-exam, roll doubles or pay " + jailFine + "kr to finish it!");
            if(dice.isDoubles()) {
                System.out.println("You got out!");
                bail(player);
                return false;
            }
            else if(jailedPlayers.get(player) < 2) {
                jailedPlayers.replace(player, jailedPlayers.get(player) + 1);
                System.out.println("You're stuck!");
                return true;
            }
            else {
                player.setCapital(player.getCapital() - jailFine);
                System.out.println("You paid the bribe and have " + player.getCapital() + "kr");
                bail(player);
                return false;
            }
        }
        return false;
    }

    /**
     * Adds a player to Jail if not already in,
     * sets number of turns in jail to 0
     * @param newInmate
     */
    public void addToJail(Player newInmate) {
        if(!jailedPlayers.containsKey(newInmate)) {
            jailedPlayers.put(newInmate, 0);
            System.out.println(newInmate.getName() + " has been sent to re-exam!");
        }
    }

    /**
     * Removes player from jail if present
     * @param player
     */
    public void bail(Player player) {
        jailedPlayers.remove(player);
        System.out.println(player.getName() + " finished their exam");
    }

    public boolean isInJail(Player player) {
        return jailedPlayers.containsKey(player);
    }
}
