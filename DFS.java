import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
leetcode 40
 */
public class DFS {
    //实际上是标准的回溯
    public List<List<Integer>> combationSum2(int[] c,int target){
        List<List<Integer>> res = new ArrayList<>();
        if(c==null || c.length==0) return res;
        Arrays.sort(c);
        dfs(c,target,0,res,new ArrayList<Integer>());
        return res;
    }
    public void dfs(int[] c,int target,int pos,List<List<Integer>> res,
            List<Integer> list){
        if(target < 0) return ;
        if(target == 0){//先进行达到目标或溢出目标的操作
            res.add(new ArrayList<Integer>(list));
            return ;
        }
        for(int i=pos;i<c.length;i++){
            if(i!=pos && c[i] == c[i-1]){
                continue;
            }//跳过重复情况
            list.add(c[i]);//添加 or 标记
            dfs(c,target-c[i],i+1,res,list);//递归
            list.remove(list.size()-1);//删除 or 抹除
        }
    }
}
