class Solution {
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0, best = 0;

        for (int right = 0; right < fruits.length; right++) {
            freq.put(fruits[right], freq.getOrDefault(fruits[right], 0) + 1);

            while (freq.size() > 2) {
                int f = fruits[left];
                freq.put(f, freq.get(f) - 1);
                if (freq.get(f) == 0) freq.remove(f);
                left++;
            }

            best = Math.max(best, right - left + 1);
        }
        return best;
        
    }
}