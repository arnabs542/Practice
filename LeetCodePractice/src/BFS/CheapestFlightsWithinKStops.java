package BFS;

import java.util.*;

public class CheapestFlightsWithinKStops {
	// using Dijkstra algorithm
	public int findCheapestPriceDijk(int n, int[][] flights, int src, int dst, int K) {
        int[][] graph = new int[n][n];
        for(int[] f : flights){
            graph[f[0]][f[1]] = f[2];
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {return a[1] - b[1];});
        // stop : price : stop
        pq.offer(new int[]{src, 0, -1});
        while(!pq.isEmpty()){
            int[] temp = pq.poll();
        	int city = temp[0];
        	int price = temp[1];
            int stop = temp[2];
            if(stop > K){
                continue;
            }
        	if(city == dst){
        		return price;
        	}
        	for(int nb = 0; nb < graph[city].length; nb++){
        		if(graph[city][nb] > 0){
        			// is valid nb
        			pq.offer(new int[]{nb, graph[city][nb] + price, stop + 1});
        		}
        	}
        }
    	return -1;
    }
	
	// using BFS
	public int findCheapestPriceBFS(int n, int[][] flights, int src, int dst, int K) {
        int[] costs = new int[n];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[src] = 0;
        int[][] graph = new int[n][n];
        for(int[] f : flights){
            graph[f[0]][f[1]] = f[2];
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);
        int stops = 0;
        while(!queue.isEmpty()){
            if(stops > K){
                break;
            }
            int[] costSnapShot = Arrays.copyOf(costs, costs.length);
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int city = queue.poll();      
        	    for(int nb = 0; nb < graph[city].length; nb++){
        		    if(graph[city][nb] > 0){
        			    // is valid nb
                        if(costs[nb] > graph[city][nb] + costSnapShot[city]){
                            costs[nb] = graph[city][nb] + costSnapShot[city];
                            queue.offer(nb);
                        }
        		    }
        	    }
            }
            stops++;
        }
    	return costs[dst] == Integer.MAX_VALUE ? -1 : costs[dst];
    }
	// using DP
	public int findCheapestPriceDP(int n, int[][] flights, int src, int dst, int K) {
		int[][] dp = new int[K + 2][n];
        for(int[] row : dp){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dp[0][src] = 0;
        for(int i = 1; i <= K + 1; i++){
        	dp[i][src] = 0;
        	for(int[] flight : flights){
        		int start = flight[0];
        		int end = flight[1];
        		int cost = flight[2];
                if(dp[i - 1][start] < Integer.MAX_VALUE){
                    dp[i][end] = Math.min(dp[i][end], dp[i - 1][start] + cost);
                }
        	}
        }
        return dp[K + 1][dst] == Integer.MAX_VALUE ? -1 : dp[K + 1][dst];
        
    }
}
