package BitManipulation;

/*
        Given an array of integers arr.
        We want to select three indices i, j and k where (0 <= i < j <= k < arr.length).
        Let's define a and b as follows:

        a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
        b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
        Note that ^ denotes the bitwise-xor operation.

        Return the number of triplets (i, j and k) Where a == b.

        Example 1:
        Input: arr = [2,3,1,6,7]
        Output: 4
        Explanation: The triplets are (0,1,2), (0,2,2), (2,3,4) and (2,4,4)

        Example 2:
        Input: arr = [1,1,1,1,1]
        Output: 10

        Example 3:
        Input: arr = [2,3]
        Output: 0

        Example 4:
        Input: arr = [1,3,5,7,9]
        Output: 3

        Example 5:
        Input: arr = [7,11,12,9,5,2,7,17,22]
        Output: 8


        Constraints:
        1 <= arr.length <= 300
        1 <= arr[i] <= 10^8

analysis:
XOR: 1 a ^ b ^ b = a
     2 0 ^ a = a
*/

public class CountTripletsThatCanFormTwoArraysOfEqualXOR {
    public int countTriplets(int[] arr) {
        int len = arr.length, res = 0;
        int[] preSum = new int[len + 1]; // append preSum[0] = 0
        for(int i = 0; i < len; i++){
            preSum[i + 1] = preSum[i] ^ arr[i];
        }
        for(int i = 0; i < len; i++){
            for(int k = i + 1; k <= len; k++){
                if(preSum[i] == preSum[k]){ // arr[i + 1:k - 1] XOR is 0
                    res += k - i - 1;
                }
            }
        }
        return res;
    }


}
