package gui;

import data.Square;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class MainController {

    @FXML private GridPane boardGUI;
    @FXML private HBox options;

    public void initialize() {
        for (int i = 0; i < 8; i++) { // column
            for (int j = 0; j < 8; j++) { // row
                String type;
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        type = "dark-square";
                    }
                    else {
                        type = "light-square";
                    }
                }
                else {
                    if (j % 2 == 0) {
                        type = "light-square";
                    }
                    else {
                        type = "dark-square";
                    }
                }
                Square newSquare = new Square(j + 1, i + 1);
                newSquare.getStyleClass().add(type);
                newSquare.getDisplay().getStyleClass().add(type);
                newSquare.onMouseClickedProperty().set(click -> System.out.println(newSquare.getSquareID()));
                boardGUI.add(newSquare, i, Math.abs(j - 7));
            }
        }
        options.setSpacing(100);
        for (Node node : options.getChildren()) {
            ((Button) node).setMinWidth(150);
        }
    }

    public void newGameHandler() {
        String color = "";
        for (Node node : boardGUI.getChildren()) {
            Square square = (Square) node;
            int row = square.getRow();
            int column = square.getColumn();

            if (row == 1) { color = "White"; }
            else if (row == 2) { square.setPieceIcon(new Image("piece_icons/WhitePawn.png")); }
            else if (row == 7) { square.setPieceIcon(new Image("piece_icons/BlackPawn.png")); }
            else if (row == 8) { color = "Black"; }

            if (row == 1 || row == 8) {
                switch (column) {
                    case 1: case 8:
                        square.setPieceIcon(new Image("piece_icons/" + color + "Rook.png"));
                        break;
                    case 2: case 7:
                        square.setPieceIcon(new Image("piece_icons/" + color + "Knight.png"));
                        break;
                    case 3: case 6:
                        square.setPieceIcon(new Image("piece_icons/" + color + "Bishop.png"));
                        break;
                    case 4:
                        square.setPieceIcon(new Image("piece_icons/" + color + "Queen.png"));
                        break;
                    case 5:
                        square.setPieceIcon(new Image("piece_icons/" + color + "King.png"));
                }
            }
        }
    }

}
