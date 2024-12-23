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
    //Number Of Islands
    public int numIslands(char[][] grid) {
        private void dfs(int i, int j, int n, int m, char[][] grid, boolean[][] visited, int[][] directions) {
            for (int[] direction : directions) {
                int newI = direction[0] + i;
                int newJ = direction[1] + j;
                if (newI >= 0 && newI < n && newJ >= 0 && newJ < m && !visited[newI][newJ] 
                    && grid[newI][newJ] == '1') {
                    visited[newI][newJ] = true;
                    dfs(newI, newJ, n, m, grid, visited, directions);
                }
            }   
        }
        int n = grid.length;
        int m = grid[0].length;
        int islands = 0;
        int[][] directions = { { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 } };
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    islands++;
                    visited[i][j] = true;
                    dfs(i, j, n, m, grid, visited, directions);
                }
            }
        }
        return islands;
    }
    //Flood Fill Algorithm
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor)
    {
        void dfs(int row, int col, int initialColor, int n, int m, int newColor, 
        int[][] ans, 
        int[][] image, 
        int[][] directions) {
            ans[row][col] = newColor;
            for (int[] direction : directions) {
                int nRow = direction[0] + row;
                int nCol = direction[1] + col;
                if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m 
                    && image[nRow][nCol] == initialColor && ans[nRow][nCol] != newColor) {
                    dfs(nRow, nCol, initialColor, n, m, newColor, ans, 
                        image, directions);  
                }
            }
        }
        int n = image.length, m = image[0].length;
        int[][] ans = image;
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        dfs(sr, sc, image[sr][sc], n, m, newColor, ans, image, directions);
        return ans;
    }
    //Rotten Oranges
    public int orangesRotting(int[][] mat) {
        class Pair {
            int row, col, time;
            public Pair(int row, int col, int time) {
                this.row = row;
                this.col = col;
                this.time = time;
            }
        }
        int n = mat.length;
        int m = mat[0].length;
        int fresh = 0;
        Queue<Pair> queue = new LinkedList<>();
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }};
        int[][] visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 2) {
                    queue.add(new Pair(i ,j, 0));
                    visited[i][j] = 2;
                } else {
                    visited[i][j] = 0;
                }  
                if (mat[i][j] == 1) {
                    fresh++;
                }
            }
        }
        int cnt = 0;
        int time = 0;
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            time = Math.max(time, pair.time);
            for (int[] direction : directions) {
                int nrow = direction[0] + pair.row;
                int ncol = direction[1] + pair.col;
                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m 
                    && mat[nrow][ncol] == 1 && visited[nrow][ncol] == 0) {
                    visited[nrow][ncol] = 1;
                    queue.add(new Pair(nrow, ncol, pair.time + 1));
                    cnt++;
                }
            }
        }
        if (cnt != fresh) {
            return -1;
        }
        return time;
    }
    //Cycle in a Graph
    public boolean isCycle(ArrayList<ArrayList<Integer>> adj) {
        class Pair {
            int node, parent;
            public Pair(int node, int parent) {
                this.node = node;
                this.parent = parent;
            }
        }
        //Breadth First Search
        private boolean detectCycle(int src, int V, 
            ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
            Queue<Pair> queue = new LinkedList<>();
            queue.add(new Pair(src, -1));
            visited[src] = true;
            while (!queue.isEmpty()) {
                Pair pair = queue.poll();
                int node = pair.node;
                int parent = pair.parent;
                for (int neighbour : adj.get(node)) {
                    if (!visited[neighbour]) {
                        visited[neighbour] = true;
                        queue.add(new Pair(neighbour, node));
                    } else if (parent != neighbour) {
                        return true;
                    }
                }
            }
            return false;
        }
        //Depth First Search
        private boolean detectCycle(int node, int parent, 
            ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
            for (int neighbour : adj.get(node)) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    if (detectCycle(neighbour, node, adj, visited)) {
                        return true;
                    }
                } else if (parent != neighbour) {
                    return true;
                }
            }   
            return false;
        }
        int V = adj.size();
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (detectCycle(i, V, adj, visited)) {
                    return true;
                }
            }
        }
        return false;
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