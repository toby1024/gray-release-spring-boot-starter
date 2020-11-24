package com.toby.gray;

import com.toby.rule.RuleProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * @Author: zhangbin
 * @Date: 2020/11/23
 */
public class DefaultGrayRelease implements IGrayRelease {
    private Logger logger = Logger.getLogger(DefaultGrayRelease.class.getName());
    @Autowired
    private RuleProperties ruleProperties;

    public DefaultGrayRelease(RuleProperties ruleProperties) {
        this.ruleProperties = ruleProperties;
    }

    @Override
    public boolean execute(String target) {
        logger.info("执行默认的灰度规则");
        return ruleProperties.getFeature().stream()
                             .filter(ruleProperties -> ruleProperties.getKey().equals(ClassUtils.getShortNameAsProperty(DefaultGrayRelease.class)))
                             .filter(feature -> feature.isEnable())
                             .anyMatch(feature -> feature.getValues().stream().anyMatch(v -> Objects.equals(v, target)));
    }
}
