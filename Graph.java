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
    //Depth First Search
    public ArrayList<Integer> dfsOfGraph(ArrayList<ArrayList<Integer>> adj) {
        void dfs(int current, ArrayList<ArrayList<Integer>> adj, boolean[] visited, ArrayList<Integer> ans) {
            if (adj.get(current).size() == 0) {
                return;
            }
            for (int neighbour : adj.get(current)) {
                if (!visited[neighbour]) {
                    ans.add(neighbour);
                    visited[neighbour] = true;
                    dfs(neighbour, adj, visited, ans);
                }
            }   
        }
        boolean[] visited = new boolean[adj.size()];
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(0);
        visited[0] = true;
        dfs(0, adj, visited, ans);
        return ans;
    }
    //Number Of Provinces
    int numProvinces(ArrayList<ArrayList<Integer>> adj, int V) {
        void dfs(int node, List<ArrayList<Integer>> adj, boolean[] visited, int V) {
            for (int i = 0; i < V; i++) {
                if (adj.get(node).get(i) == 1 && !visited[i]) {
                    visited[i] = true;
                    dfs(i, adj, visited, V);
                }
            }
        }
        boolean[] visited = new boolean[V];
        int ans = 0;
        for (int node = 0; node < V; node++) {
            if (!visited[node]) {
                visited[node] = true;
                ans++;
                dfs(node, adj, visited, V);
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