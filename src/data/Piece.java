package data;

public abstract class Piece {

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public abstract boolean move();

    public abstract boolean attack();
}
