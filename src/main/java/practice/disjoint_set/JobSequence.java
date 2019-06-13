package practice.disjoint_set;

//https://www.geeksforgeeks.org/job-sequencing-problem-set-1-greedy-algorithm/

import com.google.common.collect.Ordering;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/*
 Each task only has a unit of time.
 All tasks must finish no matter how big the penalty. So we have n timeslot.
 */
public class JobSequence {

    public static class Task {
        int priority;
        int deadline;

        public int getPriority(){
            return priority;
        }

        public Task(int dealine, int priority){
            this.deadline = dealine;
            this.priority = priority;
        }
    }


    public int greedy(Task[] jobs) {
        Comparator<Task> comparator= Ordering.natural().reverse().onResultOf(Task::getPriority);
        Arrays.sort(jobs, comparator);
        int len = jobs.length;

        Task[] results = new Task[len];

        for (int i=0; i<len; i++) {
            int targetDealine = Math.min(len, jobs[i].deadline)-1;
            for (int j=targetDealine; j>=0; j--) {
                if (results[j]==null) {
                    results[j] = jobs[i];
                    break;
                }
            }
        }
        int priority = 0;
        for (Task job: results) {
            if (job!=null) {
                priority += job.priority;
            }
        }
        return priority;
    }


    public int schedule(JobSequence.Task[] jobs) {
        int n = jobs.length+1;
        int[] tree = new int[n];
        for (int i=0; i<n; i++){
            tree[i] = i;
        }
        Arrays.sort(jobs, Comparator.comparing(JobSequence.Task::getPriority).reversed());

        List<Task> scheduled = new LinkedList<>();
        for (JobSequence.Task job: jobs){
            //target set is never to be zero
            int targetSet = Math.min(job.deadline, n-1);
            targetSet = findRoot(tree, targetSet);
            if (targetSet!=0){
                // not valid set. These job will be scheduled after its dealine.
                scheduled.add(job);
                mergetoFront(tree, targetSet);
            }
        }
        return scheduled.stream().mapToInt(JobSequence.Task::getPriority).sum();


    }

    int findRoot(int[] tree, int setId){

        int parent = setId;
        while (parent!=tree[parent]){
            parent = tree[parent];
        }
        tree[setId]= parent;
        return parent;
    }

    void mergetoFront(int[] tree, int parentId){
        int frontId = findRoot(tree, parentId-1);
        tree[parentId]=frontId;
    }

    public static class Test {
        @org.junit.Test
        public void test() {

            int[] dealines = { 4, 2, 4, 3, 1, 4, 6 };
            int[] penalties = { 70, 60, 50, 40, 30, 20, 10 };
            Task[] tasks = new Task[dealines.length];
            for (int i = 0; i < dealines.length; i++) {
                tasks[i] = new Task(dealines[i], penalties[i]);
            }

            int expectedPenalty = 50;
            int calcultedPenalty = new JobSequence().schedule(tasks);
            calcultedPenalty = Arrays.stream(penalties).sum() - calcultedPenalty;
            assertThat(expectedPenalty, equalTo(calcultedPenalty));
        }

        @org.junit.Test
        public void testSimple() {

            int[] dealines = {1, 2,2};
            int[] penalties = { 1,2,3};
            Task[] tasks = new Task[dealines.length];
            for (int i = 0; i < dealines.length; i++) {
                tasks[i] = new Task(dealines[i], penalties[i]);
            }

            int expectedPenalty = 1;
            int calcultedPenalty = new JobSequence().schedule(tasks);
            calcultedPenalty = Arrays.stream(penalties).sum() - calcultedPenalty;
            assertThat(expectedPenalty, equalTo(calcultedPenalty));
        }

        /**
         * The logic is not correct. This test can't pass.
         * Since we don't have a logic to fill the hole left before.
         */
        @org.junit.Test
        public void testSimple2() {

            int[] dealines = {2,2};
            int[] penalties = {2,3};
            Task[] tasks = new Task[dealines.length];
            for (int i = 0; i < dealines.length; i++) {
                tasks[i] = new Task(dealines[i], penalties[i]);
            }

            int expectedPenalty = 0;
            int calcultedPenalty = new JobSequence().schedule(tasks);
            calcultedPenalty = Arrays.stream(penalties).sum() - calcultedPenalty;
            assertThat(expectedPenalty, equalTo(calcultedPenalty));

        }

        /**
         * The logic is not correct. This test can't pass.
         * Since we don't have a logic to fill the hole left before.
         */
        @org.junit.Test
        public void testBig() {

            int n=1000;
            Random rand1=new Random();
            Random rand2=new Random();
            Task[] tasks = new Task[n];
            for (int i=0; i<n; i++){
                tasks[i] = new Task(rand1.nextInt(10000), rand2.nextInt(500));
            }

            Task[] tasksClone=Arrays.copyOf(tasks, n);

            JobSequence sequencer = new JobSequence();

            int expectedPenalty = sequencer.greedy(tasks);
            int calcultedPenalty = sequencer.schedule(tasksClone);
            assertThat(expectedPenalty, equalTo(calcultedPenalty));
        }
    }

}
