package org.example;

import org.example.hello.HelloService;
import org.example.servicesImpl.services.hello.HelloServiceImpl;
import org.example.servicesImpl.services.hello.HelloServiceImplV2;

import java.io.IOException;

public class ServerInstance {
    public static void main(String[] args) throws IOException {
        // 服务器端编写服务
        HelloService helloService = new HelloServiceImpl();
        HelloServiceImplV2 helloServiceImplV2 = new HelloServiceImplV2();
        // 服务器端向注册中心注册服务
        RPCServer rpcServer = new RPCServer();
        rpcServer.register(helloService, 888);
        rpcServer.register(helloServiceImplV2, 999);

        rpcServer.stop();
    }
}