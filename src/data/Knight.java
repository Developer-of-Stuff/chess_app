package data;

public class Knight extends Piece {

    public Knight(String color, Square currentSquare) {
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
