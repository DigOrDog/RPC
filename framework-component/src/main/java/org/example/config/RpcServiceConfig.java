package org.example.config;

public class RpcServiceConfig {
    private String group;
    private String version;
    private Object service;

    public RpcServiceConfig(String group, String version, Object service) {
        this.group = group;
        this.version = version;
        this.service = service;
    }

    public String getServiceName() {
        // 获取服务接口名称
        return this.service.getClass().getInterfaces()[0].getCanonicalName();
    }

    public String getGroup() {
        return group;
    }

    public String getVersion() {
        return version;
    }

    public Object getService() {
        return service;
    }

    public String getRpcServiceName() {
        return this.getServiceName() + "/" + this.getGroup() + "/" + this.getVersion();
    }
}
