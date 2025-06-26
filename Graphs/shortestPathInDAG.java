import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class shortestPathInDAG {
    
    /*
     Given a Directed Acyclic Graph of V vertices from 0 to n-1 and a 2D Integer array(or vector) edges[ ][ ] of length E, where there is a directed edge from edge[i][0] to edge[i][1] with a distance of edge[i][2] for all i.

Find the shortest path from src(0) vertex to all the vertices and if it is impossible to reach any vertex, then return -1 for that vertex.

Examples :

Input: V = 4, E = 2, edges = [[0,1,2], [0,2,1]]
Output: [0, 2, 1, -1]
Explanation: Shortest path from 0 to 1 is 0->1 with edge weight 2. Shortest path from 0 to 2 is 0->2 with edge weight 1. There is no way we can reach 3, so it's -1 for 3.
Input: V = 6, E = 7, edges = [[0,1,2], [0,4,1], [4,5,4], [4,2,2], [1,2,3], [2,3,6], [5,3,1]]
Output: [0, 2, 3, 6, 1, 5]
Explanation: Shortest path from 0 to 1 is 0->1 with edge weight 2. Shortest path from 0 to 2 is 0->4->2 with edge weight 1+2=3. Shortest path from 0 to 3 is 0->4->5->3 with edge weight 1+4+1=6. Shortest path from 0 to 4 is 0->4 with edge weight 1.Shortest path from 0 to 5 is 0->4->5 with edge weight 1+4=5.
Constraint:
1 <= V <= 100
1 <= E <= min((N*(N-1))/2,4000)
0 <= edgesi,0, edgesi,1 < n
0 <= edgei,2 <=105
     */


      class Pair{
        int node;
        int distance; 
        
        Pair(int node, int distance){
            this.node = node;
            this.distance = distance;
        }
    }

    public int[] shortestPath(int V, int E, int[][] edges) {


        // First solution -> simple BFS -> lekin isme dikkat bas itni hai ki -> queue me number of pairs bhut jyada aa jayenge -> for instance, mai 2 node pe pahuchi with distance 5 -> ab 2 is connected to 3 -> so isne us distance ko pass kr diya queue me -> ab after a while I got a better distance at 2 (let say 1) -> ab muje 2 ke neighbours k liye dobara se naye updated distance push krne padenge -> so basically, ham is solution se sare distances queue me push krte hain -> we have to update them again and again and then the neighbours too... 
        // Code here
        
        // create an adjacency List:
        ArrayList<Pair> [] adjList = new ArrayList[V];
        
        // O(V)
        for (int i = 0; i < V; i++){
            adjList[i] = new ArrayList<>();
        }
        
        // O(E)
        
        for (int [] edge : edges){
            adjList[edge[0]].add(new Pair(edge[1], edge[2]));
        }
        
        // O(V) space
        int [] distance = new int [V];
        Arrays.fill(distance, Integer.MAX_VALUE);

                Queue<Pair> q = new LinkedList<>();
        
        q.add(new Pair(0, 0));
        
        while(!q.isEmpty()){
            Pair front = q.poll();
            int node = front.node;
            int dis = front.distance;
            
            if (dis > distance[node]) continue;
            
            distance[node] = dis;
            
            // go to neighbours:
            for (Pair neighbour : adjList[node]){
                int newdis = neighbour.distance + dis;
                
                if (newdis < distance[neighbour.node]){
                    q.add(new Pair(neighbour.node, newdis));
                }
            }
            
            
        }

          // O(V):
        for (int i = 0; i < V; i++){
            if (distance[i] == Integer.MAX_VALUE){
                distance[i] = -1;
            }
        }
        
        return distance;



        // Better solution -> toposort -> only for Directed Acyclic graph ->  toposort hame ek sequence deta hai such that  2,3,4,5 -> if this is the sequence 2 will come before 3 always -> yani 2 aur 3 4 se pehle compute ho jayenge -> ab 4 tak mai 2 se aungi ya 3 se aungi -> so when I reach 4 -> 2 and 3 have already got their best paths -> and have passed those best paths to 4 -> so jab mai 5 pe jaungi -> I will pass the best path to 5 -> this is how we'll avoid extra computations 

         
    private void dfs(int node, int[] visited, ArrayList<Pair> [] adjList, Stack<Integer>st){
        // go to the neighbours:
        
        for (Pair neighbour : adjList[node]){
            if (visited[neighbour.node] == 0){
                visited[neighbour.node] = 1;
                dfs(neighbour.node, visited, adjList, st);
            }
            
        }
        
        st.push(node);
    }
    
    class Pair2{
        int node;
        int distance; 
        
        Pair2(int node, int distance){
            this.node = node;
            this.distance = distance;
        }
    }

    public int[] shortestPathInDAG2(int V, int E, int[][] edges) {
        // Code here
        
        // create an adjacency List:
        ArrayList<Pair> [] adjList = new ArrayList[V];
        
        // O(V)
        for (int i = 0; i < V; i++){
            adjList[i] = new ArrayList<>();
        }
        
        // O(E)
        
        for (int [] edge : edges){
            adjList[edge[0]].add(new Pair(edge[1], edge[2]));
        }
        
        // O(V) space
        int [] distance = new int [V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        
     
        // apply toposort:
        
        // O(V+ E)
        Stack<Integer> st = new Stack<>();
        int [] visited = new int [V];
        for (int i = 0; i < V; i++){
            if (visited[i] == 0){
                visited[i] = 1;
                dfs(i, visited, adjList, st);
            }
        }
        
        distance[0] = 0;
        
        // O(V+E)
        while(!st.isEmpty()){
            int node = st.pop();
            
            if (distance[node] != Integer.MAX_VALUE){
            for (Pair neighbours : adjList[node]){
                if (distance[neighbours.node] > distance[node] + neighbours.distance){
                    distance[neighbours.node] = distance[node] + neighbours.distance;
                }
            }
            }
        }
        
        // O(V):
        for (int i = 0; i < V; i++){
            if (distance[i] == Integer.MAX_VALUE){
                distance[i] = -1;
            }
        }
        
        return distance;

    }
        
}
