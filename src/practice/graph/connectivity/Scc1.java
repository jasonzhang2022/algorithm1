package practice.graph.connectivity;

import practice.graph.ConnectivityGraph;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class Scc1 {

    List<Set<Integer>> sccs = new LinkedList<>();

    Stack<Integer> stack  = new Stack<>();
    List<Set<Integer>> find(ConnectivityGraph graph) {

        boolean[] visited = new boolean[graph.V];
        for (int u=0; u<graph.V; u++){
            if (!visited[u]){
                DFS(graph, u, visited);
            }
        }

        Arrays.fill(visited, false);
        graph = graph.transpose();
        while (!stack.isEmpty()){
            int u= stack.pop();
            if (!visited[u]){
                Set<Integer> scc = new HashSet<>();
                DFSSCC(graph, u, visited, scc);
                sccs.add(scc);
            }
        }

        return sccs;
    }

    void DFS(ConnectivityGraph graph, int u, boolean[] visited){
        visited[u]= true;
        for (int v: graph.adj[u]){
            if (!visited[v]){
                DFS(graph, v, visited);
            }
        }
        /**
         * Technique: keep node on stack during DFS.
         * Keep node on top of stack: add node after children.
         */
        stack.push(u);
    }

    void DFSSCC(ConnectivityGraph graph, int u, boolean[] visited, Set<Integer> scc){
        visited[u]= true;
        for (int v: graph.adj[u]){
            if (!visited[v]){
                DFSSCC(graph, v, visited, scc);
            }
            //if visited, it is already in another scc.
        }
        /**
         * Technique: keep node on stack during DFS.
         * Keep node on top of stack: add node after children.
         */
        scc.add(u);
    }

    public static class Test {

        @org.junit.Test
        public void test1() {
            ConnectivityGraph g = new ConnectivityGraph(8);
            g.addEdgeDirect(0, 1);
            g.addEdgeDirect(1, 2);
            g.addEdgeDirect(1, 4);
            g.addEdgeDirect(4, 0);
            g.addEdgeDirect(0, 5);
            g.addEdgeDirect(1, 5);

            g.addEdgeDirect(5, 6);
            g.addEdgeDirect(6, 5);
            g.addEdgeDirect(2, 6);
            g.addEdgeDirect(2, 3);
            g.addEdgeDirect(3, 2);
            g.addEdgeDirect(6, 7);
            g.addEdgeDirect(3, 7);
            g.addEdgeDirect(7, 7);


            List<Set<Integer>> result=new Scc1().find(g);
            String resultStr=result.stream().map(s->s.stream().sorted().map(i->i.toString()).collect(Collectors.joining(","))).collect(Collectors.joining("|"));
            System.out.println(resultStr);
            String expected="0,1,4|2,3|5,6|7";
            assertThat(resultStr, equalTo(expected));
        }
    }



}
