package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final long SEED = 28223;
    private static final Random RANDOM = new Random(SEED);
    /**  p specifies the lower left corner of the hexagon. */
    public static class Position {
        private int x;
        private int y;
        public Position(int xx, int yy) {
            x = xx;
            y = yy;
        }
    }
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.GRASS;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.SAND;
            case 3: return Tileset.TREE;
            case 4: return Tileset.MOUNTAIN;
            default: return Tileset.NOTHING;
        }
    }
    private static int hexRowWidth(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return s + 2 * effectiveI;
    }
    private static int hexRowOffset(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return -effectiveI;
    }
    private static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int xi = 0; xi < width; xi += 1) {
            int xCoord = p.x + xi;
            int yCoord = p.y;
            world[xCoord][yCoord] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
        }
    }
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        for (int yi = 0; yi < 2 * s; yi += 1) {
            int thisRowY = p.y + yi;
            int xRowStart = p.x + hexRowOffset(s, yi);
            Position rowStartP = new Position(xRowStart, thisRowY);
            int rowWidth = hexRowWidth(s, yi);
            addRow(world, rowStartP, rowWidth, t);
        }
    }
    @Test
    public void testHexRowWidth() {
        assertEquals(3, hexRowWidth(3, 5));
        assertEquals(5, hexRowWidth(3, 4));
        assertEquals(7, hexRowWidth(3, 3));
        assertEquals(7, hexRowWidth(3, 2));
        assertEquals(5, hexRowWidth(3, 1));
        assertEquals(3, hexRowWidth(3, 0));
        assertEquals(2, hexRowWidth(2, 0));
        assertEquals(4, hexRowWidth(2, 1));
        assertEquals(4, hexRowWidth(2, 2));
        assertEquals(2, hexRowWidth(2, 3));
    }

    @Test
    public void testHexRowOffset() {
        assertEquals(0, hexRowOffset(3, 5));
        assertEquals(-1, hexRowOffset(3, 4));
        assertEquals(-2, hexRowOffset(3, 3));
        assertEquals(-2, hexRowOffset(3, 2));
        assertEquals(-1, hexRowOffset(3, 1));
        assertEquals(0, hexRowOffset(3, 0));
        assertEquals(0, hexRowOffset(2, 0));
        assertEquals(-1, hexRowOffset(2, 1));
        assertEquals(-1, hexRowOffset(2, 2));
        assertEquals(0, hexRowOffset(2, 3));
    }
    /* code above: @Source: Josh Hug */
    /* draw hexes from down to up */
    private static void drawColHexes(TETile[][] world, Position p, int s, int n) {
        Position next = new Position(p.x, p.y);
        for (int i = 0; i < n; i += 1) {
            addHexagon(world, next, s, randomTile());
            next.y += 2 * s;
        }
    }
    private static int drawCount(int i) {
        int n = i + 3;
        if (i > 2) {
            n = 10 - n;
        }
        return n;
    }
    private static Position nextPosition(Position p, int s, int i) {
        Position next = new Position(p.x, p.y);
        /* hexRowOffset is negative value. */
        next.x += s - 1 - hexRowOffset(s, s);
        if (i > 2) {
            next.y += s;
        } else {
            next.y -= s;
        }
        return next;
    }

    public static void drawTesselationHexagons(TETile[][] world, Position p, int s) {
        Position next = new Position(p.x, p.y);
        for (int i = 0; i < 5; ++i) {
            drawColHexes(world, next, s, drawCount(i));
            next = nextPosition(next, s, i + 1);
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        Position p = new Position(3, 10);
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        drawTesselationHexagons(world, p, 3);


        ter.renderFrame(world);
    }
}
