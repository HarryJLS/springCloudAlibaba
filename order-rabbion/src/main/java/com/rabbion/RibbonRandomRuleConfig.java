package com.rabbion;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JLS
 * @description:
 * @since 2023-06-10 13:55
 */
@Configuration
public class RibbonRandomRuleConfig {

    @Bean
    public IRule iRule() {
        return new RandomRule();
    }
}
