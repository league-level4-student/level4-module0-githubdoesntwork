package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		Random r = new Random();
		selectNextPath(maze.getCell(r.nextInt(width), r.nextInt(height)));
		//5. call selectNextPath method with the randomly selected cell
		
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		Random r = new Random();
		//A. mark cell as visited
currentCell.setBeenVisited(true);
		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		ArrayList<Cell> list = getUnvisitedNeighbors(currentCell);
		//C. if has unvisited neighbors,
		if(list.size()>0) {
			//C1. select one at random.
			Cell newCell = list.get(r.nextInt(list.size()));
			uncheckedCells.push(newCell);
			//C2. push it to the stack
		
			//C3. remove the wall between the two cells
			removeWalls(newCell, currentCell);
			//C4. make the new cell the current cell and mark it as visited
			newCell.setBeenVisited(true);
			//C5. call the selectNextPath method with the current cell
			selectNextPath(newCell);
		}else {
			
		//D. if all neighbors are visited
			
			//D1. if the stack is not empty
			if(uncheckedCells.size()>0) {
				// D1a. pop a cell from the stack
				Cell cell = uncheckedCells.peek();
		uncheckedCells.pop();
				// D1b. make that the current cell
		selectNextPath(cell);
				// D1c. call the selectNextPath method with the current cell
				
			}
		}
			
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if(c2.getY()==c1.getY()+1) {
			c2.setNorthWall(false);
			c1.setSouthWall(false);
		}
		if(c2.getY()==c1.getY()-1) {
			c2.setSouthWall(false);
			c1.setNorthWall(false);
		}
		if(c2.getX()==c1.getX()+1) {
			c2.setWestWall(false);
			c1.setEastWall(false);
		}
		if(c2.getX()==c1.getX()-1) {
			c2.setEastWall(false);
			c1.setWestWall(false);
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> list = new ArrayList<Cell>();
		if(c.getY()<=3&&!maze.getCell(c.getX(), c.getY()+1).hasBeenVisited()) {
			list.add(maze.getCell(c.getX(), c.getY()+1));
		}
		if(c.getY()>=1&&!maze.getCell(c.getX(), c.getY()-1).hasBeenVisited()) {
			list.add(maze.getCell(c.getX(), c.getY()-1));
		}
		if(c.getX()<=3&&!maze.getCell(c.getX()+1, c.getY()).hasBeenVisited()) {
			list.add(maze.getCell(c.getX()+1, c.getY()));
		}
		if(c.getX()>=1&&!maze.getCell(c.getX()-1, c.getY()).hasBeenVisited()) {
			list.add(maze.getCell(c.getX()-1, c.getY()));
		}
		return list;
	}
}
