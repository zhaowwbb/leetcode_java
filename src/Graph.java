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

    public void DFSV6(int startNode) {
        System.out.println("[V6]");
        boolean[] visited = new boolean[vertices];
        tranverseV6(visited, startNode);
        System.out.println("");
        System.out.println("#################");
    }

    public void tranverseV6(boolean[] visited, int startNode) {
        if (!visited[startNode]) {
            System.out.print("[" + startNode + "]");
            visited[startNode] = true;
        } else {
            return;
        }
        List<Integer> list = adjList.get(startNode);
        list.forEach(i -> tranverseV6(visited, i));
    }

    public void DFSV7(int startNode) {
        System.out.println("[V7]");
        boolean[] visited = new boolean[vertices];
        tranverseV7(visited, startNode);
    }

    public void tranverseV7(boolean[] visited, int startNode) {

        if (!visited[startNode]) {
            System.out.print("[" + startNode + "]");
            visited[startNode] = true;
        } else {
            return;
        }
        List<Integer> list = adjList.get(startNode);
        list.forEach(i -> tranverseV7(visited, i));
    }

    // ==========================================
    // 1. DEPTH-FIRST SEARCH (DFS)
    // ==========================================

    // DFS Recursive
    public void dfsRecursive(int startNode) {
        Set<Integer> visited = new HashSet<>();
        System.out.print("DFS  (Recursive): ");
        dfsHelper(startNode, visited);
        System.out.println();
    }

    private void dfsHelper(int node, Set<Integer> visited) {
        visited.add(node);
        System.out.print(node + " ");

        for (int neighbor : adjList.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    public void dfsRecursiveV2(int startNode) {
        Set<Integer> visited = new HashSet<>();
        System.out.print("DFS2 (Recursive): ");
        dfsHelperV2(startNode, visited);
        System.out.println("");
    }

    public void dfsHelperV2(int node, Set<Integer> visited) {
        visited.add(node);
        System.out.print(node + " ");

        List<Integer> list = adjList.getOrDefault(node, new ArrayList<>());
        for (int i = 0; i < list.size(); i++) {
            int neighbor = list.get(i);
            if (!visited.contains(neighbor)) {
                dfsHelperV2(neighbor, visited);
            }
        }
    }

    public void dfsRecursiveV3(int startNode) {
        Set<Integer> visited = new HashSet<>();
        System.out.print("DFS3 (Recursive): ");
        dfsHelperV3(startNode, visited);
        System.out.println();
    }

    public void dfsHelperV3(int startNode, Set<Integer> visited){
        System.out.print(startNode + " ");
        visited.add(startNode);
        List<Integer> list = adjList.getOrDefault(startNode, new ArrayList<>());
        for(Integer n : list){
            if(!visited.contains(n)){
                // visited.add(n);
                dfsHelperV3(n, visited);
            }
        }
    }

    // DFS Iterative
    public void dfsIterative(int startNode) {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        System.out.print("DFS  (Iterative): ");
        stack.push(startNode);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            if (!visited.contains(current)) {
                visited.add(current);
                System.out.print(current + " ");

                List<Integer> neighbors = adjList.getOrDefault(current, new ArrayList<>());
                // Push in reverse to match recursive output order
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    int neighbor = neighbors.get(i);
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        System.out.println();
    }

    public void dfsIterativeV2(int startNode) {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(startNode);
        System.out.print("DFS2 (Iterative): ");

        while (!stack.isEmpty()) {
            Integer node = stack.pop();
            System.out.print(node + " ");
            visited.add(node);
            List<Integer> list = adjList.getOrDefault(node, new ArrayList<Integer>());
            for (Integer i = list.size() - 1; i >= 0; i--) {
                int n = list.get(i);
                if (!visited.contains(n)) {
                    stack.push(n);
                }
            }
        }
        System.out.println();
    }

    public void dfsIterativeV3(int startNode) {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(startNode);
        System.out.print("DFS3 (Iterative): ");

        while (!stack.isEmpty()) {
            Integer current = stack.pop();
            if (!visited.contains(current)) {
                visited.add(current);
                System.out.print(current + " ");
                List<Integer> list = adjList.getOrDefault(current, new ArrayList<Integer>());
                for (int i = list.size() - 1; i >= 0; i--) {
                    Integer node = list.get(i);
                    if (!visited.contains(node)) {
                        stack.push(node);
                    }
                }
            }
        }
        System.out.println();
    }

    // ==========================================
    // 2. BREADTH-FIRST SEARCH (BFS)
    // ==========================================

    // BFS Iterative (Standard & Recommended)
    public void bfsIterative(int startNode) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        System.out.print("BFS  (Iterative): ");

        visited.add(startNode);
        queue.add(startNode);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            for (int neighbor : adjList.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public void bfsIterativeV2(int startNode) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        visited.add(startNode);
        queue.add(startNode);
        System.out.print("BFS2 (Iterative): ");
        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            System.out.print(current + " ");
            List<Integer> list = adjList.getOrDefault(current, new ArrayList<Integer>());
            for (Integer n : list) {
                if (!visited.contains(n)) {
                    queue.add(n);
                    visited.add(n);
                }
            }
        }
        System.out.println();
    }

    public void bfsIterativeV3(int startNode) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        visited.add(startNode);
        queue.add(startNode);
        System.out.print("BFS3 (Iterative): ");
        while(!queue.isEmpty()){
            Integer current = queue.poll();
            System.out.print(current + " ");
            List<Integer> list = adjList.getOrDefault(current, new ArrayList<>());
            for(Integer n : list){
                if(!visited.contains(n)){
                    visited.add(n);
                    queue.add(n);
                }
            }
        }
        System.out.println();
    }

    // BFS Recursive (Simulated using a Queue passed through recursion)
    public void bfsRecursive(int startNode) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        System.out.print("BFS  (Recursive): ");

        visited.add(startNode);
        queue.add(startNode);

        bfsRecursiveHelper(queue, visited);
        System.out.println();
    }

    private void bfsRecursiveHelper(Queue<Integer> queue, Set<Integer> visited) {
        // Base case: if the queue is empty, traversal is complete
        if (queue.isEmpty())
            return;

        int current = queue.poll();
        System.out.print(current + " ");

        // Add all unvisited neighbors to the queue
        for (int neighbor : adjList.getOrDefault(current, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                queue.add(neighbor);
            }
        }

        // Recursive call for the next item in the queue
        bfsRecursiveHelper(queue, visited);
    }

    public void bfsRecursiveV2(int startNode) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        System.out.print("BFS2 (Recursive): ");
        bfsHelperV2(queue, visited);
        System.out.println();
    }

    public void bfsHelperV2(Queue<Integer> queue, Set<Integer> visited) {
        if (queue.isEmpty()) {
            return;
        }
        Integer current = queue.poll();
        System.out.print(current + " ");
        List<Integer> list = adjList.getOrDefault(current, new ArrayList<Integer>());
        for (Integer n : list) {
            if (!visited.contains(n)) {
                queue.add(n);
                visited.add(n);
            }
        }
        bfsHelperV2(queue, visited);
    }

    public void bfsRecursiveV3(int startNode) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        visited.add(startNode);
        queue.add(startNode);
        System.out.print("BFS3 (Recursive): ");
        bfsHelpV3(queue, visited);
        System.out.println();
    }

    public void bfsHelpV3(Queue<Integer> queue, Set<Integer> visited) {
        if(queue.isEmpty()){
            return;
        }
        Integer current = queue.poll();
        System.out.print(current + " ");
        List<Integer> list = adjList.getOrDefault(current, new ArrayList<>());
        for(Integer n : list){
            if(!visited.contains(n)){
                visited.add(n);
                queue.add(n);
            }
        }
        bfsHelpV3(queue, visited);
    }

    public static void main(String[] args) {
        // Graph g = new Graph(6);

        // g.addEdge(0, 1);
        // g.addEdge(0, 2);
        // g.addEdge(1, 3);
        // g.addEdge(2, 4);
        // g.addEdge(3, 5);
        // g.addEdge(4, 5);

        // g.DFS(0);
        // }
        // // g.DFSV2(0);
        // // g.DFSV3(0);
        // // g.DFSV4(0);
        // g.DFSV5(0);
        // g.DFSV6(0);
        // g.DFSV7(0);

        Graph graph = new Graph(5);
        // Building a sample graph
        // 0
        // / \
        // 1 2
        // / \ \
        // 3 4 5
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);

        // Run both DFS variations (Goes deep down a branch first)
        graph.dfsRecursive(0); // Output: 0 1 3 4 2 5
        graph.dfsRecursiveV2(0);
        graph.dfsRecursiveV3(0);
        System.out.println("---------------------------------------");
        graph.dfsIterative(0); // Output: 0 1 3 4 2 5
        graph.dfsIterativeV2(0);
        graph.dfsIterativeV3(0);

        System.out.println("---------------------------------------");

        // Run both BFS variations (Explores layer-by-layer)
        graph.bfsIterative(0); // Output: 0 1 2 3 4 5
        graph.bfsIterativeV2(0);
        graph.bfsIterativeV3(0);
        System.out.println("---------------------------------------");
        graph.bfsRecursive(0); // Output: 0 1 2 3 4 5
        graph.bfsRecursiveV2(0);
        graph.bfsRecursiveV3(0);
    }
}