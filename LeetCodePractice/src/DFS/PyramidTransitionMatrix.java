package DFS;

import java.util.*;

import javax.xml.transform.Templates;

/*We are stacking blocks to form a pyramid. Each block has a color which is a one letter string, like `'Z'`.
For every block of color `C` we place not in the bottom row, we are placing it on top of a left block of color `A` and right block of color `B`. We are allowed to place the block there only if `(A, B, C)` is an allowed triple.
We start with a bottom row of bottom, represented as a single string. We also start with a list of allowed triples allowed. Each allowed triple is represented as a string of length 3.
Return true if we can build the pyramid all the way to the top, otherwise false.

Example 1:
Input: bottom = "XYZ", allowed = ["XYD", "YZE", "DEA", "FFF"]
Output: true
Explanation:
We can stack the pyramid like this:
    A
   / \
  D   E
 / \ / \
X   Y   Z

This works because ('X', 'Y', 'D'), ('Y', 'Z', 'E'), and ('D', 'E', 'A') are allowed triples.
Example 2:
Input: bottom = "XXYX", allowed = ["XXX", "XXY", "XYX", "XYY", "YXZ"]
Output: false
Explanation:
We can't stack the pyramid to the top.
Note that there could be allowed triples (A, B, C) and (A, B, D) with C != D.
Note:
bottom will be a string with length in range [2, 8].
allowed will have length in range [0, 200].
Letters in all strings will be chosen from the set {'A', 'B', 'C', 'D', 'E', 'F', 'G'}.*/
public class PyramidTransitionMatrix {
	public boolean pyramidTransition(String bottom, List<String> allowed) {
		Map<String, List<String>> map = new HashMap<>();
		for(String s : allowed) {
			String key = s.substring(0, 2);
			String value = s.substring(2);
			if(map.containsKey(key)) {
				map.get(key).add(value);
			} else {
				List<String> ls = new ArrayList<>();
				ls.add(value);
				map.put(key, ls);
			}
		}
        return helper(bottom, map);
    }
	private List<String> dfs(String cur, Map<String, List<String>> map) {
		int len = cur.length();
		List<String> ls = new ArrayList<>();
		ls.add("");
		for(int i = 0; i < len - 1; i++) {
			List<String> temp = new ArrayList<>();
			String candidate = cur.substring(i, i + 2);
			if(map.containsKey(candidate)) {
				temp.addAll(map.get(candidate));
			}
			List<String> nextlevel = new ArrayList<>();
			for(String s : ls) {
				for(String t : temp) {
					nextlevel.add(s + t);
				}
			}
			ls = new ArrayList<>(nextlevel);
		}
		return ls;
	}
	private boolean helper(String cur, Map<String, List<String>> map) {
		List<String> level = dfs(cur, map);
		for(String s : level) {
			if(s.length() == 1) {
				return true;
			}
			if(helper(s, map)) {
				return true;
			}
		}
		return false;		
	}
}