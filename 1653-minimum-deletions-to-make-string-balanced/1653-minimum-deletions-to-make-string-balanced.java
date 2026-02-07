class Solution {
    public int minimumDeletions(String s) {
        int bCount = 0;
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'b') {
                bCount++;
            } else { // 'a'
                ans = Math.min(ans + 1, bCount);
            }
        }
        return ans;
    }
}