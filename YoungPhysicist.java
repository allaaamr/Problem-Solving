import java.io.PrintWriter;
import java.util.*;
import java.io.*;


public class YoungPhysicist {
    public static void main(String [] args) throws IOException {
        BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
        int n = Integer.parseInt( inp.readLine());
//        System.out.println("n " + n);
        String res = "YES";
        int x = 0;
        int y = 0;
        int z = 0;

        for(int i=0; i<n; i++){
//            System.out.println("Loop " + i);
            String s = inp.readLine();
//            System.out.println("s " + s);
            String vector []  = s.split(" ");
//            System.out.println("v " + vector.length);
            x += Integer.parseInt(vector[0]);
//            System.out.println("x " + x);
            y += Integer.parseInt(vector[1]);
            z += Integer.parseInt(vector[2]);

        }
        if( x!=0 || y!=0 || z!=0)
             res = "NO";

        PrintWriter pw= new PrintWriter(System.out);
        pw.println(res);
        pw.flush();
        pw.close();
    }
}

