package org.example;

import org.example.hello.HelloService;
import org.example.hello.Message;

public class ClientInstance {
    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy("127.0.0.1", 888);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        String msg = helloService.sayHello(new Message("胡成"));
        System.out.println(msg);
        System.out.println(helloService.sayHello(new Message("牛逼")));
        for (int x = 0; x <= 1000; ++x) {
            System.out.println(helloService.sayHello(new Message(String.valueOf(x))));
        }
    }
}