package Model;

import javafx.scene.control.TextField;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameSettings {


    private int startCapital = 1500;

    private List<Player> players = new ArrayList<>();

    public boolean checkPlayers(){
        for (Player player: players){
            if (player.getName().isEmpty()){
                return false;
            }
        }
        return true;
    }
    public void setPlayerInfo(List<String> nameList, List<String> stateList){
        for (int i = 0; i< players.size();i++){
            players.get(i).setName(nameList.get(i));
            players.get(i).setState(stateList.get(i));
        }
    }
    public List<Player> amountOfPlayers(){
        return players;
    }
    public void addPlayer(){
        players.add(new Player(players.size(),startCapital));
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


}
