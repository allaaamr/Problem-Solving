package DynamicProgramming;

import java.util.*;
class SubsetSumDP {


    //Naive Recursive Approach
     static boolean SubsetSumRecursive ( int values [],int n, int sum){
         if( sum ==0)
             return true;

         if (n==0)
             return false;

        if (values[n-1]> sum)
            SubsetSumRecursive(values, n-1, sum);

        return SubsetSumRecursive(values, n-1,  sum - values[n-1] )
                || SubsetSumRecursive(values, n-1,  sum );


    }
    static boolean SubsetSumDP ( int values [], int sum){
         int n = values.length;
         boolean S [][] = new boolean[n][sum+1];
         for(int i=0; i<n ; i++){
             for(int j=0; j<= sum; j++){
                 if(i==0)
                     continue;
                 if(j==0)
                     S[i][j] = true;
                 else
                     try {
                         return S[i - 1][j] || S[i - 1][j - values[i]];
                     } catch( ArrayIndexOutOfBoundsException e) {
                         S[i][j] = false;
                     }
             }
         }
         return S[values.length-1][sum];
     }

    public static void main(String args[])
    {
        int set[] = { 3, 34, 4, 12, 5, 2 };
        int sum = 9;
        int n = set.length;
        if (SubsetSumDP(set, sum) == true)
            System.out.println("True");
        else
            System.out.println("False");
    }
}
