class Solution {
    public double separateSquares(int[][] squares) {
        int n = squares.length;

        long minY = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;

        double total = 0.0;
        for (int[] s : squares) {
            long y = s[1];
            long l = s[2];
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y + l);
            total += (double) l * (double) l;
        }

        double half = total / 2.0;
        double lo = (double) minY;
        double hi = (double) maxY;

        for (int it = 0; it < 70; it++) { 
            double mid = lo + (hi - lo) / 2.0;
            if (areaBelow(squares, mid) < half) {
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return hi;
    }

    private double areaBelow(int[][] squares, double Y) {
        double sum = 0.0;

        for (int[] s : squares) {
            double y = s[1];
            double l = s[2];
            double top = y + l;

            if (Y <= y) {
                // adds 0
            } else if (Y >= top) {
                sum += l * l;
            } else {
                sum += (Y - y) * l;
            }
        }

        return sum;
        
    }
}