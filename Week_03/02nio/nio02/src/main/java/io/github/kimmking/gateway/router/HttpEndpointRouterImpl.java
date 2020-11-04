package io.github.kimmking.gateway.router;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HttpEndpointRouterImpl implements HttpEndpointRouter{

    @Override
    public String route(List<String> endpoints) {
        Random random = new Random();
        int i = random.nextInt(endpoints.size() );
        return endpoints.get(i);
    }

    public static void main(String[] args) {
        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("http://localhost:8088");
        new HttpEndpointRouterImpl().route(urlList);
    }
}
