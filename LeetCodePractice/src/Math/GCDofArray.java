package Math;
//find the greatest common divisor of a positive array
public class GCDofArray {
	public int generalizedGCD(int num, int[] arr)
    {
        // WRITE YOUR CODE HERE
        int res = arr[0];
        for(int i = 1; i < num; i++){
            res = gcd(res, arr[i]);
        }
        return res;
        
    }
    private int gcd(int a, int b){
        if(a == 0){
            return b;
        }
        return gcd(b % a, a);
    }
}
