import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class MazeSolverTester {

	@Test
	public void test_bfs_maze1() {
        Maze maze = new Maze();
        maze.loadMaze("Mazes/maze-1.txt");
        MazeSolver queue_solver = new MazeSolver(maze, new QueueWorklist());
        queue_solver.solve();
        assertEquals(queue_solver.getPath(), "Found the Escape!" + 
        		"\nPath from start to finish: [0,0] [0,1] [0,2] [0,3] [0,4] [0,5] [0,6] ");
        assertEquals(queue_solver.getMaze().toString(), ("SxxxxxE\n")); 
        assertEquals(queue_solver.getWorklistSize(), 0);

	}
	@Test
    public void test_dfs_maze1() {
        Maze maze = new Maze();
        maze.loadMaze("Mazes/maze-1.txt");       
        MazeSolver stack_solver = new MazeSolver(maze, new StackWorklist());
        stack_solver.solve();
        assertEquals(stack_solver.getPath(), "Found the Escape!\n" + 
                "Path from start to finish: [0,0] [0,1] [0,2] [0,3] [0,4] [0,5] [0,6] ");
        assertEquals(stack_solver.getMaze().toString(), ("SxxxxxE\n"));
        assertEquals(stack_solver.getWorklistSize(), 0);
    }
	@Test
	public void test_bfs_maze2() {
        Maze maze = new Maze();
        maze.loadMaze("Mazes/maze-2.txt");       
        MazeSolver queue_solver = new MazeSolver(maze, new QueueWorklist());
        queue_solver.solve();
        assertEquals(queue_solver.getPath(), "Found the Escape!" + 
        		"\nPath from start to finish: [0,0] [0,1] [0,2] [1,2] [2,2] ");
        assertEquals(queue_solver.getMaze().toString(), ("Sxx\n" + "..x\n" + "..E\n"));
        assertEquals(queue_solver.getWorklistSize(), 0);

	}
	
	@Test
    public void test_dfs_maze2() {
        Maze maze = new Maze();
        maze.loadMaze("Mazes/maze-2.txt");        
        MazeSolver stack_solver = new MazeSolver(maze, new StackWorklist());
        stack_solver.solve();
        assertEquals(stack_solver.getPath(), "Found the Escape!" + 
                "\nPath from start to finish: [0,0] [1,0] [2,0] [2,1] [2,2] ");
        assertEquals(stack_solver.getMaze().toString(), ("So_\n" + "xoo\n" + "xxE\n"));
        assertEquals(stack_solver.getWorklistSize(), 3);

    }
	@Test
    public void test_bfs_maze3() {
        Maze maze = new Maze();
        maze.loadMaze("Mazes/maze-3.txt");       
        MazeSolver queue_solver = new MazeSolver(maze, new QueueWorklist());
        queue_solver.solve();
        assertEquals(queue_solver.getPath(), "Found the Escape!" + 
                "\nPath from start to finish: [0,1] [0,0] [1,0] [2,0] [3,0] [4,0] [5,0] [6,0] [7,0] [8,0] [9,0] [9,1] [9,2] [9,3] [9,4] [9,5] [9,6] [9,7] [9,8] [8,8] [7,8] [6,8] [5,8] [4,8] [3,8] [2,8] [2,7] [2,6] [2,5] [2,4] [2,3] [2,2] [3,2] [4,2] [5,2] [6,2] [7,2] [7,3] [7,4] [7,5] [7,6] [6,6] [5,6] [4,6] [4,5] [4,4] [5,4] ");
        assertEquals(queue_solver.getMaze().toString(), (
                "xS........\n"
                +"x#########\n"
                +"x#xxxxxxx#\n"
                +"x#x#####x#\n"
                +"x#x#xxx#x#\n"
                +"x#x#E#x#x#\n"
                +"x#x###x#x#\n"
                +"x#xxxxx#x#\n"
                +"x#######x#\n"
                +"xxxxxxxxx#\n"
                ));
        assertEquals(queue_solver.getWorklistSize(), 0);
    }
    
    @Test
    public void test_dfs_maze3() {
        Maze maze = new Maze();
        maze.loadMaze("Mazes/maze-3.txt");        
        MazeSolver stack_solver = new MazeSolver(maze, new StackWorklist());
        stack_solver.solve();
        assertEquals(stack_solver.getPath(), "Found the Escape!" + 
                "\nPath from start to finish: [0,1] [0,0] [1,0] [2,0] [3,0] [4,0] [5,0] [6,0] [7,0] [8,0] [9,0] [9,1] [9,2] [9,3] [9,4] [9,5] [9,6] [9,7] [9,8] [8,8] [7,8] [6,8] [5,8] [4,8] [3,8] [2,8] [2,7] [2,6] [2,5] [2,4] [2,3] [2,2] [3,2] [4,2] [5,2] [6,2] [7,2] [7,3] [7,4] [7,5] [7,6] [6,6] [5,6] [4,6] [4,5] [4,4] [5,4] ");
        assertEquals(stack_solver.getMaze().toString(), (
                "xSo_______\n"
                +"x#########\n"
                +"x#xxxxxxx#\n"
                +"x#x#####x#\n"
                +"x#x#xxx#x#\n"
                +"x#x#E#x#x#\n"
                +"x#x###x#x#\n"
                +"x#xxxxx#x#\n"
                +"x#######x#\n"
                +"xxxxxxxxx#\n"
                ));
        assertEquals(stack_solver.getWorklistSize(), 1);

    }
    
    @Test
    public void test_bfs_maze4() {
        Maze maze = new Maze();
        maze.loadMaze("Mazes/maze-4.txt");       
        MazeSolver queue_solver = new MazeSolver(maze, new QueueWorklist());
        queue_solver.solve();
        assertEquals(queue_solver.getPath(), "Found the Escape!" + 
                "\nPath from start to finish: [0,0] [0,1] [0,2] [0,3] [0,4] [0,5] [1,5] [2,5] [3,5] [4,5] [5,5] ");
        assertEquals(queue_solver.getMaze().toString(), (
                "Sxxxxx....\n"+
                ".....x....\n"+
                ".....x...o\n"+
                ".....x..o_\n"+
                ".....x.o__\n"+
                ".....Eo___\n"+
                "....oo____\n"+
                "...o______\n"+
                "..o_______\n"+
                ".o________\n"
                ));
        assertEquals(queue_solver.getWorklistSize(), 9);

    }
    
    @Test
    public void test_dfs_maze4() {
        Maze maze = new Maze();
        maze.loadMaze("Mazes/maze-4.txt");        
        MazeSolver stack_solver = new MazeSolver(maze, new StackWorklist());
        stack_solver.solve();
        assertEquals(stack_solver.getPath(), "Found the Escape!" + 
                "\nPath from start to finish: [0,0] [1,0] [2,0] [3,0] [4,0] [5,0] [6,0] [7,0] [8,0] [9,0] [9,1] [9,2] [9,3] [9,4] [9,5] [9,6] [9,7] [9,8] [9,9] [8,9] [7,9] [7,8] [7,7] [7,6] [7,5] [7,4] [7,3] [7,2] [6,2] [5,2] [5,3] [5,4] [5,5] ");
        assertEquals(stack_solver.getMaze().toString(), (
                "So________\n"+
                "xo________\n"+
                "xo________\n"+
                "xo________\n"+
                "xooooo____\n"+
                "xoxxxEo___\n"+
                "xoxooooooo\n"+
                "xoxxxxxxxx\n"+
                "xoooooooox\n"+
                "xxxxxxxxxx\n"
                ));
        assertEquals(stack_solver.getWorklistSize(), 28);

    }

    @Test
    public void test_bfs_maze5() {
        Maze maze = new Maze();
        maze.loadMaze("Mazes/maze-5.txt");       
        MazeSolver queue_solver = new MazeSolver(maze, new QueueWorklist());
        queue_solver.solve();
        assertEquals(queue_solver.getPath(), "Uh Oh!! There's no escape!!");
        assertEquals(queue_solver.getMaze().toString(), ("S...\n" + "..##\n" + "..#_\n"+"..#E\n"));
        assertEquals(queue_solver.getWorklistSize(), 0);

    }
    
    @Test
    public void test_dfs_maze5() {
        Maze maze = new Maze();
        maze.loadMaze("Mazes/maze-5.txt");        
        MazeSolver stack_solver = new MazeSolver(maze, new StackWorklist());
        stack_solver.solve();
        assertEquals(stack_solver.getPath(), "Uh Oh!! There's no escape!!");
        assertEquals(stack_solver.getMaze().toString(), ("S...\n" + "..##\n" + "..#_\n"+"..#E\n"));
        assertEquals(stack_solver.getWorklistSize(), 0);

    }
	
}
