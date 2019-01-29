public class backTrace {
    //N皇后问题
    int Sum,N;
    int[] x = new int[N];//col,x[col]存储col这个点
    public int NQueen(int n){
        Sum = 0;
        N = n;
        backTrace(1);
        return Sum;
    }
    public boolean place(int col){
        for(int i=1;i<col;i++){
            if(Math.abs(col - i)==Math.abs(x[col]-x[i])||x[col]==x[i]){
                return false;
            }
        }
        return true;
    }
    public void backTrace(int t){
        if(t>N){
            Sum++;
        }else{
            for(int i=1;i<=N;i++){
                x[t] = i;
                if(place(t)){
                    backTrace(t+1);
                }
            }
        }
    }
    //求数字分段相乘结果最大值
    int num=0;
    int K=0;
    long ans = 0;
    public void work(int cur,int i,int k,long v){
        if(i==k){
            ans = Math.max(ans,v);
        }
        if(cur == 0){
            return;
        }
        int MOD = 1;
        while(cur/MOD!=0){
            work(cur%MOD,i+1,k,v*(cur/MOD));
            MOD*=10;
        }
    }
    int count  = 0;
    public int countArrangement(int N){
        boolean[] visited = new boolean[N];
        calculate(N,1,visited);
        return count;
    }
    public void calculate(int N,int pos,boolean[] visited){
        if(pos>N){
            count++;
        }
        for(int i=1;i<=N;i++){
            if(!visited[i] && (pos%i==0||i%pos==0)){
                visited[i] = true;
                calculate(N,pos+1,visited);
                visited[i] = false;
            }
        }
    }

}
