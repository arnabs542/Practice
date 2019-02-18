package UnionFind;
/*Given an array equations of strings that represent relationships between variables, each string equations[i] has length 4 and takes one of two different forms: "a==b" or "a!=b".  Here, a and b are lowercase letters (not necessarily different) that represent one-letter variable names.

Return true if and only if it is possible to assign integers to variable names so as to satisfy all the given equations.

 

Example 1:

Input: ["a==b","b!=a"]
Output: false
Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.  There is no way to assign the variables to satisfy both equations.
Example 2:

Input: ["b==a","a==b"]
Output: true
Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
Example 3:

Input: ["a==b","b==c","a==c"]
Output: true
Example 4:

Input: ["a==b","b!=c","c==a"]
Output: false
Example 5:

Input: ["c==c","b==d","x!=z"]
Output: true*/
public class SatisfiabilityOfEqualityEquations {
	int[] id;
	public boolean equationsPossible(String[] equations) {
		//all lower case alphabet
        id = new int[26];
        for(int i = 0; i < 26; i++){
        	id[i] = i;
        }
        for(String s : equations){
        	if(s.charAt(1) == '='){
        		union(s.charAt(0) - 'a', s.charAt(3) - 'a');
        	}
        }
        for(String s : equations){
        	if(s.charAt(1) == '!'){
        		int root1 = find(s.charAt(0) - 'a');
        		int root2 = find(s.charAt(3) - 'a');
        		if(root1 == root2){
        			return false;
        		}
        	}
        }
        return true;
    }
	private int find(int x){
		int root = x;
		while(root != id[root]){
			root = id[root];
		}
		while(x != root){
			int temp = id[x];
			id[x] = root;
			x = temp;
		}
		return root;
	}
	private void union(int a, int b){
		int root_a = find(a);
		int root_b = find(b);
		if(root_a != root_b){
			id[root_a] = root_b;
		}
	}
}
