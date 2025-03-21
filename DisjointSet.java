class DisjointSet {
    List<Integer> size;
    List<Integer> parent;
    List<Integer> rank;
    public DisjointSet (int n) {
        rank = new ArrayList<>();
        size = new ArrayList<>();
        parent = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            size.add(1);
            parent.add(i);
        }
    } 
    public int findUParent(int node) {
        if (node == parent.get(node)) {
            return node;
        }
        int ul_node = findUParent(parent.get(node));
        parent.set(node, ul_node);
        return ul_node;
    }
    public void unionBySize(int u, int v) {
        int ul_u = findUParent(u);
        int ul_v = findUParent(v);
        if (ul_u == ul_v) {
            return;
        }
        if (size.get(ul_u) > size.get(ul_v)) {
            parent.set(ul_v, ul_u);
            size.set(ul_u, size.get(ul_v) + size.get(ul_u));
        } else {
            parent.set(ul_u, ul_v);
            size.set(ul_v, size.get(ul_v) + size.get(ul_u));
        }
    }
    public void unionByRank (int u, int v) {
        int ultimateParentOfU =  findUltimateParent(u);
        int ultimateParentOfV = findUltimateParent(v);
        if (ultimateParentOfU == ultimateParentOfV) {
            return;
        }
        if (rank.get(ultimateParentOfU) < rank.get(ultimateParentOfV)) {
            parent.set(ultimateParentOfU, ultimateParentOfV);
        } else if (rank.get(ultimateParentOfU) > rank.get(ultimateParentOfV)) {
            parent.set(ultimateParentOfV, ultimateParentOfU);
        } else {
            parent.set(ultimateParentOfV, ultimateParentOfU);
            int rankU = rank.get(ultimateParentOfU);
            rank.set(ultimateParentOfV, rankU + 1);
        }
    }
    //Krushal's Algorithm
    private int spanningTree(int V, int E, List<List<int[]>> adj) {
        class Edge implements Comparable<Edge> {
            int dist;
            int src;
            int dst;
            public Edge (int dist, int src, int dst) {
                this.dist = dist;
                this.src = src;
                this.dst = dst;
            }
            public int compareTo(Edge compareEdge) {
                return this.dist - compareEdge.dist;
            }
        }
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < adj.size(); i++) {
            for (int[] edge : adj.get(i)) {
                edges.add(new Edge(edge[1], i, edge[0]));
            }
        }
        Collections.sort(edges);
        DisjointSet dsu = new DisjointSet(V);
        int max_Wt = 0;
        for (int i = 0; i < edges.size(); i++) {
            int wt = edges.get(i).dist;
            int src = edges.get(i).src;
            int dst = edges.get(i).dst;
            if (dsu.findUParent(src) != dsu.findUParent(dst)) {
                max_Wt += wt;
                dsu.union(src, dst);
            }
        }
        return max_Wt;
    }
    //Number of Provinces
    public int findCircleNum(int[][] isConnected) {
        public int numberOfComponents() {
            int components = 0;
            for (int i = 0; i < parent.size(); i++) {
                if (parent.get(i) == i) {
                    components++;
                }
            }
            return components;
        }
        int n = isConnected.length;
        DisjointSet dsu = new DisjointSet(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && isConnected[i][j] == 1) {
                    dsu.unionBySize(i, j);
                }
            }
        }
        return dsu.numberOfComponents();
    }
    //Accounts Merge
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        Map<String, Integer> mails = new HashMap<>();
        DisjointSet dsu = new DisjointSet(n);
        for (int i = 0; i < n; i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String mail = account.get(j);
                if (!mails.containsKey(mail)) {
                    mails.put(mail, i);
                } else {
                    dsu.unionBySize(i, mails.get(mail));
                }
            }
        }
        List<List<String>> mergedMail = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            mergedMail.add(new ArrayList<>());
        }
        for (Map.Entry<String, Integer> personMail : mails.entrySet()) {
            String mail = personMail.getKey();
            int node = dsu.findParent(personMail.getValue());
            mergedMail.get(node).add(mail);
        }
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (mergedMail.get(i).size() == 0) {
                continue;
            }
            Collections.sort(mergedMail.get(i));
            List<String> temp = new ArrayList<>();
            temp.add(accounts.get(i).get(0));
            temp.addAll(mergedMail.get(i));
            ans.add(new ArrayList<>(temp));
        }
        return ans;
    }
    //Number of Islands- II
    public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {
        private boolean isValid (int nrow, int ncol, int n, int m) {
            return nrow >= 0 && ncol >= 0 && nrow < n && ncol < m;
        }
        List<Integer> ans = new ArrayList<>();
        DisjointSet ds = new DisjointSet(rows * cols);
        boolean[][] visited = new boolean[rows][cols];
        int[][] directions = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        int cnt = 0;
        for (int[] operator : operators) {
            int row = operator[0];
            int col = operator[1];
            if (visited[row][col]) {
                ans.add(cnt);
                continue;
            }
            cnt++;
            visited[row][col] = true;
            for (int[] direction : directions) {
                int nrow = row + direction[0];
                int ncol = col + direction[1];
                if (isValid(nrow, ncol, rows, cols)) {
                    if (visited[nrow][ncol]) {
                        int pos = row * cols + col;
                        int adjPos = nrow * cols + ncol;
                        cnt -= ds.unionBySize(pos, adjPos);
                    }
                }
            }
            ans.add(cnt);
        }
        return ans;
    }
    //Maximum Connected Group
    public int MaxConnection(int grid[][]) {
        int n = grid.length, m = grid[0].length;
        int[][] directions = { { -1, 0 }, { 0, 1 }, { 1, 0 } , { 0, -1 }};
        DisjointSet ds = new DisjointSet(n * m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    for (int[] direction : directions) {
                        int row = direction[0];
                        int col = direction[1];
                        int adjRow = i + row;
                        int adjCol = j + col;
                        if (adjRow >= 0 && adjCol >= 0 && adjRow < n && adjCol < m
                            && grid[adjRow][adjCol] == 1) {
                            int pos = i * m + j;
                            int adjPos = adjRow * m + adjCol;
                            ds.unionBySize(pos, adjPos);
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    int sum = 1;
                    Set<Integer> parents = new HashSet<>();
                    for (int[] direction : directions) {
                        int row = direction[0];
                        int col = direction[1];
                        int adjRow = i + row;
                        int adjCol = j + col;
                        if (adjRow >= 0 && adjCol >= 0 && adjRow < n && adjCol < m
                            && grid[adjRow][adjCol] == 1) {
                            int adjPos = adjRow * m + adjCol;
                            parents.add(ds.findParent(adjPos));
                        }
                    }
                    for (int parent : parents) {
                        sum += ds.sizeOfParent(parent);
                    }
                    ans = Math.max(ans, sum);
                }
            }
        }
        for (int cell = 0; cell < n * n; cell++) {
            ans = Math.max(ans, ds.sizeOfParent(cell));
        }
        return ans;
    }
}
    