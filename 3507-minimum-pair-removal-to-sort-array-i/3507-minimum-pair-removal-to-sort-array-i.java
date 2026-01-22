class Solution {
    public int minimumPairRemoval(int[] nums) {
        List<Integer> a = new ArrayList<>();
        for (int x : nums) a.add(x);

        int ops = 0;
        while (!isNonDecreasing(a)) {
            int bestIdx = 0;
            int bestSum = a.get(0) + a.get(1);

            for (int i = 1; i < a.size() - 1; i++) {
                int s = a.get(i) + a.get(i + 1);
                if (s < bestSum) {
                    bestSum = s;
                    bestIdx = i;
                }
            }

            a.set(bestIdx, bestSum);
            a.remove(bestIdx + 1);
            ops++;
        }
        return ops;
    }

    private boolean isNonDecreasing(List<Integer> a) {
        for (int i = 1; i < a.size(); i++) {
            if (a.get(i) < a.get(i - 1)) return false;
        }
        return true;
        
    }
}