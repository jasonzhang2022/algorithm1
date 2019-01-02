package practice.graph.cover;

import org.junit.Test;
import practice.graph.ConnectivityGraph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * A cycle cover all nodes.
 *  This is like trying all possible path
 */
public class HamiltonCycle {


    boolean[] visited;

    LinkedList<Integer> cycle = new LinkedList<>();
    int n;
    public  LinkedList<Integer>find(ConnectivityGraph graph){
        n = graph.V;
        visited =new boolean[n];
        Arrays.fill(visited, false);

        helper(0, graph);
        return cycle;
    }



    public boolean helper(int u, ConnectivityGraph graph){
        visited[u]=true;
        cycle.add(u);

        if (cycle.size()==n){
            if (graph.adj[u].contains(0)){
                return true;
            } else {
                cycle.removeLast();
                visited[u]=false;

                return false;
            }
        }

        for (int v: graph.adj[u]){
            if (!visited[v]){
                if (helper(v, graph)){
                    return true;
                }
            }
        }

        cycle.removeLast();
        visited[u]=false;

        return false;
    }
    public static class Test {
        @org.junit.Test
        public void testHamiltonianCycle()
        {
            // Create ArticulationPoints given in above diagrams
            ConnectivityGraph g1 = new ConnectivityGraph(4);
            g1.addEdgeDirect(0,1);
            g1.addEdgeDirect(1,2);
            g1.addEdgeDirect(2,3);
            g1.addEdgeDirect(3,0);


            String result =new HamiltonCycle().find(g1).stream().map(i->i.toString()).collect(Collectors.joining("->"));
            String expected="0->1->2->3";
            assertThat(result, equalTo(expected));
        }


        @org.junit.Test
        public void testHamiltonianCycleOne()
        {
            // Create ArticulationPoints given in above diagrams
            ConnectivityGraph g1 = new ConnectivityGraph(5);
            g1.addEdgeDirect(0,1);
            g1.addEdgeDirect(1,3);
            g1.addEdgeDirect(1,2);
            g1.addEdgeDirect(2,4);
            g1.addEdgeDirect(4,1);
            g1.addEdgeDirect(4,3);
            g1.addEdgeDirect(3,0);



            String result =new HamiltonCycle().find(g1).stream().map(i->i.toString()).collect(Collectors.joining("->"));
            String expected="0->1->2->4->3";
            assertThat(result, equalTo(expected));
        }

        @org.junit.Test
        public void testHamiltonianCycleOneUndirected()
        {
            // Create ArticulationPoints given in above diagrams
            ConnectivityGraph g1 = new ConnectivityGraph(5);
            g1.addEdge(0,1);
            g1.addEdge(1,3);
            g1.addEdge(1,2);
            g1.addEdge(2,4);
            g1.addEdge(4,1);
            g1.addEdge(4,3);
            g1.addEdge(3,0);



            String result =new HamiltonCycle().find(g1).stream().map(i->i.toString()).collect(Collectors.joining("->"));
            String expected="0->1->2->4->3";
            assertThat(result, equalTo(expected));
        }

        @org.junit.Test
        public void testHamiltonianCycleNegative()
        {
            // Create ArticulationPoints given in above diagrams
            ConnectivityGraph g1 = new ConnectivityGraph(4);
            g1.addEdgeDirect(0,1);
            g1.addEdgeDirect(1,2);
            g1.addEdgeDirect(2,3);
            g1.addEdgeDirect(3,1);


            String result =new HamiltonCycle().find(g1).stream().map(i->i.toString()).collect(Collectors.joining("->"));
            String expected="";
            assertThat(result, equalTo(expected));
        }
    }

}

