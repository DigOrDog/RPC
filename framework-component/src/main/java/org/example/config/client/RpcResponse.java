package org.example.config.client;

public class RpcResponse {

    int code; //1 表示响应成功  0 表示响应失败
    private String host;
    private int port;
    private Object result;

    // 用于zookeeper返回服务的host以及port信息
    public RpcResponse(String host, int port, int code) {
        this.host = host;
        this.port = port;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public Object getResult() {
        return result;
    }

    // 用于服务器端返回服务执行情况的结果
    public RpcResponse(Object result, int code) {
        this.code = code;
        this.result = result;
    }

}
