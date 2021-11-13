package data;

public class King extends Piece {

    private boolean firstMove = true;

    public King(String color, Square currentSquare) {
        super(color, currentSquare);
    }

    @Override
    public boolean move(Square destinationSquare) {
        return false;
    }

    @Override
    public boolean attack(Square destinationSquare) {
        return false;
    }
}
