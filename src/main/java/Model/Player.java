package Model;

import View.Piece;

public class Player {
    private int capital;
    private Piece piece;
    private int pieceID;   // maybe not the best approach, might add unnecessary dependencies
    private int position; // Temporary until we have fixed tile which should handle this

    public Player(int pieceID) throws Exception {
        this.pieceID = pieceID;
        this.piece = new Piece(pieceID);
        this.capital = 1500;
        this.position = 0;
    }

    public void move(int movement) {
        piece.getImg().setX(piece.getImg().getX() + movement);
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }
}
