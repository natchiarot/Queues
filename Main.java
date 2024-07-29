package Queues;

public class Main {
    public static void main(String[] args) {
        QueueArray queueArray = new QueueArray(5);

        queueArray.enQueue("Cat");
        queueArray.enQueue("Dog");
        queueArray.enQueue("Cat");
        queueArray.enQueue("Dog");
        System.out.println(queueArray.peek());
        System.out.println(queueArray);
        queueArray.deQueueAny();
        queueArray.deQueueDog();
        queueArray.deQueueCat();
        System.out.println(queueArray);
        queueArray.deleteQueue();
        System.out.println(queueArray);
    }
}
