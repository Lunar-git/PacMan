package Models;

public enum EntityType {
    PACKMAN(4), WALL(1), NOTHING(0), GHOST(5), POINT(2);

    int asInteger;
    EntityType(int number){
        this.asInteger = number;
    }
    public int asInteger(){
        return this.asInteger;
    }
}
