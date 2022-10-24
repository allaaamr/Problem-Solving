package Graphs;

class SquidGame {

    static String [] partitions;
    static String [] EPartitions;
    public static String res;
    public static int sum(int [] l, int start, int end){
        int sum = 0;
        for(int i=start; i<=end; i++){
            sum+=l[i];
        }
        return sum;
    }

    public static String GetPartitions(int k, int maxSum, int l[]){

       EPartitions = new String [k+1];
       int sum = 0;
       int playersNeeded = 1;
        EPartitions[1] ="";
        for(int i =0; i< l.length; i++){
           if (sum + l[i]> maxSum){
                   sum=l[i];
                    EPartitions[playersNeeded + 1] = ";" + l[i];
                    playersNeeded++;
            }
           //remaining players that are not assigned any field yet = (k-playersNeeded)
           // remaining fields = l.length-i
            else if((l.length-i) > k-playersNeeded){
                sum += l[i];
                if( (EPartitions [playersNeeded].equals("")) ||( !(EPartitions [playersNeeded].equals("")) && (EPartitions [playersNeeded].charAt(EPartitions [playersNeeded].length()-1)) == ';'))
                   EPartitions [playersNeeded] += l[i] ;
                else
                    EPartitions [playersNeeded] += "," + l[i] ;
            }
            else{
               sum=l[i];
               EPartitions[playersNeeded + 1] = ";" + l[i];
               playersNeeded++;
           }

        }
        EPartitions[0] = maxSum + ";";
        partitions = EPartitions;
        String result="";
        for (int m =0; m<=partitions.length-1; m++){
            result+= partitions[m];
       }
        return  result;
    }

    public static String naive(int k , int [] l){
        int max = naiveHelper(k,l, 0, l.length-1);
        String result = GetPartitions(k, max, l);
        return result;
    }
    public static int naiveHelper (int k, int [] l, int start, int end) {
        int smallest_maximumSum;
        if(k==1) {
//            TrialPartitions[p] = start + " "+ end;
            smallest_maximumSum = sum(l, start, end);

        }
        else {
            smallest_maximumSum = Integer.MAX_VALUE;
            for (int j = start + 1; j <= end; j++) {
                int firstPartition = sum(l, start, j - 1);
//                TrialPartitions[p] = start + " " + (j - 1);
                int RemainingPartitions = naiveHelper(k - 1, l, j, end);
                int maximum_sum = Math.max(firstPartition, RemainingPartitions);
                if (smallest_maximumSum > maximum_sum) {
                    smallest_maximumSum = maximum_sum;
//                    if(partitions[0] != null && (Integer.parseInt(partitions[0]) !=  maximum_sum ))
//                         partitions = TrialPartitions.clone();

                }


            }
        }
//
// //       partitions[0] = smallest_maximumSum+"";
       return smallest_maximumSum;
            }

    public static String reconstruct( int []l, int players, int MaxSum){
        res="";
        int sum = 0;
        int playersNeeded = 1;
        boolean firstElement= true;
        for(int i =0; i< l.length; i++){


            if (sum + l[i]> MaxSum){
                sum=l[i];
                res += ";" + l[i];
                playersNeeded++;
            }

            else if((l.length-i) > players-playersNeeded){
                if (firstElement){
                    res +=  ";" + l[i];
                    firstElement = false;
                }
                else {
                    res +=  "," + l[i];
                }
                sum += l[i];
            }
            else{
                sum=l[i];
                res +=  ";" + l[i];
                playersNeeded++;
            }
        }

        return  MaxSum + res;

    }

    public static boolean CheckIfPossibleSum (int [] l, int players, int MidSum){
        int sum = 0;
        int playersNeeded = 1;
        for(int i =0; i< l.length; i++){

            if(l[i]>MidSum)
                return false;

            if (sum + l[i]> MidSum){
                    sum=l[i];
                    playersNeeded++;
            }
            else {
                sum += l[i];
            }
        }
        if (playersNeeded > players)
                return false;
        return true;

    }

    public static String efficient (int k, int [] l){
        int minimumMaxSum = Integer.MIN_VALUE; //biggest value in array
        int biggestMaxSum =0; //total sum
        for (int i = 0; i<=l.length-1 ; i++){
            biggestMaxSum += l[i];
            if( l[i] > minimumMaxSum)
                minimumMaxSum = l[i];
        }
        int low = minimumMaxSum;
        int high = biggestMaxSum;
        int middleSum;
        int MaxSum =0;
        while(low <= high){
             middleSum = (low + high)/2;
             if (CheckIfPossibleSum(l, k, middleSum)){
                 MaxSum = middleSum;
                 high = middleSum -1;
             }
             else
                 low = middleSum +1;

        }


        return reconstruct(l,k, MaxSum);






    }


    public static void main (String[] args)
    {
//        int l[] = {10, 20, 30, 40};
//        System.out.println( efficient( 2, l));



    }
}


