package DynamicProgramming;

import java.util.Arrays;


public class AssemblyLineDP {


    public static int[] AssemblyLineDP(int a[][] , int t[][], int e[], int x[]) {
        int stations = a[0].length; //number of stations
        int f[][] = new int[2][a[1].length];
        int path[][] = new int[2][a[1].length];

        //entry of first assembly line
        f[0][0] = e[0] + a[0][0];
        f[1][0] = e[1] + a[1][0];


        for (int i = 1; i < stations; i++) {

            if ((f[0][i - 1] + a[0][i]) < (f[1][i - 1] + t[1][i] + a[0][i])) {
                f[0][i] = f[0][i - 1] + a[0][i]; // go directly from previous station on same assemblu linne
                path[0][i - 1] = 1;
            } else {
                f[0][i] = f[1][i - 1] + t[1][i] + a[0][i]; //transfer from second assembly line
                path[0][i - 1] = 2;
            }


            if ((f[1][i - 1] + a[1][i]) < (f[0][i - 1] + t[0][i] + a[1][i])) {
                f[1][i] = f[1][i - 1] + a[1][i]; // go directly from previous station on same assemblu linne
                path[1][i - 1] = 2;
            } else {
                f[1][i] = f[0][i - 1] + t[0][i] + a[1][i]; //transfer from first assembly line
                path[1][i - 1] = 1;

            }


        }

        //Optimal Route
        if (f[0][stations - 1] + x[0] < f[1][stations - 1] + x[1]) {
            path[0][stations-1]= 1;
            return path[0];
        }
        path[1][stations-1]= 2;
        return path[1];
    }

    public static void main (String[] args)
    {
        int a[][] = {{4, 5, 3, 2},
                {2, 10, 1, 4}};
        int t[][] = {{0, 7, 4, 5},
                {0, 9, 2, 8}};
        int e[] = {10, 12}, x[] = {18, 7};

        System.out.println(Arrays.toString(AssemblyLineDP(a, t, e, x)));

    }
}
