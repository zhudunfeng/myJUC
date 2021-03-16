package com.adun.test_blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列核心方法练习
 * 抛出异常
 * add()
 * remove()
 * element()
 *
 *
 *
 * 插入操作
 * （1）public abstract boolean add(E paramE)：将指定的元素插入队列中，在成功时返回true，如果当前没有可用的空间，则抛出IllegalStateException。如果该元素是null，则抛出NullPointerException异常。
 *
 * （2）public abstract boolean offer(E paramE)：将指定的元素插入队列中，在成功时返回true，如果当前没有可用的空间，则返回false。
 *
 * （3）offer(E o, long timeout, TimeUnit unit)：将指定的元素插入队列中，可以设定等待的时间，如果在设定的等待时间内仍不能向队列中加入元素，则返回false。
 *
 * （4）public abstract void put(E paramE) throwsInterruptedException：将指定的元素插入队列中，如果队列已经满了，则阻塞、等待可用的队列空间的释放，直到有可用的队列空间释放且插入成功为止。
 *
 * 获取数据操作
 * （1）poll()：取走队列队首的对象，如果取不到数据，则返回null。
 *
 * （2）poll(long timeout, TimeUnit unit)：取走队列队首的对象，如果在指定的时间内队列有数据可取，则返回队列中的数据，否则等待一定时间，在等待超时并且没有数据可取时，返回null。
 *
 * （3）take()：取走队列队首的对象，如果队列为空，则进入阻塞状态等待，直到队列有新的数据被加入，再及时取出新加入的数据。
 *
 * （4）drainTo(Collection collection)：一次性从队列中批量获取所有可用的数据对象，同时可以指定获取数据的个数，通过该方法可以提升获取数据的效率，避免多次频繁操作引起的队列锁定。
 *
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        //第一组，抛出异常组
        //添加元素
//        System.out.println(blockingQueue.add("a"));
//        System.out.println(blockingQueue.add("b"));
//        System.out.println(blockingQueue.add("c"));
        //System.out.println(blockingQueue.add("d"));
        //取出元素
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.remove());

        //取出队头元素
//        System.out.println(blockingQueue.element());
//        System.out.println(blockingQueue.element());
//        System.out.println(blockingQueue.element());



        /**当阻塞队列已满时，offer（）
         * 将指定的元素插入队列中，可以设定等待的时间，如果在设定的等待时间内仍不能向队列中加入元素，则返回false。
         * */
        //第二组：true false组
        //添加元素
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d",1, TimeUnit.SECONDS));

        TimeUnit.SECONDS.sleep(2);
        //取出元素
        System.out.println(blockingQueue.poll(2,TimeUnit.SECONDS));
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
        //System.out.println(blockingQueue.poll());

        //取出队首元素
        //System.out.println(blockingQueue.peek());

    }
}
