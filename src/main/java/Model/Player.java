package Model;

import View.Piece;

public class Player {
    private int capital;
    private Piece piece;
    private int pieceID;   // maybe not the best approach, might add unnecessary dependencies
    private int position; // Temporary until we have fixed tile which should handle this

    public Player(int capital, int pieceID, int position) throws Exception {
        this.capital = capital;
        this.pieceID = pieceID;
        this.position = position;
        this.piece = new Piece(pieceID);
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
