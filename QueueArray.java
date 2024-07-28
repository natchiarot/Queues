package Queues;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;

public class QueueArray {
    private String [] arr;
    private int backOfQueue;
    private int nItems;
    int beginningOfQueue;
    private Map<String, LocalDateTime> arrivalTimes;  // Using a map to store each animals arrival date.

    public QueueArray(int size) {
        this.arr = new String[size];
        this.backOfQueue = -1;
        this.beginningOfQueue = -1;
        this.nItems = 0;
        this.arrivalTimes = new HashMap<>();
        System.out.println("The Queue is successfully created wih size of: " + size);
    }

    //isFull
    public boolean isFull(){
        if(backOfQueue == arr.length-1){
            return true;
        } else {
            return false;
        }
    }

    //isEmpty
    public boolean isEmpty(){
       return (nItems == 0);
    }

    //Enqueue - add to back of queue.
    public void enQueue (String value){
        if (isFull()){
            System.out.println("The Queue is Full");
            return;
        }
        if (isEmpty()) {
            beginningOfQueue = 0;
            backOfQueue++;
            nItems++;
            arr[backOfQueue] = value;
            System.out.println("Successfully inserted " + value + " in the queue");
        } else {
            backOfQueue++;
            nItems++;
            arr[backOfQueue] = value;
            System.out.println("Successfully inserted " + value + " in the queue");
        }
        // Made the decision to make this do the exact time it was added so that it would go
        // along with the rules of a queue.
        LocalDateTime arrivalTime = LocalDateTime.now();
        System.out.println(arrivalTime);
        arrivalTimes.put(value, arrivalTime);
    }

    //deQueue - remove from front of queue.
    public String deQueueAny(){
        if (isEmpty()){
            System.out.println("The queue is empty");
            return null;
        } else {
            LocalDateTime earliest = null;
            String result = null;

            for (Entry<String, LocalDateTime> entry : arrivalTimes.entrySet()) {
                if (earliest == null || entry.getValue().isBefore(earliest)) {
                    earliest = entry.getValue();
                    result = entry.getKey();
                }
            }
            System.out.println(earliest + result);
            arrivalTimes.remove(result);
            beginningOfQueue++;
            if (beginningOfQueue > backOfQueue){
                beginningOfQueue = backOfQueue = -1;
            }
            nItems--;
            return result;
        }
    }

    public String deQueueDog(String value){
        if (isEmpty()){
            System.out.println("The queue is empty");
            return null;
        } else {
//            String result = arr[beginningOfQueue];
            beginningOfQueue++;
            if (beginningOfQueue > backOfQueue){
                beginningOfQueue = backOfQueue = -1;
            }
            nItems--;
            return value;
        }
    }

    public String deQueueCat(String value){
        if (isEmpty()){
            System.out.println("The queue is empty");
            return null;
        } else {
            String result = arr[beginningOfQueue];
            arrivalTimes.remove(result);
            beginningOfQueue++;
            if (beginningOfQueue > backOfQueue){
                beginningOfQueue = backOfQueue = -1;
            }
            nItems--;
            return result;
        }
    }

    public LocalDateTime getArrivalTime(String value) {
        return arrivalTimes.get(value);
    }

    // Peek
    public String peek(){
        if (!isEmpty()){
            return arr[beginningOfQueue];

        } else {
            System.out.println("The Queue is empty");
            return null;
        }
    }

    //delete
    public void deleteQueue(){
        arr = null;
//        arrivalTimes.clear();
        System.out.println("The queue is successfully deleted");
    }

    @Override
    public String toString() {
        return "backOfQueue=" + backOfQueue +
                ", nItems=" + nItems +
                ", beginningOfQueue=" + beginningOfQueue +
                ", arrivalTimes=" + arrivalTimes +
                '}';
    }

}
