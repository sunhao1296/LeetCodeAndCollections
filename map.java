import java.util.HashMap;
import java.util.Map;

public class map {
    /*
    leetcode 535
    两个map即可，注意转换使用的是Integer，相当于62进制
     */
    Map<Integer,String> map1 = new HashMap<>();
    Map<String,Integer> map2 = new HashMap<>();
    String s = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public String encode1(String longURL){
        if(!map2.containsKey(longURL)){
            map1.put(map1.size()+1,longURL);
            map2.put(longURL,map1.size()+1);
        }
        int n = map2.get(longURL);
        StringBuilder sb = new StringBuilder();
        while(n>0){
            int index = n%62;
            n /= 62;
            sb.insert(0,s.charAt(index));
        }
        return sb.toString();
    }
    public String decode1(String shortURL){
        int val = 0;
        int n = shortURL.length();
        for(int i=0;i<n;i++){
            val = val*62 + s.indexOf(shortURL.charAt(i));
        }
        return map1.get(val);
    }
}
