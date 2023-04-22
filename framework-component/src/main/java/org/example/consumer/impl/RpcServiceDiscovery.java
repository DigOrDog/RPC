package org.example.consumer.impl;

import org.apache.curator.framework.CuratorFramework;
import org.example.config.client.RpcRequest;
import org.example.config.client.RpcResponse;
import org.example.consumer.ServiceDiscovery;
import org.example.zookeeper.CuratorUtils;

import java.util.List;

public class RpcServiceDiscovery implements ServiceDiscovery {

    @Override
    public RpcResponse findService(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse("", 0, 0);
        String rpcServiceName = rpcRequest.getRpcServiceName();
        CuratorFramework zkClient = CuratorUtils.getZkClient();
        List<String> serviceNodes = CuratorUtils.getChildrenNodes(zkClient, rpcServiceName);
        if (serviceNodes.isEmpty()) {
            System.out.println("客户端请求该服务{[" + rpcServiceName + "]}失败");
        } else {
            /**
             * 目前未采用负载均衡，只考虑使用第一个节点进行响应服务
             * */
            String[] split = serviceNodes.get(0).split(":");
            rpcResponse = new RpcResponse(split[0], Integer.valueOf(split[1]), 1);
        }
        return rpcResponse;
    }
}
