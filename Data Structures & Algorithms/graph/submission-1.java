class Graph {
    private final Map<Integer, ArrayList<Integer>> adjList = new HashMap<>();

    public Graph() {

    }

    public void addEdge(int src, int dst) {
        ArrayList<Integer> cur = adjList.getOrDefault(src, new ArrayList<Integer>());
        cur.add(dst);
        adjList.put(src, cur);
    }

    public boolean removeEdge(int src, int dst) {
        return adjList.get(src) != null && adjList.get(src).remove(Integer.valueOf(dst));
    }

    public boolean hasPath(int src, int dst) {
        return findDest(src, dst, new HashSet<Integer>());
    }

    private boolean findDest(int src, int dst, HashSet<Integer> visited) {
        if (visited.contains(src)) {
            return false;
        }
        if (src == dst) {
            return true;
        }
        visited.add(src);
        if (adjList.get(src) == null) {
            return false;
        }
        for (int neighbour : adjList.get(src)){
            if (findDest(neighbour, dst, visited)){
                return true;
            };
        }
        return false;
    }
}
