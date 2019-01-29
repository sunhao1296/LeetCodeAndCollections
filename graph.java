import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class graph {
    /**
     * bfs
     * @param list
     * @param x
     * @return
     */
    public int check(ArrayList<ArrayList> list , int x) {
        int sum = 0;
        Queue<ArrayList> queue = new LinkedList();
        queue.add(list.get(x - 1));
        while (!queue.isEmpty()) {
            List list1 = queue.poll();
            sum += (int) list1.get(1);
            List inner = ((List) list1.get(2));
            int size = inner.size();
            for (int i = 0; i < size; i++) {
                queue.add(list.get((Integer) inner.get(i) - 1));
            }
        }
        return sum;
    }

    /**
     * leetcode 695
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid){
        int maxArea = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    maxArea = Math.max(maxArea,dfs(grid,i,j));
                }
            }
        }
        return maxArea;
    }
    public int dfs(int[][] grid,int i,int j){
        int gridLen = grid.length;
        int gridWid = grid[0].length;
        if(i<0||i>gridLen||j<0||j>gridWid){
            return 0;
        }
        if(grid[i][j]==0){
            return 0;
        }
        return dfs(grid,i-i,j)+dfs(grid,i+1,j)+dfs(grid,i,j-1)+dfs(grid,i,j+1);
    }

    /**
     * leetcode 93
     */
    private List<String> ret;
    public List<String> restoreIpAddress(String s){
        ret = new ArrayList<>();
        doRestore(0,"",s);
        return ret;
    }
    public void doRestore(int k,String path,String s){
        if(k==4||s.length()==0){
            if(k==4&&s.length()==0){
                ret.add(path);
            }
            return;
        }
        for(int i=0;i<s.length()&&i<=2;i++){
            if(i!=0&&s.charAt(0)=='0'){
                break;
            }
            String part = s.substring(0,i+1);
            if (Integer.valueOf(part) <= 255) {
                doRestore(k + 1, path.length() != 0 ? path + "." + part : part, s.substring(i + 1));
            }
        }
    }
}
