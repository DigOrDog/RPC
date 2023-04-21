package org.example;

import org.example.config.RpcServiceConfig;
import org.example.hello.HelloService;
import org.example.servicesImpl.services.hello.HelloServiceImpl;
import org.example.servicesImpl.services.hello.HelloServiceImplV2;

import java.io.IOException;
import java.net.InetAddress;

public class ServerInstance {
    public static void main(String[] args) throws IOException {
        // 服务器端编写服务
        HelloService helloService = new HelloServiceImpl();
        HelloServiceImplV2 helloServiceImplV2 = new HelloServiceImplV2();
        // 服务器端向注册中心注册服务
        RPCServer rpcServer = new RPCServer();
        InetAddress localHost = InetAddress.getLocalHost();
        RpcServiceConfig helloServiceOneConfig = new RpcServiceConfig("01", "01", helloService);
        RpcServiceConfig helloServiceTwoConfig = new RpcServiceConfig("02", "01", helloServiceImplV2);
        rpcServer.register(helloServiceOneConfig, localHost, 888);
        rpcServer.register(helloServiceTwoConfig, localHost, 999);

        rpcServer.stop();
    }
}