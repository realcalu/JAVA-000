package io.rpcfx.demo.core.handler;

import com.alibaba.fastjson.JSON;
import io.rpcfx.demo.core.entity.RpcfxRequest;
import io.rpcfx.demo.core.entity.RpcfxResponse;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RpcfxInvocationHandler implements InvocationHandler {
    private final Class<?> serviceClass;
    private final String url;
    private final MediaType JSONTYPE;
    public <T> RpcfxInvocationHandler(Class<T> serviceClass, String url,MediaType JSONTYPE) {
        this.serviceClass = serviceClass;
        this.url = url;
        this.JSONTYPE =JSONTYPE;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
        RpcfxRequest request = new RpcfxRequest();
        request.setServiceClass(this.serviceClass.getName());
        request.setMethod(method.getName());
        request.setParams(params);

        RpcfxResponse response = post(request, url);
        return JSON.parse(response.getResult().toString());
    }

    private RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
        String reqJson = JSON.toJSONString(req);
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(JSONTYPE, reqJson))
                .build();
        String respJson = client.newCall(request).execute().body().string();
        return JSON.parseObject(respJson, RpcfxResponse.class);
    }
}