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

    public int[] extract(String inputs[]) {
        int n=0;
        // find out hoe many numbers we have.
        for (int i=0; i<inputs.length; i++) {
            if (!inputs[i].equals("E")){
                n = Math.max(Integer.parseInt(inputs[i]), n);
            }
        }

        // set each number belongs to.
        int[] set = new int[n+1];

        // set starts from zero,
        int m =0;
        for (String s: inputs){
            if (s.equals("E")){
                m++;
                continue;
            }
            set[Integer.parseInt(s)] = m;
        }
        //set relationship
        // set at m contained remaining elements after extraction.
        int[] tree = new int[m+1];
        for (int i=0; i<=m; i++){
            tree[i]=i;
        }

        int[] result = new int[m];
        for (int i =1; i<=n; i++){
            int setIndex = findRoot(tree, set[i]);
            if (setIndex!=m){
                result[setIndex] = i;
                mergeSetToNextSet(tree, setIndex);
            }
        }

        // if result[x]==0, there is no number for set x since 0 is not a valid number for extraction.
        return Arrays.stream(result).filter(i->i!=0).toArray();
    }

    public int findRoot(int[] tree, int i){
        int parent = i;

        while ( tree[parent]!=parent) {
            parent = tree[parent];
        }
        tree[i]=parent;
        return parent;
    }

    public void mergeSetToNextSet(int[] tree, int setId) {
        tree[setId] = findRoot(tree, setId+1);
    }

    public static class TestCase {
        @Test
        public void testStartWithE(){
            String[] input={"E", "1", "2"};
            int[] expected={};
            String expectedStr=Arrays.stream(expected).mapToObj(Integer::toString).collect(Collectors.joining(","));
            int[] result=new OfflineMinimum().extract(input);
            String resultStr=Arrays.stream(result).mapToObj(Integer::toString).collect(Collectors.joining(","));
            assertThat(resultStr, equalTo(expectedStr));
        }

        @Test
        public void testEndE(){
            String[] input={"1", "2", "E"};
            int[] expected={1};
            String expectedStr=Arrays.stream(expected).mapToObj(Integer::toString).collect(Collectors.joining(","));
            int[] result=new OfflineMinimum().extract(input);
            String resultStr=Arrays.stream(result).mapToObj(Integer::toString).collect(Collectors.joining(","));
            assertThat(resultStr, equalTo(expectedStr));
        }

        @Test
        public void testSomeEmptyExtractionInMiddle(){
            String[] input={"1", "E", "E", "2"};
            int[] expected={1};
            String expectedStr=Arrays.stream(expected).mapToObj(Integer::toString).collect(Collectors.joining(","));
            int[] result=new OfflineMinimum().extract(input);
            String resultStr=Arrays.stream(result).mapToObj(Integer::toString).collect(Collectors.joining(","));
            assertThat(resultStr, equalTo(expectedStr));
        }

        @Test
        public void testBasic(){
            String[] input={"4", "8", "E", "3", "E", "9", "2", "6", "E", "E", "E", "1", "7", "E", "5"};
            int[] expected={4, 3,2,6,8,1};
            String expectedStr=Arrays.stream(expected).mapToObj(Integer::toString).collect(Collectors.joining(","));
            int[] result=new OfflineMinimum().extract(input);
            String resultStr=Arrays.stream(result).mapToObj(Integer::toString).collect(Collectors.joining(","));
            assertThat(resultStr, equalTo(expectedStr));
        }
    }


}
