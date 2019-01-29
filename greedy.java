import java.util.*;

public class greedy {
    /**
     * leetcode 763
     * @param s
     * @return
     */
    public List<Integer> partitionLabels(String s){
        List<Integer> ans = new ArrayList();
        int count = 0;
        int[] lastIndex = new int[26];
        int m = 0;
        for(int i=0;i<s.length();i++){
            lastIndex[s.charAt(i)-'a'] = i;
        }
        for(int i=0;i<s.length();i++){
            count++;
            m = Math.max(m, lastIndex[s.charAt(i)-'a']);
            if(m==i){
                ans.add(count);
                count=0;
            }
        }
        return ans;
    }

    /**
     * leetcode 406
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people){
        if(people == null || people.length == 0 || people[0].length == 0){
            return new int[0][0];
        }
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]){
                    return o1[1]-o2[1];
                }
                return o2[0]-o1[0];
            }

        });
        int n = people.length;
        List<int[]> temp = new ArrayList<>();
        int i=0;
        for(i = 0; i < n; i++) {
            temp.add(people[i][1], new int[]{people[i][0],people[i][1]});
        }

        int[][] ret = new int[n][2];
        for(i=0;i<n;i++){
            ret[i][0] = temp.get(i)[0];
            ret[i][1] = temp.get(i)[1];
        }
        return ret;
    }

    /**
     * leetcode 215
     * 寻找第k大的元素
     * 堆排序方法和快速选择方法
     */

    public int findKthLargestByHeap(int[] nums, int k){
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int val:nums){
            queue.offer(val);
            if(queue.size()>k){
                queue.poll();
            }
        }
        return queue.peek();
    }
    public int quickSelect(int[] nums, int begin, int end, int k){
        int i=begin,j=end,mid=nums[(i+j)>>1];
        int temp=0;
        while (i<=j)//注意,小于等于
        {
            while (nums[i]<mid)i++;
            while (nums[j]>mid)j--;
            if (i<=j)
            {
                temp=nums[i];nums[i]=nums[j];nums[j]=temp;
                i++;j--;
            }
        }
        if (begin<j && k<=j){
            return quickSelect(nums,begin,j,k);//分治
        }
        if (i<end && k>=i){
            return quickSelect(nums,i,end,k);
        }
        return nums[k];//如果不属于任何一方,就结束,返回
    }

    /**
     * leetcode 347
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k){
        List<Integer> numsFreq = new ArrayList<>();
        Map<Integer,Integer> numsMap = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            numsMap.put(nums[i], numsMap.getOrDefault(nums[i],0)+1);
        }
        List<Integer>[] counts = new List[nums.length+1];
        for(int key:numsMap.keySet()){
            int frequency = numsMap.get(key);
            if(counts[frequency] == null){
                counts[frequency] = new ArrayList<>();
            }
            counts[frequency].add(key);
        }
        //对桶排序结果再桶排序（空间需求小的情况）
        for(int i=counts.length-1;i>=0 && numsFreq.size()<k;i--){
            if(counts[i]!=null){
                numsFreq.addAll(counts[i]);
            }
        }
        return numsFreq;
    }


}
