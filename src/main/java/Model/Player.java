package Model;

import View.Piece;

public class Player {
    private final Piece piece;
    private int capital;
    private final int playerId;
    private int position;
    private boolean hasPassedGo;

    public Player(int playerId) throws Exception {
        this.playerId = playerId;
        this.capital = 1500;
        this.position = 0;
        this.piece = new Piece(1, this);
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
        //piece.getImg().setX(piece.getImg().getX() + movement);
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
        notifyPiece();
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
        notifyPiece();
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

    public void notifyPiece() {
        piece.updatePosition();
    }
}
