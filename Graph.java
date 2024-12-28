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
    //Distance of nearest cell having 1
    public int[][] nearest(int[][] grid)
    {
        class Pair {
            int row, col, dist;
            public Pair (int row, int col, int dist) {
                this.row = row;
                this.col = col;
                this.dist = dist;
            }
        }   
        int n = grid.length, m = grid[0].length;
        int[][] ans = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        Queue<Pair> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    ans[i][j] = 0;
                    visited[i][j] = true;
                    queue.add(new Pair(i, j, 0));
                }
            }
        }
        int[][] directions = { { -1, 0 } , { 1, 0 }, { 0, -1 }, { 0, 1 } };
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int row = pair.row;
            int col = pair.col;
            int dist = pair.dist;
            for (int[] direction : directions) {
                int nrow = row + direction[0];
                int ncol = col + direction[1];
                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m &&
                    !visited[nrow][ncol] && grid[nrow][ncol] == 0) {
                    visited[nrow][ncol] = true;
                    ans[nrow][ncol] = dist + 1;
                    queue.add(new Pair(nrow, ncol, dist + 1));
                }
            }
        }
        return ans;
    }
    //Replace 'O's with 'X's
    public char[][] fill(char mat[][]) {
        private void dfs(int row, int col, int n, int m, int[][] directions, boolean[][] visited, char[][] grid) {
            for (int[] direction : directions) {
                int nrow = row + direction[0];
                int ncol = col + direction[1];
                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && 
                    grid[nrow][ncol] == 'O' && !visited[nrow][ncol]) {
                        visited[nrow][ncol] = true;
                    dfs(nrow, ncol,n, m, directions, visited, grid);    
                }
            }
        }   
        int n = mat.length, m = mat[0].length;
        boolean[][] visited = new boolean[n][m];
        char[][] ans = new char[n][m];
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans[i][j] = mat[i][j];
                if (mat[i][j] == 'O' && !visited[i][j]) {
                    if (i == 0 || i == n -1 || j == 0 || j == m - 1) {
                        visited[i][j] = true;
                        dfs(i, j, n, m, directions, visited, mat);
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && mat[i][j] == 'O') {
                    ans[i][j] = 'X';
                }
            }
        }
        return ans;
    }
    //Number of Enclaves
    public int numberOfEnclaves(int[][] grid) {
        class Pair {
            int row;
            int col;
            public Pair (int row, int col) {
                this.row = row;
                this.col = col;
            }
        }
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        Queue<Pair> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                        visited[i][j] = true;
                        queue.add(new Pair(i, j));
                    }
                }
            }
        }
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            for (int[] direction : directions) {
                int nrow = pair.row + direction[0];
                int ncol = pair.col + direction[1];
                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m 
                    && grid[nrow][ncol] == 1 && !visited[nrow][ncol]) {
                    visited[nrow][ncol] = true;
                    queue.add(new Pair(nrow, ncol));
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    ans++;
                }
            }
        }
        return ans;
    }
    //Number of Distinct Islands
    public int countDistinctIslands(int[][] grid) {
        private void dfs(int row, int col, int n, int m, int baseRow, int baseCol, 
            int[][] grid, 
            int[][] directions, 
            boolean[][] visited, 
            List<String> ds) 
        {
            visited[row][col] = true;
            ds.add(toString(row - baseRow, col - baseCol));
            for (int[] direction : directions) {
                int nrow = row + direction[0];
                int ncol = col + direction[1];
                if (nrow >= 0 && ncol >= 0 && nrow < n && ncol < m && grid[nrow][ncol] == 1
                    && !visited[nrow][ncol]) {
                    dfs(nrow, ncol, n, m, baseRow, baseCol, grid, directions, visited, ds);
                }
            }
        }
        private String toString(int r, int c) {
            return Integer.toString(r) + " " + Integer.toString(c);
        }
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        Set<List<String>> seen = new HashSet<>();
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    List<String> ds = new ArrayList<>();
                    dfs(i, j, n, m, i, j, grid, directions, visited, ds);
                    seen.add(ds);
                }
            }
        }
        return seen.size();
    }
    //Bipartite Graph
    public boolean isBipartite(ArrayList<ArrayList<Integer>> adj) {
        class Pair {
            int node;
            int color;
            public Pair(int node, int color) {
                this.node = node;
                this.color = color;
            }
        }
        //DFS
        private boolean dfs(int node, int[][] graph, int[] coloring) {
            for (int neighbour : graph[node]) {
                if (coloring[neighbour] == -1) {
                    coloring[neighbour] = 1 - coloring[node];
                    if (!dfs(neighbour, graph, coloring)) {
                        return false;
                    }
                } else if (coloring[neighbour] == coloring[node]) {
                    return false;
                }
            }
            return true;
        }
        //BFS
        Queue<Pair> queue = new LinkedList<>();
        int[] colors = { 0, 1 };
        int[] nodes = new int[adj.size()];
        Arrays.fill(nodes, -1);
        nodes[0] = 0;
        queue.add(new Pair(0, 0));
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int node = pair.node;
            int color = pair.color;
            for (int neighbour : adj.get(node)) {
                if (nodes[neighbour] == -1) {
                    for (int c : colors) {
                        if (c != color) {
                            nodes[neighbour] = c;
                            queue.add(new Pair(neighbour, c));
                        }
                    } 
                    if (nodes[neighbour] == -1) {
                        return false;
                    }
                } else if (nodes[neighbour] == color) {
                    return false;
                }
            }
        }
        return true;
    }
    //Cycle in Directed Graph
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        private boolean dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] path) {
            visited[node] = true;
            path[node] = true;
            for (int neighbour : adj.get(node)) {
                if (!visited[neighbour]) {
                    if (dfs(neighbour, adj, visited, path)) {
                       return true;
                    }
                } else if (path[neighbour]) {
                    return true;
                }
            }
            path[node] = false;
            return false;
        }
        private boolean bfs(ArrayList<ArrayList<Integer>> adj) {
            int V = adj.size();
            int[] inNodes = new int[V];
            Arrays.fill(inNodes, 0);
            ArrayList<Integer> ans = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                for (int neighbour : adj.get(i)) {
                    inNodes[neighbour]++;
                }
            }
            Queue<Integer> queue = new LinkedList<>();
            for (int node = 0; node < V; node++) {
                if (inNodes[node] == 0) {
                    queue.add(node);
                }
            }
            while (!queue.isEmpty()) {
                int currentNode = queue.poll();
                for (int neighbour : adj.get(currentNode)) {
                    inNodes[neighbour]--; 
                    if (inNodes[neighbour] == 0) {
                        queue.add(neighbour);
                    }
                }
                ans.add(currentNode);
            }
            return ans.size() == n;
        }
        boolean[] visited = new boolean[V];
        boolean[] path = new boolean[V];
        for (int node = 0; node < V; node++) {
            if (!visited[node]) {
                if (dfs(node, adj, visited, path)) {
                    return true;
                }
            }
        }
        return false;
    }
    //Eventual Safe State
    public List<Integer> eventualSafeNodes(int V, List<List<Integer>> adj) {
        private List<Integer> bfs(List<List<Integer>> adj) {
            int V = adj.size();
            List<List<Integer>> newGraph = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                newGraph.add(i, new ArrayList<>());
            }
            int[] inDegrees = new int[V];
            for (int node = 0; node < V; node++) {
                for (int neighbour : adj.get(node)) {
                    newGraph.get(neighbour).add(node);
                    inDegrees[node]++;
                }
            }
            Queue<Integer> queue = new LinkedList<>();
            for (int node = 0; node < V; node++) {
                if (inDegrees[node] == 0) {
                    queue.add(node);
                }
            }
            List<Integer> ans =  new ArrayList<>();
            while (!queue.isEmpty()) {
                int currentNode = queue.poll();
                for (int neighbour : newGraph.get(currentNode)) {
                    inDegrees[neighbour]--;
                    if (inDegress[neighbour] == 0) {
                        queue.add(neighbour);
                    }
                }
                ans.add(currentNode);
            }
            //Sorting the nodes
            Collections.sort(ans);
            return ans;
        }
        private boolean dfs(int node, List<List<Integer>> adj, boolean[] visited, boolean[] path, boolean[] check) {
            visited[node] = true;
            path[node] = true;
            check[node] = false;
            for (int neighbour : adj.get(node)) {
                if (!visited[neighbour]) {
                    if (dfs(neighbour, adj, visited, path, check)) {
                        return true;
                    }
                } else if (path[neighbour]) {
                    return true;
                }
            }
            check[node] = true;
            path[node] = false;
            return false;
        }
        boolean[] visited = new boolean[V];
        boolean[] path = new boolean[V];
        boolean[] check = new boolean[V];
        for (int node = 0; node < V; node++) {
            if (!visited[node]) {
                dfs(node, adj, visited, path, check);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int node = 0; node < V; node++) {
            if (check[node]) {
                ans.add(node);
            }
        }
        return ans;
    }
    //Topological Sorting
    public ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> adj) {
        private void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
            visited[node] = true;
            for (int neighbour : adj.get(node)) {
                if (!visited[neighbour]) {
                    dfs(neighbour, adj, visited, stack);
                }
            }
            stack.push(node);
        }
        private ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {
            int V = adj.size();
            int[] inNodes = new int[V];
            Arrays.fill(inNodes, 0);
            ArrayList<Integer> ans = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                for (int neighbour : adj.get(i)) {
                    inNodes[neighbour]++;
                }
            }
            Queue<Integer> queue = new LinkedList<>();
            for (int node = 0; node < V; node++) {
                if (inNodes[node] == 0) {
                    queue.add(node);
                }
            }
            while (!queue.isEmpty()) {
                int currentNode = queue.poll();
                for (int neighbour : adj.get(currentNode)) {
                    inNodes[neighbour]--; 
                    if (inNodes[neighbour] == 0) {
                        queue.add(neighbour);
                    }
                }
                ans.add(currentNode);
            }
            return ans;
        }
        int V = adj.size();
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for (int node = 0; node < V; node++){
            if (!visited[node]) {
                dfs(node, adj, visited, stack);
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        while (!stack.isEmpty()) {
            ans.add(stack.pop());
        }
        return ans;
    }
    //Alien Dictionary
    public String findOrder(String[] dict, int k) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            adj.add(i, new ArrayList<>());
        }
        int[] inDegrees = new int[k];
        Arrays.fill(inDegrees, 0);
        for (int i = 0; i < dict.length - 1; i++) {
            String s1 = dict[i];
            String s2 = dict[i + 1];
            int index1 = 0, index2 = 0;
            while (index1 < s1.length() && index2 < s2.length() 
                && s1.charAt(index1) == s2.charAt(index2)) {
                index1++;
                index2++;
            }
            if (index1 == s1.length() || index2 == s2.length()) {
                continue;
            }
            int u = s1.charAt(index1) - 'a';
            int v = s2.charAt(index2) - 'a';
            adj.get(u).add(v);
            inDegrees[v]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int node = 0; node < k; node++) {
            if (inDegrees[node] == 0) {
                queue.add(node);
            }
        }
        String ans = "";
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            for (int neighbour : adj.get(currentNode)) {
                inDegrees[neighbour]--;
                if (inDegrees[neighbour] == 0) {
                    queue.add(neighbour);
                }
            }
            ans += ((char) (currentNode + (int) 'a'));
        }
        if (ans.length() != k) {
            return "";
        }
        return ans;
    }
    //Shortest Path in Directed Acyclic Graph
    public int[] shortestPath(int V, int E, int[][] edges) {
        class Pair {
            int node;
            int weight;
            public Pair(int node, int weight) {
                this.node = node;
                this.weight = weight;
            }
        }
        private void dfs(int node, List<List<Pair>> adj, boolean[] visited, Stack<Integer> stack) {
            visited[node] = true;
            for (Pair neighbour : adj.get(node)) {
                if (!visited[neighbour.node]) {
                    dfs(neighbour.node, adj, visited, stack);
                }
            }
            stack.push(node);
        }
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(i, new ArrayList<Pair>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            adj.get(u).add(new Pair(v, weight));
        }
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for (int node = 0; node < V; node++) {
            if (!visited[node]) {
                dfs(node, adj, visited, stack);
            }
        }
        int[] dist = new int[V];
        Arrays.fill(dist, (int) 1e9);
        dist[0] = 0;
        while (!stack.isEmpty()) {
            int node = stack.pop();
            for (Pair neighbour : adj.get(node)) {
                int v = neighbour.node;
                int w = neighbour.weight;
                if (dist[node] + w < dist[v]) {
                    dist[v] = dist[node] + w;
                }
            }
        }
        for (int node = 0; node < V; node++) {
            if (dist[node] == 1e9) {
                dist[node] = -1;
            }
        }
        return dist;
    }
    //Shortest path in Undirected Path
    public int[] shortestPath(ArrayList<ArrayList<Integer>> adj, int src) {
        int[] dist = new int[adj.size()];
        Arrays.fill(dist, (int) 1e9);
        dist[src] = 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(src, 0));
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int node = pair.node;
            int wt = pair.weight;
            for (int neighbour : adj.get(node)) {
                if (dist[node] + 1 < dist[neighbour]) {
                    dist[neighbour] = dist[node] + 1;
                    queue.add(new Pair(neighbour, dist[neighbour]));
                }
            }
        }
        for (int i = 0; i < adj.size(); i++) {
            if (dist[i] == 1e9) {
                dist[i] = -1;
            }
        }
        return dist;
    }
    //Word Ladder
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        class Pair {
            String word;
            int level;
            public Pair(String word, int level) {
                this.word = word;
                this.level = level;
            }
        }
        Set<String> set = new HashSet<>();
        for (String word : wordList) {
            set.add(word);
        }
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(beginWord, 1));
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int lvl = pair.level;
            if (pair.word.equals(endWord)) {
                return lvl;
            }
            for (int i = 0; i < pair.word.length(); i++) {
                StringBuilder word = new StringBuilder(pair.word);
                for (char chr = 'a'; chr <= 'z'; chr++) {
                    word.setCharAt(i ,chr);
                    if (set.contains(word.toString())) {
                        set.remove(word.toString());
                        queue.add(new Pair(word.toString(), lvl + 1));
                    }
                }
            }
        }
        return 0;
    }
    //Word Ladder- II
    public ArrayList<ArrayList<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>();
        for (String word : wordList) {
            set.add(word);
        }
        List<String> wrds = new ArrayList<>();
        wrds.add(beginWord);
        Queue<List<String>> queue = new LinkedList<>();
        queue.add(wrds);
        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<String> seen = new HashSet<>();
            for (int i = 0; i < size; i++) {
                List<String> words = queue.peek();
                queue.remove();
                if (words.get(words.size() - 1).equals(endWord)) {
                    if (ans.size() == 0) {
                        ans.add(new ArrayList<>(words));
                    } else if (ans.get(0).size() == words.size()) {
                        ans.add(new ArrayList<>(words));
                    }
                    continue;
                }
                for (int j = 0; j < words.get(words.size() - 1).length(); j++) {
                    StringBuilder word = new StringBuilder(words.get(words.size() - 1));
                    for (char chr = 'a'; chr <= 'z'; chr++) {
                        word.setCharAt(j, chr);
                        if (set.contains(word.toString())) {
                            seen.add(word.toString());
                            words.add(word.toString());
                            List<String> temp = new ArrayList<>(words);
                            queue.add(temp);
                            words.remove(words.size() - 1);
                        }
                    }
                }
            }
            for (String s : seen) {
                set.remove(s);
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
    //Shortest Path for a Particular Node
    public List<Integer> shortestPath(int n, int m, int edges[][]) {
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];
            adj.get(u).add(new Pair(wt, v));
            adj.get(v).add(new Pair(wt, u));
        }
        int[] parent = new int[n + 1];
        int[] dist = new int[n + 1];
        for (int i = 1; i <= n ;i++) {
            parent[i] = i;
            dist[i] = Integer.MAX_VALUE;
        }
        dist[1] = 0;
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.first, b.first)
        );
        minHeap.offer(new Pair(0, 1));
        while (!minHeap.isEmpty()) {
            Pair top = minHeap.poll();
            int node = top.second;
            int dis = top.first;
            for (Pair neighbour : adj.get(node)) {
                int v = neighbour.second;
                int edgeWeight = neighbour.first;
                if (edgeWeight + dis < dist[v]) {
                    dist[v] = dis + edgeWeight;
                    minHeap.offer(new Pair(dist[v], v));
                    parent[v] = node;
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        if (dist[n] == Integer.MAX_VALUE) {
            ans.add(-1);
            return ans;
        }
        int node = n;
        while (parent[node] != node) {
            ans.add(node);
            node = parent[node];
        }
        ans.add(1);
        Collections.reverse(ans);
        ans.add(0, dist[n]);
        return ans;
    }
    //Shortest Path in Binary Maze
    int shortestPath(int[][] grid, int[] source, int[] destination) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dist = new int[n][m];
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], (int) 1e9);
        }
        Queue<int[]> queue = new LinkedList<>();
        dist[source[0]][source[1]] = 0;
        int[] src = { 0, source[0], source[1] };
        queue.add(src);
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[1];
            int col = current[2];
            int dis = current[0];
            if (row == destination[0] && col == destination[1]) {
                return dis;
            }
            for (int[] direction : directions) {
                int nrow = row + direction[0];
                int ncol = col + direction[1];
                if (nrow >= 0 && ncol >= 0 && ncol < m && nrow < n 
                    && grid[nrow][ncol] == 1) {
                    if (dis + 1 < dist[nrow][ncol]) {
                        dist[nrow][ncol] = dis + 1;
                        queue.add(new int[]{ dis + 1, nrow, ncol });
                    }    
                }
            }
        }
        return -1;
    }
    //Path with Minimum Effort
    public int MinimumEffort(int n, int m, int[][] heights) {
        class Tuple {
            int diff;
            int row;
            int col;
            public Tuple(int diff, int row, int col) {
                this.diff = diff;
                this.row = row;
                this.col = col;
            }
        }
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], (int) 1e9);
        }
        PriorityQueue<Tuple> minHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.diff, b.diff)
        );
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        Tuple tup = new Tuple(0, 0, 0);
        dist[0][0] = 0;
        minHeap.offer(tup);
        while (!minHeap.isEmpty()) {
            Tuple tuple = minHeap.poll();
            int diff = tuple.diff;
            int row = tuple.row;
            int col = tuple.col;
            if (row == n - 1 && col == m - 1) {
                return diff;
            }
            for (int[] direction : directions) {
                int nrow = row + direction[0];
                int ncol = col + direction[1];
                if (nrow >= 0 && ncol >= 0 && nrow < n && ncol < m) {
                    int newEffort = Math.max(
                        Math.abs(heights[row][col] - heights[nrow][ncol]), 
                        diff
                    );
                    if (newEffort < dist[nrow][ncol]) {
                        dist[nrow][ncol] = newEffort;
                        minHeap.offer(new Tuple(newEffort, nrow, ncol));
                    }
                }
            }
        }
        return -1;
    }
    //Cheapest Flight within K Stops
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        class Tuple {
            int cost;
            int node;
            int stop;
            public Tuple(int stop, int node, int cost) {
                this.cost = cost;
                this.node = node;
                this.stop = stop;
            }
            public Tuple(int cost, int node) {
                this.cost = cost;
                this.node = node;
            }
        }
        List<List<Tuple>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] flight : flights) {
            adj.get(flight[0]).add(new Tuple(flight[2], flight[1]));
        }
        int[] dist = new int[n];
        Arrays.fill(dist, (int) 1e9);
        dist[src] = 0;
        Queue<Tuple> queue = new LinkedList<>();
        Tuple source = new Tuple(0, src, 0);
        queue.add(source);
        while (!queue.isEmpty()) {
            Tuple tuple = queue.poll();
            int cost = tuple.cost;
            int node = tuple.node;
            int stop = tuple.stop;
            if (stop > k) {
                continue;
            }
            for (Tuple neighbour : adj.get(node)) {
                int adjNode = neighbour.node;
                int adjCost = neighbour.cost;
                if (adjCost + cost < dist[adjNode] && stop <= k) {
                    dist[adjNode] = adjCost + cost;
                    queue.add(new Tuple(stop + 1, adjNode, dist[adjNode]));
                }
            }
        }
        if (dist[dst] == (int) 1e9) {
            return -1;
        }
        return dist[dst];
    }
    //Minimum multiplications to reach end
    private int minimumMultiplications(int[] arr, int start, int end) {
        private static final int MOD = 100000;
        if (start == end) {
            return 0;
        }
        int[] dist = new int[100000];
        Arrays.fill(dist, (int) 1e9);
        dist[start] = 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(start, 0));
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int steps = pair.steps;
            int node = pair.node;
            for (int mul : arr) {
                int adjNode = (mul * node) % MOD;
                if (steps + 1 < dist[adjNode]) {
                    dist[adjNode] = steps + 1;
                    if (adjNode == end) {
                        return steps + 1;
                    }
                    queue.add(new Pair(adjNode, steps + 1));
                }
            }
        }
        return -1;
    }
    //Number of Ways to Arrive at Destination
    public int countPaths(int n, int[][] roads) {
        class Pair {
            long dist;
            int node;
            public Pair (long dist, int node) {
                this.dist = dist;
                this.node = node;
            }
        }
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }     
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int dist = road[2];
            adj.get(u).add(new Pair(dist, v));
            adj.get(v).add(new Pair(dist, u));
        }
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(Comparator.comparingLong(a -> a.dist));
        long[] dist = new long[n];
        long[] paths = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        Arrays.fill(paths, 0);
        dist[0] = 0;
        paths[0] = 1;
        minHeap.offer(new Pair(0, 0));
        int mod = (int) 1e9 + 7;
        while (!minHeap.isEmpty()) {
            Pair pair = minHeap.poll();
            int node = pair.node;
            long dis = pair.dist;
            for (Pair neighbour : adj.get(node)) {
                int adjNode = neighbour.node;
                long edW = neighbour.dist;
                if (edW + dis < dist[adjNode]) {
                    dist[adjNode] = edW + dis;
                    minHeap.offer(new Pair(edW + dis, adjNode));
                    paths[adjNode] = paths[node];
                } else if (edW + dis == dist[adjNode]) {
                    paths[adjNode] = (paths[adjNode] + paths[node]) % mod;
                }
            }
        }
        return (int) paths[n - 1] % mod;
    }
    //Bellman Ford Algorithm
    public int[] bellmanFord(int V, int[][] edges, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, (int) 1e8);
        dist[src] = 0;
        for (int i = 0; i < V - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];
                if (dist[u] != 1e8 && dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                }
            }
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            if (dist[u] != 1e8 && dist[u] + wt < dist[v]) {
                return new int[]{ -1 };
            }
        }
        return dist;
    }
    //Warshall's Algorithm
    public void shortestDistance(int[][] cost) {
        int n = cost.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cost[i][j] == -1) {
                    cost[i][j] = (int) 1e9;
                } 
            }
        }
        for (int via = 0; via < n; via++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    cost[i][j] = Math.min(cost[i][j], cost[i][via] + cost[via][j]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cost[i][j] == (int) 1e9) {
                    cost[i][j] = -1;
                } 
            }
        }
    }
}