package org.example.hello;


import org.example.Service;

public interface HelloService extends Service {
    public String sayHello(Message msg);

}
