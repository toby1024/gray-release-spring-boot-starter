package com.toby.config;

import com.toby.gray.DefaultGrayRelease;
import com.toby.gray.GrayRuleFactory;
import com.toby.gray.IGrayRelease;
import com.toby.rule.RuleProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.logging.Logger;

/**
 * @Author: zhangbin
 * @Date: 2020/11/23
 */
@Configuration
@ConditionalOnProperty(value = "gray.release.enabled", havingValue = "true")
@EnableConfigurationProperties(RuleProperties.class)
public class GrayAutoConfigure implements ApplicationContextAware{
    private Logger logger = Logger.getLogger(GrayAutoConfigure.class.getName());

    @Autowired
    private RuleProperties ruleProperties;
    private Map<String, IGrayRelease> map;


    @Bean
    public GrayRuleFactory init() {
        logger.info("=======gray release start=======");
        GrayRuleFactory grayRuleFactory = new GrayRuleFactory();
        map.put("defaultGrayRelease", new DefaultGrayRelease(ruleProperties));
        grayRuleFactory.setGrayReleaseMap(map);
        return grayRuleFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        map = applicationContext.getBeansOfType(IGrayRelease.class);
    }
}
