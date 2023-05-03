package org.example;

import org.example.config.client.RpcRequest;
import org.example.config.client.RpcResponse;
import org.example.config.server.RpcServiceConfig;
import org.example.consumer.RpcClientProxy;
import org.example.consumer.impl.RpcServiceDiscovery;
import org.example.hello.HelloService;
import org.example.hello.Message;
import org.example.math.SumService;

import java.util.ArrayList;

class ServiceConfig {
    private String group;
    private String version;
    private Class<?> service;

    public ServiceConfig(String group, String version, Class<?> service) {
        this.group = group;
        this.version = version;
        this.service = service;
    }

    public String getGroup() {
        return group;
    }

    public String getVersion() {
        return version;
    }

    public Class<?> getService() {
        return service;
    }


}
public class ClientInstance {


    public Object serviceUse(ServiceConfig serviceConfig) {
        RpcServiceDiscovery rpcServiceDiscovery = new RpcServiceDiscovery();
        RpcRequest rpcRequest = new RpcRequest(serviceConfig.getService().getName(), serviceConfig.getGroup(), serviceConfig.getVersion());
        RpcResponse rpcResponse = rpcServiceDiscovery.findService(rpcRequest);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(rpcResponse.getHost(), rpcResponse.getPort());
        return rpcClientProxy.getProxy(serviceConfig.getService());
    }
    public static void main(String[] args) throws Exception {
        ClientInstance clientInstance = new ClientInstance();
        HelloService helloService1 = (HelloService)clientInstance.serviceUse(new ServiceConfig("01", "01", HelloService.class));
        HelloService helloService2 = (HelloService)clientInstance.serviceUse(new ServiceConfig("02", "01", HelloService.class));
        System.out.println(helloService1.sayHello(new Message("group=01, verison=01")));
        System.out.println(helloService2.sayHello(new Message("group=02, verison=01")));
        SumService service = (SumService)clientInstance.serviceUse(new ServiceConfig("buaa", "01", SumService.class));
        ArrayList<Integer> integers = new ArrayList<>();
        ArrayList<Double> doubles = new ArrayList<>();
        ArrayList<Number> numbers = new ArrayList<>();
        for (int i = 1; i <= 10; ++i) {
            integers.add(i);
            doubles.add(i + 0.5);
            numbers.add(i); numbers.add(i + 0.5);
        }
        System.out.println(service.getSum(integers).intValue()); // 55
        System.out.println(service.getSum(doubles).doubleValue()); // 60
        System.out.println(service.getSum(numbers).doubleValue()); // 115


    }
}