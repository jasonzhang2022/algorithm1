package practice.graph.bipartitematch;

import java.util.*;

public class BipartiteMatch {
    int m;
    int n;

    /**
     * Internally use index starting from one as index.
     */
    List<Set<Integer>> adjacents;
    public BipartiteMatch(int leftNum, int rightNum){
        this.m = leftNum;
        this.n = rightNum;
        this.adjacents = new ArrayList<>(leftNum);
        for (int i=0; i<=leftNum; i++){
            adjacents.add(new HashSet<>());
        }
    }
    public void addEdge(int u, int v){
        adjacents.get(u).add(v);
    }

    int[] pairU;
    int[] pairV;
    int[] dist ;

    // the advantage to use m, n as special value instead of 0.
    // source and target have different value instead of 0 in both cases.
    // No need to convert index i to i+1
    public int[] maxMatch(){

        pairU = new int[m];
        // valid value will be [0, n-1], -1 means unpaired.
        Arrays.fill(pairU, -1);


        pairV = new int[n];
        // valid value is [0, m-1], -1 means unpaired.
        Arrays.fill(pairV, -1);

        dist = new int[m];

        while (findAugumentingPath()){
            for (int u=0; u<m; u++){
                if (pairU[u] == -1) {
                    findPathDFS(u);
                }
            }
        }

        return pairU;
    }

    /**
     * How to find augumenting path
     * 1. Start from free vertex at right.
     * 2. alternating path.
     * 3. end with free vextex at left.
     *
     * @return whether there is at least one path
     */
    boolean findAugumentingPath(){

        // dist serves as two purpose.
        // 1. whether u is vertex in one augumenting path
        // 2. if it is (!=-1), what level it is from first node.
        // 3. whether it is visited
        Queue<Integer> potentialUs = new LinkedList<>();
        for (int u=0; u<m; u++) {
            if (pairU[u] == -1) {
                dist[u] = 0;
                potentialUs.offer(u);
            } else {
                dist[u] = -1;
            }
        }

        boolean reachedTarget= false;

        while (!potentialUs.isEmpty()) {
            int u = potentialUs.poll();

            for (int v: adjacents.get(u)){
                int uprimer = pairV[v];
                if (uprimer ==-1) {
                    reachedTarget = true;
                } else if (dist[uprimer] == -1) {
                    // not visited;
                    dist[uprimer] = dist[u]+1;
                    potentialUs.offer(uprimer);
                }
            }
        }
        return reachedTarget;
    }

    boolean findPathDFS(int u){
        for (int v: adjacents.get(u)){
            int uprimer = pairV[v];
            if (uprimer ==-1) {
                // reach free vertex

                pairU[u]= v;
                pairV[v] = u;
                dist[u] = -1;
                return true;
            } else if (dist[uprimer] == dist[u]+1) {
                if (findPathDFS(uprimer)){
                    pairU[u]= v;
                    pairV[v] = u;
                    dist[u] = -1;
                    return true;
                }
            }
        }
        return false;
    }

}
