class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long sumAbs = 0;
        int negCount = 0;
        long minAbs = Long.MAX_VALUE;

        for (int[] row : matrix) {
            for (int x : row) {
                if (x < 0) negCount++;
                long ax = Math.abs((long) x);
                sumAbs += ax;
                if (ax < minAbs) minAbs = ax;
            }
        }

        if (negCount % 2 == 0) return sumAbs;
        return sumAbs - 2 * minAbs;

    }
}