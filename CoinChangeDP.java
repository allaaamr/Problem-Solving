public class CoinChangeDP {


    public static int CoinChangeRecursive(int[] coins, int M, int N){

        if(N==0)
            return 1;
        if(N<0)
        	return 0;
        if(M<=0)
        	return 0;
        else return CoinChangeRecursive(coins, M-1, N) + CoinChangeRecursive(coins, M, N-coins[M-1]);
        
    }
    
    public static int CoinChangeDP (int[] coins, int M, int N){
    	
    	//      0  1  2  3  4  5  6  7  8  9  10
    	//      1  0  1  1  1  2  3  2  4  4  5
    	//Space O(N)
    	int [] result = new int [N+1];
    	result [0]=1;
    	
    	for( int i = 0; i < M; i++) {
    		for( int j = coins[i]; j <= N; j++) {
    			result[j] += result[j-coins[i]];      
    		}
    		
    	}
    		
    	
    	
    	return result[N];
    }

    public static void main(String[] args){
        int [] coins = {2,5,3, 6};
        System.out.println(CoinChangeRecursive(coins, 4, 10));
        System.out.println(CoinChangeDP(coins, 4, 10));

    }
}
