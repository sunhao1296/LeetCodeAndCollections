import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Union {

    public int[] id;//对应索引所在的集
    public int[] sz;//所在集的size，合并时小集合大集
    public int count;
    public Union(int N){
        id = new int[N];
        for(int i=0;i<id.length;i++){
            id[i] = i;
        }
    }//初始化，每个节点的集都对应自己
    public boolean connected(int p,int q){
        return id[p] == id[q];
    }
    public int root(int i){
        //找到节点所在的集
        while(i!=id[i]){
            id[i] = id[id[i]];
            i = id[i];
        }
        return id[i];
        /*
        递归的路径压缩算法
        if(i!=id[i]){
            id[i] = root(id[i]);
         }
         return id[i];
         */
    }
    public void union(int p,int q){
        int i = root(p);
        int j = root(q);
        //root使每个值都找到自己的集合
        if(i==j){
            return ;
        }
        if(sz[i]<sz[j]){
            id[i] = j;
            sz[j] += sz[i];
        }else{
            id[j] = i;
            sz[i] += sz[j];
        }
        count-- ;
    }


}
