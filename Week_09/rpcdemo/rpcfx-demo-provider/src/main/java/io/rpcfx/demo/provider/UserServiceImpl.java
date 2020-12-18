package io.rpcfx.demo.provider;


import io.rpcfx.demo.api.User;
import io.rpcfx.demo.api.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        User user = new User();
        user.setId(1);
        user.setName("calu");
        return user;
    }


}
