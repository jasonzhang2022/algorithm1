package practice.graph.cover;

import org.junit.Test;
import practice.graph.ConnectivityGraph;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class EulerTour {

    List<String> path = new LinkedList<>();

    //use adjacet matrix so edge can be removed easily.

    int oddNode = -1;
    int n;

    List<String> tour(int[][] graph){
        n = graph.length;
        int oddNum = 0;
        for (int row=0; row<n; row++){
            int degree = 0;
            for (int col=0; col<n; col++){
                if (graph[row][col]==1){
                    degree++;
                }
            }

            if (degree%2==1){
                oddNum++;
                oddNode = row;
            }
        }

        if (oddNum!=0 && oddNum!=2){
            return path;
        }
        dfs(graph, oddNum);
        return path;
    }


    public void dfs(int[][] graph, int u) {

        //find the node with largest degree.
        //different from typical DFS, we only pick one node.
        int degree = 0;
        int v = -1;
        int anyv = -1;
        for (int col=0; col<n; col++){
            if (graph[u][col]!=1){
                continue;
            }
           if (isBridge(graph, u, col)){
               anyv=col;
               continue;
           }
           v=col;
           break;
        }
        //no edge anymore
        if (v==-1){
            //last edge.
            v = anyv;
        }
        if (v==-1){
            return;
        }

        //remove edge u->v
        graph[u][v]=0;
        graph[v][u]=0;
        path.add(String.format("%d->%d", u, v));
        dfs(graph, v);
    }

    boolean isBridge(int[][] grid, int u, int v) {

        boolean[] visited = new boolean[grid.length];
        int reachableVertices = dfsCount(grid, u, visited);

        Arrays.fill(visited, false);
        grid[u][v]=0;
        grid[v][u]=0;
        int newReachableVertices = dfsCount(grid, u, visited);
        grid[u][v]=1;
        grid[v][u]=1;
        return newReachableVertices<reachableVertices;
    }
    int dfsCount(int[][] grid, int u, boolean[] visited){

        visited[u] = true;
        int visitedNumber = 1;
        for (int v=0; v<grid.length; v++){
            if (grid[u][v]==1 && !visited[v]) {
                visitedNumber+= dfsCount(grid, v, visited);
            }
        }
        return visitedNumber;
    }


    public static class Test {

        @org.junit.Test
        public void test1(){
            ConnectivityGraph g1 = new ConnectivityGraph(4);
            g1.addEdge(0,1);
            g1.addEdge(1,2);
            g1.addEdge(2,3);
            g1.addEdge(3,0);

            String result=new  EulerTour().tour(g1.toGrid()).stream().collect(Collectors.joining(","));
            String expected="0->1,1->2,2->3,3->0";
            assertThat(result, equalTo(expected));
        }

        @org.junit.Test
        public void test2(){
            ConnectivityGraph g1 = new ConnectivityGraph(5);
            g1.addEdge(0,1);
            g1.addEdge(1,2);
            g1.addEdge(2,0);
            g1.addEdge(1,3);
            g1.addEdge(3,4);
            g1.addEdge(4,1);

            String result=new  EulerTour().tour(g1.toGrid()).stream().collect(Collectors.joining(","));
            String expected="0->1,1->3,3->4,4->1,1->2,2->0";
            assertThat(result, equalTo(expected));
        }

        @org.junit.Test
        public void test3(){
            ConnectivityGraph g1 = new ConnectivityGraph(7);
            g1.addEdge(0,1);
            g1.addEdge(1,2);
            g1.addEdge(2,0);



            g1.addEdge(4,5);
            g1.addEdge(5,6);
            g1.addEdge(6,4);


            g1.addEdge(1,3);
            g1.addEdge(3,4);
            g1.addEdge(4,1);

            String result=new  EulerTour().tour(g1.toGrid()).stream().collect(Collectors.joining(","));
            String expected="0->1,1->3,3->4,4->5,5->6,6->4,4->1,1->2,2->0";
            assertThat(result, equalTo(expected));
        }

        @org.junit.Test
        public void test4(){
            ConnectivityGraph g1 = new ConnectivityGraph(7);
            g1.addEdge(0,1);
            g1.addEdge(1,2);
            g1.addEdge(2,0);



            g1.addEdge(1,5);
            g1.addEdge(5,6);
            g1.addEdge(6,1);


            g1.addEdge(1,3);
            g1.addEdge(3,4);
            g1.addEdge(4,1);

            String result=new  EulerTour().tour(g1.toGrid()).stream().collect(Collectors.joining(","));
            String expected="0->1,1->3,3->4,4->1,1->5,5->6,6->1,1->2,2->0";
            assertThat(result, equalTo(expected));
        }
    }
}
