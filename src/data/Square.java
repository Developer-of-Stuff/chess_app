package data;

public class Square {

    private boolean occupied;
    private int row;
    private int column;

    public Square(boolean occupied, int row, int column) {
        this.occupied = occupied;
        this.row = row;
        this.column = column;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
