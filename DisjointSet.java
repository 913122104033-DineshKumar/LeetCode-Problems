class DisjointSet {
    List<Integer> rank;
    List<Integer> parent;
    List<Integer> size;
    public DisjoitSet(int n) {
        for (int i = 0; i <= n; i++) {
            size.add(1);
            rank.add(0);
            parent.add(i);
        }
    }
    public int findUltimateParent (int node) {
        if (node == parent.get(node)) {
            return node;
        }
        int ultimateParent =  findUltimateParent(parent.get(node));
        parenet.set(node, ultimateParent);
        return parent.get(node);
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
    public void unionBySize (int u, int v) {
        int ultimateParentOfU = findUltimateParent(u);
        int ultimateParentOfV = findUltimateParent(v);
        if (ultimateParentOfU == ultimateParentOfV) {
            return;
        }
        if (size.get(ultimateParentOfU) < size.get(ultimateParentOfV)) {
            parent.set(ultimateParentOfU, ultimateParentOfV);
            int sizeOfV = size.get(ultimateParentOfV);
            size.set(ultimateParentOfV, sizeOfV + size.get(ultimateParentOfU));
        } else {
            parent.set(ultimateParentOfV, ultimateParentOfU);
            int sizeOfU = size.get(ultimateParentOfU);
            size.set(ultimateParentOfU, sizeOfU + size.get(ultimateParentOfV));
        }
    }
}