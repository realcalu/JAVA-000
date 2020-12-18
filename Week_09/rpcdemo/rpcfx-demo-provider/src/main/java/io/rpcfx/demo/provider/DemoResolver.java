package io.rpcfx.demo.provider;

import io.rpcfx.demo.core.api.RpcfxResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.Arrays;

public class DemoResolver implements RpcfxResolver, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object resolve(String serviceClass) {
        return this.applicationContext.getBean(serviceClass);
    }

    @Override
    public Object getBeanByName(String name,String method) {
        try {
            Class<?> aClass = Class.forName(name);
            String[] beanNamesForType = applicationContext.getBeanNamesForType(aClass);
            for(String s:beanNamesForType){
                if(s.equals(name))
                    continue;
                else {
                    Object bean = applicationContext.getBean(s);
                    Method method1 = Arrays.stream(bean.getClass().getMethods()).filter(m -> m.getName().equals(method)).findFirst().orElse(null);
                    if(method1!=null)
                        return bean;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getAllBean(){
        String[] beans = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beans);
        for (String bean : beans)
        {
            System.out.println(bean + " of Type :: " + applicationContext.getBean(bean).getClass());
        }
    }
}
