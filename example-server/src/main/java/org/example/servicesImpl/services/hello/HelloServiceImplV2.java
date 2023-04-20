package org.example.servicesImpl.services.hello;

import org.example.hello.HelloService;
import org.example.hello.Message;

public class HelloServiceImplV2 implements HelloService {
    @Override
    public String sayHello(Message msg) {
        return "I am version 2";
    }
}
