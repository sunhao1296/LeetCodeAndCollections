import java.util.Arrays;

public class search {
    /**
     * 这个是用自带的二分查找实现的，调用前先排序
     * @param array
     * @param key
     * @return
     */
    public int binarysearch(int[] array,int key){
        Arrays.sort(array);
        return Arrays.binarySearch(array,key);
    }
    /**
     * 自己实现
     */
    public int binarySearch(int[] array,int key){
        int startPos = 0,endPos = array.length - 1;
        int mid = array.length/2;
        while(startPos < endPos){
            mid = (startPos + endPos - 1)/2 +1;
            if(key == array[mid]){ return mid;}
            if(key < array[mid]){
                endPos = mid - 1;
            }
            else {
                startPos = mid + 1;
            }
        }
        return -1;
    }

    /**
     * leetcode 540
     * @param array
     * @return
     */
    public int singleNonDuplicate(int[] array){
        int mid = array.length/2;
        int startPos = 0,endPos = array.length - 1;
        while(startPos < endPos){
            mid = (startPos + endPos - 1)/2 +1;
            if(mid%2==0) {mid--;}
            if(array[mid] == array[mid + 1]) {startPos = mid + 2;}
            else {endPos = mid;}
        }
        return array[startPos];
    }
}
