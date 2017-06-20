import java.util.*;

public class Piece {
    ArrayList<Coord> vertexes;
    Coord ref;
    double angle;

    public Piece() {
        vertexes = new ArrayList<Coord>();
        ref = new Coord();
        angle = 0.0;
    }

    public Piece(Piece copy) {
        this();
        for(int i=0;i<copy.size();i++) {
            this.add(copy.get(i).clone());
        }
        this.angle = copy.angle;
    }

    public Piece clone() {
        return new Piece(this);
    }

    void add(Coord c) {
        if(vertexes.size() == 0) ref = c.clone();
        vertexes.add(new Coord(c,-1*ref.x,-1*ref.y));
    }

    Coord get(int i) {
        return translate(vertexes.get(i));
    }

    void remove(int i) {
        vertexes.remove(i);
    }

    void removeLast() {
        vertexes.remove(vertexes.size()-1);
    }

    int size() {
        return vertexes.size();
    }

    void init() {
        vertexes = new ArrayList<Coord>();
    }

    Coord translate(Coord c) {
        return new Coord(new Coord(c,angle),ref.x,ref.y);
    }

    public String toString() {
        String temp = "";
        temp += vertexes.size() + "\n";
        for(int i=0;i<vertexes.size();i++) {
            temp += vertexes.get(i).toString();
            if(i != vertexes.size()-1)
                temp += "\n";
        }
        return temp;
    }

    public String toStringLine() {
        String temp = "";
        temp += vertexes.size() + " ";
        for(int i=0;i<vertexes.size();i++) {
            temp += vertexes.get(i).toStringInt();
            if(i != vertexes.size()-1)
                temp += " ";
        }
        return temp;
    }
}
