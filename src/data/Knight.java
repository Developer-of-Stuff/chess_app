package data;

public class Knight extends Piece {

    public Knight(String color, Square currentSquare) {
        super(color, currentSquare);
    }

    @Override
    public boolean move(Square destinationSquare) {
        int rowDistance = destinationSquare.getRow() - getCurrentSquare().getRow();
        int columnDistance = destinationSquare.getColumn() - getCurrentSquare().getColumn();
        if ((Math.abs(columnDistance) == 2 && Math.abs(rowDistance) == 1) || (Math.abs(rowDistance) == 2 && Math.abs(columnDistance) == 1)) {
            if (destinationSquare.isOccupied()) {
                return attack(destinationSquare);
            }
            else { return true; }
        }
        return false;
    }

    @Override
    public boolean attack(Square destinationSquare) {
        if (!destinationSquare.getPiece().getColor().equals(this.getColor())) {
            return !destinationSquare.getPiece().getClass().getSimpleName().equals("King");
        }
        return false;
    }
}
