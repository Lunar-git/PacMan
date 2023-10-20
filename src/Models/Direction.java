package Models;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public Cords asCords() {
        if(this == UP)return new Cords(0,-1);
        if(this == DOWN)return new Cords(0,1);
        if(this == LEFT)return new Cords(1, 0);
        if(this == RIGHT)return new Cords(-1,0);
        return new Cords(0,0);
    }
    public Direction invert(){
        switch (this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case RIGHT:
                return LEFT;
            case LEFT:
                return RIGHT;
        }
        return RIGHT;
    }
}