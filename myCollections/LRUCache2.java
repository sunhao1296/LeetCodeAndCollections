package myCollections;

import java.util.HashMap;

public class LRUCache2 {
    /*
    整体采用map+双向链表模拟
     */
    class LRUNode{
        int key;
        int value;
        LRUNode prev;
        LRUNode next;
        public LRUNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    private HashMap<Integer,LRUNode> map;
    private int capacity;
    private LRUNode head;
    private LRUNode tail;
    public void put(int key,int value){
        LRUNode node = map.get(key);
        if(node!=null){
            node.value = value;
            remove(node,false);//false是让这一node在链表中被删除（然后再作为头部），实际上是调整位置，true则在链表和map中均删除
        }
        else{
            node = new LRUNode(key,value);
            if(map.size()>=capacity)
                remove(tail,true);
            map.put(key,node);
        }
        setHead(node);
    }
    public int get(int key){
        LRUNode node = map.get(key);
        if (node != null) {
            // 将刚操作的元素放到head，因为这一操作虽然没有修改但是访问了，也要把这个node置于头部
            remove(node, false);
            setHead(node);
            return node.value;
        }
        return -1;
    }
    private void setHead(LRUNode node){
        if(head!=null){
            node.next = head;
            head.prev = node;
        }
        head = node;
        if(tail == null)
            tail = node;
    }
    public void remove(LRUNode node,boolean flag){
        if(node.prev!=null)
            node.prev.next = node.next;
        if(node.next!=null)
            node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
        if(flag)
            map.remove(node.key);
    }
    public LRUCache2(int capacity){
        this.capacity = capacity;
        this.map = new HashMap<>();
    }
}
