package io.rpcfx.demo.provider;

import io.rpcfx.demo.core.api.RpcfxInvoker;
import io.rpcfx.demo.core.api.RpcfxResolver;
import io.rpcfx.demo.core.entity.RpcfxRequest;
import io.rpcfx.demo.core.entity.RpcfxResponse;
import io.rpcfx.demo.core.exception.RpcfxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RpcfxServerApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RpcfxServerApplication.class, args);
	}

	@Autowired
	RpcfxInvoker invoker;

	@PostMapping("/")
	public RpcfxResponse invoke(@RequestBody RpcfxRequest request) {
		try {
			return invoker.invoke(request);
		} catch (RpcfxException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Bean
	public RpcfxInvoker createInvoker(@Autowired RpcfxResolver resolver){
		return new RpcfxInvoker(resolver);
	}

	@Bean
	public RpcfxResolver createResolver(){
		return new DemoResolver();
	}

}
