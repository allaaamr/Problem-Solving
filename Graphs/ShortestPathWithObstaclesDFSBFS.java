package Graphs;

import java.util.*;

class Vertex {
    int row;
    int column;
    String path; //path to this vertex from the source
    public Vertex(int row, int column, String res)
    {
        this.row = row;
        this.column = column;
        this.path = res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return row == vertex.row && column == vertex.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}

public class FinalGame {

    static boolean flag=true;
    public static String dfs (String grid) {
        flag = true;
        int stepsCount = 0;
        String[] div = grid.split(";");
        //----------------------------------------
        String[] rowsColumns = div[0].split(",");
        int m = (Integer.parseInt(rowsColumns[0]));
        int n = (Integer.parseInt(rowsColumns[1]));
        //----------------------------------------
        String[] start = div[1].split(",");
        int startRow = (Integer.parseInt(start[0]));
        int startColumn = (Integer.parseInt(start[1]));
        //----------------------------------------
        String[] end = div[3].split(",");
        int endRow = (Integer.parseInt(end[0]));
        int endColumn = (Integer.parseInt(end[1]));
        //----------------------------------------
        String [] obs = div[2].split(",");

        if(startRow == endRow && startColumn == endColumn)
            return ";0";

        HashSet Obstacles = new HashSet();
        for(int i=0; i<obs.length; i+=2)
            Obstacles.add(new Vertex(Integer.parseInt(obs[i]), Integer.parseInt(obs[i+1]), ""));

        HashSet Discovered = new HashSet();
        String res = dfsHelper("", startRow,startColumn, Obstacles, Discovered, endRow, endColumn, m, n) ;

        if(!res.equals("No Solution")) {
            res = res.substring(0, res.length() - 1);
            stepsCount = res.split(",").length;
            res += ";" + stepsCount;
        }
        return res;
    }

    public static String dfsHelper (String res,int row, int column, HashSet Obstacles, HashSet Discovered, int endRow, int endColumn, int m, int n) {
        String a = "";
        if (row == endRow && column == endColumn) {
            flag = false;
            return res;
        }
        Vertex right = new Vertex(row, column+1, "");
        if (column < (n-1) && !Obstacles.contains(right) && !Discovered.contains(right) && flag ) {
            Discovered.add(right);
            a =  dfsHelper( res + "right,",  row, column + 1, Obstacles, Discovered, endRow, endColumn, m, n);
            if(!a.equals("No Solution"))
                return a;
        }

        Vertex left = new Vertex(row, column-1, "");
        if (column > 0 &&  !Obstacles.contains(left) && !Discovered.contains(left) && flag  ) {
            Discovered.add(left);
            a= dfsHelper(res + "left,", row, column - 1, Obstacles, Discovered , endRow, endColumn, m, n);
            if(!a.equals("No Solution"))
                return a;
        }
        Vertex down = new Vertex(row+1, column, "");
        if (row < (m-1) &&  !Obstacles.contains(down) && !Discovered.contains(down) && flag  ) {
            Discovered.add(down);
            a= dfsHelper( res + "down," , row + 1, column, Obstacles, Discovered , endRow, endColumn, m, n);
            if(!a.equals("No Solution"))
                return a;
        }
        Vertex up = new Vertex(row-1, column, "");
        if (row >0  && !Obstacles.contains(up) && !Discovered.contains(up) && flag  ) {
            Discovered.add(up);
            a= dfsHelper( res + "up,", row - 1, column, Obstacles, Discovered , endRow, endColumn, m, n);
            if(!a.equals("No Solution"))
                return a;
        }

        return "No Solution";
    }

    public static String bfs (String grid) {
        int stepsCount = 0;
        String[] div = grid.split(";");
        //----------------------------------------
        String[] rowsColumns = div[0].split(",");
        int m = (Integer.parseInt(rowsColumns[0]));
        int n = (Integer.parseInt(rowsColumns[1]));
        //----------------------------------------
        String[] start = div[1].split(",");
        int startRow = (Integer.parseInt(start[0]));
        int startColumn = (Integer.parseInt(start[1]));
        //----------------------------------------
        String[] end = div[3].split(",");
        int endRow = (Integer.parseInt(end[0]));
        int endColumn = (Integer.parseInt(end[1]));
        //----------------------------------------
        String [] obs = div[2].split(",");
        HashSet Obstacles = new HashSet();
        for(int i=0; i<obs.length; i+=2)
            Obstacles.add(new Vertex(Integer.parseInt(obs[i]), Integer.parseInt(obs[i+1]), ""));

        if(startRow == endRow && startColumn == endColumn)
            return ";0";

        //find source and add it to the linked list.
        Vertex source = new Vertex(startRow, startColumn, "");
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(source);
        HashSet Discovered = new HashSet();

        while(!queue.isEmpty()){
            Vertex dequedVertex = queue.remove();
            int row = dequedVertex.row;
            int col = dequedVertex.column;
            String vertexpath = dequedVertex.path;
            Discovered.add(dequedVertex);

            //check if it is the destination
            if(row == endRow && col == endColumn) {
                vertexpath = vertexpath.substring(1);
                stepsCount = vertexpath.split(",").length;
                vertexpath += ";" + stepsCount;
                return vertexpath;
            }

            // check if we can go right
            Vertex right = new Vertex(row, col+1, vertexpath +",right");
            if(col < (n-1) && !Obstacles.contains(right) && !Discovered.contains(right) )
                queue.add(right);

            Vertex left = new Vertex(row, col-1, vertexpath +",left");
            // check if we can go left
            if(col > 0 && !Obstacles.contains(left) && !Discovered.contains(left))
                queue.add(left);

            Vertex up = new Vertex(row-1, col, vertexpath +",up");
            // check if we can go up
            if(row>0 && !Obstacles.contains(up) && !Discovered.contains(up))
                queue.add(up);

            Vertex down = new Vertex(row+1, col, vertexpath +",down");
            // check if we can go down
            if(row<(m-1) && !Obstacles.contains(down) && !Discovered.contains(down))
                queue.add(down);
        }

        return "No Solution";
    }

    public static void main(String [] args){
        String grid = "10,10;5,3;0,1,8,0,3,9,5,8,6,7,2,1,0,8,3,2,0,0,7,4,3,9,5,5,1,0,2,7,2,0;1,7";
        System.out.println(dfs(grid));
    }
}
