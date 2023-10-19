import java.io.*;
import java.util.*;
import java.util.stream.*;

public class ColorTubes {

  private static final boolean DEBUG = false;
  private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  // ========================================================
  // Helpers
  // ========================================================

  private static class Tube implements Iterable<Integer> {
    int bot, mid, top;

    public Tube(int bot, int mid, int top) {
      this.bot = bot;
      this.mid = mid;
      this.top = top;
    }

    public int findNonMatch(int c) {
      if (bot != c) return bot;
      if (mid != c) return mid;
      if (top != c) return top;
      throw new RuntimeException("Could not find non-match for color");
    }

    public int getTopColor() {
      if (top != EMPTY) return top;
      if (mid != EMPTY) return mid;
      if (bot != EMPTY) return bot;
      return EMPTY;
    }

    public boolean isEmpty() {
      return bot == EMPTY;
    }

    public boolean isUniformColor() {
      return bot == mid && mid == top;
    }

    public void replaceLeftMatch(int c1, int c2) {
      if (bot == c1) bot = c2;
      else if (mid == c1) mid = c2;
      else if (top == c1) top = c2;
      else throw new RuntimeException("No left match");
    }

    public void replaceRightMatch(int c1, int c2) {
      if (top == c1) top = c2;
      else if (mid == c1) mid = c2;
      else if (bot == c1) bot = c2;
      else throw new RuntimeException("No right match");
    }

    public Iterator<Integer> iterator() {
      return Arrays.stream(new int[] {bot, mid, top}).iterator();
    }

    public String toString() {
      return String.format("Tube(%d, %d, %d)", bot, mid, top);
    }
  }

  @SuppressWarnings("unused")
  private static int next() throws IOException {
    return Integer.parseInt(reader.readLine());
  }

  @SuppressWarnings("unused")
  private static int[] nextArray() throws IOException {
    return Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
  }

