package org.example;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class RPCServer {
    // 服务列表：提供的服务[IP + 端口] 简化版，服务列表是公开状态
    // 线程池：执行任务
    private ExecutorService threadPool;


    public RPCServer() {
        int corePoolSize = 10;
        int maximumPoolSize = 100;
        long keepAliveTime = 1;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        this.threadPool = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.MINUTES,
                workQueue,
                threadFactory);
    }

    public void register(Object service, int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        threadPool.submit(new Worker(service, serverSocket));
    }

    public void stop() {
        threadPool.shutdown();
    }
}
