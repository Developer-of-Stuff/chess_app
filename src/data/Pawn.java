package data;

public class Pawn extends Piece {

    private boolean firstMove = true;

    public Pawn(String color, Square currentSquare) {
        super(color, currentSquare);
    }

    @Override
    public boolean move(Square destinationSquare) {
        int rowDistance = destinationSquare.getRow() - getCurrentSquare().getRow();
        int columnDistance = destinationSquare.getColumn() - getCurrentSquare().getColumn();
        if (getColor().equals("White")) {
            if (rowDistance <= 2 && rowDistance > 0) {
                if (destinationSquare.isOccupied()) {
                    return attack(destinationSquare);
                }
                else {
                    if (columnDistance == 0) {
                        if (firstMove) {
                            firstMove = false;
                            return true;
                        } else {
                            return rowDistance == 1;
                        }
                    }
                }
            }
        }
        else {
            if (rowDistance >= -2 && rowDistance < 0) {
                if (destinationSquare.isOccupied()) {
                    return attack(destinationSquare);
                }
                else {
                    if (columnDistance == 0) {
                        if (firstMove) {
                            firstMove = false;
                            return true;
                        } else return rowDistance == -1;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean attack(Square destinationSquare) {
        int columnDistance = destinationSquare.getColumn() - getCurrentSquare().getColumn();
        if (!destinationSquare.getPiece().getColor().equals(this.getColor())) {
            if (columnDistance == 1 || columnDistance == -1) {
                if (!destinationSquare.getPiece().getClass().getSimpleName().equals("King") && firstMove) {
                    firstMove = false;
                }
                return !destinationSquare.getPiece().getClass().getSimpleName().equals("King");
            }
            else {
                return false;
            }
        }
        return false;
    }
}