  @SuppressWarnings("unused")
  private static Tube nextTube() throws IOException {
    String[] line = reader.readLine().split(" ");
    return new Tube(
        Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]));
  }

  @SuppressWarnings("unused")
  private static void print(String label, int[] arr) {
    if (!DEBUG) return;
    System.out.println("");
    System.out.println("> " + label);
    System.out.println(Arrays.toString(arr));
    System.out.println("");
  }

  @SuppressWarnings("unused")
  private static void print(String label, int[][] arr) {
    if (!DEBUG) return;
    System.out.println("");
    System.out.println("> " + label);
    for (int[] row : arr) System.out.println(Arrays.toString(row));
    System.out.println("");
  }

  @SuppressWarnings("unused")
  private static <E> void print(String label, E e) {
    if (!DEBUG) return;
    System.out.println("");
    System.out.println("> " + label);
    System.out.println(e);
    System.out.println("");
  }

  @SuppressWarnings("unused")
  private static <E> void print(String label, E[] arr) {
    if (!DEBUG) return;
    System.out.println("");
    System.out.println("> " + label);
    for (E e : arr) System.out.println(e);
    System.out.println("");
  }

  // ========================================================
  // Solution
  // ========================================================

  private static final int EMPTY = 0;

  public static void main(String[] args) throws IOException {
    int n = next();

    Tube[] tubes = new Tube[n + 1];
    Tube[] locs = new Tube[n + 1]; // tube locations of each color (including EMPTY)
    Arrays.setAll(locs, i -> new Tube(0, 0, 0));

    for (int t = 0; t < n + 1; t++) { // t will be used for tubes
      Tube tube = nextTube();
      // store tubes as 1-indexed so that 0 can be used as a sentinel value
      // 0 is already a significant value since it represents the EMPTY color
      // c will be used for colors
      for (int c : tube) locs[c].replaceLeftMatch(EMPTY, t + 1);
      tubes[t] = tube;
    }

    print("Tubes", tubes);
    print("Locs", locs);

    List<String> moves = solve(tubes, locs);
    System.out.println(moves.size());
    moves.forEach(System.out::println);
  }

  private static List<String> solve(Tube[] tubes, Tube[] locs) {
    List<String> moves = new ArrayList<>();

    // ensure that the 0th tube is empty (solves for color EMPTY)
    makeTubeEmpty(tubes, 0, locs, moves);
    print("Make Empty", tubes);

    // solve for each next tube
    for (int t = 1; t < tubes.length; t++) {
      // if the tube is already solved, skip
      if (tubes[t].isUniformColor()) continue;
      // we know where all of the EMPTY spots are (they're in tube 0)
      // we also know that every other tube is full (because tube 0 has all three EMPTY spots)
      int c = tubes[t].bot; // check solve color for tube t, tubes[t].bot == c
      // check cases
      if (tubes[t].mid != c && tubes[t].top == c) {
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
        print("Tube[t](c, x, c)", tubes);
        // tubes[t].mid == c
      }
      if (tubes[t].mid != c) {
        int t2 = locs[c].findNonMatch(t + 1) - 1;
        if (tubes[t2].top != c) moveBall(tubes, t2, 0, locs, moves);
        if (tubes[t2].top == EMPTY && tubes[t2].mid != c) moveBall(tubes, t2, 0, locs, moves);
        // at most two balls have been moved from tube t2 to tube 0
        // ensure that tube 0 has two balls
        if (tubes[0].bot == EMPTY) moveBall(tubes, t, 0, locs, moves);
        if (tubes[0].mid == EMPTY) moveBall(tubes, t, 0, locs, moves);
        // move ball with color c to top of tube 0
        moveBall(tubes, t2, 0, locs, moves);
        // move remaining balls from tube t to tube t2 until only BOT ball is left
        if (tubes[t].top != EMPTY) moveBall(tubes, t, t2, locs, moves);
        if (tubes[t].mid != EMPTY) moveBall(tubes, t, t2, locs, moves);
        // move balls from tube 0 to tube t
        moveBall(tubes, 0, t, locs, moves);
        moveBall(tubes, 0, t, locs, moves);
        // tubes[t].mid == c
        makeTubeEmpty(tubes, 0, locs, moves);
        print("Tubes[t](c, x, x)", tubes);
      }
      if (tubes[t].top != c) {
        // locate only other tube with color c that's not tube t
        int t2 = locs[c].findNonMatch(t + 1) - 1;
        if (tubes[t2].top != c) moveBall(tubes, t2, 0, locs, moves);
        if (tubes[t2].top == EMPTY && tubes[t2].mid != c) moveBall(tubes, t2, 0, locs, moves);
        // at most two balls have been moved from tube t2 to tube 0
        moveBall(tubes, t, 0, locs, moves);
        moveBall(tubes, t2, t, locs, moves);
        makeTubeEmpty(tubes, 0, locs, moves);
        print("Tube[t](c, c, x)", tubes);
      }
      // tubes[t] = [c, c, c]
    }

    return moves;
  }

  private static void makeTubeEmpty(Tube[] tubes, int t, Tube[] locs, List<String> moves) {
    while (!tubes[t].isEmpty()) popTube(tubes, t, locs, moves);
  }

  private static void popTube(Tube[] tubes, int t, Tube[] locs, List<String> moves) {
    int t2 = locs[EMPTY].findNonMatch(t + 1) - 1;
    moveBall(tubes, t, t2, locs, moves);
  }

  private static void moveBall(Tube[] tubes, int t1, int t2, Tube[] locs, List<String> moves) {
    if (t1 == t2) throw new RuntimeException("Cannot move ball from a tube to itself");
    int c = tubes[t1].getTopColor();
    tubes[t1].replaceRightMatch(c, EMPTY);
    tubes[t2].replaceLeftMatch(EMPTY, c);
    locs[c].replaceLeftMatch(t1 + 1, t2 + 1);
    locs[EMPTY].replaceLeftMatch(t2 + 1, t1 + 1);
    // tubes are 0-indexed but must be printed 1-indexed
    moves.add(String.format("%d %d", t1 + 1, t2 + 1));
  }
}
