package Graph.StrongConnectComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.
        A critical connection is a connection that, if removed, will make some server unable to reach some other server.
        Return all critical connections in the network in any order.

        Example 1:
        Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
        Output: [[1,3]]
        Explanation: [[3,1]] is also accepted.

        Constraints:
        1 <= n <= 10^5
        n-1 <= connections.length <= 10^5
        connections[i][0] != connections[i][1]
        There are no repeated connections.

Basically, Tarjan's Algorithm uses dfs to travel through the graph to find if current vertex u, can travel back to u or previous vertex
timeMap records the earliest vertex's time u can reach
*/
public class CriticalConnectionsInaNetwork {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Integer> timeMap = new HashMap<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (List<Integer> connection : connections) {
            int start = connection.get(0);
            int end = connection.get(1);
            graph.computeIfAbsent(start, x -> new ArrayList<>()).add(end);
            graph.computeIfAbsent(end, x -> new ArrayList<>()).add(start);
        }
        dfs(graph, timeMap, res, new HashSet<>(), 0, -1, 0);
        return res;
    }

    private void dfs(Map<Integer, List<Integer>> graph, Map<Integer, Integer> timeMap, List<List<Integer>> res, Set<Integer> visited,
                     int cur, int pre, int time) {
        visited.add(cur);
        timeMap.put(cur, time);
        for (int nb : graph.get(cur)) {
            if (nb == pre) {
                continue;
            }
            if(!visited.contains(nb)){
                dfs(graph, timeMap, res, visited, nb, cur, time + 1);
            }
            timeMap.put(cur, Math.min(timeMap.get(cur), timeMap.get(nb)));
            if(time < timeMap.get(nb)){
                res.add(Arrays.asList(cur, nb));
            }
        }
    }
}
