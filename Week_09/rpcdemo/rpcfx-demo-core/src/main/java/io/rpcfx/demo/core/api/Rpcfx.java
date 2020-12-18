package io.rpcfx.demo.core.api;


import io.rpcfx.demo.core.handler.RpcfxInvocationHandler;
import okhttp3.MediaType;

import java.lang.reflect.Proxy;

public class Rpcfx {
    public  <T> T create(final Class<T> serviceClass, final String url,MediaType JSONTYPE) {

        // 0. 替换动态代理 -> AOP
        return (T) Proxy.newProxyInstance(Rpcfx.class.getClassLoader(), new Class[]{serviceClass}, new RpcfxInvocationHandler(serviceClass, url,JSONTYPE));

    }
}
