import java.io.*;
import java.util.*;
import java.util.stream.*;

public class ColorTubes {

  private static boolean DEBUG = false;
  private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  // ========================================================
  // StdIn Helpers
  // ========================================================

  @SuppressWarnings("unused")
  private static int next() throws IOException {
    return Integer.parseInt(reader.readLine());
  }

  @SuppressWarnings("unused")
  private static int[] nextTuple() throws IOException {
    return Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
  }

  @SuppressWarnings("unused")
  private static void print(int[] arr) {
    if (!DEBUG) return;
    System.out.println("");
    System.out.println(Arrays.toString(arr));
    System.out.println("");
  }

  @SuppressWarnings("unused")
  private static void print(int[][] arr) {
    if (!DEBUG) return;
    System.out.println("");
    for (int[] row : arr) System.out.println(Arrays.toString(row));
    System.out.println("");
  }

  // ========================================================
  // Solution
  // ========================================================

  private static final int TOP = 2;
  private static final int MID = 1;
  private static final int BOT = 0;

  private static final int EMPTY = 0;

  public static void main(String[] args) throws IOException {
    int n = next();

    int[][] tubes = new int[n + 1][];
    int[][] locs = new int[n + 1][3]; // tube locations of each color (including EMPTY)
    for (int t = 0; t < n + 1; t++) { // t will be used for tubes
      int[] tube = nextTuple();
      for (int c : tube) { // c will be used for colors
        // store tubes as 1-indexed so that 0 can be used as a sentinel value
        // 0 is already a significant value since it represents the EMPTY color
        if (locs[c][BOT] == EMPTY) locs[c][BOT] = t + 1;
        else if (locs[c][MID] == EMPTY) locs[c][MID] = t + 1;
        else if (locs[c][TOP] == EMPTY) locs[c][TOP] = t + 1;
        else throw new RuntimeException("Too many balls of the same color");
      }
      tubes[t] = tube;
    }

    print(tubes);
    print(locs);

    List<String> moves = new ArrayList<>();

    // ensure that the 0th tube is empty (solves for color EMPTY)
    makeTubeEmpty(tubes, 0, locs, moves);
    print(tubes);

    // solve for each next tube
    for (int t = 1; t < n + 1; t++) {
      // if the tube is already solved, skip
      if (tubes[t][BOT] == tubes[t][MID] && tubes[t][MID] == tubes[t][TOP]) continue;
      // we know where all of the EMPTY spots are (they're in tube 0)
      // we also know that every other tube is full (because tube 0 has all three EMPTY spots)
      int c = tubes[t][BOT]; // check solve color for tube t, tubes[t][BOT] == c
      // check cases
      if (tubes[t][MID] != c && tubes[t][TOP] == c) {
        // grab any tube that we know exists
        // if tube t is tube 1, then tube 2 must exist,
        //   if tube 0 and tube 1 are the only tubes, then tube 1 is already solved
        int t2 = (t != 1) ? 1 : 2;
        moveBall(tubes, t2, 0, locs, moves);
        moveBall(tubes, t, t2, locs, moves);
        moveBall(tubes, t, 0, locs, moves);
        moveBall(tubes, t2, t, locs, moves);
        moveBall(tubes, 0, t, locs, moves);
        moveBall(tubes, 0, t2, locs, moves);
        print(tubes);
        // tubes[t][MID] == c
      }
      if (tubes[t][MID] != c) {
        int t2 = findTubeWithColorIgnoringTube(locs, c, t);
        if (tubes[t2][TOP] != c) moveBall(tubes, t2, 0, locs, moves);
        if (tubes[t2][TOP] == EMPTY && tubes[t2][MID] != c) moveBall(tubes, t2, 0, locs, moves);
        // at most two balls have been moved from tube t2 to tube 0
        // ensure that tube 0 has two balls
        if (tubes[0][BOT] == EMPTY) moveBall(tubes, t, 0, locs, moves);
        if (tubes[0][MID] == EMPTY) moveBall(tubes, t, 0, locs, moves);
        // move ball with color c to top of tube 0
        moveBall(tubes, t2, 0, locs, moves);
        // move remaining balls from tube t to tube t2 until only BOT ball is left
        if (tubes[t][TOP] != EMPTY) moveBall(tubes, t, t2, locs, moves);
        if (tubes[t][MID] != EMPTY) moveBall(tubes, t, t2, locs, moves);
        // move balls from tube 0 to tube t
        moveBall(tubes, 0, t, locs, moves);
        moveBall(tubes, 0, t, locs, moves);
        // tubes[t][MID] == c
        makeTubeEmpty(tubes, 0, locs, moves);
        print(tubes);
      }
      if (tubes[t][TOP] != c) {
        // locate only other tube with color c that's not tube t
        int t2 = findTubeWithColorIgnoringTube(locs, c, t);
        if (tubes[t2][TOP] != c) moveBall(tubes, t2, 0, locs, moves);
        if (tubes[t2][TOP] == EMPTY && tubes[t2][MID] != c) moveBall(tubes, t2, 0, locs, moves);
        // at most two balls have been moved from tube t2 to tube 0
        moveBall(tubes, t, 0, locs, moves);
        moveBall(tubes, t2, t, locs, moves);
        makeTubeEmpty(tubes, 0, locs, moves);
        print(tubes);
      }
      // tubes[t] = [c, c, c]
    }

    System.out.println(moves.size());
    moves.forEach(System.out::println);
  }

