// Problem Link : https://leetcode.com/problems/find-median-from-data-stream/description/

class MedianFinder {

    // Max heap stores the smaller half of numbers
    PriorityQueue<Integer> maxHeap;

    // Min heap stores the larger half of numbers
    PriorityQueue<Integer> minHeap;

    // Stores the current median
    double median;

    // balance = size(maxHeap) - size(minHeap)
    int balance;

    public MedianFinder() {
        // Max heap: largest element on top
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Min heap: smallest element on top
        minHeap = new PriorityQueue<>();

        median = 0;
        balance = 0;
    }

    // Insert into max heap and update balance
    private void pushIntoMaxHeap(int x) {
        maxHeap.offer(x);
        balance++;
    }

    // Remove from max heap and update balance
    private int pollFromMaxHeap() {
        int x = maxHeap.poll();
        balance--;
        return x;
    }

    // Insert into min heap and update balance
    private void pushIntoMinHeap(int x) {
        minHeap.offer(x);
        balance--;
    }

    // Remove from min heap and update balance
    private int pollFromMinHeap() {
        int x = minHeap.poll();
        balance++;
        return x;
    }

    public void addNum(int num) {
        // Decide heap based on current median
        if (num <= median) {
            pushIntoMaxHeap(num);
        } else {
            pushIntoMinHeap(num);
        }

        // Ensure heaps remain balanced
        performBalancing();

        // Recalculate median
        updateMedian();
    }

    // Keeps balance in range [0, 1]
    private void performBalancing() {
        if (balance == -1) {
            // Min heap has more elements
            int x = pollFromMinHeap();
            pushIntoMaxHeap(x);
        } else if (balance == 2) {
            // Max heap has too many elements
            int x = pollFromMaxHeap();
            pushIntoMinHeap(x);
        }
    }

    // Updates median based on balance
    private void updateMedian() {
        if (balance == 0) {
            median = (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            median = maxHeap.peek();
        }
    }

    public double findMedian() {
        return median;
    }
}
