class DisjointSet {
    List<Integer> rank;
    List<Integer> parent;
    public DisjoitSet(int n) {
        for (int i = 0; i <= n; i++) {
            rank.add(0);
            parent.add(i);
        }
    }
    public int findParent(int node) {
        if (node == parent.get(node)) {
            return node;
        }
        int ultimateParent =  findParent(parent.get(node));
        parenet.set(node, ultimateParent);
        return parent.get(node);
    }
    public void union (int u, int v) {
        int ultimateParentOfU =  findParent(u);
        int ultimateParentOfV = findParent(v);
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
}