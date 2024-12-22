public class Graph {
    //Breadth First Search
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[adj.size()];
        ArrayList<Integer> ans = new ArrayList<>();
        queue.add(0);
        ans.add(0);
        visited[0] = true;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbour : adj.get(current)) {
               if (!visited[neighbour]) {
                   queue.add(neighbour);
                   ans.add(neighbour);
                   visited[neighbour] = true;
               } 
            }  
        }
        return ans;
    }
    //Dijstra Algorithm Implementation
    public ArrayList<Integer> dijkstra(ArrayList<ArrayList<iPair>> adj, int src) {
        class iPair {
            int first, second;
            public iPair(int first, int second) {
                this.first = first;
                this.second = second;
            }
        }
        PriorityQueue<iPair> minPq = new PriorityQueue<>((x, y) -> x.second - y.second);
        int[] dist = new int[adj.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        minPq.add(new iPair(src, 0));
        while (!minPq.isEmpty()) {
            iPair node = minPq.poll();
            int vertex = node.first;
            int cost = node.second;
            for (iPair neighbour : adj.get(vertex)) {
                int nextVertex = neighbour.first;
                int edgeWeight = neighbour.second;
                if (cost + edgeWeight < dist[nextVertex]) {
                    dist[nextVertex] = cost + edgeWeight;
                    minPq.add(new iPair(nextVertex, dist[nextVertex]));
                }
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int dis : dist) {
            ans.add(dis);
        }
        return ans;
    }
}