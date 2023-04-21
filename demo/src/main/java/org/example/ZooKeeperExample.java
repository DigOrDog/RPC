package org.example;

import org.apache.zookeeper.*;

import java.io.IOException;

public class ZooKeeperExample {
    // ZooKeeper 服务器地址和端口号
    private static final String ZOOKEEPER_ADDRESS = "localhost:2181";
    // 连接超时时间
    private static final int SESSION_TIMEOUT = 5000;
    // ZooKeeper 实例
    private ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeperExample example = new ZooKeeperExample();
        example.connect();
        example.deleteNode("/example");
        example.createNode("/example", "Hello ZooKeeper!");
        example.updateNode("/example", "Hello ZooKeeper Again!");
        example.readNode("/example");
        example.close();
    }

    public void readNode(String path) throws InterruptedException, KeeperException {
        byte[] data = zooKeeper.getData(path, false, null);
        System.out.println("Node " + path + " data: " + new String(data));
    }
    /**
     * 建立 ZooKeeper 连接
     */
    private void connect() throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper(ZOOKEEPER_ADDRESS, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                // 不需要处理事件
            }
        });
        // 等待连接成功
        while (zooKeeper.getState() != ZooKeeper.States.CONNECTED) {
            Thread.sleep(100);
        }
    }

    /**
     * 关闭 ZooKeeper 连接
     */
    private void close() throws InterruptedException {
        zooKeeper.close();
    }

    /**
     * 创建节点
     */
    private void createNode(String path, String data) throws KeeperException, InterruptedException {
        // 创建持久节点，不设置 ACL
        zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    /**
     * 更新节点
     */
    private void updateNode(String path, String data) throws KeeperException, InterruptedException {
        // 更新节点数据
        zooKeeper.setData(path, data.getBytes(), -1);
    }

    /**
     * 删除节点
     */
    private void deleteNode(String path) throws KeeperException, InterruptedException, KeeperException {
        // 删除节点及其所有子节点
        zooKeeper.delete(path, -1);
    }
}

