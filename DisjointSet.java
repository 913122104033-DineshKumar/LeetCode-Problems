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
}
    