// Problem Link : https://leetcode.com/problems/task-scheduler/description/

class Solution {
    class Pair {
    int first;
    int second;
        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }   

    public int leastInterval(char[] tasks, int n) {
        
        int[] charFreq = new int[26];

        for(char c : tasks) {
            int index = c-'A';
            charFreq[index]++;
        }

        int t=1;

        // At every time t, we'll choose the task that needs to be done most number of times.
        // At any point of time, this Priority Queue will hold only those tasks, that are allowed at that time t.
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        // This Queue will temporarily hold the tasks that are in cool-off period, at the correct time,
        // those tasks will be pushed back to the Priority Queue.
        Queue<Pair> q = new LinkedList<>();

        for(int x : charFreq) {
            if(x > 0)
                pq.offer(x);
        }

        while(!pq.isEmpty() || q.size() != 0) {
            if(q.size() > 0) {
                Pair front = q.peek();
                if(front.second == t) {
                    pq.offer(q.poll().first);
                }
            }
            if(!pq.isEmpty()) {
                int x = pq.poll();
                if(x>1)
                    q.offer(new Pair(x-1, t+n+1));
            }
            t++;     
        }
        return t-1;
    }
}
