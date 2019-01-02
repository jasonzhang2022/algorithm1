package practice.graph.cover;


import practice.graph.ConnectivityGraph;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class EulerTourLinear {
    public List<Integer> tour(ConnectivityGraph graph){
        List<Integer> cycle = new LinkedList<>();
        populateCycle(graph, cycle, 0);

        for (ListIterator<Integer> iter= cycle.listIterator(); iter.hasNext();){
            int u = iter.next();
            while (!graph.adj[u].isEmpty()){
                List<Integer> childCycle = new LinkedList<>();
                populateCycle(graph, childCycle, u);
                Iterator<Integer> childIter = childCycle.iterator();
                childIter.next();
                while (childIter.hasNext()) {
                    iter.add(childIter.next());
                }
            }
        }
        return cycle;
    }


    void populateCycle(ConnectivityGraph graph, List<Integer> cycle, int u){

        do {
            cycle.add(u);
            if (!graph.adj[u].isEmpty()) {
                u = graph.adj[u].removeFirst();
            } else {
                u=-1;
            }

        } while(u!=-1);
    }

    public static class Test {

        @org.junit.Test
        public void test1(){
            ConnectivityGraph g1 = new ConnectivityGraph(4);
            g1.addEdgeDirect(0,1);
            g1.addEdgeDirect(1,2);
            g1.addEdgeDirect(2,3);
            g1.addEdgeDirect(3,0);

            String result=new  EulerTourLinear().tour(g1).stream().map(i->i.toString()).collect(Collectors.joining("->"));
            String expected="0->1->2->3->0";
            assertThat(result, equalTo(expected));
        }

        @org.junit.Test
        public void test2(){
            ConnectivityGraph g1 = new ConnectivityGraph(5);
            g1.addEdgeDirect(0,1);
            g1.addEdgeDirect(1,2);
            g1.addEdgeDirect(2,0);
            g1.addEdgeDirect(1,3);
            g1.addEdgeDirect(3,4);
            g1.addEdgeDirect(4,1);

            String result=new  EulerTourLinear().tour(g1).stream().map(i->i.toString()).collect(Collectors.joining("->"));
            String expected="0->1->3->4->1->2->0";
            assertThat(result, equalTo(expected));
        }

        @org.junit.Test
        public void test3(){
            ConnectivityGraph g1 = new ConnectivityGraph(7);
            g1.addEdgeDirect(0,1);
            g1.addEdgeDirect(1,2);
            g1.addEdgeDirect(2,0);



            g1.addEdgeDirect(4,5);
            g1.addEdgeDirect(5,6);
            g1.addEdgeDirect(6,4);


            g1.addEdgeDirect(1,3);
            g1.addEdgeDirect(3,4);
            g1.addEdgeDirect(4,1);

            String result=new  EulerTourLinear().tour(g1).stream().map(i->i.toString()).collect(Collectors.joining("->"));
            String expected="0->1->3->4->5->6->4->1->2->0";
            assertThat(result, equalTo(expected));
        }

        @org.junit.Test
        public void test4(){
            ConnectivityGraph g1 = new ConnectivityGraph(7);
            g1.addEdgeDirect(0,1);
            g1.addEdgeDirect(1,2);
            g1.addEdgeDirect(2,0);



            g1.addEdgeDirect(1,5);
            g1.addEdgeDirect(5,6);
            g1.addEdgeDirect(6,1);


            g1.addEdgeDirect(1,3);
            g1.addEdgeDirect(3,4);
            g1.addEdgeDirect(4,1);

            String result=new  EulerTourLinear().tour(g1).stream().map(i->i.toString()).collect(Collectors.joining("->"));
            String expected="0->1->5->6->1->3->4->1->2->0";
            assertThat(result, equalTo(expected));
        }
    }


    public List<Integer> tourStack(ConnectivityGraph graph){
        Stack<Integer>  traversal = new Stack<>();
        Deque<Integer> result = new LinkedList<>();

        traversal.push(0);



        while (!traversal.isEmpty()) {
            int topNode = traversal.peek();
            if (graph.adj[topNode].isEmpty()){
                result.offerFirst(topNode);
                traversal.pop();
                continue;
            }

            int currentNode=topNode;
            while(!graph.adj[currentNode].isEmpty()){
                int nextNode = graph.adj[currentNode].remove();
                traversal.push(nextNode);
                currentNode = nextNode;
            }
        }

        return new LinkedList<>(result);

    }

    public static class TestStack {

        @org.junit.Test
        public void test1(){
            ConnectivityGraph g1 = new ConnectivityGraph(4);
            g1.addEdgeDirect(0,1);
            g1.addEdgeDirect(1,2);
            g1.addEdgeDirect(2,3);
            g1.addEdgeDirect(3,0);

            String result=new  EulerTourLinear().tourStack(g1).stream().map(i->i.toString()).collect(Collectors.joining("->"));
            String expected="0->1->2->3->0";
            assertThat(result, equalTo(expected));
        }

        @org.junit.Test
        public void test2(){
            ConnectivityGraph g1 = new ConnectivityGraph(5);
            g1.addEdgeDirect(0,1);
            g1.addEdgeDirect(1,2);
            g1.addEdgeDirect(2,0);
            g1.addEdgeDirect(1,3);
            g1.addEdgeDirect(3,4);
            g1.addEdgeDirect(4,1);

            String result=new  EulerTourLinear().tourStack(g1).stream().map(i->i.toString()).collect(Collectors.joining("->"));
            String expected="0->1->3->4->1->2->0";
            assertThat(result, equalTo(expected));
        }

        @org.junit.Test
        public void test3(){
            ConnectivityGraph g1 = new ConnectivityGraph(7);
            g1.addEdgeDirect(0,1);
            g1.addEdgeDirect(1,2);
            g1.addEdgeDirect(2,0);



            g1.addEdgeDirect(4,5);
            g1.addEdgeDirect(5,6);
            g1.addEdgeDirect(6,4);


            g1.addEdgeDirect(1,3);
            g1.addEdgeDirect(3,4);
            g1.addEdgeDirect(4,1);

            String result=new  EulerTourLinear().tourStack(g1).stream().map(i->i.toString()).collect(Collectors.joining("->"));
            String expected="0->1->3->4->5->6->4->1->2->0";
            assertThat(result, equalTo(expected));
        }

        @org.junit.Test
        public void test4(){
            ConnectivityGraph g1 = new ConnectivityGraph(7);
            g1.addEdgeDirect(0,1);
            g1.addEdgeDirect(1,2);
            g1.addEdgeDirect(2,0);



            g1.addEdgeDirect(1,5);
            g1.addEdgeDirect(5,6);
            g1.addEdgeDirect(6,1);


            g1.addEdgeDirect(1,3);
            g1.addEdgeDirect(3,4);
            g1.addEdgeDirect(4,1);

            String result=new  EulerTourLinear().tourStack(g1).stream().map(i->i.toString()).collect(Collectors.joining("->"));
            String expected="0->1->5->6->1->3->4->1->2->0";
            assertThat(result, equalTo(expected));
        }
    }


}
