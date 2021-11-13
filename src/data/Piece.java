package data;

import javafx.scene.image.Image;

public abstract class Piece {

    private final String color;
    private Square currentSquare;
    private Image pieceIcon;

    public Piece(String color, Square currentSquare) {
        this.color = color;
        this.currentSquare = currentSquare;
        setPieceIcon(new Image("piece_icons/" + color + this.getClass().getSimpleName() + ".png"));
    }

    public String getColor() {
        return color;
    }

    public Square getCurrentSquare() { return currentSquare; }

    public void setCurrentSquare(Square currentSquare) { this.currentSquare = currentSquare; }

    public Image getPieceIcon() { return pieceIcon; }

    public void setPieceIcon(Image pieceIcon) { this.pieceIcon = pieceIcon; }

    public abstract boolean move(Square destinationSquare);

    public abstract boolean attack(Square destinationSquare);
}
