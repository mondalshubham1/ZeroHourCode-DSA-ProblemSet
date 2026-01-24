// Problem Link : https://leetcode.com/problems/minimum-cost-to-hire-k-workers/description/

class Solution {
    // Helper class to store wage-to-quality ratio and quality
    class Pair {
        double ratio;   // wage / quality
        int quality;

        Pair(double ratio, int quality) {
            this.ratio = ratio;
            this.quality = quality;
        }
    }

    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;

        // Array to store (ratio, quality) for each worker
        Pair[] wqRatios = new Pair[n];

        // Compute wage-to-quality ratio for every worker
        for (int i = 0; i < n; i++) {
            wqRatios[i] = new Pair(wage[i] / (quality[i] * 1.0), quality[i]);
        }

        // Sort workers by increasing ratio
        Arrays.sort(wqRatios, (a, b) -> Double.compare(a.ratio, b.ratio));

        int quality_sum = 0;

        // Max heap to keep track of largest quality among selected workers
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        // Initialize heap with first k workers (smallest ratios)
        for (int i = 0; i < k; i++) {
            pq.offer(wqRatios[i].quality);
            quality_sum += wqRatios[i].quality;
        }

        // Initial answer using the k-th worker's ratio
        double ans = wqRatios[k - 1].ratio * quality_sum;

        // Try replacing the worker with largest quality as ratio increases
        for (int i = k; i < n; i++) {

            // Remove the worker with maximum quality to minimize total quality sum
            int maxQuality = pq.poll();
            quality_sum -= maxQuality;

            // Add current worker
            pq.offer(wqRatios[i].quality);
            quality_sum += wqRatios[i].quality;

            // Update minimum cost using current ratio
            ans = Math.min(ans, wqRatios[i].ratio * quality_sum);
        }

        return ans;
    }
}
