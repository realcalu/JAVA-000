package io.rpcfx.demo.provider;

import lombok.Data;

@Data
public class ServiceProviderDesc {

    private String host;
    private Integer port;
    private String serviceClass;
}