  private static void makeTubeEmpty(int[][] tubes, int t, int[][] locs, List<String> moves) {
    if (tubes[t][TOP] != EMPTY) popTube(tubes, t, locs, moves);
    if (tubes[t][MID] != EMPTY) popTube(tubes, t, locs, moves);
    if (tubes[t][BOT] != EMPTY) popTube(tubes, t, locs, moves);
  }

  private static void popTube(int[][] tubes, int t, int[][] locs, List<String> moves) {
    int t2 = findTubeWithColorIgnoringTube(locs, EMPTY, t);
    moveBall(tubes, t, t2, locs, moves);
  }

  private static void moveBall(int[][] tubes, int t1, int t2, int[][] locs, List<String> moves) {
    if (t1 == t2) throw new RuntimeException("Cannot move ball from a tube to itself");
    // locate the top ball in tube t1
    int i = (tubes[t1][TOP] != EMPTY) ? TOP : ((tubes[t1][MID] != EMPTY) ? MID : BOT);
    // locate the bot EMPTY slot in tube t2
    int j = (tubes[t2][BOT] == EMPTY) ? BOT : ((tubes[t2][MID] == EMPTY) ? MID : TOP);
    swap(tubes[t1], i, tubes[t2], j);
    // grab the color that was just moved
    int c = tubes[t2][j];
    // locate tube t1 in locs[c] who had that color
    i = (locs[c][BOT] == t1 + 1) ? BOT : ((locs[c][MID] == t1 + 1) ? MID : TOP);
    // locate tube t2 in locs[EMPTY] which was EMPTY
    j = (locs[EMPTY][BOT] == t2 + 1) ? BOT : ((locs[EMPTY][MID] == t2 + 1) ? MID : TOP);
    swap(locs[c], i, locs[EMPTY], j);
    // tubes are 0-indexed but must be printed 1-indexed
    moves.add(String.format("%d %d", t1 + 1, t2 + 1));
  }

  private static void swap(int[] a, int i, int[] b, int j) {
    int tmp = a[i];
    a[i] = b[j];
    b[j] = tmp;
  }

  private static int findTubeWithColorIgnoringTube(int[][] locs, int c, int t) {
    // keep in mind that locs tubes are 1-indexed and tube t is 0-indexed
    int i = (locs[c][BOT] != t + 1) ? BOT : ((locs[c][MID] != t + 1) ? MID : TOP);
    if (locs[c][i] == t + 1) throw new RuntimeException("Tube t has all of color c");
    return locs[c][i] - 1;
  }
}
