package data;

import javafx.scene.image.Image;

public class Pawn extends Piece {

    private boolean firstMove = true;

    public Pawn(String color, Square currentSquare) {
        super(color, currentSquare);
        setPieceIcon(new Image("piece_icons/" + color + "Pawn.png"));
    }

    @Override
    public boolean move(Square destinationSquare) {
        int rowDistance = destinationSquare.getRow() - getCurrentSquare().getRow();
        int columnDistance = destinationSquare.getColumn() - getCurrentSquare().getColumn();
        if (getColor().equals("White")) {
            if (rowDistance <= 2 && rowDistance > 0) {
                if (destinationSquare.isOccupied()) {
                    if (!destinationSquare.getPiece().getColor().equals(this.getColor())) {
                        if (columnDistance == 1 || columnDistance == -1) {
                            return attack();
                        }
                        else {
                            return false;
                        }
                    }
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
                    if (!destinationSquare.getPiece().getColor().equals(this.getColor())) {
                        if (columnDistance == 1 || columnDistance == -1) {
                            return attack();
                        }
                    }
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
    public boolean attack() {
        return false;
    }
}
