package practice.graph.cover;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/*

Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:

Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
Example 2:

Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.

 */
public class Itinerary {

    /*
     * Use the edge convention
     */
    public List<String> findItinerary(String[][] tickets) {
        Map<String, List<String>> adjList = new HashMap<>();
        for (String[] leg: tickets){
            adjList.putIfAbsent(leg[0], new LinkedList<>());
            adjList.computeIfPresent(leg[0], (key, oldValue)-> { oldValue.add(leg[1]); return oldValue; });
        }
        for (List<String> value: adjList.values()){
            Collections.sort(value);
        }

       Stack<String> traversalStack = new Stack<>();
        Queue<String> resultQueue = new LinkedList<>();


        traversalStack.push("JFK");
        while (!traversalStack.isEmpty()){
            String currentNode = traversalStack.peek();
            if (adjList.getOrDefault(currentNode, Collections.EMPTY_LIST).isEmpty()) {
                ((LinkedList<String>) resultQueue).offerFirst(currentNode);
                traversalStack.pop();
                continue;
            }


            do  {
                String nextNode = ((LinkedList<String>)adjList.get(currentNode)).removeFirst();;
                traversalStack.push(nextNode);
                currentNode = nextNode;
            } while (adjList.containsKey(currentNode) && !adjList.get(currentNode).isEmpty());
        }

        return new LinkedList<>(resultQueue);
    }

    public static class TestCase {

        @Test
        public void test() {
            Itinerary finder = new Itinerary();
            String[][] tickets = { { "MUC", "LHR" }, { "JFK", "MUC" }, { "SFO", "SJC" }, { "LHR", "SFO" } };
            String result = finder.findItinerary(tickets).stream().collect(Collectors.joining("->"));
            String expect = "JFK->MUC->LHR->SFO->SJC";
            assertThat(expect, equalTo(result));

        }

        @Test
        public void test1() {
            Itinerary finder = new Itinerary();
            String[][] tickets = { { "JFK", "SFO" }, { "JFK", "ATL" }, { "SFO", "ATL" }, { "ATL", "JFK" },
                    { "ATL", "SFO" } };
            String result = finder.findItinerary(tickets).stream().collect(Collectors.joining("->"));
            String expect = "JFK->ATL->JFK->SFO->ATL->SFO";
            assertThat(expect, equalTo(result));

        }

        @Test
        public void test2() {
            Itinerary finder = new Itinerary();
            String[][] tickets = { { "JFK", "AAA" }, { "AAA", "BBB" }, { "AAA", "CCC" }, { "CCC", "DDD" },
                    { "DDD", "AAA" } };
            String result = finder.findItinerary(tickets).stream().collect(Collectors.joining("->"));
            String expect = "JFK->AAA->CCC->DDD->AAA->BBB";
            assertThat(expect, equalTo(result));

        }

        @Test
        public void test3() {
            Itinerary finder = new Itinerary();
            String[][] tickets = { { "JFK", "AAA" }, { "AAA", "BBB" }, { "AAA", "CCC" }, { "CCC", "DDD" },
                    { "DDD", "AAA" }, {"BBB", "DDD"} };
            String result = finder.findItinerary(tickets).stream().collect(Collectors.joining("->"));
            String expect = "JFK->AAA->BBB->DDD->AAA->CCC->DDD";
            assertThat(expect, equalTo(result));

        }
    }
}
