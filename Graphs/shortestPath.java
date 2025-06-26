// Shortest path with unit weights:


/* 
Shortest Path in Undirected Graph
Difficulty: MediumAccuracy: 49.98%Submissions: 146K+Points: 4Average Time: 20m
You are given an adjacency list, adj of Undirected Graph having unit weight of the edges, find the shortest path from src to all the vertex and if it is unreachable to reach any vertex, then return -1 for that vertex.

Examples :

Input: adj[][] = [[1, 3], [0, 2], [1, 6], [0, 4], [3, 5], [4, 6], [2, 5, 7, 8], [6, 8], [7, 6]], src=0
Output: [0, 1, 2, 1, 2, 3, 3, 4, 4]
 
Input: adj[][]= [[3], [3], [], [0, 1]], src=3
Output: [1, 1, -1, 0]

Input: adj[][]= [[], [], [], [4], [3], [], []], src=1
Output: [-1, 0, -1, -1, -1, -1, -1] 
Constraint:
1<=adj.size()<=104
0<=edges<=adj.size()-1

*/


/*Intution : bas simple BFS algo lagana hai -> aur evenly apne neighbours k paas jao -> and assign them distance -> as distance of the current node + 1
 
distance array create krna hai -> agar us node pe we have already reached with a smaller distance -> then do not change that , else update it with a shorter distance


 */


 class shortestPath {
    
    class Pair{
        int node;
        int distance;
        
        Pair(int node, int distance){
            this.node = node;
            this.distance = distance;
        }
    }
    // Function to find the shortest path from a source node to all other nodes
    public int[] shortestPath(ArrayList<ArrayList<Integer>> adj, int src) {
        // code here
        
        // put the source and distance zero in the queue data structure:
        int n = adj.size();
    
        Queue<Pair> q = new LinkedList<>();
        
        q.add(new Pair(src, 0));
        
        int []distance = new int [n];
        
        for (int i = 0; i < n; i++){
            distance[i] = Integer.MAX_VALUE;
        }
        
        while(!q.isEmpty()){
            Pair front = q.poll();
            int node = front.node;
            
            int dis = front.distance;
            if (distance[node] < dis) continue; // yaha dubara isliye check kr rahe hai -> cuz neighbours ko check krte time -> ho sakta hai ek better distance queue me pada ho -> but abhi tak update nhi hua hai 
            distance[node] = dis;
            
            // go to the neighbours:
            for (int neighbour : adj.get(node)){
                if (distance[neighbour] > dis+1){
                    q.add(new Pair(neighbour, dis+1));
                }
            }
            
            
        }
        
        for (int i = 0; i < n; i++){
            if(distance[i] == Integer.MAX_VALUE){
                distance[i] = -1;
            }
                
        }
        
        return distance;
        
        
        
    }
}
