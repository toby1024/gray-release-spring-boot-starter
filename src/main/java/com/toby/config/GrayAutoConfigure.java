package com.toby.config;

import com.toby.gray.GrayRelease;
import com.toby.rule.RuleProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

/**
 * @Author: zhangbin
 * @Date: 2020/11/23
 */
@Configuration
@ConditionalOnProperty(value = "gray.release.enabled", havingValue = "true")
@EnableConfigurationProperties(RuleProperties.class)
public class GrayAutoConfigure {
    private Logger logger = Logger.getLogger(GrayAutoConfigure.class.getName());

    @Autowired
    private RuleProperties ruleProperties;

    @Bean
    public GrayRelease init(){
        logger.info("=======start=======");
        return new GrayRelease(ruleProperties);
    }
}
