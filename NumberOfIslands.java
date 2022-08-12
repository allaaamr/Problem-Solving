class Solution {
 public static int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0)
                return 0;
            int counter = 0;
            for (int i = 0; i< grid.length; i++){
                for (int j = 0; j<grid[0].length; j++){
                    if(grid[i][j]=='1'){
                        counter ++;
                        dfs(grid, i, j );
                    }
                }
            }
            return counter;
        }
        public static void dfs(char[][] grid,  int sr, int sc){
            grid[sr][sc] = 0;
            if(sr>=1 && grid[sr-1][sc] =='1')
                dfs(grid,  sr-1, sc);
            if(sc>=1 && grid[sr][sc-1] == '1' )
                dfs(grid,  sr, sc-1);
            if(sc + 1 < grid[0].length && grid[sr][sc+1] == '1')
                dfs(grid, sr, sc+1);
            if(sr + 1 < grid.length && grid[sr+1][sc] == '1')
                dfs(grid,  sr+1, sc);
            return ;
        }
 
    

}
