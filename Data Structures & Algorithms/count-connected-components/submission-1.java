class Solution {
    private Map<Integer, List<Integer>> adjList = new HashMap<>();
    private Set<Integer> visited = new HashSet<>();
    public int countComponents(int n, int[][] edges) {
        for (int i = 0; i < n; i++){
            adjList.put(i, new ArrayList<Integer>());
        }
        for (int[] edge : edges){
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        int res = 0;
        for (int i = 0; i < n; i++){
            if (!visited.contains(i)){
                res++;
                dfs(i);
            }
        }
        return res;
    }
    private void dfs(int node){
        visited.add(node);
        for (int child: adjList.get(node)){
            if (!visited.contains(child)){
                dfs(child);
            }
        }
    }
}
