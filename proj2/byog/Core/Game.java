package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.List;
import java.util.Random;


public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        input = input.toLowerCase();
        TETile[][] world = null;
        char flag = input.charAt(0);
        switch(flag) {
            case 'n':
                world = newGame(input);
                break;
            case 'l':
                break;
            case 'q':
                break;
        }
        return world;
    }
    private TETile[][] newGame(String input) {
        TETile[][] world = null;
        int i = input.indexOf('s');
        String realInput = input.substring(1, i);
        long seed = Long.valueOf(realInput);
        world = worldGenerator(seed);
        return world;
    }
    /** https://zhuanlan.zhihu.com/p/27381213 solution 1.*/
    private  TETile[][] worldGenerator(long seed) {
        Random random = new Random(seed);
        TETile[][] world = new TETile[HEIGHT][WIDTH];
        initializeWorld(world);
        List<Room> rooms  = roomsGenerator(world, random);
        hallwaysGenerator(world, rooms);
        return world;
    }
    private void initializeWorld(TETile[][] world) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.WALL;
            }
        }

        for (int x = 1; x < WIDTH; x += 2) {
            for (int y = 1; y < HEIGHT; y += 2) {
                world[x][y] = Tileset.FLOOR;
            }
        }
    }
    private List<Room> roomsGenerator(TETile[][] world, Random random) {
        return null;
    }
}
