package org.example.provider.impl;

import org.apache.curator.framework.CuratorFramework;
import org.example.config.server.RpcServiceConfig;
import org.example.provider.ServiceProvider;
import org.example.zookeeper.CuratorUtils;

import java.net.InetAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RpcServiceProvider implements ServiceProvider {
    /**
     * key表示服务名+Group+Version(this.getServiceName() + "/" + this.getGroup() + "/" + this.getVersion();)
     * value表示服务名所对应的对象
     * */
    private Map<String, Object> serviceMap;

    public RpcServiceProvider() {
        this.serviceMap = new ConcurrentHashMap<>();
    }


    @Override
    public void publishService(RpcServiceConfig rpcServiceConfig, InetAddress inetAddress, int port) {
        /**
         * rpcServiceConfig表示服务信息
         * */
        String rpcServiceName = rpcServiceConfig.getRpcServiceName();
        if (serviceMap.containsKey(rpcServiceName)) {
            System.out.println(rpcServiceName + "服务已经发布过了...");
        } else {
            serviceMap.put(rpcServiceName, rpcServiceConfig.getService());
            System.out.println(rpcServiceName + "服务正在发布...");
            try {
                String servicePath = CuratorUtils.ZK_REGISTER_ROOT_PATH + "/" + rpcServiceName +
                        "/" + inetAddress.getHostAddress() + ":" + port;
                CuratorFramework zkClient = CuratorUtils.getZkClient();
                CuratorUtils.createPersistentNode(zkClient, servicePath);
            } catch (Exception e) {

            }


        }
    }
}
