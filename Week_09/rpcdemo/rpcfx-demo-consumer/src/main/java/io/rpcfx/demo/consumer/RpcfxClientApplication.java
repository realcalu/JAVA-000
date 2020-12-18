package io.rpcfx.demo.consumer;

import io.rpcfx.demo.api.User;
import io.rpcfx.demo.api.UserService;
import io.rpcfx.demo.core.api.Rpcfx;

import okhttp3.MediaType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RpcfxClientApplication {

	// 二方库
	// 三方库 lib
	// nexus, userserivce -> userdao -> user
	public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

	public static void main(String[] args) {
		Rpcfx rpcfx = new Rpcfx();

		UserService service = rpcfx.create(UserService.class, "http://localhost:8081/",JSONTYPE);
		User user = service.findById(1);
		System.out.println(user.toString());
		// UserService service = new xxx();
		// service.findById

	}

}



