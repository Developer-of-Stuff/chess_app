package gui;

import data.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class MainController {

    @FXML private GridPane boardGUI;
    @FXML private HBox options;

    private Square moveOriginSquare;
    private String moveOrder = "White";
    private boolean flipped = false;

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

                newSquare.setOnDragDetected(mouseEvent -> {
                    if (newSquare.getPiece() != null) {
                        moveOriginSquare = newSquare;
                        Dragboard db = newSquare.startDragAndDrop(TransferMode.ANY);
                        ClipboardContent content = new ClipboardContent();
                        Image selectedImage = newSquare.getPiece().getPieceIcon();
                        content.putImage(selectedImage);
                        newSquare.setPieceIconView(null);
                        db.setContent(content);
                        mouseEvent.consume();
                    }
                });

                newSquare.setOnDragOver(dragEvent -> {
                    if (dragEvent.getGestureSource() != newSquare && dragEvent.getDragboard().hasImage()) {
                        dragEvent.acceptTransferModes(TransferMode.MOVE);
                    }
                    dragEvent.consume();
                });

                newSquare.setOnDragDone(dragEvent -> {
                    Dragboard db = dragEvent.getDragboard();
                    if (!(dragEvent.getTransferMode() == TransferMode.MOVE)) {
                        moveOriginSquare.setPieceIconView(db.getImage());
                    }
                    dragEvent.consume();
                });

                newSquare.setOnDragDropped(dragEvent -> {
                    Dragboard db = dragEvent.getDragboard();
                    boolean success = false;
                    if (moveOriginSquare.getPiece().move(newSquare) && moveOriginSquare.getPiece().getColor().equals(moveOrder)) {
                        boolean pathSuccess = checkPath(newSquare);
                        if (pathSuccess) {
                            if (moveOrder.equals("White")) {
                                moveOrder = "Black";
                            }
                            else {
                                moveOrder = "White";
                            }
                            if (moveOriginSquare.getPiece().getClass().getSimpleName().equals("King")) {
                                System.out.println("King move");
                                int columnDistance = newSquare.getColumn() - moveOriginSquare.getColumn();
                                if (Math.abs(columnDistance) == 2) {
                                    Square rookSquare;
                                    Square newRookSquare;
                                    if (columnDistance == 2) {
                                        rookSquare = getSquareFromBoard(moveOriginSquare.getColumn() + columnDistance + 1, moveOriginSquare.getRow());
                                        newRookSquare = getSquareFromBoard(moveOriginSquare.getColumn() + 1, moveOriginSquare.getRow());
                                    }
                                    else {
                                        rookSquare = getSquareFromBoard(moveOriginSquare.getColumn() + columnDistance - 2, moveOriginSquare.getRow());
                                        newRookSquare = getSquareFromBoard(moveOriginSquare.getColumn() - 1, moveOriginSquare.getRow());
                                    }
                                    newRookSquare.setPiece(rookSquare.getPiece());
                                    newRookSquare.getPiece().setCurrentSquare(newRookSquare);
                                    rookSquare.setPiece(null);
                                }
                            }
                            newSquare.setPiece(moveOriginSquare.getPiece());
                            newSquare.getPiece().setCurrentSquare(newSquare);
                            moveOriginSquare.setPiece(null);
                            success = true;
                        }
                        else {
                            moveOriginSquare.setPieceIconView(db.getImage());
                        }
                    }
                    else {
                        moveOriginSquare.setPieceIconView(db.getImage());
                    }
                    dragEvent.setDropCompleted(success);
                    dragEvent.consume();
                });

                boardGUI.add(newSquare, i, Math.abs(j - 7));
            }
        }
        options.setSpacing(100);
        for (Node node : options.getChildren()) {
            ((Button) node).setMinWidth(150);
        }
    }

    public void newGameHandler() {
        moveOrder = "White";
        String color = "";
        for (Node node : boardGUI.getChildren()) {
            Square square = (Square) node;
            int row = square.getRow();
            int column = square.getColumn();

            if (row == 1) { color = "White"; }
            else if (row == 2) {
                square.setPiece(new Pawn("White", square));
            }
            else if (row == 7) {
                square.setPiece(new Pawn("Black", square));
            }
            else if (row == 8) { color = "Black"; }

            if (row == 1 || row == 8) {
                switch (column) {
                    case 1: case 8:
                        square.setPiece(new Rook(color, square));
                        break;
                    case 2: case 7:
                        square.setPiece(new Knight(color, square));
                        break;
                    case 3: case 6:
                        square.setPiece(new Bishop(color, square));
                        break;
                    case 4:
                        square.setPiece(new Queen(color, square));
                        break;
                    case 5:
                        square.setPiece(new King(color, square));
                }
            }
            else if (row < 7 && row > 2) {
                square.setPiece(null);
            }
        }
    }

    public void flipBoardHandler() {
        Square[][] squareArray = new Square[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squareArray[i][j] = getSquareFromBoard(i + 1, j + 1);
                boardGUI.getChildren().remove(getSquareFromBoard(i + 1, j + 1));
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!flipped) {
                    boardGUI.add(squareArray[i][j], Math.abs(i - 7), j);
                }
                else {
                    boardGUI.add(squareArray[i][j], i, Math.abs(j - 7));
                }
            }
        }
        flipped = !flipped;
    }

    public Square getSquareFromBoard(int col, int row) {
        for (Node node : boardGUI.getChildren()) {
            Square square = (Square) node;
            if (square.getRow() == row && square.getColumn() == col) {
                return square;
            }
        }
        return null;
    }

    public boolean checkPath(Square newSquare) {
//      Handle pathing, i.e. if a piece is in the way.
        int rowDistance = newSquare.getRow() - moveOriginSquare.getRow();
        int columnDistance = newSquare.getColumn() - moveOriginSquare.getColumn();
        boolean pathSuccess = true;
//      Forward or backward movement.
        if (rowDistance != 0 && columnDistance == 0) {
            int sign = -1;
            if (rowDistance < 0) {
                sign = 1;
            }
            while (rowDistance != 0) {
                Square checkedSquare = getSquareFromBoard(moveOriginSquare.getColumn(), moveOriginSquare.getRow() + rowDistance);
                if (checkedSquare.getPiece() != null) {
                    if (!(checkedSquare == newSquare)) {
                        System.out.println("Square " + checkedSquare.getSquareID() + " is occupied!");
                        pathSuccess = false;
                        break;
                    }
                }
                rowDistance = rowDistance + sign;
            }
        }
//      Lateral movement.
        else if (columnDistance != 0 && rowDistance == 0) {
            int sign = -1;
            if (columnDistance < 0) {
                sign = 1;
            }

            if (moveOriginSquare.getPiece().getClass().getSimpleName().equals("King")) {
                if (Math.abs(columnDistance) == 2) {
                    int addition = -2;
                    if (columnDistance == 2) {
                        addition = 1;
                    }
                    Square rookSquare = getSquareFromBoard(moveOriginSquare.getColumn() + columnDistance + addition, moveOriginSquare.getRow());
                    if (rookSquare.getPiece().getClass().getSimpleName().equals("Rook") ) {
                        Rook rook = (Rook) rookSquare.getPiece();
                        if (!rook.isFirstMove()) {
                            pathSuccess = false;
                        }
                    }
                    else {
                        pathSuccess = false;
                    }
                }
            }
            while (columnDistance != 0) {
                Square checkedSquare = getSquareFromBoard(moveOriginSquare.getColumn() + columnDistance, moveOriginSquare.getRow());
                if (checkedSquare.getPiece() != null) {
                    if (!(checkedSquare == newSquare)) {
                        System.out.println("Square " + checkedSquare.getSquareID() + " is occupied!");
                        pathSuccess = false;
                        break;
                    }
                }
                columnDistance = columnDistance + sign;
            }
        }
//      Diagonal movement.
        else if (Math.abs(columnDistance) == Math.abs(rowDistance)) {
            int rowSign = -1;
            int columnSign = -1;
            if (columnDistance < 0) {
                columnSign = 1;
            }
            if (rowDistance < 0) {
                rowSign = 1;
            }
            while (columnDistance != 0 && rowDistance != 0) {
                Square checkedSquare = getSquareFromBoard(moveOriginSquare.getColumn() + columnDistance, moveOriginSquare.getRow() + rowDistance);
                if (checkedSquare.getPiece() != null) {
                    if (!(checkedSquare == newSquare)) {
                        System.out.println("Square " + checkedSquare.getSquareID() + " is occupied!");
                        pathSuccess = false;
                        break;
                    }
                }
                columnDistance = columnDistance + columnSign;
                rowDistance = rowDistance + rowSign;
            }
        }
        return pathSuccess;
    }

}
