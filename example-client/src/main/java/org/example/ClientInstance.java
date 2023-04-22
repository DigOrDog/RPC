package org.example;

import org.example.config.client.RpcRequest;
import org.example.config.client.RpcResponse;
import org.example.consumer.RpcClientProxy;
import org.example.consumer.impl.RpcServiceDiscovery;
import org.example.hello.HelloService;
import org.example.hello.Message;

public class ClientInstance {
    public static void main(String[] args) {
        RpcServiceDiscovery rpcServiceDiscovery = new RpcServiceDiscovery();
        RpcRequest rpcRequest = new RpcRequest(HelloService.class.getName(), "02", "01");
        RpcResponse rpcResponse = rpcServiceDiscovery.findService(rpcRequest);
        RpcClientProxy rpcClientProxy = null;
        if (rpcResponse.getCode() == 1) {
            rpcClientProxy = new RpcClientProxy(rpcResponse.getHost(), rpcResponse.getPort());
            HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
            String msg = helloService.sayHello(new Message("胡成"));
            System.out.println(msg);
        } else {
            // 请求失败
        }
    }
}