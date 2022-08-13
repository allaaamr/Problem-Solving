public class islandsG {
        public static int numEnclaves(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0)
                return 0;
            int count = 0;
            boolean [][] flags = new boolean [grid.length][grid[0].length];
            boolean [][] visited = new boolean [grid.length][grid[0].length];
            for (int i = 0; i< grid.length; i++){
                for (int j = 0; j<grid[0].length; j++){
                    visited [i][j]= true;
                    if(grid[i][j]==1){
                        if(dfs(grid, i, j, flags, visited )){
                            System.out.println(j);
                            count++;
                        }
                        else {
                            flags[i][j] = true;
                        }
                    }
                }
                System.out.println(java.util.Arrays.toString(flags[i]));
                System.out.println(count);
            }

            return count;
        }

        public static boolean dfs(int[][] grid,  int sr, int sc, boolean [][] flags, boolean [][] visited){
            visited [sr][sc] = true;
            boolean flag = true;
            if(sr==0 || sc == 0){
                flags[sr][sc] = true;
                return false;
            }
            if(sr>=1 && grid[sr-1][sc] ==1){
                if(flags[sr-1][sc]) {
                    flags[sr][sc] = true;
                    return false;
                }
                if (!visited[sr-1][sc]) {
                    flag = flag && dfs(grid, sr - 1, sc, flags, visited);
                    flags[sr][sc] = !flag;
                }
            }

            if(sc>=1 && grid[sr][sc-1] == 1 ){
                if(flags[sr][sc-1]) {
                    flags[sr][sc] = true;
                    return false;
                }
                if (!visited[sr][sc-1]) {
                    flag = flag && dfs(grid, sr, sc - 1, flags, visited);
                    flags[sr][sc] = !flag;
                }

            }

            if(sc + 1 < grid[0].length && grid[sr][sc+1] == 1){
                if (flags[sr][sc+1]) {
                    flags[sr][sc] = true;
                    return false;
                }

                if (!visited[sr][sc+1]) {
                    flag = flag && dfs(grid, sr, sc + 1, flags, visited);
                    flags[sr][sc] = !flag;
                }
            }

            if(sr + 1 < grid.length && grid[sr+1][sc] == 1){
                if (flags[sr+1][sc]){
                    flags[sr][sc] = true;
                    return false;
                }

                if (!visited[sr+1][sc]) {
                    flag = flag && dfs(grid, sr + 1, sc, flags, visited);
                    flags[sr][sc] = !flag;
                }
            }


            return flag;
        }



    public static void main(String [] args){
//            int [][] m =  {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
//            int [][] m = {{0,1,1,0},{0,0,1,0},{0,0,1,0},{0,0,0,0}};
//            int [][] m ={{0,0,0,1,1,1,0,1,0,0},{1,1,0,0,0,1,0,1,1,1},{0,0,0,1,1,1,0,1,0,0},{0,1,1,0,0,0,1,0,1,0},{0,1,1,1,1,1,0,0,1,0},{0,0,1,0,1,1,1,1,0,1},{0,1,1,0,0,0,1,1,1,1},{0,0,1,0,0,1,0,1,0,1},{1,0,1,0,1,1,0,0,0,0},{0,0,0,0,1,1,0,0,0,1}};
            int [][] m = {{0,0,0,1,1,1,0,1,0,0},{0,1,1,0,0,0,1,0,1,0},{0,1,1,1,1,1,0,0,1,0},{0,0,1,0,1,1,1,1,0,1}};
            System.out.println(numEnclaves(m));

        }


}
