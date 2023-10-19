import java.io.*;
import java.util.*;
import java.util.stream.*;

public class HuntTheWumpus {

  private static final boolean DEBUG = false;

  // ========================================================
  // Solution
  // ========================================================

  public static void main(String[] args) throws IOException {
    int s = next();

    List<Tuple> wumpus = new ArrayList<>();
    boolean[][] locs = new boolean[10][10];

    while (wumpus.size() < 4) {
      s = s + s / 13 + 15;
      Tuple w = new Tuple(s % 100 / 10, s % 10);
      if (locs[w.x][w.y]) continue;
      wumpus.add(w);
      locs[w.x][w.y] = true;
    }

    int moves = 0;
    while (!wumpus.isEmpty() && moves < 250) {
      int in = next();
      Tuple guess = new Tuple(in % 100 / 10, in % 10);

      if (locs[guess.x][guess.y]) {
        System.out.println("You hit a wumpus!");
        locs[guess.x][guess.y] = false;
        wumpus.remove(guess);
      }

      OptionalInt minDist =
          wumpus.stream().mapToInt(t -> Math.abs(t.x - guess.x) + Math.abs(t.y - guess.y)).min();
      if (minDist.isPresent()) System.out.println(minDist.getAsInt());

      moves++;
    }

    System.out.printf("Your score is %d moves.\n", moves);
  }

  // ========================================================
  // Helpers
  // ========================================================

  private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  private static class Tuple {
    int x, y;

    public Tuple(int... args) {
      this.x = args[0];
      this.y = args[1];
    }

    public boolean equals(Object o) {
      return o instanceof Tuple && this.x == ((Tuple) o).x && this.y == ((Tuple) o).y;
    }

    public String toString() {
      return String.format("Tuple(%d, %d)", x, y);
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
  private static String nextLine() throws IOException {
    return reader.readLine();
  }

  @SuppressWarnings("unused")
  private static Tuple nextTuple() throws IOException {
    return new Tuple(nextArray());
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
  private static void print(String label, Object o) {
    if (!DEBUG) return;
    System.out.println("");
    System.out.println("> " + label);
    System.out.println(o);
    System.out.println("");
  }

  @SuppressWarnings("unused")
  private static void print(String label, Object[] arr) {
    if (!DEBUG) return;
    System.out.println("");
    System.out.println("> " + label);
    for (Object o : arr) System.out.println(o);
    System.out.println("");
  }
}
