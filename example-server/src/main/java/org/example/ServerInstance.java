package org.example;

import org.example.config.server.RpcServiceConfig;
import org.example.hello.HelloService;
import org.example.provider.RPCServer;
import org.example.servicesImpl.services.hello.HelloServiceImpl;
import org.example.servicesImpl.services.hello.HelloServiceImplV2;
import org.example.servicesImpl.services.math.SumServiceImpl;

import java.io.IOException;
import java.net.InetAddress;


public class ServerInstance {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 服务器端编写服务
        HelloService helloService = new HelloServiceImpl();
        HelloServiceImplV2 helloServiceImplV2 = new HelloServiceImplV2();
        SumServiceImpl sumService = new SumServiceImpl();
        // 服务器端向注册中心注册服务
        RPCServer rpcServer = new RPCServer();
        InetAddress localHost = InetAddress.getLocalHost();
        RpcServiceConfig helloServiceOneConfig = new RpcServiceConfig("01", "01", helloService);
        RpcServiceConfig helloServiceTwoConfig = new RpcServiceConfig("02", "01", helloServiceImplV2);
        RpcServiceConfig sumServiceOneConfig = new RpcServiceConfig("buaa", "01", sumService);
        rpcServer.register(helloServiceOneConfig, localHost, 888);
        rpcServer.register(helloServiceTwoConfig, localHost, 999);
        rpcServer.register(sumServiceOneConfig, localHost, 1000);

        rpcServer.stop();
    }
}