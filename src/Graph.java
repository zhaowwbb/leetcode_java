import java.util.*;

class Graph {
    private int vertices;
    private Map<Integer, List<Integer>> adjList;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.adjList = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            adjList.put(i, new LinkedList<>());
        }
    }

    public void addEdge(int source, int destination) {
        adjList.get(source).add(destination);
    }

    // Main DFS function
    public void DFS(int startNode) {
        boolean[] visited = new boolean[vertices];
        System.out.println("Depth First Traversal starting from node " + startNode + ":");
        dfsRecursive(startNode, visited);
        System.out.println("");
        System.out.println("#################");
    }

    // Helper function for recursion
    private void dfsRecursive(int currentNode, boolean[] visited) {
        // 1. Mark current node as visited
        visited[currentNode] = true;
        System.out.print(currentNode + " ");

        // 2. Recur for all adjacent vertices
        for (int neighbor : adjList.get(currentNode)) {
            if (!visited[neighbor]) {
                dfsRecursive(neighbor, visited);
            }
        }
    }

    public void DFSV2(int startNode) {
        boolean[] visited = new boolean[vertices];
        System.out.println("DFSV2, root node=" + startNode);

        recursiveDFSV2(startNode, visited);
        System.out.println("");
        System.out.println("#################");
    }

    public void recursiveDFSV2(int startNode, boolean[] visited) {
        visited[startNode] = true;
        System.out.print("[" + startNode + "]");
        for (int n : adjList.get(startNode)) {
            if (!visited[n]) {
                recursiveDFSV2(n, visited);
            }

        }
    }

    public void DFSV3(int startNode) {
        System.out.println("DFSV3(), start node=" + startNode);
        boolean[] visited = new boolean[vertices];
        recursiveV3(startNode, visited);
        System.out.println("");
        System.out.println("#################");
    }

    public void recursiveV3(int currentNode, boolean[] visited) {
        visited[currentNode] = true;
        System.out.print("[" + currentNode + "]");
        for (int neighbor : adjList.get(currentNode)) {
            if (!visited[neighbor]) {
                recursiveV3(neighbor, visited);
            }
        }
    }

    public void DFSV4(int startNode) {
        boolean[] visited = new boolean[vertices];
        System.out.println("[V4]");
        recursiviveDFS4(startNode, visited);
        System.out.println("");
        System.out.println("#################");
    }

    public void recursiviveDFS4(int startNode, boolean[] visited) {
        visited[startNode] = true;
        System.out.print("[" + startNode + "]");
        for (int n : adjList.get(startNode)) {
            if (!visited[n]) {
                recursiviveDFS4(n, visited);
            }
        }
    }

    public void DFSV5(int startNode) {
        System.out.println("[V5]");
        boolean[] visited = new boolean[vertices];
        recursiveDFSV5(startNode, visited);
        System.out.println("");
        System.out.println("#################");
    }

    public void recursiveDFSV5(int node, boolean[] visited) {
        if (!visited[node]) {
            System.out.print("[" + node + "]");
            visited[node] = true;
        } else {
            return;
        }

        List<Integer> list = adjList.get(node);
        for (int i : list) {
            recursiveDFSV5(i, visited);
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(6);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 5);
        g.addEdge(4, 5);

        g.DFS(0);
        // g.DFSV2(0);
        // g.DFSV3(0);
        g.DFSV4(0);
        g.DFSV5(0);
    }
}