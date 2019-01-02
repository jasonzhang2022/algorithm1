package practice.graph.connectivity;

import practice.graph.ConnectivityGraph;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Use the same approach as connectivity find.
 *
 *
 */
public class Scc2 {

    List<Set<Integer>> sccs = new LinkedList<>();

    Stack<Integer> stack  = new Stack<>();
    int[] visited;
    int[] disc;
    int[] low;
    int[] parent;

    int time = 1;
    List<Set<Integer>> find(ConnectivityGraph graph) {

        visited = new int[graph.V];
        disc = new int[graph.V];
        low = new int[graph.V];
        parent  = new int[graph.V];
        Arrays.fill(parent, -1);

        for (int u=0; u<graph.V; u++){
            if (visited[u]==0){
                DFS(graph, u);
            }
        }
        return sccs;
    }

    void DFS(ConnectivityGraph graph, int u){
        visited[u]= 1; //grey

        disc[u] = time;
        low[u] = time;
        time++;
        // Add parent at the bottom of stack.
        // different from Topology sorting
        stack.push(u);


        for (int v: graph.adj[u]){
            if (visited[v]==0){
                parent[v]= u;
                DFS(graph, v);

                low[u] = Math.min(low[u], low[v]);
            } else if (visited[v] == 1) {
                // u->v is a back edge.
                low[u] = Math.min(low[u], disc[v]);
            }
        }
        /**
         * Technique: keep node on stack during DFS.
         * Keep node on top of stack: add node after children.
         */
        visited[u] = 2; //back;

        if (low[u] == disc[u]) {
            //all nodes headed by v doesn't have loop to u or its ancestors.
            // very important technique
            Set<Integer> scc = new HashSet<>();
            while (stack.peek()!=u){
                scc.add(stack.pop());
            }
            scc.add(stack.pop());
            sccs.add( scc);
        }
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


            List<Set<Integer>> result=new Scc2().find(g);
            String resultStr=result.stream().map(s->s.stream().sorted().map(i->i.toString()).collect(Collectors.joining(","))).collect(Collectors.joining("|"));
            System.out.println(resultStr);
            String expected="7|5,6|2,3|0,1,4";
            assertThat(resultStr, equalTo(expected));
        }
    }

}
