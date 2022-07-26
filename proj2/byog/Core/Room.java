package byog.Core;

import byog.TileEngine.TETile;

import java.util.List;

public class Room {
    private int height;
    private int width;
    private Position bottomLeft;
    private Position upRight;
    public Room(Position b, Position t) {
        bottomLeft = b;
        upRight = t;
        height = upRight.getY() - bottomLeft.getY();
        width = upRight.getX() - bottomLeft.getX();
    }
   public boolean isOverlapped(Room other) {
       Position center = getcenter();
       Position thatCenter = other.getcenter();
       int xDis = Math.abs(center.getX() - thatCenter.getX());
       boolean xOverlap = xDis <= (getWidth() / 2 + other.getWidth() / 2);
       int yDis = Math.abs(center.getY() - thatCenter.getY());
       boolean yOverlap = yDis <= (getHeight() / 2 + other.getHeight() / 2);
       return yOverlap && xOverlap;
   }
   public boolean isOverlapped(List<Room> rooms) {
        for (Room r: rooms) {
            if (r == this) {
                continue;
            }
            if (isOverlapped(r)) {
                return true;
            }
        }
        return false;
   }

   public void drawRoom(TETile[][] world, TETile t) {
        for (int i = bottomLeft.getY(); i <= upRight.getY(); ++i) {
            for (int j = bottomLeft.getX(); j <= upRight.getX(); ++j) {
                world[i][j] = t;
            }
        }
   }
   public int getWidth() {
        return width;
   }
   public int getHeight() {
        return height;
   }
   public Position getcenter() {
        return new Position(((bottomLeft.getX()) + (upRight.getX()) / 2),((bottomLeft.getY() + upRight.getY()) / 2));
   }
}
