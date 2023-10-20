package Components;

import Models.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

public class Board extends AbstractTableModel {
    private Integer[] columns;
    private Object[][] rows;

    // 1 - wall
    // 0 - null
    // 2 - point
    // 3 - improvement / upgrade
    // 4 - pacman

    public Board(int x, int y) {
        this.columns = new Integer[x];
        this.rows = new Object[x][y];

//        for (int i = 0; i < rows.length; i++) {
//            Arrays.fill(rows[i], 0);
//        }
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                rows[i][j] = null;
            }
        }

        // Generate the maze
        generateMaze(x, y);
    }

    private void generateMaze(int x, int y) {
        boolean[][] visited = new boolean[x][y];
        Stack<Point> stack = new Stack<>();

        // Initialize all cells as unvisited
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                visited[i][j] = false;
            }
        }

        // Choose a random starting cell
        int startX = (int) (Math.random() * (x - 2)) + 1;
        int startY = (int) (Math.random() * (y - 2)) + 1;
        Point startCell = new Point(startX, startY);

        // Set the starting cell as visited and push it onto the stack
        visited[startX][startY] = true;
        stack.push(startCell);

        while (!stack.isEmpty()) {
            Point currentCell = stack.peek();
            int currentX = currentCell.x;
            int currentY = currentCell.y;

            // Get unvisited neighboring cells
            ArrayList<Point> unvisitedNeighbors = getUnvisitedNeighbors(currentX, currentY, visited);

            if (!unvisitedNeighbors.isEmpty()) {
                // Choose a random unvisited neighboring cell
                int randomIndex = (int) (Math.random() * unvisitedNeighbors.size());
                Point nextCell = unvisitedNeighbors.get(randomIndex);
                int nextX = nextCell.x;
                int nextY = nextCell.y;

                // Remove the wall between the current cell and the chosen neighboring cell
                int wallX = (currentX + nextX) / 2;
                int wallY = (currentY + nextY) / 2;
                rows[wallX][wallY] = 1; // Set as wall
                visited[nextX][nextY] = true;
                stack.push(nextCell);
            } else {
                // Dead end reached, backtrack
                stack.pop();
            }
        }

        // Clear the boundaries of the maze
        for (int i = 0; i < x; i++) {
            rows[i][0] = 2; // Clear left boundary
            rows[i][y - 1] = 2; // Clear right boundary
        }
        for (int j = 0; j < y; j++) {
            rows[0][j] = 2; // Clear top boundary
            rows[x - 1][j] = 2; // Clear bottom boundary
        }

        // Fill empty cells with points
        for (int i = 1; i < x - 1; i++) {
            for (int j = 1; j < y - 1; j++) {
                if (rows[i][j] == null) {
                    rows[i][j] = 2; // Set as point
                }
            }
        }
    }

    private Point getRandomUnvisitedCell(boolean[][] visited) {
        int x = visited.length;
        int y = visited[0].length;

        ArrayList<Point> unvisitedCells = new ArrayList<>();
        for (int i = 1; i < x - 1; i++) {
            for (int j = 1; j < y - 1; j++) {
                if (!visited[i][j]) {
                    unvisitedCells.add(new Point(i, j));
                }
            }
        }

        if (!unvisitedCells.isEmpty()) {
            int randomIndex = (int) (Math.random() * unvisitedCells.size());
            return unvisitedCells.get(randomIndex);
        }

        return null;
    }

    private ArrayList<Point> getUnvisitedNeighbors(int x, int y, boolean[][] visited) {
        ArrayList<Point> unvisitedNeighbors = new ArrayList<>();
        int[][] directions = {{0, -2}, {2, 0}, {0, 2}, {-2, 0}}; // Up, Right, Down, Left

        for (int[] dir : directions) {
            int neighborX = x + dir[0];
            int neighborY = y + dir[1];

            // Check if the neighbor is within the maze bounds and unvisited
            if (neighborX >= 0 && neighborX < visited.length &&
                    neighborY >= 0 && neighborY < visited[0].length &&
                    !visited[neighborX][neighborY]) {
                unvisitedNeighbors.add(new Point(neighborX, neighborY));
            }
        }

        return unvisitedNeighbors;
    }



    @Override
    public int getRowCount() {
        return rows[0].length;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rows[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(aValue instanceof Entity){
            rows[rowIndex][columnIndex] = aValue;
        }
        if(aValue instanceof Integer) {
            rows[rowIndex][columnIndex] = aValue;
        }
    }
    public ArrayList<Cords> getWalls(){
        // например Object o, и я могу удостовериться что этот Object является числом через o instanceof Integer
        ArrayList<Cords> output = new ArrayList<>();
        for (int x = 0; x < rows.length; x++) {
            Object[] a = rows[x];
            for (int y = 0; y < a.length; y++) {
                Object o = rows[x][y];
                if(o instanceof Integer){
                    if((int)o == EntityType.WALL.asInteger()){
                        output.add(new Cords(x,y));
                    }
                }
            }
        }
        return output;
    }
}