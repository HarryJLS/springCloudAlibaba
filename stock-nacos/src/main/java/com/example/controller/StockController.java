package com.example.controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.csp.sentinel.slots.block.BlockException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JLS
 * @description:
 * @since 2023-06-03 21:30
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    private static final Logger log = LoggerFactory.getLogger(StockController.class);

//    @Resource
//    private RestTemplate restTemplate;

    @Value("${server.port}")
    private String port;

    /**
     * @SentinelResource 改善接口中资源定义和被流控降级后的处理方法 使用方式，添加sentinel-annotation-aspectj
     *                   依赖，配置bean->SentinelResourceAspect, fallBack 当接口出现了异常，就可以交给fallback指定的方法处理
     */
    @GetMapping("/hello")
    @ResponseBody
//    @SentinelResource(value = "hello", blockHandler = "blockHandlerStr")
    // value:资源名，blockHandler:限流后的处理方法(默认该方法必须声明在同一个类中)
    public String hello() {
        System.out.println("扣减库存成功" + port);
//        throw new RuntimeException("扣减库存失败");
        // restTemplate.getForObject("http://order-service/order/hello", String.class);

        // Entry entry = null;
        // try {
        // // sentinel针对资源名进行限制
        // entry = SphU.entry("hello");
        // log.info("sentinel限流成功==========================");
        // } catch (BlockException e) {
        // log.error("sentinel限流失败==========================");
        // return "系统繁忙，请稍后再试";
        // } finally {
        // if (entry != null) {
        // entry.exit();
        // }
        // }
         return "hello";
    }

    public String fallbackStr(Throwable e) {
        log.error("异常处理测试=====》》》》》》》》》》》》》", e);
        return "系统繁忙，请稍后再试 fallback ";
    }

    /**
     * 注意： 1.一定要public 2.返回值一定要和源方法一致，参数也要源方法的参数, 如果不想声明在一个类中，可以使用blockHandlerClass
     */
    public String blockHandlerStr(BlockException e) {
        log.error("sentinel限流失败=================test====");
        return "系统繁忙，请稍后再试blockHandlerStr";
    }

     @PostConstruct
     private static void initFlowRules() {

     List<FlowRule> rules = new ArrayList<>();

     FlowRule rule = new FlowRule();
     // 设置资源名进行监控
     rule.setResource("hello");
     // 设置流控规则 QPS
     rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
     // 设置受保护的资源阈值
     // Set limit QPS to 1.
     rule.setCount(1);
     rules.add(rule);

     // 加载配置好的规则
     FlowRuleManager.loadRules(rules);
     log.info("sentinel限流规则加载成功==========================" + rules);
     }

    public static void initDegradeRule() {
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule();
        // 设置资源名进行监控
        rule.setResource("hello");
        // 设置流控规则 QPS
        rule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        // 设置受保护的资源阈值
        // 触发熔断的异常数
        rule.setCount(2);
        // 触发熔断的最小请求数
        rule.setTimeWindow(10);
        rule.setMinRequestAmount(2);
        // 统计时长 单位毫秒
        rule.setStatIntervalMs(60 * 1000);
        rules.add(rule);

        // 加载配置好的规则
        DegradeRuleManager.loadRules(rules);
        log.info("sentinel限流规则加载成功==========================" + rules);
    }

}
