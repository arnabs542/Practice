package DFS;

/*
Given a 2D array A, each cell is 0 (representing sea) or 1 (representing land)

A move consists of walking from one land square 4-directionally to another land square, or off the boundary of the grid.

Return the number of land squares in the grid for which we cannot walk off the boundary of the grid in any number of moves.



Example 1:

Input: [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
Output: 3
Explanation:
There are three 1s that are enclosed by 0s, and one 1 that isn't enclosed because its on the boundary.
Example 2:

Input: [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
Output: 0
Explanation:
All 1s are either on the boundary or can reach the boundary.


Note:

1 <= A.length <= 500
1 <= A[i].length <= 500
0 <= A[i][j] <= 1
All rows have the same size.
 */
public class NumberOfEnclaves {
    int rows, cols;
    int[] dirs = {0, 1, 0, -1, 0};

    public int numEnclaves(int[][] A) {
        rows = A.length;
        cols = A[0].length;
        for (int r = 0; r < rows; r++) {
            if(A[r][0] == 1){
                dfs(A, r, 0);
            }
            if(A[r][cols - 1] == 1){
                dfs(A, r, cols - 1);
            }
        }
        for (int c = 0; c < cols; c++) {
            if(A[0][c] == 1){
                dfs(A, 0, c);
            }
            if(A[rows - 1][c] == 1){
                dfs(A, rows - 1, c);
            }
        }
        int res = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (A[r][c] == 1) {
                    res++;
                }
            }
        }
        return res;
    }

    private void dfs(int[][] A, int r, int c) {
        A[r][c] = 0;
        for (int i = 0; i < dirs.length - 1; i++) {
            int nb_r = r + dirs[i];
            int nb_c = c + dirs[i + 1];
            if (nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols && A[nb_r][nb_c] == 1) {
                dfs(A, nb_r, nb_c);
            }
        }
    }
}
