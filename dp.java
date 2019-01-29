import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class dp {
    /*
    leetcode 309
     */
    public int maxProfit(int[] prices){
        int len = prices.length;
        if(len<=1) return 0;
//        int[] buy = new int[len];
//        int[] sell = new int[len];
        int s0=0,s1=0,s2=0;
        int b0=-prices[0],b1=-prices[0];
        for(int i=1;i<len;i++){
            b0 = Math.max(b1, s2-prices[i]);
            s0 = Math.max(b1+prices[i],s1);
        }
        return s0;
    }
    /*
    leetcode 464
    记录路径的回溯，非常复杂
     */
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int[] visited = new int[maxChoosableInteger + 1];
        if (desiredTotal < 2 || maxChoosableInteger >= desiredTotal) {
            return true;
        }
        int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (sum < desiredTotal) {
            return false;
        }
        if (sum == desiredTotal) {
            return maxChoosableInteger % 2 == 1;
        }
        return canIWinFrom(maxChoosableInteger, desiredTotal, visited, new HashMap<>());
    }

    private boolean canIWinFrom(int maxChoosableInteger, int desiredTotal, int[] visited, Map<String, Boolean> map) {
        if(desiredTotal <= 0){
            return false;
        }
        String strVisited  = Arrays.toString(visited);
        if(map.containsKey(desiredTotal)){
            return map.get(desiredTotal);
        }
        else{
            for(int i=1;i<=maxChoosableInteger;i++){
                if(visited[i] == 1){
                    continue;
                }
                else {
                    visited[i] = 1;
                    if (!canIWinFrom(maxChoosableInteger, desiredTotal - i, visited, map)) {
                        map.put(strVisited, true);
                    }
                    return true;
                }
            }
            map.put(strVisited,false);
            return false;
        }

//        if (desiredTotal <= 0) {
//            return false;
//        }
//        String strVisited = Arrays.toString(visited);
//        if (map.containsKey(strVisited)) {
//            return map.get(strVisited);
//        }
//        for (int i = 1; i <= maxChoosableInteger; i++) {
//            if (visited[i] == 1) {
//                continue;
//            }
//            visited[i] = 1;
//            if (!canIWinFrom(maxChoosableInteger, desiredTotal - i, visited, map)) {
//                visited[i] = 0;
//                map.put(strVisited, true);
//                return true;
//            }
//            visited[i] = 0;
//        }
//        map.put(strVisited, false);
//        return false;
    }
    /*
    leetcode 583
    最长公共子序列，使用二维dp数组
     */
    public int minDistance(String word1,String word2){
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for(int i=0;i<=word1.length();i++){
            for(int j=0;j<=word2.length();j++){
                if(i==0 || j==0) dp[i][j] = 0;
                else dp[i][j] = word1.charAt(i) == word2.charAt(j)?
                        dp[i-1][j-1]+1 : Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        int val =  dp[word1.length()][word2.length()];
        return word1.length() - val + word2.length() - val;
    }
}
