class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
     
       int current_color = image[sr][sc];
       if (color != current_color) 
            dfs(image, sr, sc, current_color, color);
       return image; 
    }
    public void dfs(int[][] image, int sr, int sc, int current_color, int new_color){
        if( image[sr][sc] == current_color){
            image[sr][sc] = new_color;
            if(sr>=1)
                dfs(image, sr-1, sc, current_color, new_color);
            if(sc>=1 )
                dfs(image, sr, sc-1, current_color, new_color);
            if(sr+1 < image.length)
                dfs(image, sr+1, sc, current_color, new_color);
            if(sc+1 < image[0].length)
                dfs(image, sr, sc+1, current_color, new_color);
        }
    }
}
