//The approach here is to traverse the graph through BFS.
//We first change the color of the given index to the given color. Then we add it to the queue and then we start processing their neighbors. 
//We change their color to the given color if they match with the original color of the given index and add them to the queue to continue the same for their neighbors as well
//Time Complexity: O(m*n) where m and n are rows and columns of the given matrix
//Space Complexity: O(m*n)
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int m = image.length;
        int n = image[0].length;
        int[][] dirs = new int[][] {{-1,0}, {1,0}, {0,-1}, {0,1}};
        Queue<int[]> q = new LinkedList<>();
        int orgCol = image[sr][sc];
        if(orgCol == color) return image;
        q.add(new int[]{sr, sc});
        image[sr][sc] = color;
        while(!q.isEmpty()){
            int[] curr = q.poll();
            for(int[] dir: dirs){
                int nr = curr[0] + dir[0];
                int nc = curr[1] + dir[1];
                if(nr>=0 && nr<m && nc>=0 && nc<n && image[nr][nc] == orgCol){
                    image[nr][nc] = color;
                    q.add(new int[]{nr, nc});
                }
            }
        }
        return image;
    }
}

//In this approach, we traverse the graph through DFS.
//We start with the element at the given index, we change it's color and perform the recursion to it's neighbors and it continues for all of it's neighbors
//The recursion stops and returns to parent if the element is not within the matrix or if is not of the original color of the given element.
//Time Complexity: O(m*n)
//Space Complexity: O(m*n)
class Solution1 {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int[][] dirs = new int[][] {{-1,0}, {1,0}, {0,-1}, {0,1}};
        int orgCol = image[sr][sc];
        if(orgCol == color) return image;
        dfs(image, sr, sc, color, orgCol, dirs);
        return image;        
    }

    private void dfs(int[][] image, int sr, int sc, int color, int orgCol, int[][] dirs){
        //base
        if(sr<0 || sr == image.length || sc < 0 || sc == image[0].length || image[sr][sc] != orgCol){
            return;
        }
        image[sr][sc] = color;
        for(int[] dir: dirs){
            int nr = sr + dir[0];
            int nc = sc + dir[1];
            dfs(image, nr, nc, color, orgCol, dirs);
        }
    }
}