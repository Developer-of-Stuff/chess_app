package data;

public class Rook extends Piece {

    private boolean firstMove = true;

    public Rook(String color, Square currentSquare) {
        super(color, currentSquare);
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    @Override
    public boolean move(Square destinationSquare) {
        int rowDistance = destinationSquare.getRow() - getCurrentSquare().getRow();
        int columnDistance = destinationSquare.getColumn() - getCurrentSquare().getColumn();
        if ((rowDistance != 0 && columnDistance == 0) || (rowDistance == 0 && columnDistance != 0)) {
            if (destinationSquare.isOccupied()) {
                return attack(destinationSquare);
            }
            else {
                if (firstMove) {
                    firstMove = false;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean attack(Square destinationSquare) {
        if (!destinationSquare.getPiece().getColor().equals(this.getColor())) {
            if (!destinationSquare.getPiece().getClass().getSimpleName().equals("King") && firstMove) {
                firstMove = false;
            }
            return !destinationSquare.getPiece().getClass().getSimpleName().equals("King");
        }
        return false;
    }
}
