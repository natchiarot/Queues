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
    private Map<LocalDateTime, String> arrivalTimes;  // Using a map to store each animals arrival datetime.

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

    //Enqueue
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
        arrivalTimes.put(arrivalTime, value);
    }

    //deQueue
    public String deQueueAny(){
        if (isEmpty()){
            System.out.println("The queue is empty");
            return null;
        } else {
            LocalDateTime earliest = null;
            String result = null;
            boolean validEntry = false;

            for (Entry<LocalDateTime, String> entry : arrivalTimes.entrySet()) {
                if (!entry.getValue().equals("Dog") && !entry.getValue().equals("Cat")) {
                    System.out.println("Value(s) that have been entered are neither Dogs nor Cats");
                    break;
                }
                validEntry = true;
                if (earliest == null || entry.getKey().isBefore(earliest)) {
                    earliest = entry.getKey();
                    result = entry.getValue();
                }
            }

            if (validEntry) {
                for (int i = beginningOfQueue; i <= backOfQueue; i++) {
                    if (arr[i].equals(result)) {
                        arr[i] = null;
                        break;
                    }
                }

                arrivalTimes.remove(earliest);
                beginningOfQueue++;
                if (beginningOfQueue > backOfQueue) {
                    beginningOfQueue = backOfQueue = -1;
                }
                nItems--;
            }
            if (validEntry) {
                System.out.println("DeQueueAny: Successfully adopted " + result + " " + earliest);
            }
            return result;
        }
    }

    public String deQueueDog(){
        if (isEmpty()){
            System.out.println("The queue is empty");
            return null;
        } else {
            LocalDateTime earliestTime = null;
            String earliestDog = null;

            for (Entry<LocalDateTime, String> entry : arrivalTimes.entrySet()) {
                if (entry.getValue().equals("Dog")) {
                    if (earliestTime == null || entry.getKey().isBefore(earliestTime)) {
                        earliestTime = entry.getKey();
                        earliestDog = entry.getValue();
                    }
                }
            }

            if (earliestDog != null) {
                for (int i = beginningOfQueue; i <= backOfQueue; i++) {
                    if (arr[i].equals(earliestDog)) {
                        arr[i] = null;
                        break;
                    }
                }
            }

            arrivalTimes.remove(earliestTime);
            beginningOfQueue++;
            if (beginningOfQueue > backOfQueue){
                beginningOfQueue = backOfQueue = -1;
            }
            nItems--;
            if (earliestDog != null) {
                System.out.println("Successfully adopted " + earliestDog + " " + earliestTime);
            } else {
                System.out.println("No cats available for adoption.");
            }
            return earliestDog;
        }
    }

    public String deQueueCat(){
        if (isEmpty()){
            System.out.println("The queue is empty");
            return null;
        } else {
            LocalDateTime earliestTime = null;
            String earliestCat = null;

            for (Entry<LocalDateTime, String> entry : arrivalTimes.entrySet()) {
                if (entry.getValue().equals("Cat")) {
                    if (earliestTime == null || entry.getKey().isBefore(earliestTime)) {
                        earliestTime = entry.getKey();
                        earliestCat = entry.getValue();
                    }
                }
            }

            if (earliestCat != null) {
                for (int i = beginningOfQueue; i <= backOfQueue; i++) {
                    if (arr[i].equals(earliestCat)) {
                        arr[i] = null;
                        break;
                    }
                }
            }

            arrivalTimes.remove(earliestTime);
            beginningOfQueue++;
            if (beginningOfQueue > backOfQueue){
                beginningOfQueue = backOfQueue = -1;
            }
            nItems--;
            if (earliestCat != null) {
            System.out.println("Successfully adopted " + earliestCat + " " + earliestTime);
            } else {
                System.out.println("No cats available for adoption.");
            }
            return earliestCat;
        }
    }

    // Peek
    public String peek(){
        if (!isEmpty()){
            LocalDateTime earliest = null;
            String value = arr[beginningOfQueue];
            for (Entry<LocalDateTime, String> entry : arrivalTimes.entrySet()) {
                if (entry.getValue().equals(value)) {
                    earliest = entry.getKey();
                }
            }
            return "Peek: " + value + " " + earliest;


        } else {
            System.out.println("The Queue is empty");
            return null;
        }
    }

    //delete
    public void deleteQueue(){
        arr = null;
        backOfQueue = -1;
        beginningOfQueue = -1;
        nItems = 0;
        arrivalTimes.clear();
        System.out.println("The queue is successfully deleted");
    }

    @Override
    public String toString() {
        return "QueueArray{" +
                "arr=" + Arrays.toString(arr) +
                ", backOfQueue=" + backOfQueue +
                ", nItems=" + nItems +
                ", beginningOfQueue=" + beginningOfQueue +
                ", arrivalTimes=" + arrivalTimes +
                '}';
    }
}
