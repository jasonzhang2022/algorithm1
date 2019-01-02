package practice.graph;

import java.util.LinkedList;

public class ConnectivityGraph {
    public int V;   // No. of vertices

    // Array  of lists for Adjacency List Representation
    public LinkedList<Integer> adj[];
    public int time = 0;
    public static final int NIL = -1;

    // Constructor
    public ConnectivityGraph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    //Function to add an edge into the ArticulationPoint
    public void addEdge(int v, int w)
    {
        adj[v].add(w);  // Add w to v's list.
        adj[w].add(v);  //Add v to w's list
    }

    //Function to add an edge into the ArticulationPoint
    public void addEdgeDirect(int v, int w)
    {
        adj[v].add(w);  // Add w to v's list.
    }



    public ConnectivityGraph transpose(){
        ConnectivityGraph graph = new ConnectivityGraph(this.V);
        for (int u=0; u<V; u++){
            for (int v: adj[u]){
                graph.addEdgeDirect(v, u);
            }
        }
        return graph;
    }

    public LinkedList<Integer>[] cloneEdges(){
        LinkedList<Integer>[] ret=new LinkedList[adj.length];


        for (int i=0; i<adj.length; i++){
            ret[i]=new LinkedList<>(adj[i]);
        }
        return ret;
    }

    public int[][] toGrid(){
        int[][] grid= new int[V][V];
        for(int row=0; row<V; row++){
            for (int col: adj[row]){
                grid[row][col]=1;
            }
        }
        return grid;
    }
}
