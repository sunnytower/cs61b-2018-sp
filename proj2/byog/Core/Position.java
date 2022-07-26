package byog.Core;

public class Position {
    private int x;
    private int y;
    public Position(int xx, int yy) {
       x = xx;
       y = yy;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Position midPosition(Position p) {
        int midX = p.x - x;
        int midY = p.y - y;
        return new Position((int) (x + 0.5 * midX) , (int ) (y + 0.5 * midY));
    }
}
