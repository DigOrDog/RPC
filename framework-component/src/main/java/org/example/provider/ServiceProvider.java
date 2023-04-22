package org.example.provider;

import org.example.config.server.RpcServiceConfig;

import java.net.InetAddress;

public interface ServiceProvider {
    public void publishService(RpcServiceConfig rpcServiceConfig, InetAddress inetAddress, int port);

}
