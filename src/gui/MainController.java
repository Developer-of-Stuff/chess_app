package gui;

import data.Square;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class MainController {

    @FXML private GridPane boardGUI;

    private final Square[][] board = new Square[8][8];

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
                Square newSquare = new Square(false, j + 1, i + 1);
                board[i][j] = newSquare;
                Button newButton = new Button();
                newButton.setText("" + newSquare.getColumnLetter() + newSquare.getRow());
                newButton.setAlignment(Pos.BOTTOM_LEFT);
                newButton.setMinSize(75, 75);
                newButton.setFocusTraversable(false);
                newButton.getStyleClass().add("square");
                newButton.getStyleClass().add(type);
                newButton.onMouseClickedProperty().set(click -> System.out.println(newButton.getText()));
                boardGUI.add(newButton, i, Math.abs(j - 7));
            }
        }
    }

}
