import java.util.*;

public class Puzzle {
    Piece frame;
    ArrayList<Piece> pieces;

    public Puzzle() {
        init();
    }

    public Puzzle(Puzzle copy) {
        this.frame = copy.frame.clone();
        for(int i=0;i<copy.pieces.size();i++) {
            this.pieces.add(copy.pieces.get(i).clone());
        }
    }

    public Puzzle clone() {
        return new Puzzle(this);
    }

    void add(Piece p) {
        pieces.add(p);
    }

    Piece get(int i) {
        return pieces.get(i);
    }

    void remove(int i) {
        pieces.remove(i);
    }

    void removeLast() {
        pieces.remove(pieces.size()-1);
    }

    int size() {
        return pieces.size();
    }

    void init() {
        frame = new Piece();
        pieces = new ArrayList<Piece>();
        for(Piece p : pieces)
            p = new Piece();
    }

    public String toString() {
        String temp = "";
        temp += frame.toString() + "\n";
        temp += pieces.size() + "\n";
        for(int i=0;i<pieces.size();i++) {
            pieces.get(i).angle = (int)(Math.random()*4)*Math.PI/2.0;
            temp += pieces.get(i).toString();
            if(i != pieces.size()-1)
                temp += "\n";
        }
        return temp;
    }

    public String toStringLine() {
        String temp = "";
        temp += pieces.size() + ":";
        for(int i=0;i<pieces.size();i++) {
            pieces.get(i).angle = (int)(Math.random()*4)*Math.PI/2.0;
            temp += pieces.get(i).toStringLine();
            temp += ":";
        }
        temp += frame.toStringLine() + "\n";
        return temp;
    }
}
