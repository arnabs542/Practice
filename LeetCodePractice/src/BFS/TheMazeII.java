package BFS;

import java.util.*;

/*
There is a ball in a maze with empty spaces and walls.
The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall.
When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination.
The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included).
If the ball cannot stop at the destination, return -1.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space.
You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

Example
Given:
a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

start coordinate (rowStart, colStart) = (0, 4)
destination coordinate (rowDest, colDest) = (4, 4)

Return:12
Notice
1.There is only one ball and one destination in the maze.
2.Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
3.The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
4.The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
*/
public class TheMazeII {
	class Point {
		int x, y, dist;
		public Point(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	int rows, cols;
	/**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: the shortest distance for the ball to stop at the destination
     */
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
    	int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        rows = maze.length;
        cols = maze[0].length;
        Point startPoint = new Point(start[0], start[1], 0);
        Queue<Point> queue = new LinkedList<>();
        int[][] cost = new int[rows][cols];
        for(int[] row : cost){
        	Arrays.fill(row, Integer.MAX_VALUE);
        }
        queue.offer(startPoint);
        cost[startPoint.x][startPoint.y] = 0;
        while(!queue.isEmpty()){
        	Point cur = queue.poll();
        	for(int i = 0; i < 4; i++){
        		Point nb = new Point(cur.x, cur.y, cur.dist);
				while (nb.x + dx[i] >= 0 && nb.x + dx[i] < rows && nb.y + dy[i] >= 0 && nb.y + dy[i] < cols
						&& maze[nb.x + dx[i]][nb.y + dy[i]] == 0) {
					nb.x += dx[i];
					nb.y += dy[i];
					nb.dist++;
				}
        		if(nb.dist < cost[nb.x][nb.y]){
        			queue.offer(nb);
        			cost[nb.x][nb.y] = nb.dist;
        		}
        	}
        }
        return cost[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : cost[destination[0]][destination[1]];
    }
}
