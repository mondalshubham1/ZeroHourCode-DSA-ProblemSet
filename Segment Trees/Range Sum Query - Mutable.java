// Problem Link : https://leetcode.com/problems/range-sum-query-mutable/description/

class NumArray {

    // Array to store the Segment Tree
    // Size = 4*n to safely store all nodes
    int[] segmentTree;

    // Size of original array
    int n;

    // Constructor: builds the segment tree from input array
    public NumArray(int[] nums) {
        n = nums.length;

        // Allocate segment tree array
        segmentTree = new int[4 * n];

        // Build the tree starting from root index = 0
        // Range = [0, n-1]
        buildSegmentTree(nums, 0, 0, n - 1);
    }

    // Builds the segment tree recursively
    // idx = current node index in segmentTree[]
    // i = start index of current range
    // j = end index of current range
    private void buildSegmentTree(int[] nums, int idx, int i, int j) {

        // Base case: leaf node (single element)
        if (i == j) {
            segmentTree[idx] = nums[i];
            return;
        }

        // Find middle of range
        int mid = (i + j) / 2;

        // Build left child (2*idx+1)
        buildSegmentTree(nums, 2 * idx + 1, i, mid);

        // Build right child (2*idx+2)
        buildSegmentTree(nums, 2 * idx + 2, mid + 1, j);

        // Internal node stores sum of left and right child
        segmentTree[idx] = segmentTree[2 * idx + 1] + segmentTree[2 * idx + 2];
    }

    // Updates value at a given index in the original array
    public void update(int index, int val) {
        updateHelper(index, val, 0, 0, n - 1);
    }

    // Helper function to update segment tree
    // index = position to update
    // val = new value
    // idx = current segment tree node
    // i, j = range represented by this node
    private void updateHelper(int index, int val, int idx, int i, int j) {

        // If we reached the leaf node
        if (i == j) {
            segmentTree[idx] = val;
            return;
        }

        int mid = (i + j) / 2;

        // Decide whether to go left or right
        if (index <= mid) {
            updateHelper(index, val, 2 * idx + 1, i, mid);
        } else {
            updateHelper(index, val, 2 * idx + 2, mid + 1, j);
        }

        // Update current node after child is updated
        segmentTree[idx] = segmentTree[2 * idx + 1] + segmentTree[2 * idx + 2];
    }

    // Returns sum of elements in range [left, right]
    public int sumRange(int left, int right) {
        return sumRangeHelper(0, 0, n - 1, left, right);
    }

    // Helper function for range sum query
    // idx = current node index
    // i, j = range represented by this node
    // left, right = query range
    private int sumRangeHelper(int idx, int i, int j, int left, int right) {

        // If current range is completely outside query range
        if (left > j || right < i) {
            return 0;
        }

        // If current range is completely inside query range
        if (i >= left && j <= right) {
            return segmentTree[idx];
        }

        // Partial overlap: split into left and right children
        int mid = (i + j) / 2;

        return sumRangeHelper(2 * idx + 1, i, mid, left, right)
             + sumRangeHelper(2 * idx + 2, mid + 1, j, left, right);
    }
}

/**
 * Usage:
 * NumArray obj = new NumArray(nums);
 * obj.update(index, val);
 * int ans = obj.sumRange(left, right);
 */
