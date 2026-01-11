class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int rows = matrix.length, cols = matrix[0].length;
        int[] heights = new int[cols];
        int best = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == '1') heights[c] += 1;
                else heights[c] = 0;
            }
            best = Math.max(best, largestRectangleArea(heights));
        }
        return best;
    }

    private int largestRectangleArea(int[] h) {
        int n = h.length;
        Deque<Integer> st = new ArrayDeque<>();
        int max = 0;

        for (int i = 0; i <= n; i++) {
            int cur = (i == n) ? 0 : h[i];
            while (!st.isEmpty() && cur < h[st.peek()]) {
                int height = h[st.pop()];
                int leftSmallerIndex = st.isEmpty() ? -1 : st.peek();
                int width = i - leftSmallerIndex - 1;
                max = Math.max(max, height * width);
            }
            st.push(i);
        }
        return max;
        
    }
}