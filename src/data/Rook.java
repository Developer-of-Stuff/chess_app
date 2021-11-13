package data;

import gui.MainController;

public class Rook extends Piece {

    private boolean firstMove;

    public Rook(String color, Square currentSquare) {
        super(color, currentSquare);
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
            return !destinationSquare.getPiece().getClass().getSimpleName().equals("King");
        }
        return false;
    }
}
