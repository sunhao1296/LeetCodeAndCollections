public class Deque
{
    int count;
    int front,tail;
    int[] queue;
    public Deque(int k){
        queue = new int[k];
        front = -1;
        tail = -1;
        count = 0;
    }
    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        front = (front - 1 + queue.length) % queue.length;
//        front = (front - 1 + queue.length) % queue.length;
        if (isEmpty()) {
            tail = front;
        }//注意空的时候tail总随着front动
        queue[front] = value;
        count++;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        tail = (tail + 1) % queue.length;
//        tail = tail + 1;
//        tail %= queue.length;
        if (isEmpty()) {
            front = tail;
        }
        queue[tail] = value;
        count++;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % queue.length;
        //根本就不用改变值，只需要把front的指向+1即可。front=tail即为空/满
        count--;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        tail = (tail - 1 + queue.length) % queue.length;
        //删除和增加的情况正好相反
        count--;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        return isEmpty() ? -1 : queue[front];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        return isEmpty() ? -1 : queue[tail];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return count == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return count == queue.length;
    }
}
