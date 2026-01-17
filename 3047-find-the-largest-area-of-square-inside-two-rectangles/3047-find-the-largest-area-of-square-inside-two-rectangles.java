class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        long bestSide = 0;

        for (int i = 0; i < n; i++) {
            int ax1 = bottomLeft[i][0], ay1 = bottomLeft[i][1];
            int ax2 = topRight[i][0],   ay2 = topRight[i][1];

            for (int j = i + 1; j < n; j++) {
                int bx1 = bottomLeft[j][0], by1 = bottomLeft[j][1];
                int bx2 = topRight[j][0],   by2 = topRight[j][1];

                int xLeft  = Math.max(ax1, bx1);
                int yBot   = Math.max(ay1, by1);
                int xRight = Math.min(ax2, bx2);
                int yTop   = Math.min(ay2, by2);

                int w = xRight - xLeft;
                int h = yTop - yBot;

                if (w > 0 && h > 0) {
                    long side = Math.min(w, h);
                    if (side > bestSide) bestSide = side;
                }
            }
        }

        return bestSide * bestSide;
    }
}