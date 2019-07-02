package BST;

import java.util.HashMap;
import java.util.Map;

/*The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:

Input: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \ 
     3   1

Output: 7 
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:

Input: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \ 
 1   3   1

Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.*/
public class HouseRobberIII {
	public int rob(TreeNode root) {
		return dfs(root, new HashMap<>());
	}

	private int dfs(TreeNode root, Map<TreeNode, Integer> map) {
		if (root == null) {
			return 0;
		}
		if (map.containsKey(root)) {
			return map.get(root);
		}
		// take root money
		int temp = 0;
		if (root.left != null) {
			temp += dfs(root.left.left, map) + dfs(root.left.right, map);
		}
		if (root.right != null) {
			temp += dfs(root.right.left, map) + dfs(root.right.right, map);
		}
		int res = Math.max(temp + root.val, dfs(root.left, map) + dfs(root.right, map));
		map.put(root, res);
		return res;
	}
}