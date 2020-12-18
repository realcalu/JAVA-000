package io.rpcfx.demo.core.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.rpcfx.demo.core.entity.RpcfxRequest;
import io.rpcfx.demo.core.entity.RpcfxResponse;
import io.rpcfx.demo.core.exception.RpcfxException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class RpcfxInvoker {

    private RpcfxResolver resolver;

    public RpcfxInvoker(RpcfxResolver resolver){
        this.resolver = resolver;
    }
/**/
    public RpcfxResponse invoke(RpcfxRequest request) throws RpcfxException{
        RpcfxResponse response = new RpcfxResponse();
        String serviceClass = request.getServiceClass();

        // 作业1：改成泛型和反射
//        Object service = resolver.getBeanByName("userServiceImpl");//this.applicationContext.getBean(serviceClass);

        Object service = resolver.getBeanByName(serviceClass,request.getMethod());
        if(service==null){
            response.setStatus(false);
            response.setException(new RpcfxException(serviceClass+" is not exist"));
            return response;
        }
        try {
            Method method = resolveMethodFromClass(service.getClass(), request.getMethod());
            Object result = method.invoke(service, request.getParams()); // dubbo, fastjson,
            // 两次json序列化能否合并成一个
            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
            response.setStatus(true);
        } catch ( IllegalAccessException | InvocationTargetException e) {
            // 3.Xstream
            // 2.封装一个统一的RpcfxException
            // 客户端也需要判断异常
            e.printStackTrace();
            RpcfxException rpcfxException = new RpcfxException("method " + request.getMethod() + " is not exist");
            rpcfxException.addSuppressed(e);
            response.setException(rpcfxException);
            response.setStatus(false);
            throw rpcfxException;
        }finally {
            return response;
        }
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().orElse(null);
    }

}
