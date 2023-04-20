package org.example.servicesImpl.services.hello;

import org.example.hello.HelloService;
import org.example.hello.Message;

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(Message message) {
        return "hello：收到请求{" + message.getMsg() + "}";
    }
}
