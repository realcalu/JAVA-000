package class4;


import java.util.concurrent.*;

/**
 * @version 1.0
 * @Description 10种方式，等用户线程执行完再返回主线程
 * @Author ouyang
 * @Date
 **/
public class threadDemo {
    private static volatile int res;
    private static ExecutorService executorService = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws ExecutionException, InterruptedException, BrokenBarrierException {

        long start=System.currentTimeMillis();
        Integer result1 = method1();
        System.out.println("methoe1 异步计算结果为："+result1);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");



        start=System.currentTimeMillis();
        Integer result2 = method2();
        System.out.println("methoe2 异步计算结果为："+result2);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");


        start=System.currentTimeMillis();
        Integer result3 = method3();
        System.out.println("methoe3 异步计算结果为："+result3);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");


        start=System.currentTimeMillis();
        Integer result4 = method4();
        System.out.println("methoe4 异步计算结果为："+result4);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        executorService.shutdown();

        start=System.currentTimeMillis();
        Integer result5 = method5();
        System.out.println("methoe5 异步计算结果为："+result5);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        start=System.currentTimeMillis();
        Integer result6 = method6();
        System.out.println("methoe6 异步计算结果为："+result6);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");


        start=System.currentTimeMillis();
        Integer result7 = method7();
        System.out.println("methoe7 异步计算结果为："+result7);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        start=System.currentTimeMillis();
        Integer result8 = method8();
        System.out.println("methoe8 异步计算结果为："+result8);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");


        start=System.currentTimeMillis();
        Integer result9 = method9();
        System.out.println("methoe9 异步计算结果为："+result9);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");


        start=System.currentTimeMillis();
        Integer result10 = method10();
        System.out.println("methoe10 异步计算结果为："+result10);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");


    }

    //使用睡眠
    private static Integer method1() throws ExecutionException, InterruptedException {

        res = 0;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                res = sum();
            }
        });
        thread.start();
        Thread.sleep(5);
        return res;
    }

    //使用Callable+Future获取执行结果
    private static Integer method2() throws ExecutionException, InterruptedException {
        Future<Integer> future = executorService.submit(new Callable() {
            @Override
            public Integer call() throws Exception {
                return sum();
            }
        });
        Integer integer = future.get();
        return integer;
    }

    //使用Callable+FutureTask 用线程池获取执行结果
    private static Integer method3() throws ExecutionException, InterruptedException {
        Callable<Integer> call = new Callable<Integer>() {
            public Integer call() throws Exception {
                return sum();
            }
        };
        FutureTask<Integer> futureTask = new FutureTask<Integer>(call);
        executorService.submit(futureTask);
        return futureTask.get();
    }

    //FutureTask 循环等待是否完成
    private static Integer method4() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return sum();
            }
        });
        new Thread(futureTask).start();
        do {

        }while (!futureTask.isDone());
        return futureTask.get();
    }

    //join方式
    private static Integer method5() throws ExecutionException, InterruptedException {
        res = 0;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 199999; i++) {
                    res = sum();
                }
            }
        });
        thread.start();
        //不加join 返回0
        thread.join();
        return res;
    }


    //yield方式
    private static Integer method6() throws ExecutionException, InterruptedException {
        res = 0;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                res = sum();
            }
        });
        thread.start();
        while (Thread.activeCount()>=2){
            Thread.yield();
        }
        return res;
    }

    //thread.isalive
    private static Integer method7() throws ExecutionException, InterruptedException {
        res = 0;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                res = sum();
            }
        });
        thread.start();
        while (thread.isAlive()){

        }
        return res;
    }

    //countDownLatch
    private static Integer method8() throws  InterruptedException {
        res = 0;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    res = sum();
                }finally {
                    //释放锁
                    countDownLatch.countDown();
                }
            }
        });
        thread.start();
        //这里不加返回0
        countDownLatch.await();
        return res;
    }

    //CompletableFuture
    private static Integer method9() throws ExecutionException, InterruptedException {
        return CompletableFuture.supplyAsync(()->{return sum();}).get();//.thenApplyAsync(v -> v + "world").join();
    }

    //CyclicBarrier
    private static Integer method10() throws BrokenBarrierException, InterruptedException {
        res = 0;
        CyclicBarrier barrier = new CyclicBarrier(2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                res = sum();
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        barrier.await();
        return res;
    }







    private static int sum() {
        int sum =1;
        for (int i = 0; i < 199999999; i++) {
            sum++;
        }
        return sum;
    }

}
