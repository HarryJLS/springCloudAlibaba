package com.rabbion;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author JLS
 * @description:
 * @since 2023-06-10 14:12
 */
public class CustomRule extends AbstractLoadBalancerRule {

    @Override
    public Server choose(Object o) {
        // 获取当前请求的实例
        ILoadBalancer loadBalancer = this.getLoadBalancer();
        List<Server> reachableServerList = loadBalancer.getReachableServers();

        int random = ThreadLocalRandom.current().nextInt(reachableServerList.size());

        Server server = reachableServerList.get(random);


        return server;
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
}
