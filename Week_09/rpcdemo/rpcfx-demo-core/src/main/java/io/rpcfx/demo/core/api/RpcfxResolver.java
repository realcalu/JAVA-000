package io.rpcfx.demo.core.api;

public interface RpcfxResolver {

    Object resolve(String serviceClass);
    Object getBeanByName(String name,String method);

    void getAllBean();

}
