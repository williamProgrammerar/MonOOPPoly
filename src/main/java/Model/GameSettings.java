package Model;

import java.util.ArrayList;
import java.util.List;

public class GameSettings {
    public List<Player> getPlayers() {
        return players;
    }

    private List<Player> players = new ArrayList<>();

    public boolean checkPlayers(){
        for (Player player: players){
            if (player.getName().isEmpty() || player.getState().isEmpty()){
                return false;
            }
        }
        return true;
    }
    public List<Player> amountOfPlayers(){
        return players;
    }
    public void addPlayer(){
        players.add(new Player(players.size()));
    }
    public void removePlayer(){
        players.remove(players.size() -1);
    }

}
