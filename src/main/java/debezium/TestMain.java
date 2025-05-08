package debezium;

import com.google.common.util.concurrent.MoreExecutors;

import java.time.LocalDateTime;
import java.util.concurrent.*;

public class TestMain {

    public static void main(String[] args) {
        int corePoolSize = 1;
        int maxPoolSize = 5;
        long keepAliveTime = 0L;
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(5);
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();


        ThreadPoolExecutor exec = new ThreadPoolExecutor(corePoolSize,maxPoolSize,keepAliveTime,java.util.concurrent.TimeUnit.SECONDS, queue,handler);
        ExecutorService executorService = MoreExecutors.getExitingExecutorService(exec, 10000, TimeUnit.MILLISECONDS);

        int count = 2;
        while (count > 0) {
            count--;
            for (int i = 0; i < 10; i++) {
                final int cnt = i;
                System.out.println("Iteration: " + cnt);
                executorService.execute(() -> {
                    try {
                        System.out.println("At time: " + LocalDateTime.now() + " task:" + cnt + " " + Thread.currentThread().getName() + " queue size:" + exec.getQueue().size());
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                });
            }
            //exec.shutdown();
        }

        while (true){}
    }

}
