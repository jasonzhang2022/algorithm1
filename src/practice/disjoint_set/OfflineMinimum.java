package practice.disjoint_set;

/*
http://llhuii.is-programmer.com/posts/31126.html
  off-line minimum problem
 */

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/*
 * We can solve this by priority queue during interview
 *
 * Questions: we will insert n numbers [1-n].
 * These n numbers are interspersed by extraction.
 *
 * Idea: Each continuous insertion is a disjoint set.
 * The disjoint set is represented by Extract index. (Not by the insertion element number).
 *
 * After we have all these disjoint set computed,
 * We extract from small number. Small number is definitely extracted by following extract.
 *
 * NOTE:
 *  1. always union to right. Not by rank.
 *  2. set id is not  leaf's value.
 *
 */
public class OfflineMinimum {



    public int[] extract(String[] input, int n) {
        // zero is not used
        int[] disjointSet = new int[n + 1];
        Arrays.fill(disjointSet, -1);

        //zero is not used.
        int m = input.length - n;
        int[] parents = new int[m + 2];
        int currentSetID = 1;
        parents[currentSetID] = currentSetID;


        for (String one : input) {
            if (one.equals("E")) {
                currentSetID++;
                parents[currentSetID] = currentSetID;
            } else {
                int number = Integer.parseInt(one);
                disjointSet[number] = currentSetID;
            }
        }

        //zero is not used.
        int[] output = new int[m + 2];
        Arrays.fill(output, -1);

        for (int i = 1; i <= n; i++) {
            int setID = find(i, disjointSet, parents);
            if (output[setID] == -1) {
                output[setID] = i;
                //we set an output. We effectively merge this set with next set.
                union(parents, setID);
            }
        }

        return Arrays.copyOfRange(output, 1, output.length-1);

    }

    public int find(int number, int[] disjointSet, int[] parents) {

        int setID = disjointSet[number];
        setID = findSetID(setID, parents);
        disjointSet[number] = setID;
        return setID;
    }


    public int findSetID(int i, int[] setIDTree) {
        if (setIDTree[i] != i) {
            int topparent = findSetID(setIDTree[setIDTree[i]], setIDTree);
            setIDTree[i] = topparent;
        }
        return setIDTree[i];
    }

    //rank is not important. Always merge with right disjoint set
    public void union(int[] parents, int u) {
        //don't union last elements.
        if (u == parents.length - 1) {
            return;
        }
        int uparent = findSetID(u, parents);
        // out of bound check
        int uplusParent = findSetID(u + 1, parents);
        parents[uparent] = uplusParent;

    }


    public static class TestCase {
        @Test
        public void testStartWithE(){
            String[] input={"E", "1", "2"};
            int[] expected={-1};
            String expectedStr=Arrays.stream(expected).mapToObj(a->Integer.toString(a)).collect(Collectors.joining(","));
            int[] result=new OfflineMinimum().extract(input, 2);
            String resultStr=Arrays.stream(result).limit(expected.length).mapToObj(a->Integer.toString(a)).collect(Collectors.joining(","));
            assertThat(resultStr, equalTo(expectedStr));
        }

        @Test
        public void testEndE(){
            String[] input={"1", "2", "E"};
            int[] expected={1};
            String expectedStr=Arrays.stream(expected).mapToObj(a->Integer.toString(a)).collect(Collectors.joining(","));
            int[] result=new OfflineMinimum().extract(input, 2);
            String resultStr=Arrays.stream(result).limit(expected.length).mapToObj(a->Integer.toString(a)).collect(Collectors.joining(","));
            assertThat(resultStr, equalTo(expectedStr));
        }

        @Test
        public void testSomeEmptyExtractionInMiddle(){
            String[] input={"1", "E", "E", "2"};
            int[] expected={1, -1};
            String expectedStr=Arrays.stream(expected).mapToObj(a->Integer.toString(a)).collect(Collectors.joining(","));
            int[] result=new OfflineMinimum().extract(input, 2);
            String resultStr=Arrays.stream(result).limit(expected.length).mapToObj(a->Integer.toString(a)).collect(Collectors.joining(","));
            assertThat(resultStr, equalTo(expectedStr));
        }

        @Test
        public void testBasic(){
            String[] input={"4", "8", "E", "3", "E", "9", "2", "6", "E", "E", "E", "1", "7", "E", "5"};
            int[] expected={4, 3,2,6,8,1};
            String expectedStr=Arrays.stream(expected).mapToObj(a->Integer.toString(a)).collect(Collectors.joining(","));
            int[] result=new OfflineMinimum().extract(input, 9);
            String resultStr=Arrays.stream(result).limit(expected.length).mapToObj(a->Integer.toString(a)).collect(Collectors.joining(","));
            assertThat(resultStr, equalTo(expectedStr));
        }
    }


}
