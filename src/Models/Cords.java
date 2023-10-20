package Models;

public class Cords{
    public int x,y;
    public Cords(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public Cords setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public Cords setY(int y) {
        this.y = y;
        return this;
    }

    public Cords add(Cords c){
        return new Cords(x,y).setX(x+c.x).setY(y+c.y);
    }

    @Override
    public boolean equals(Object o){
        if(o == null)return false;
        if(!(o instanceof Cords))return false;
        Cords c = (Cords) o;
        return c.x == x && c.y == y;
    }

    @Override
    public String toString() {
        return "Cords{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}