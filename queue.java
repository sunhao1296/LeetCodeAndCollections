import java.util.LinkedList;
import java.util.Queue;

public class queue {
    public static int minSteps(int n) {
        int[] dp = new int[1002];

        dp[0]=0;dp[1]=1;dp[2]=2;dp[3]=3;
        if(n<=3) return dp[n];
        for(int i=4;i<=n;i++){
            int minstep = 1001;
            for(int j=1;j<i&&i%j==0;j++){
                int step = dp[j] + i/j - 1;
                if(step<minstep){
                    minstep=step;
                }
            }
            dp[i]=minstep;
        }
        return dp[n];
    }
    public static void main(){
        System.out.println(minSteps(3));
    }
}
