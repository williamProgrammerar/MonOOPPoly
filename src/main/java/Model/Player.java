package Model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int capital;
    private String name;
    private String state;
    private final int playerId;
    private int position;
    private boolean hasPassedGo;
    private List<Property> properties = new ArrayList<>();

    public Player(int playerId, int capital) {
        this.playerId = playerId;
        this.capital = capital;
        this.position = 0;
        this.name = "Inget namn än";
    }

    /**
     * move() updates position by adding int spaces to the previous value of position.
     * if statement checks if the position has exceeded the amount of spaces on the board.
     * if(true): correct the position according to the board, hasPassedGo = true
     * else if statement checks if the position is less than space 0 on the board (GO).
     * else if(true): correct the position according to the board, hasPassedGo = false
     * @param spaces number of spaces player should move
     */
    public void move(int spaces) {
        position += spaces;
        if(position >= 40) {
            position -= 40;
            hasPassedGo = true;
        }
        else if (position < 0) {
            position += 40;
            hasPassedGo = false;
        }
        else {
            hasPassedGo = false;
        }
    }

    /**
     * moveTo() moves the player to a specific position.
     * if int space is less than current position, it means the player has either passed go or been forced to move backwards.
     * boolean passedGo determines what happened.
     * @param space space player should move to
     * @param isForwardMovement boolean that determines whether player walks forward or not
     */
    public void moveTo(int space, boolean isForwardMovement) {
        hasPassedGo = (space < position) && isForwardMovement;
        position = space;
    }

    public int getPosition() {
        return position;
    }

    public boolean HasPassedGo() {
        return hasPassedGo;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public void buyProperty(Property property) {
        if(!property.isOwned()) {
            if (property.getPrice() <= capital) {
                properties.add(property);
                capital -= property.getPrice();
                property.setOwned(true);
                property.setOwnerId(playerId);
                System.out.println("Player " + playerId + " bought " + property.getSpaceName());
                System.out.println("Player " + playerId + " has " + capital + " dollars left");
            } else {
                System.out.println("Not enough capital");
            }
        }
        else {
            System.out.println("Property already has owner");
        }
    }

    public List<Property> getProperties() {
        return properties;
    }
}
