package Model;

/**
 * PlayerItem is used as items for the selectPlayer comboBox in the TradeController class.
 *
 * @author williamProgrammerar
 */
public class PlayerItem {
    private final String playerName;
    private final Player player;

    public PlayerItem(Player player) {
        this.playerName = player.getName();
        this.player = player;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Player getPlayer() {
        return player;
    }
}
