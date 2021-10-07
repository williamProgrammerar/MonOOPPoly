package Model;

import javafx.scene.control.TextField;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameSettings {


    private int startCapital = 1500;

    private List<Player> players = new ArrayList<>();

    /**
     * This method makes sure that all active players has a name.
     * @return
     */
    public boolean checkPlayers(){
        for (Player player: players){
            if (player.getName().isEmpty()){
                return false;
            }
        }
        return true;
    }

    /**
     * this method sets the names and "state" in gameSettings from the input it has recieved.
     * @param nameList A list of names
     * @param stateList A list of states, if the player is human or not.
     */
    public void setPlayerInfo(List<String> nameList, List<String> stateList){
        for (int i = 0; i< players.size();i++){
            players.get(i).setName(nameList.get(i));
            players.get(i).setState(stateList.get(i));
        }
    }
    public List<Player> amountOfPlayers(){
        return players;
    }

    /**
     * This Method adds a player to the gameSettings it does not allow more then 4 players and throws an exception
     * if a user tries to implement more.
     */
    public void addPlayer() {
        if(players.size() < 4){
            players.add(new Player(players.size(),startCapital));
        }
        else {
            System.out.println("This game supports a maximum of 4 players");
            throw new IllegalArgumentException();
        }
    }
    public void removePlayer(){
        players.remove(players.size() -1);
    }
    public List<Player> getPlayers() {
        return players;
    }
    public int getStartCapital() {
        return startCapital;
    }
    public void setPlayerName(){

    }

    public void setStartCapital(int startCapital) {
        this.startCapital = startCapital;
    }


    public class TooManyPlayersExpection extends Throwable {
    }
}
