package practice.graph;

import java.util.*;

public class BipartiteMatch {

    int left;
    int right;

    /**
     * Internally use index starting from one as index.
     */
    List<Set<Integer>> adjacents;
    public BipartiteMatch(int leftNum, int rightNum){
        this.left = leftNum;
        this.right = rightNum;
        this.adjacents = new ArrayList<>(leftNum+1);
        for (int i=0; i<=leftNum; i++){
            adjacents.add(new HashSet<>());
        }
    }
    public void addEdge(int u, int v){
        adjacents.get(u+1).add(v+1);
    }

    /*
      pairU[i] = 0, it is not paired
     */
    int[] result;
    int[] pairU;
    int[] pairV;
    int[] dist ;
    public int[] maxMatch(){
        result = new int[left];
        Arrays.fill(result, -1);
        pairU = new int[left+1];
        pairV = new int[right+1];
        while (hasAugumentPathBFS()){
            for (int u=1; u<=left; u++){
                if (pairU[u]==0){
                    findPathDFS(u);
                }
            }
        }
        for (int u=1; u<=left; u++){
            result[u-1]= pairU[u]-1;
        }
        return result;
    }

    boolean hasAugumentPathBFS(){
        Queue<Integer> queue = new LinkedList<>();
        dist= new int[left+1];
        //add all free node to queue.
        for (int u = 1; u<=left; u++){
            // not matched to any node
            if (pairU[u] == 0){
                queue.offer(u);
                dist[u]=0;
            } else {
                dist[u]=Integer.MAX_VALUE;
            }
        }
        // pair 0 doesn't match to any node.
        dist[0] = Integer.MAX_VALUE;

        while (!queue.isEmpty()){
            int u = queue.poll();

            /*
             * We are going to visit next node.
             * In a typical BFS,
             *  1.we need to mark node visit or not before enqueueing so we don't enqueue it twice.
             *  2. we don't visit visited node.
             *  3. we has a constraint what node can visit (for example, blocked cell can't be visited).
             *
             *  dist[u] has several meaning
             *  1. if it is MAX_VALUE, it is not visisted yet
             *  2. if !=MAX_VALUE, a) it is visited (so it can be visited again from left, prevent looping)
              *  b) roughly who visited this node.
             *
             *  IN typical BFS, v should be enqueued. Here we enqueue u'.
             *
             */
            for (int v : adjacents.get(u)){
                // this is the residual edge.
                int uPrime= pairV[v];

                //mutiple node can reach v, then indirectly to uPrime.
                // If v is a matched before, v can only go it is matched u'
                // If v is not matched, v goes ot 0. And mark 0 as visited. No other u can touch v any more.
                // This ensure v is visited once.
                if (dist[uPrime]==Integer.MAX_VALUE) {
                    // the distance actually also encode  parent-child path information.
                    dist[uPrime] = dist[u] + 1;
                }
                //we can remove this line since adjacents[0] is empty.
                if (uPrime!=0){
                    queue.offer(uPrime);
                }
            }
        }
        return dist[0]!=Integer.MAX_VALUE;
    }

    boolean findPathDFS(int u){
        for (int v: adjacents.get(u)){
            int uPrime = pairV[v];
            if (uPrime ==0){
                pairV[v]=u;
                pairU[u]=v;
                return true;
            }
            if (dist[uPrime] == dist[u]+1) {
                if (findPathDFS(uPrime)) {
                    pairV[v]=u;
                    pairU[u]=v;
                    return true;
                }
            }
        }
        //no augument path from u
        dist[u] = Integer.MAX_VALUE;
        return false;
    }

}
