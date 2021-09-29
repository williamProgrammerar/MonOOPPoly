package Model;

import View.Piece;

public class Player {
    private int capital;
    private Piece piece;
    private int pieceID;   // maybe not the best approach, might add unnecessary dependencies
    private int position;
    private boolean hasPassedGo;

    public Player(int pieceID) throws Exception {
        this.pieceID = pieceID;
        this.piece = new Piece(pieceID);
        this.capital = 1500;
        this.position = 0;
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
    }

    public void moveTo(int space) {
        position = space;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }
}
