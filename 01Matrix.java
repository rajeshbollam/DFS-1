//In this approach, we find the distance from 0s to 1s using BFS traversal. 
//We first add all the 0s into the queue and make 1s as -1s for avoiding confusion while marking them later as levels, could misunderstand as 1s and gives bugs
//We then process the elements from queue. For each element, we check if there are any neighbors which are -1, and mark them with the current distance.
//After each level, we increment the distance.
//We start first with distance variable as 1 because the elements that are adjacent to 0's should have a distance 1 and not 0, becuase we changed the elements to -1.
//Time Complexity: O(m*n) where m and n are rows and columns of the given matrix
//Space Complexity: O(m*n)

import java.util.LinkedList;
import java.util.Queue;

class Solution1 {
    public int[][] updateMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return matrix;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dirs = new int[][] {{1,0}, {0,1}, {-1,0}, {0,-1}};
        Queue<int[]>q = new LinkedList<>();
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(matrix[i][j] == 0){
                    q.add(new int[]{i,j});
                } else{
                    matrix[i][j] = -1;
                }
            }
        }
        int dist = 1; //because we are making our values as -1
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i<size; i++){
                int[] curr = q.poll();
                for(int[] dir: dirs){
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];
                    if(nr>=0 && nr<m && nc>=0 && nc<n && matrix[nr][nc] == -1) {
                        q.add(new int[] {nr, nc});
                        matrix[nr][nc] = dist;
                    }
                }
            }
            dist++;
        }
        return matrix;
    }
}