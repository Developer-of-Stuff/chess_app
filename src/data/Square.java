package data;

public class Square {

    private boolean occupied;
    private final int row;
    private final int column;

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

    public int getColumn() {
        return column;
    }

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
}
