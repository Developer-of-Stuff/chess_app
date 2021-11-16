package data;

public class King extends Piece {

    private boolean firstMove = true;

    public King(String color, Square currentSquare) {
        super(color, currentSquare);
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    @Override
    public boolean move(Square destinationSquare) {
        int rowDistance = destinationSquare.getRow() - getCurrentSquare().getRow();
        int columnDistance = destinationSquare.getColumn() - getCurrentSquare().getColumn();
        if (Math.abs(rowDistance) <= 1 && Math.abs(columnDistance) <= 1) {
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
        else if (Math.abs(columnDistance) == 2 && firstMove) {
            firstMove = false;
            return !destinationSquare.isOccupied();
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
