package practice.graph.cover;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/*

Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the
 itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

If there are multiple valid itineraries, you should return the itinerary that has the
smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"]
has a smaller lexical order than ["JFK", "LGB"].
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

        Map<String, LinkedList<String>> graph = new HashMap<>();
        for (String[] segment: tickets) {
            String city1 = segment[0];
            String city2 = segment[1];
            graph.putIfAbsent(city1, new LinkedList<>());
            graph.get(city1).add(city2);
        }

        for(String key: graph.keySet()) {
            Collections.sort(graph.get(key));
        }

        Stack<String> stack = new Stack<>();
        tourHelper(graph, "JFK", stack);
        List<String> results = new LinkedList<>();
        while(!stack.isEmpty()){
            results.add(stack.pop());
        }
        return results;
    }

    public void tourHelper(Map<String, LinkedList<String>> graph, String airport, Stack<String> tour) {

        while(graph.containsKey(airport) && !graph.get(airport).isEmpty()){
            String firstNode =  graph.get(airport).removeFirst();
            tourHelper(graph, firstNode, tour);
        }
        tour.push(airport);
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
