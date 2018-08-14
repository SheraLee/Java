import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Maze {
    private Square[][] maze;
    private int numRows;
    private int numCols;

    /** 0-arg constructor
    */
    public Maze() {
        numRows = 0;
        numCols = 0;
    }

    /** loads a maze from a file to a 2d array
    * @param pathname to maze to load
    * @return true if load was a success
    */
    public boolean loadMaze(String fname) {
        String line;
        BufferedReader inputStrem;
        StringTokenizer st;

        try {
            int currentRow = 0;

            inputStrem = new BufferedReader(new FileReader(fname));
            line = inputStrem.readLine();
            if (line != null) {
                st = new StringTokenizer(line);
                numRows = Integer.parseInt(st.nextToken());
                numCols = Integer.parseInt(st.nextToken());
                maze = new Square[numRows][numCols];
            } else {
                inputStrem.close();
                return false;
            }

            while ((line = inputStrem.readLine()) != null) {
                if (numRows == 0) {  // true if reading first line in file, containing  numRows numColums
                    st = new StringTokenizer(line);
                    numRows = Integer.parseInt(st.nextToken());
                    numCols = Integer.parseInt(st.nextToken());
                    maze = new Square[numRows][numCols];
                } else if (line.length() == 1) {
                    break; 
                } else {
                    int col = 0;
                    for (int c = 0; c < line.length(); c++) {
                        if (line.charAt(c) == ' ') continue;
                        maze[currentRow][col] = new Square(currentRow, col, Character.getNumericValue(line.charAt(c)));
                        col++;
                    }
                    currentRow++;
                }
            }
        } catch (IOException e) {
            System.out.println (e.toString());
            System.out.println("Could not find file " + fname);
        } 

        return true;
    }

    /** find list of valid neighbors of square
    * @param square to find neigbors of
    * @return list of square's neighbors
    */
    public ArrayList<Square> getNeighbors(Square sq) {
        ArrayList<Square> neighbors = new ArrayList<>();
        int down = sq.getRow() + 1;
        int left = sq.getCol() - 1;
        int up = sq.getRow() - 1; 
        int right = sq.getCol() + 1;

        if (up >= 0 && maze[up][sq.getCol()].isValid()) {
            neighbors.add(maze[up][sq.getCol()]);
        }

        if (right < numCols && maze[sq.getRow()][right].isValid()) {
            neighbors.add(maze[sq.getRow()][right]);
        }

        if (down < numRows && maze[down][sq.getCol()].isValid()) {
            neighbors.add(maze[down][sq.getCol()]);
        }
        
        if (left >= 0 && maze[sq.getRow()][left].isValid()) {
            neighbors.add(maze[sq.getRow()][left]);
        }
        return neighbors;
    }

    /** finds starts cell in maze
    * @return Square which corresponds to the start cell
    */
    public Square getStart() {
        for (int c = 0; c < numCols; c++)  {
            for (int r = 0; r < numRows; r++) {
                if (maze[r][c].isStart()) {
                    return maze[r][c];
                }
            }
        }
        return null;
    }

    /** finds end cell in maze
    * @return Square which corresponds to the end cell
    */
    public Square getFinish() {
        for (int c = 0; c < numCols; c++) {
            for (int r = 0; r < numRows; r++) {
                if (maze[r][c].isEnd()) {
                    return maze[r][c];
                }
            }
        }
        return null;
    }

    /** marks Square at particular location as visited
    * @param 0-indexed row index
    * @param 0-indexed cell index
    */
    public void setVisit(int row, int col) {
        maze[row][col].setVisited();
    }

    /** remove all linked squares and sets visited flags to false
    */
    public void clearMaze() {
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                maze[i][j].clearFinalPath();
                maze[i][j].clearVisited();
                maze[i][j].setPrevious(null);
            }
        }
    }

    /** getMaze
    * @return 2d array of maze
    */
    public Square[][] getMaze() {
        return this.maze;
    }

    /** toString
    * @return string representation of maze
    */
    @Override
    public String toString() {
        String s = "";
        for (int r = 0; r < numRows; r++)  {
            for (int c = 0; c < numCols; c++)  {
                s = s + maze[r][c].toString();
            }
            s = s + "\n";
        }
        return s;  
    }

}
