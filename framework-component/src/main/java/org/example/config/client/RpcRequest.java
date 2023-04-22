package org.example.config.client;

import java.io.Serializable;

public class RpcRequest implements Serializable {
    private String interfaceName;
    private String group;
    private String version;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] paramTypes;

    // 用于向zookeeper请求服务的地址
    public RpcRequest(String interfaceName, String group, String version) {
        this.interfaceName = interfaceName;
        this.group = group;
        this.version = version;
    }

    // 用于向服务器端请求服务的返回值
    public RpcRequest(String methodName, Object[] parameters, Class<?>[] paramTypes) {
        this.methodName = methodName;
        this.parameters = parameters;
        this.paramTypes = paramTypes;
    }

    public String getMethodName() {
        return methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public String getRpcServiceName() {
        return this.interfaceName + "/" + this.group + "/" + this.version;
    }


}
