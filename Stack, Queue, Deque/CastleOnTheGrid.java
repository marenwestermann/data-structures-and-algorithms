/* Problem: 
You are given a square grid with some cells open (.) and some blocked (X). Your 
playing piece can move along any row or column until it reaches the edge of the grid 
or a blocked cell. Given a grid, a start and an end position, determine the minimum
number of moves it will take to get to the end position. 
For example, you are given a grid with sides n = 3 described as follows:
...
.X.
...
Your start position is (startX, startY) = (0,0) and your end position is (goalX,
goalY) = (1,2). The path is (0,0) --> (0,2) --> (1,2). It therefore takes 2 moves to
get to the goal.

Note: this solution translates the grid into a 2D integer array. */

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

// class for creating grid cell objects
class Cell {
    private int row;
    private int col;
    private char dir;
    private int value;

    public Cell(int row, int col, char dir, int value) {
        this.row = row; // row index
        this.col = col; // column index
        this.dir = dir; // direction of movement ('h' or 'v')
        this.value = value; // cell value (= min. moves)
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getDir() {
        return dir;
    }

    public int getValue() {
        return value;
    }
}

public class CastleOnTheGrid {

    // helper function for moving upwards
    private static int moveUp(int[][] a, int goalRow, int goalCol, LinkedList<Cell> queue, Cell cell, int moves) {

        int i = cell.getRow()-1;
        int j = cell.getCol();
        while (i >= 0 && a[i][j] != -1) {
            if (i == goalRow && j == goalCol) return moves;
            if (a[i][j] == 0) { // 0 means we haven't seen this cell
                a[i][j] = moves;
                queue.add(new Cell(i, j, 'v', moves));
            }
            i--;
        }
        return -1; // -1 means we haven't found the goal
    }

    // helper function for moving downwards    
    private static int moveDown(int[][] a, int goalRow, int goalCol, LinkedList<Cell> queue, Cell cell, int moves) {

        int i = cell.getRow()+1;
        int j = cell.getCol();
        while (i < a.length && a[i][j] != -1) {
            if (i == goalRow && j == goalCol) return moves;
            if (a[i][j] == 0) { // 0 means we haven't seen this cell
                a[i][j] = moves;
                queue.add(new Cell(i, j, 'v', moves));

            }
            i++;
        }
        return -1; // -1 means we haven't found the goal
    }

    // helper function for moving left    
    private static int moveLeft(int[][] a, int goalRow, int goalCol, LinkedList<Cell> queue, Cell cell, int moves) {
        int i = cell.getRow();
        int j = cell.getCol()-1;
        while (j >= 0 && a[i][j] != -1) {
            if (i == goalRow && j == goalCol) return moves;
            if (a[i][j] == 0) { // 0 means we haven't seen this cell
                a[i][j] = moves;
                queue.add(new Cell(i, j, 'h', moves));
            }
            j--;
        }
        return -1; // -1 means we haven't found the goal
    }

    // helper function for moving right    
    private static int moveRight(int[][] a, int goalRow, int goalCol, LinkedList<Cell> queue, Cell cell, int moves) {
        // fill cells to the right
        int i = cell.getRow();
        int j = cell.getCol()+1;
        while (j < a.length && a[i][j] != -1) {
            if (i == goalRow && j == goalCol) return moves;
            if (a[i][j] == 0) { // 0 means we haven't seen this cell
                a[i][j] = moves;
                queue.add(new Cell(i, j, 'h', moves));
            }
            j++;

        }
        return -1; // -1 means we haven't found the goal
    }

    static int minimumMoves(String[] grid, int startRow, int startCol, int goalRow, int goalCol) {
        
        // special case: start and goal are the same
        if (startRow == goalRow && startCol == goalCol) return 0;
        
        // translate string array into 2D integer array
        int[][] a = new int[grid.length][grid.length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i].charAt(j) == 'X') {
                   a[i][j] = -1;
                }
            }
        }

        // initialise variables
        LinkedList<Cell> queue = new LinkedList<Cell>(); // queue for keeping track of visited cells
        int moves = 1; // # of moves it takes to reach a cell from the start position
        a[startRow][startCol] = moves; // set start to 1 to mark it as seen
        int target = -1; // -1 means we haven't found the target
        Cell cell = new Cell(startRow, startCol, 'a', 0); // initialise start cell

        // start: visit cells in straight line from start in clockwise order; stop if we found the target
        target = moveUp(a, goalRow, goalCol, queue, cell, moves);
        if (target != -1) return target;
        target = moveRight(a, goalRow, goalCol, queue, cell, moves);
        if (target != -1) return target;
        target = moveDown(a, goalRow, goalCol, queue, cell, moves);
        if (target != -1) return target;
        target = moveLeft(a, goalRow, goalCol, queue, cell, moves);
        if (target != -1) return target;

        // search iteratively for target
        while (true) {

            Cell next = queue.remove();
            moves = next.getValue() + 1;
            char d = next.getDir(); // direction (horizontal or vertical)

            if (d == 'v') {
                target = moveLeft(a, goalRow, goalCol, queue, next, moves);
                if (target != -1) return target;
                target = moveRight(a, goalRow, goalCol, queue, next, moves);
                if (target != -1) return target;
            } else if (d == 'h') {
                target = moveUp(a, goalRow, goalCol, queue, next, moves);
                if (target != -1) return target;
                target = moveDown(a, goalRow, goalCol, queue, next, moves);
                if (target != -1) return target;
            }

        }

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt(); // grid length
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] grid = new String[n];

        for (int i = 0; i < n; i++) { // create grid
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        String[] startXStartY = scanner.nextLine().split(" "); // read in start and end positions

        int startX = Integer.parseInt(startXStartY[0]);

        int startY = Integer.parseInt(startXStartY[1]);

        int goalX = Integer.parseInt(startXStartY[2]);

        int goalY = Integer.parseInt(startXStartY[3]);

        int result = minimumMoves(grid, startX, startY, goalX, goalY);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

