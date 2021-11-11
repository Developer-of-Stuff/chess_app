package data;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class Square extends Button {

    private boolean occupied;
    private Piece piece;
    private final StackPane display;
    private final ImageView pieceIcon;
    private final int row;
    private final int column;

    public Square(int row, int column) {
        this.occupied = false;
        this.row = row;
        this.column = column;
        this.display = new StackPane();
        this.pieceIcon = new ImageView();
        this.pieceIcon.setPreserveRatio(true);
        this.pieceIcon.setFitWidth(70);
        this.pieceIcon.setTranslateX(8);
        this.pieceIcon.setTranslateY(-8);
        this.display.getChildren().add(new Text(getSquareID()));
        this.display.getChildren().add(this.pieceIcon);
        this.display.setAlignment(Pos.BOTTOM_LEFT);
        setGraphic(this.display);
        setMinSize(100, 100);
        setFocusTraversable(false);
        getStyleClass().add("square");
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Piece getPiece() { return piece; }

    public void setPiece(Piece piece) {
        if (piece == null) {
            this.piece = null;
            setPieceIconView(null);
        }
        else {
            this.piece = piece;
            setPieceIconView(this.piece.getPieceIcon());
        }
    }

    public StackPane getDisplay() { return display; }

    public void setPieceIconView(Image pieceImage) { this.pieceIcon.setImage(pieceImage); }

    public int getRow() { return row; }

    public int getColumn() { return column; }

    public char getColumnLetter() {
        switch(column) {
            case 1: return 'A';
            case 2: return 'B';
            case 3: return 'C';
            case 4: return 'D';
            case 5: return 'E';
            case 6: return 'F';
            case 7: return 'G';
            default: return 'H';
        }
    }

    public String getSquareID() { return "" + getColumnLetter() + getRow(); }
}
