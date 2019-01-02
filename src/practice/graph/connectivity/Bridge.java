package practice.graph.connectivity;

import org.junit.Test;
import practice.graph.ConnectivityGraph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Difference between articulation point and bridge.
 * 1. From U->V, low[V]=disc[u]+1 >low[u].
 * So when U->V is removed, subtree headed by V can't be reached anymore.
 * <p>
 * 2. There is root judge anymore since we check edge right now
 */

/**
 * This algorithm find all bridge which is a weakpoint.
 *
 * Judge whether a edge(A-B) is a bridge is easy
 * DFS(A): Remember the reachable nodes. Reachability
 * Remove A->B, do a DFS(A) again. A->B is a bridge if the reachable nobde become less.
 *
 */
public class Bridge {

    /*
    We don't maintain 3 colors since
    undirected graph doesn't have forward and cross edge.
     */
    boolean[] visited;
    int[] parent;
    int[] disc;
    int[] low;

    List<String> results;
    int time = 0;
    ConnectivityGraph graph;

    public List<String> find(ConnectivityGraph graph) {
        time = 0;
        this.graph = graph;
        visited = new boolean[graph.V];

        parent = new int[graph.V];
        Arrays.fill(parent, -1);

        disc = new int[graph.V];
        low = new int[graph.V];
        results = new LinkedList<>();

        for (int v = 0; v < graph.V; v++) {
            if (!visited[v]) {
                helper(v);
            }
        }
        return results;
    }

    void helper(int u) {

        visited[u] = true;
        disc[u] = time;
        low[u] = time;
        ++time;

        int children = 0;
        for (int v : graph.adj[u]) {
            if (!visited[v]) {
                parent[v] = u;
                helper(v);
                children++;

                // The top node can reach through the subtree headed by U.
                low[u] = Math.min(low[u], low[v]);

                //condition for not root node
                if (low[v] > disc[u]) {
                    results.add(String.format("%d->%d", Math.min(u, v), Math.max(u, v)));
                }
            } else if (parent[u] != v) {
                /*
                 u->v is a back edge.
                 1. Judge parent to make sure we don't use the edge twice.

                 node u can reach disc[v].
                 We don't use low[v]: low[v] represents the lowest node that can be reached
                  by all its branches.

                  It also contains the lowest node from other branches which u is not in.
                 */
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    public static class TestCase {
        @Test
        public void testBridge1() {
            // Create ArticulationPoints given in above diagrams
            ConnectivityGraph g1 = new ConnectivityGraph(5);
            g1.addEdge(1, 0);
            g1.addEdge(0, 2);
            g1.addEdge(2, 1);
            g1.addEdge(0, 3);
            g1.addEdge(3, 4);
            String result = new Bridge().find(g1).stream().collect(Collectors.joining(";"));
            assertThat(result, equalTo("3->4;0->3"));
        }

        @Test
        public void testBridge2() {
            ConnectivityGraph g2 = new ConnectivityGraph(4);
            g2.addEdge(0, 1);
            g2.addEdge(1, 2);
            g2.addEdge(2, 3);
            String result = new Bridge().find(g2).stream().collect(Collectors.joining(";"));
            assertThat(result, equalTo("2->3;1->2;0->1"));
        }

        @Test
        public void testBridge3() {
            ConnectivityGraph g3 = new ConnectivityGraph(7);
            g3.addEdge(0, 1);
            g3.addEdge(1, 2);
            g3.addEdge(2, 0);
            g3.addEdge(1, 3);
            g3.addEdge(1, 4);
            g3.addEdge(1, 6);
            g3.addEdge(3, 5);
            g3.addEdge(4, 5);
            String result = new Bridge().find(g3).stream().collect(Collectors.joining(";"));
            assertThat(result, equalTo("1->6"));
        }
    }

}
