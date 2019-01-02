package practice.graph;

//https://leetcode.com/problems/minimum-height-trees/

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/*
 * Scan from edge nodes.
 *  What do we learn from this exercise:
 *   How do we scan graph
 *
 */
public class MinimumHeightTree {


    //don't need visited array since it is tree structure.
    boolean[] visisted;
    List<Integer> find(int[][] edges){

        int n= edges.length +1;
        visisted = new boolean[n];

        ConnectivityGraph graph = new ConnectivityGraph(n);
        for (int[] edge: edges){
            graph.addEdge(edge[0], edge[1]);
        }

        Set<Integer> currentQueue = new HashSet<>();
        for (int u=0; u<graph.V; u++){
            if (graph.adj[u].size() ==1){
                currentQueue.add(u);
            }
        }

        Set<Integer> nextQueue = new HashSet<>();
        do {
            for (int u: currentQueue) {
                //use visited to avoid child revisit back to parent.
                visisted[u] = true;
                for (int v: graph.adj[u]){
                    if (!visisted[v]){
                        nextQueue.add(v);
                    }
                }
            }
            Set<Integer> tmp= currentQueue;
            currentQueue = nextQueue;
            nextQueue = tmp;
        } while (currentQueue.size()>2);

        return new LinkedList<>(currentQueue);
    }

    public static class TestCase {

        @Test
        public void test(){
            int [][] edges=new int[][]{{1, 0}, {1, 2}, {1, 3}};
            MinimumHeightTree mht=new MinimumHeightTree();
            String result=mht.find(edges).stream().map(i->i.toString()).collect(Collectors.joining(","));
            assertEquals("1", result);
        }

        @Test
        public void test1(){
            int [][] edges=new int[][]{{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
            MinimumHeightTree mht=new MinimumHeightTree();
            String result=mht.find( edges).stream().map(i->i.toString()).collect(Collectors.joining(","));
            assertEquals("3,4", result);
        }
    }

}
