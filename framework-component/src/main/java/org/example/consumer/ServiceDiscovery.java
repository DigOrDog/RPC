package org.example.consumer;

import org.example.config.client.RpcRequest;
import org.example.config.client.RpcResponse;

public interface ServiceDiscovery {
    public RpcResponse findService(RpcRequest rpcRequest);
}
