import java.util.*;

public class Others {
    public class point{
        int start;
        int end;
    };
    Map<Integer,Integer> map = new HashMap<>();

    /**
     * leetcode 241
     * @param input
     * @return
     */
    public List<Integer> diffWaysToCompute(String input){
        int n = input.length();
        List<Integer> ret = new ArrayList<>();
        for(int i=0;i<n;i++){
            char c = input.charAt(i);
            if(c == '+'|| c == '-' ||c =='*'){
                List<Integer> left = diffWaysToCompute(input.substring(0,i));
                List<Integer> right = diffWaysToCompute(input.substring(i+1));
                for(int l:left){
                    for(int r:right){
                        if(c=='+'){
                            ret.add(l+r);
                        }
                        if(c=='-'){
                            ret.add(l-r);
                        }
                        if(c=='*'){
                            ret.add(l*r);
                        }
                    }
                }
            }

        }
        if(ret.size()==0){
            ret.add(Integer.valueOf(input));
        }
        return ret;
    }
}
