package gui;

import data.Square;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class MainController {

    @FXML private GridPane boardGUI;

    private final Square[][] board = new Square[8][8];

    public void initialize() {
        for (int i = 0; i < 8; i++) { // row
            for (int j = 0; j < 8; j++) { // column
                String color;
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        color = "#3E2722";
                    }
                    else {
                        color = "#F9C79F";
                    }
                }
                else {
                    if (j % 2 == 0) {
                        color = "#F9C79F";
                    }
                    else {
                        color = "#3E2722";
                    }
                }
                Square newSquare = new Square(false, i + 1, j + 1);
                board[i][j] = newSquare;
                Button newButton = new Button();
                newButton.setMinSize(50, 50);
                newButton.setStyle("-fx-background-color: " + color);
                boardGUI.add(newButton, i, j);
            }
        }
    }

}
