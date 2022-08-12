class Solution {

    public static int maxAreaOfIsland(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int max = 0;
        int maxSoFar = 0;
        for (int i = 0; i< grid.length; i++){
            for (int j = 0; j<grid[0].length; j++){
                if(grid[i][j]==1){
                    int area = 0;
                    max = dfs(grid, i, j , area) ;
                    if(max > maxSoFar)
                        maxSoFar = max;
                }
            }
        }
        return maxSoFar;
    }
    public static int dfs(int[][] grid,  int sr, int sc, int area){
        int areaUp = 0 , areaLeft = 0 , areaRight = 0, areaDown = 0;
        grid[sr][sc] = 0;
        if(sr>=1 && grid[sr-1][sc] ==1)
            areaUp = dfs(grid,  sr-1, sc, ++area);
        if(sc>=1 && grid[sr][sc-1] == 1 )
            areaLeft = dfs(grid,  sr, sc-1, ++area);
        if(sc + 1 < grid[0].length && grid[sr][sc+1] == 1)
            areaRight = dfs(grid, sr, sc+1, ++area);
        if(sr + 1 < grid.length && grid[sr+1][sc] == 1)
            areaDown = dfs(grid,  sr+1, sc, ++area);
        return (areaUp + areaLeft + areaRight + areaDown) + 1;
    }


}
